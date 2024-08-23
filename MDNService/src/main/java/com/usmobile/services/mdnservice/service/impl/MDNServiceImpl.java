package com.usmobile.services.mdnservice.service.impl;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import com.usmobile.services.mdnservice.data.request.AddMDNRequestDTO;
import com.usmobile.services.mdnservice.data.request.SetCycleDayOfMDNRequestDTO;
import com.usmobile.services.mdnservice.data.response.AddMDNResponseDTO;
import com.usmobile.services.mdnservice.data.response.SetCycleDayOfMDNResponseDTO;
import com.usmobile.services.mdnservice.domain.MDN;
import com.usmobile.services.mdnservice.domain.MDNUserInfo;
import com.usmobile.services.mdnservice.domain.UserInfo;
import com.usmobile.services.mdnservice.exception.ActiveMDNUserPairCouldNotBeFoundException;
import com.usmobile.services.mdnservice.exception.MdnAlreadyExistsException;
import com.usmobile.services.mdnservice.exception.UserCouldNotBeFoundException;
import com.usmobile.services.mdnservice.exception.UserHasAlreadyThisMDNException;
import com.usmobile.services.mdnservice.kafka.KafkaSender;
import com.usmobile.services.mdnservice.mapper.MDNUserInfoMapper;
import com.usmobile.services.mdnservice.repository.MDNUserRepository;
import com.usmobile.services.mdnservice.repository.UserInfoRepository;
import com.usmobile.services.mdnservice.service.MDNService;
import com.usmobile.services.mdnservice.validation.MDNValidator;
import com.usmobile.shared.model.data.MDNCycleEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MDNServiceImpl implements MDNService {

    private final MDNValidator validator;

    private final UserInfoRepository userRepository;

    private final MDNUserRepository mdnUserRepository;

    private final MDNUserInfoMapper mapper;

    private final KafkaSender kafkaSender;
    @Override
    public AddMDNResponseDTO addMDN(AddMDNRequestDTO requestDTO) throws Exception{
        validator.runValidationsForAddingMDN(requestDTO);
        UserInfo user = findUserInfo(requestDTO.getMdnUserInfoDTO().getUserId());
        String mdnS = requestDTO.getMdnUserInfoDTO().getMdn();
        checkMdnIsProperToAdd(user, mdnS);
        addMDNToUser(user, mdnS);
        MDNUserInfo mdnUserInfo = saveMDNUserInfo(user.getUserId(),mdnS);
        return AddMDNResponseDTO.builder()
                .mdnUserInfoDTO(mapper.toDto(mdnUserInfo))
                .message(mdnS + " is successfully added to user " + user.getUserId())
                .build();
    }

    private void addMDNToUser(UserInfo user, String mdnS) {
        MDN mdn = createMDN(mdnS);
        user.getMdnMap().put(mdnS, mdn);
        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);
    }

    private UserInfo findUserInfo(String userId) throws UserCouldNotBeFoundException{
        UserInfo userInfo = userRepository.findUserInfoByUserIdAndActiveTrue(userId);
        if(ObjectUtils.isEmpty(userInfo)) {
            throw new UserCouldNotBeFoundException(userId);
        }
        return userInfo;
    }

    private MDNUserInfo saveMDNUserInfo(String userId, String mdnS) {
        MDNUserInfo mdnUserInfo = MDNUserInfo.builder()
                .userId(userId)
                .mdn(mdnS)
                .build();
        mdnUserInfo.setCreatedDate(LocalDateTime.now());
        mdnUserInfo.setUpdatedDate(LocalDateTime.now());
        return mdnUserRepository.save(mdnUserInfo);

    }

    private void sendCycleInfo(MDNUserInfo mdnUserInfo) {
        MDNCycleEventDTO eventDTO = MDNCycleEventDTO.builder()
                .cycleDay(mdnUserInfo.getCycleDayOfMonth())
                .mdn(mdnUserInfo.getMdn())
                .userId(mdnUserInfo.getUserId())
                .build();
        kafkaSender.send(eventDTO);
        log.info("we are sending cycleInfo " + eventDTO);
    }

    @Override
    public SetCycleDayOfMDNResponseDTO setCycleDayOfMDN(SetCycleDayOfMDNRequestDTO requestDTO) throws Exception{
        validator.runValidationsForSetCycleDay(requestDTO);
        MDNUserInfo mdnUserInfo = getMDNUserInfo(requestDTO.getMdnUserInfoDTO().getMdn(),
                requestDTO.getMdnUserInfoDTO().getUserId());
        mdnUserInfo.setCycleDayOfMonth(requestDTO.getDayOfMonth());
        mdnUserInfo.setUpdatedDate(LocalDateTime.now());
        mdnUserRepository.save(mdnUserInfo);
        sendCycleInfo(mdnUserInfo);
        return SetCycleDayOfMDNResponseDTO.builder()
                .mdnUserInfoDTO(mapper.toDto(mdnUserInfo))
                .message("cycle day is successfully set")
                .build();
    }

    private MDNUserInfo getMDNUserInfo(String mdn, String userId) throws Exception{
        checkUserExists(userId);
        MDNUserInfo mdnUserInfo = mdnUserRepository.findMDNUserInfoByMdnAndUserId(mdn,userId);
        if(ObjectUtils.isEmpty(mdnUserInfo)){
            throw new ActiveMDNUserPairCouldNotBeFoundException(userId, mdn);
        }
        return mdnUserInfo;
    }

    private void checkUserExists(String userId) throws Exception{
        findUserInfo(userId);
    }

    private MDN createMDN(String mdn) {
        return MDN.builder()
                .mdn(mdn)
                .startDate(LocalDateTime.now())
                .active(true)
                .build();
    }

    private void checkMdnIsProperToAdd(UserInfo userInfo, String mdn) throws Exception{
        if(userInfo.getMdnMap().containsKey(mdn) ){
            throw new UserHasAlreadyThisMDNException(userInfo.getUserId(),mdn);
        }
        if(mdnUserRepository.existsMDNUserInfoByMdn(mdn)){
            throw new MdnAlreadyExistsException(mdn);
        }
    }

}

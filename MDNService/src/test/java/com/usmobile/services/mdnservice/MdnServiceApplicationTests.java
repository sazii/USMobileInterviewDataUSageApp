package com.usmobile.services.mdnservice;

import com.usmobile.services.mdnservice.data.MDNUserInfoDTO;
import com.usmobile.services.mdnservice.data.request.AddMDNRequestDTO;
import com.usmobile.services.mdnservice.domain.MDNUserInfo;
import com.usmobile.services.mdnservice.domain.UserInfo;
import com.usmobile.services.mdnservice.mapper.MDNUserInfoMapper;
import com.usmobile.services.mdnservice.repository.MDNUserRepository;
import com.usmobile.services.mdnservice.repository.UserInfoRepository;
import com.usmobile.services.mdnservice.service.MDNService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class MdnServiceApplicationTests {
    @MockBean
	private MDNUserRepository repository;
	@MockBean
	private UserInfoRepository userInfoRepository;
	@Autowired
	private MDNService mdnService;
	@Autowired
	private MDNUserInfoMapper mapper;


	@Test
	public void addMDNTest() throws Exception {
		String userId = "234", mdn ="12";
		MDNUserInfo mdnUserInfo = MDNUserInfo.builder().userId(userId).mdn(mdn).build();
		UserInfo userInfo = UserInfo.builder().userId(userId).active(true).mdnMap(new HashMap<>()).build();
		when(repository.save(Mockito.any(MDNUserInfo.class))).thenAnswer(i -> i.getArguments()[0]);
		when(userInfoRepository.findUserInfoByUserIdAndActiveTrue(userId)).thenReturn(userInfo);
		when(userInfoRepository.save(Mockito.any(UserInfo.class))).thenAnswer(i -> i.getArguments()[0]);
		MDNUserInfoDTO expected = mapper.toDto(mdnUserInfo);
		MDNUserInfoDTO actual = mdnService.addMDN(createAddMDNRequestDTo(userId, mdn)).getMdnUserInfoDTO();
		assertEquals(expected,actual);
	}

	private AddMDNRequestDTO createAddMDNRequestDTo(String userId, String mdn) {
		AddMDNRequestDTO requestDTO = new AddMDNRequestDTO();
		requestDTO.setMdnUserInfoDTO(createMDNUserInfoDto(userId, mdn));
		return requestDTO;
	}

	private MDNUserInfoDTO createMDNUserInfoDto(String userId, String mdn) {
		MDNUserInfoDTO mdnUserInfoDTO = new MDNUserInfoDTO();
		mdnUserInfoDTO.setUserId(userId);
		mdnUserInfoDTO.setMdn(mdn);
		return mdnUserInfoDTO;
	}

}

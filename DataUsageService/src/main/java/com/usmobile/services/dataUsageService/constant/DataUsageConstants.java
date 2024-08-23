package com.usmobile.services.dataUsageService.constant;

import java.util.Map;

public interface DataUsageConstants {

    interface CurrentCycle {
        String CURRENT_CYCLE_NOT_FOUND = "current cycle not found";
        interface CycleStartDate {
            int HOUR =0;
            int MINUTE = 1;
            int SECOND = 1;
        }
        interface CycleEndDate {
            int HOUR = 23;
            int MINUTE = 58;
            int SECOND = 58;
        }
    }
    interface idPrefixes{
       String CYCLE_ID_PREFIX = "CYC-";
    }

    interface KafkaStream {
        String TOPIC_NAME = "Daily-Usage-Stream-Topic2";
        int BATCH_SIZE = 500;
        Long WAIT_DURATION_FOR_FLUSH = 5000L;
        String REST_BATCH_KEY = "flush-batch";
        String FINAL_BATCH_KEY = "final-batch";
    }
    /*

     */

    interface CSVFields {
        String MDN = "MDN";
        String USER_ID = "USER_ID";
        String USAGE_DAY = "USAGE_DAY";
        String USAGE_MONTH = "USAGE_MONTH";
        String USAGE_YEAR = "USAGE_YEAR";
        String USED_HOUR = "USED_HOUR";
        String USED_MINUTE = "USED_MINUTE";
        String USED_DATA_IN_MB = "USED_DATA_IN_MB";
        Map<String, Integer> fieldIndexMap = Map.of(
                MDN, 0,
                USER_ID,1,
                USAGE_DAY,2,
                USAGE_MONTH,3,
                USAGE_YEAR,4,
                USED_HOUR,5,
                USED_MINUTE,6,
                USED_DATA_IN_MB, 7
        );


    }
}

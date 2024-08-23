
$${\color{orange}US  \space Mobile  \space Interview/  \space Data  \space Usage  \space App}$$

$${\color{orange}1. Introduction}$$

This document outlines the architecture and functionality of the application developed as part of the interview process for US Mobile. It provides a detailed explanation of how each service within the app operates

$${\color{orange}2. Architecture \space Overview}$$

$${\color{orange}2.1. High \space Level \space Architecture}$$

![AD_4nXcuLvQf_Ll6FXKbAyuOxC56FyndpQoWlHPo2Z67O5RPjBMZSDEx76-I2dXXeIs2NwOV-L-eWOjs--AJkAHhZXqdtMUWIiZ3CnBeveNeeV-lR23vJQqoL0lV](https://github.com/user-attachments/assets/23eaac21-9868-491f-b2d6-f4c08199cfa4)

$${\color{orange}2.2. Service \space Descriptions}$$

**User Service:** This service handles the CRUD operations for user profiles, allowing for the creation and real-time updating of user information within the system's database.

**MDN Service:** Dedicated to managing mobile directory numbers (MDNs), this service assigns MDNs to users and sets the cycle day for billing and service continuity. It efficiently manages the association between users and their respective MDNs.

**Data Usage Service:** This service is responsible for ingesting and processing daily usage data provided in CSV files. It efficiently stores data and manages the lifecycle of billing cycles for each user-MDN pair. The service automates the creation of new cycles and maintains a detailed history of data usage, ensuring accurate and up-to-date cycle information.

$${\color{orange}3. Getting \space Started}$$

$${\color{orange}3.1. Prerequisites:}$$
Java 17
Spring-boot 3.3.2 (web, mongo, kafka, kafka-streams)
Maven
Apache Kafka

$${\color{orange}3.2. Installation \space Guide:}$$

1- Clone repository

Begin by cloning the source code repository from the designated version control system. Use the following command to clone the repository:

git clone [repository URL]

2- Build Modules in the following order:

Model,
UserService,
MDN Service,
DataUsageService

$${\color{orange}4. API \space Documentation}$$

$${\color{orange}4.1. EndPoints}$$

$${\color{orange}4.1.1. User\space Service \space EndPoints}$$

$${\color{orange}4.1.1.1. createUser}$$

**HTTPRequest Type:** POST

**URL:** http://localhost:8087/user/create

**Request Json Exaple:**

```json
{
    "userDto":{
        "firstName":"ali",
        "lastName": "ada",
        "email":"ali@ada.com",
        "password": "azman"

    }
}
```


Response Json Exaple:

```json
{
    "userDto": {
        "userId": "US-986a6eb4-1e62-4694-aad0-381d4bd0eb26",
        "firstName": "ali",
        "lastName": "ada",
        "email": "ali@ada.com",
        "fullName": "ali ada"
    },
    "message": "user is successfully created"
}
```

$${\color{orange}4.1.1.2. updateUserProfile}$$

**HTTPRequest Type:** PUT

**URL:** http://localhost:8087/user/updateProfile

**Request Json Exaple:**

```json
{
    "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
    "userRequestDto": {
        "password": "azmanli"
    }
}
```

**Response Json Example:**

```json
{
    "userDto": {
        "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "firstName": "alim",
        "lastName": "adam",
        "email": "alim@adam.com",
        "fullName": "alim adam"
    },
    "message": "user is successfully updated"
}
```

$${\color{orange}4.1.2. MDN  \space Service \space EndPoints}$$

$${\color{orange}4.1.2.1.addMDN}$$

**HTTPRequest Type:** POST

**URL:** http://localhost:8089/mdn/add

**Request Json Exaple:**

```json
{
    "mdnUserInfoDTO": {
        "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    }
}
```

**Response Json Example:**

```json
{
    "mdnUserInfoDTO": {
        "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    },
    "message": "3579 is successfully added to user US-b98c8d3a-f646-4325-b5b6-d27bc0917454"
}
```

$${\color{orange}4.1.2.2. setCycleDay}$$

**HTTPRequest Type:** POST

**URL:** http://localhost:8089/mdn/setCycleDay

**Request Json Exaple:**

```json
{
    "mdnUserInfoDTO": {
         "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    },
    "dayOfMonth": 22
}
```

**Response Json Example: **

```json
{
    "mdnUserInfoDTO": {
        "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    },
    "message": "cycle day is successfully set"
}
```

$${\color{orange}4.1.3. Data \space Usage \space Service \space EndPoints}$$

$${\color{orange}4.1.3.1. getCycleHistory}$$

**HTTPRequest Type:** GET

**URL:** http://localhost:8088/cycle/all

**Request Json Exaple:**

```json
{
    "userId":"US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
    "mdn":"3579"
}
```

**Response Json Example:**

```json
{
    "cycleInfoDTOList": [
        {
            "cycleId": "CYC-dd75e751-1c32-4fbf-975d-873656081d85",
            "startDate": "2024-08-22T00:01:01",
            "endDate": "2024-09-21T23:59:59"
        },
        {
            "cycleId": "CYC-8f9322ba-167f-43f8-bc0c-a434751eac01",
            "startDate": "2024-06-23T23:51:45.571",
            "endDate": "2024-07-22T23:51:45.571"
        }
    ]
}
```


$${\color{orange}4.1.3.2. getCurCycleDailyUsageOFMDN}$$


**HTTPRequest Type:** GET

**URL:** http://localhost:8088/currentCycle/dailyUsage

**Request Json Exaple:**

```json
{
    "userId":"US-5430ede9-417d-492e-be13-29200a27b3e6",
    "mdn":"2347",
    "page": 2,
    "pageSize": "6"
}
```

**Response Json Example:**

```json
{
    "dailyUsageDTOs": {
        "totalElements": 42,
        "totalPages": 7,
        "size": 6,
        "content": [
            {
                "date": "2024-09-09T11:26:00",
                "dataUsedInMB": 567
            },
            {
                "date": "2024-09-09T11:26:00",
                "dataUsedInMB": 234
            },
            {
                "date": "2024-09-09T11:26:00",
                "dataUsedInMB": 123
            },
            {
                "date": "2024-09-09T11:26:00",
                "dataUsedInMB": 235
            },
            {
                "date": "2024-09-09T11:26:00",
                "dataUsedInMB": 456
            },
            {
                "date": "2024-09-09T11:26:00",
                "dataUsedInMB": 245
            }
         
        ],
        "number": 2,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "numberOfElements": 12,
        "pageable": {
            "pageNumber": 2,
            "pageSize": 6,
            "sort": {
                "empty": true,
                "unsorted": true,
                "sorted": false
            },
            "offset": 30,
            "unpaged": false,
            "paged": true
        },
        "first": false,
        "last": true,
        "empty": false
    },
    "userId": "US-5430ede9-417d-492e-be13-29200a27b3e6",
    "mdn": "2347",
    "message": "dailyUsages are successfully retrieved"
}
```

**$${\color{orange}5.Design \space Decisions \space And \space Some \space Important \space Data \space Flow \space Explanations}$$**

**$${\color{orange}5.1.Cycle \space  Management:}$$**

**Cycle Creation:** Whenever a cycle day is set for a user, a new cycle is automatically initiated. This setup ensures that each user's cycle timeline starts precisely on the designated day.

**Cycle Update Process:** The system runs an automated cycle update routine daily at 00:30. This process scans for user-MDN pairs whose cycle end date is the current day. It then closes the current cycle and initiates the next billing cycle for these pairs, ensuring seamless transition and continuous cycle management.

**$${\color{orange}5.2.Daily \space Data \space Usage \space Management:}$$**

$${\color{orange}Data \space Ingestion \space via \space Kafka \space Streams:}$$ The DataUsageService is configured to continuously listen for Daily Usage Data through Kafka Streams. This ensures real-time data processing and minimizes latency in data handling.

$${\color{orange}Data \space Format \space and \space Processing:}$$ Incoming Daily Usage Data is transmitted in CSV format via Kafka Streams. The service employs batch processing techniques with a pre-defined batch size to manage the data efficiently. This batch size is configurable and can be adjusted according to system requirements by modifying the BATCH_SIZE field in the DataUsageConstants file.

$${\color{orange}CSV \space File \space Example:}$$ For a sample of the expected CSV file format and its contents, you can refer to the example provided at the following link:
https://docs.google.com/spreadsheets/d/1YdwpgVL2lJnqIhC5TO9VNxyDss1CwZTU-7BD10t-dfU/edit?gid=0#gid=0

This example will help clarify the data structure and formatting required for processing.

$${\color{orange}Sending \space CSV \space Files \space via \space Kafka:}$$ To send a CSV file to the Daily Usage Data stream, use the following command in your terminal:
<pre lang=lisp>
/usr/local/bin/kafka-console-producer --broker-list localhost:9092 --topic Daily-Usage-Stream-Topic2 < file.csv
</pre>
        
This command pushes the contents of file.csv to the Daily-Usage-Stream-Topic2 on Kafka, ensuring that the DataUsageService receives and processes the data.

**$${\color{orange}6. Potential \space Next \space Features}$$**

$${\color{orange}6.1.Transfer\space  MDN}$$

$${\color{orange}Feature \space Overview:}$$ The system architecture currently supports the potential implementation of an MDN (Mobile Directory Number) transfer feature. This capability would allow the reallocation of an MDN from one user to another within the platform.

**Implementation Guide:**

$${\color{orange}Deactivate \space Existing \space MDN:}$$ To initiate the transfer, first set the active field to false in the MDN field within the UserInfo entity of the MDNService. This action marks the MDN as inactive for the current user, effectively freeing it up for reassignment.

$${\color{orange}Assign \space to \space New \space User:}$$ Utilize the addMDN endpoint to assign the now inactive MDN to a new user. This endpoint will handle the addition of the MDN to the requested user’s profile and reactivate it under the new user’s account.

$${\color{orange}Considerations:}$$ Implementing this feature should include robust validation to ensure that an MDN can only be transferred under specific conditions, such as user consent and verification of the MDN's status. Additionally, proper logging of all transfer activities will be crucial for audit and tracking purposes.

$${\color{orange}7.Improvements}$$

Api-GateWay Implemntation

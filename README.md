US Mobile Interview/ Data Usage App

**1. Introduction**

This document outlines the architecture and functionality of the application developed as part of the interview process for US Mobile. It provides a detailed explanation of how each service within the app operates

**2.Architecture Overview**

**2.1 High Level Architecture:**
![AD_4nXcuLvQf_Ll6FXKbAyuOxC56FyndpQoWlHPo2Z67O5RPjBMZSDEx76-I2dXXeIs2NwOV-L-eWOjs--AJkAHhZXqdtMUWIiZ3CnBeveNeeV-lR23vJQqoL0lV](https://github.com/user-attachments/assets/23eaac21-9868-491f-b2d6-f4c08199cfa4)
**2.2 Service Descriptions**

**User Service:** This service handles the CRUD operations for user profiles, allowing for the creation and real-time updating of user information within the system's database.

**MDN Service:** Dedicated to managing mobile directory numbers (MDNs), this service assigns MDNs to users and sets the cycle day for billing and service continuity. It efficiently manages the association between users and their respective MDNs.

**Data Usage Service:** This service is responsible for ingesting and processing daily usage data provided in CSV files. It efficiently stores data and manages the lifecycle of billing cycles for each user-MDN pair. The service automates the creation of new cycles and maintains a detailed history of data usage, ensuring accurate and up-to-date cycle information.

**3. Getting Started**

**3.1 Prerequisites:**
Java 17
Spring-boot 3.3.2 (web, mongo, kafka)
Maven

**3.2 Installation Guide:**

1- Clone repository

Begin by cloning the source code repository from the designated version control system. Use the following command to clone the repository:

git clone [repository URL]

2- Build Modules in the following order:

Model
UserService
MDN Service
DataUsageService

**4. API Documentation**

**4.1 EndPoints**

**4.1.1 User Service EndPoints**

**4.1.1.1 createUser**

**HTTPRequest Type:** POST

**URL:** http://localhost:8087/user/create

**Request Json Exaple:**

<pre lang=lisp>
{
    "userDto":{
        "firstName":"ali",
        "lastName": "ada",
        "email":"ali@ada.com",
        "password": "azman"

    }
}
</pre>

Response Json Exaple:

<pre lang=lisp>
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
</pre>

**4.1.1.2 updateUserProfile**

**HTTPRequest Type:** PUT

**URL:** http://localhost:8087/user/updateProfile

**Request Json Exaple:**

<pre lang=lisp>
{
    "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
    "userRequestDto": {
        "password": "azmanli"
    }
}
</pre>

**Response Json Example:**

<pre lang=lisp>
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
</pre>

**4.1.2 MDN Service EndPoints**

**4.1.2.1 addMDN**

**HTTPRequest Type:** POST

**URL:** http://localhost:8089/mdn/add

**Request Json Exaple:**

<pre lang=lisp>
{
    "mdnUserInfoDTO": {
        "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    }
}
</pre>

**Response Json Example:**

<pre lang=lisp>
{
    "mdnUserInfoDTO": {
        "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    },
    "message": "3579 is successfully added to user US-b98c8d3a-f646-4325-b5b6-d27bc0917454"
}
</pre>

**4.1.2.2 setCycleDay**

**HTTPRequest Type:** POST

**URL:** http://localhost:8089/mdn/setCycleDay

**Request Json Exaple:**

<pre lang=lisp>
{
    "mdnUserInfoDTO": {
         "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    },
    "dayOfMonth": 22
}
</pre>

**Response Json Example: **

<pre lang=lisp>
{
    "mdnUserInfoDTO": {
        "userId": "US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
        "mdn": "3579"
    },
    "message": "cycle day is successfully set"
}
</pre>

**4.1.3 DataUsageService EndPoints**

**4.1.3.1 getCycleHistory**

**HTTPRequest Type:** GET

**URL:** http://localhost:8088/cycle/all

**Request Json Exaple:**

<pre lang=lisp>
{
    "userId":"US-b98c8d3a-f646-4325-b5b6-d27bc0917454",
    "mdn":"3579"
}
</pre>

**Response Json Example:**

<pre lang=lisp>
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
</pre>

**4.1.3.2 getCurCycleDailyUsageOFMDN**

**HTTPRequest Type:** GET

**URL:** http://localhost:8088/currentCycle/dailyUsage

**Request Json Exaple:**

<pre lang=lisp>
{
    "userId":"US-5430ede9-417d-492e-be13-29200a27b3e6",
    "mdn":"2347",
    "page": 2,
    "pageSize": "6"
}
</pre>

**Response Json Example:**

<pre lang=lisp>
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
</pre>






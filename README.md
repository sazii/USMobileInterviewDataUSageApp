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

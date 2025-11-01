# Emilo-Api-Automation-
REST-Assured + TestNG automated API testing framework for Emilo app
#  Emilo Automation Framework

This repository contains **API Automation** and **UI Automation** test suites for the Emilo application.  
It uses **Java**, **TestNG**, **Appium**, **REST Assured**, and **Maven** for end-to-end testing.

---

##  Modules

###  API Automation
- Built using **REST Assured**
- Covers CRUD and functional API validation
- Includes JSON schema validation and assertions
- Configurable base URLs and headers

###  UI Automation
- Built using **Appium + Selenium**
- Supports Android UI automation for the Emilo mobile app
- Handles login flow, screen validations, and element interactions
- Uses reusable **DriverFactory** and **TestBaseUi** setup

---

## ⚙️ Tech Stack

| Layer | Tool |
|--------|------|
| Language | Java 17 |
| Test Framework | TestNG |
| API Automation | REST Assured |
| Mobile UI Automation | Appium |
| Build Tool | Maven |
| Reporting | Allure |

---

##  Setup Instructions

### Prerequisites
- **Java 17+**
- **Maven 3.9+**
- **Node.js 18+**
- **Appium Server 2.x**
- **Android SDK** installed and configured
- Emulator or real Android device connected

### Install Dependencies
```bash
mvn clean install


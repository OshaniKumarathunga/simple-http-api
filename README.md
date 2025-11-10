# Simple HTTP API

## Overview
This project implements a simple HTTP API using Spring Boot and Maven. The API exposes a single endpoint `/hello-world` which responds based on the first letter of the provided `name` query parameter.

---

## Requirements
The API behaves according to the following rules:

1. If the first letter of the name is in **A–M** (case-insensitive):
    - HTTP 200 OK
    - `{ "message": "Hello <Name>" }`

2. If the first letter is in **N–Z** (case-insensitive):
    - HTTP 400 Bad Request
    - `{ "error": "Invalid Input" }`

3. If the `name` parameter is missing, empty, or invalid:
    - HTTP 400 Bad Request
    - `{ "error": "Invalid Input" }`

---

## How to Run the Application

### Prerequisites
- Java 17 or later
- Maven 3.8+
- IntelliJ IDEA or another IDE (optional)
### Steps
1. Clone the repository:
```
git clone https://github.com/OshaniKumarathunga/simple-api.git
cd simple-http-api
```
2. Build the project:
   ```mvn clean install```
3. Run the application: ```mvn spring-boot:run```
4. Call the API: ```GET http://localhost:8080/hello-world?name=alice```


---

## How to Run the Tests


```mvn test```


---

## Assumptions Made
- Only the **first character** of the name is used for validation.
- Input is trimmed before processing.
- Non-alphabetic starting characters (digits, symbols, unicode) are treated as invalid.
- Validation is case-insensitive.
- Only a GET endpoint is required as defined in the task.
- All error responses return the same JSON structure `{ "error": "Invalid Input" }`.

---

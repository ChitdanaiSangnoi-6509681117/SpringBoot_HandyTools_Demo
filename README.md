# HandyTools - Tool Management System

## Overview
HandyTools is a RESTful API service for managing tools and their borrowing status. It provides a robust system for tracking tool ownership, location, and borrowing history.

## Tech Stack
- **Framework**: Spring Boot 3.2.2
- **Language**: Java 17
- **Database**: H2 Database (In-memory)
- **Build Tool**: Maven
- **API Testing**: VS Code REST Client
- **Documentation**: OpenAPI/Swagger

## Key Features
- Tool inventory management
- Tool borrowing system
- Owner verification
- Location tracking
- State management validation

## API Endpoints

### Success Operations
- `GET /handytools` - List all tools
- `GET /handytools/{id}` - Get specific tool
- `POST /handytools` - Create new tool
- `PUT /handytools/{id}` - Update tool status
- `DELETE /handytools/{id}` - Remove tool

### State Validations
- Prevents tools from being marked as borrowed without a borrower
- Ensures returned tools have no borrower assigned
- Prevents ownership changes
- Validates tool existence before operations

## Project Structure
```plaintext
handytools/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── dev/
│                   └── chitdanai/
│                       └── handytools/
│                           ├── HandytoolsApplication.java
│                           ├── HandytoolController.java
│                           ├── Storage.java
│                           └── StorageRepository.java
├── pom.xml
└── testclient.rest
```

## Running the Application
```bash
mvn spring-boot:run
```

## Testing
Using VS Code REST Client extension (`.rest` files)
- Success cases
- Error handling
- State validation
- Business rule enforcement

## Sample Data
Initial database is populated with:
```java
- Camera (owned by Chitdanai, borrowed by Mother)
- Atomic Bomb (owned by Oppenheimer, not borrowed)
- Kryptonite (owned by Clark, borrowed by Batman)
```

## Error Handling
- 400 Bad Request: Invalid state transitions
- 403 Forbidden: Unauthorized owner changes
- 404 Not Found: Non-existent resources
- 409 Conflict: Already borrowed tools

## Future Enhancements
- User authentication
- Borrowing history
- Tool categories
- Maintenance tracking
- Due date management

Feel free to contribute or raise issues!

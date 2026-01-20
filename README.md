# 📚 Library Management System (Core Java)

## Overview

This project is a **console-based Library Management System** implemented in **core Java**, based on UML class and use-case diagrams created during the analysis and design phase.  
The system manages **books, members, reservations, halls, and suggestions** using in-memory data structures.

The goal of the project is to demonstrate **object-oriented design**, **clean separation of concerns**, and **practical system logic** without using frameworks or databases.

---

## Features

- Search books by title  
- Reserve and cancel book reservations with date conflict detection  
- Reserve and cancel hall reservations with date & time conflict detection  
- Submit book suggestions  
- In-memory storage using Java collections (Hashmap, List)
- Unit tests using JUnit  

---

## Design & Architecture

- **Model layer**: domain entities (Book, Member, Reservation, Hall, etc.)  
- **Service layer**: system logic and validation (`LibrarySystem`)  
- **UI layer**: console-based menu (`ConsoleMenu`)  
- **Enums** used for fixed system states (reservation status, hall type, etc.)

The implementation is **based on the original UML diagrams**, with minor refinements made during coding to better align with Java best practices such as encapsulation and service-layer responsibility.

UML diagrams can be found in:

```
docs/
```

---

## Technologies Used

- Java (core)  
- Java Collections (`List`, `Map`, `HashMap`, `ArrayList`)  
- Java Time API (`LocalDate`, `LocalTime`)  
- JUnit  
- Git & GitHub  

---

## How to Run

1. Clone the repository  
2. Compile the project  
3. Run `Main.java`  
4. Interact with the system through the console menu  

---

## Notes

- The system uses **in-memory data storage** (no database).  
- Data is seeded at startup for demonstration and testing purposes.  
- Persistence (e.g., serialization) can be added as a future enhancement.

---

## Author

Software Engineering student – Hashemite University of Jordan  
This project was developed as part of academic coursework and self-practice.

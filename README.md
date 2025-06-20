# 💸 Expense Splitter API (Spring Boot)

A RESTful backend service to split and manage group expenses fairly — inspired by apps like Splitwise. Useful for roommates, trip planners, or friends splitting bills.

---

## 🚀 Features

### ✅ Core Functionality
- Add, view, update, and delete expenses
- Supports `EQUAL`, `EXACT`, and `PERCENTAGE` split types
- Automatically tracks who paid and who owes
- Settlement summary with minimized transactions
- Error handling and input validation
- Extracts people from expense data (no separate user creation needed)

---

## 📦 Tech Stack

- **Backend**: Java 17, Spring Boot 3
- **Database**: PostgreSQL (can use Railway, Render, or local setup)
- **Validation**: Jakarta Bean Validation
- **API Testing**: Postman
- **Deployment**: Railway / Render / any public platform

---

## 🛠️ Setup Instructions

### 1. Clone the Repository
```
git clone https://github.com/your-username/expense-splitter-api.git
cd expense-splitter-api
2. Configure Database
Update your application.properties:
```

## 2. Configure Database
Update your application.properties:

``` 
spring.datasource.url=jdbc:postgresql://<your-db-host>/<your-db-name>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

 ```


## 3. Build & Run

```
./mvnw clean install
./mvnw spring-boot:run
App will be running at: http://localhost:8080
```

## 📘 API Documentation
## 📁 Expense Management
### ➕ Add Expense
POST /expenses

```
{
  "amount": 600.0,
  "description": "Dinner at restaurant",
  "paidBy": "Shantanu",
  "splitType": "EQUAL",
  "shares": null
}


```


### 📝 Update Expense
PUT /expenses/{id}

Use the same request body format as POST.

### ❌ Delete Expense
DELETE /expenses/{id}

### 📄 Get All Expenses
GET /expenses

### 🤝 People & Settlement
### 👥 List All People
GET /expenses/people

### 💰 View Balances
GET /balances

Returns each person’s net balance (positive = owed, negative = owes)

### 📉 Settlement Summary
GET /settlements

Returns optimized transactions like:

[
  { "from": "Sanket", "to": "Shantanu", "amount": 200 },
  { "from": "Om", "to": "Shantanu", "amount": 150 }
]
🧪 Testing with Postman
A public Postman collection is included with:

All required API endpoints

Sample test data

Folder organization by feature

Pre-filled users: Shantanu, Sanket, Om

### 🧾 Example Use Case
Add expenses:

Dinner (₹600 by Shantanu)

Groceries (₹450 by Sanket)

Petrol (₹300 by Om)

### View balances:


{
  "Shantanu": 400.0,
  "Sanket": -200.0,
  "Om": -200.0
}
View settlement summary:

json
Copy
Edit
[
  { "from": "Sanket", "to": "Shantanu", "amount": 200 },
  { "from": "Om", "to": "Shantanu", "amount": 200 }
]
### 🧠 Project Structure

```
src/main/java/com/example/expensesplitter/
├── controller
│   └── ExpenseController.java
│   └── SettlementController.java
├── dto
│   └── ExpenseDTO.java
│   └── SettlementDTO.java
├── model
│   └── Expense.java
│   └── SplitType.java
├── repository
│   └── ExpenseRepository.java
├── service
│   └── ExpenseService.java
│   └── SettlementService.java
├── exception
│   └── InvalidExpenseException.java
│   └── GlobalExceptionHandler.java
└── ExpenseSplitterApplication.java
```

### ⚠️ Known Limitations
No authentication or user sessions

Only handles basic expense logic

Floating-point rounding may affect precise totals

Frontend not included (optional)

### 📌 Optional Enhancements (Extra Credit)
Recurring expenses (e.g. rent, subscriptions)

Expense categories (Food, Travel, etc.)

Monthly spending reports

Web dashboard for visual summary

### 🙌 Contributing
Pull requests are welcome! For major changes, open an issue first to discuss what you'd like to change.

### 📄 License
This project is licensed under the MIT License.

### 🙏 Acknowledgements
Inspired by:

Splitwise

Google Pay Bill Split
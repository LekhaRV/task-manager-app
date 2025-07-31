# 📝 Task Manager App

A full-stack task management application built with **React**, **Spring Boot**, and **PostgreSQL**.  
This app allows users to create, edit, complete, and manage tasks with deadlines and categories.

---

## 🚀 Features

- ✅ Add, edit, and delete tasks
- 📅 Assign deadlines (validated to be in the future)
- 🏷️ Categorize tasks
- ⚠️ Automatic status update: `Pending`, `Completed`, `Outdated`
- ✔️ Mark tasks as completed
- ❌ Validation with detailed error messages shown on the frontend
- 🎨 Responsive and styled UI using Bootstrap and animations

---

## 🧰 Tech Stack

### 🔹 Frontend (React)
- React (with hooks)
- React Bootstrap
- React Icons
- Axios for API calls
- Animate.css and custom CSS for UI animations

### 🔹 Backend (Spring Boot)
- Spring Boot
- Spring Data JPA
- Jakarta Bean Validation (`@NotBlank`, `@Future`)
- PostgreSQL Database
- RESTful API with layered architecture
- Global exception handling (`@ControllerAdvice`)

---

## 📁 Project Structure
task-manager-app/
│
├── frontend/ # React Frontend
│ ├── public/
│ ├── src/
│ │ ├── api/
│ │ ├── components/
│ │ ├── App.js
│ │ └── index.js
│ ├── package.json
│
└── backend/ # Spring Boot Backend
├── src/main/java/
│ └── com/example/demo/
│ ├── controller/
│ ├── model/
│ ├── service/
│ ├── repository/
│ └── exception/
├── src/main/resources/
├── pom.xml

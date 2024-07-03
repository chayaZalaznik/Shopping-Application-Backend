### Backend Documentation

# Shopping Application Backend

## Table of Contents
1. [Introduction](#introduction)
2. [Project Overview](#project-overview)
3. [Project Logic](#project-logic)
4. [Technology Stack](#technology-stack)
5. [Installation and Setup](#installation-and-setup)
6. [Usage](#usage)

## Introduction
Welcome to the Shopping Application! This project is a modern, user-friendly web application designed to provide a seamless shopping experience. Users can browse products, add them to their cart, manage orders, and more.

## Project Overview
The Shopping Application backend is built using modern web technologies and includes the following main features:
- User Authentication and Authorization
- Product Management
- Shopping Cart Management
- Order Management
- Favorite Management

## Project Logic
### User Authentication and Authorization
- **Registration:** New users can register by providing their details. The details are securely stored in the backend.
- **Login:** Registered users can log in using their credentials. Upon successful login, a JWT token is issued.
- **Logout:** Users can log out, which invalidates the JWT token.

### Product Management
- **Add Product:** Admins can add new products to the database.
- **Update Product:** Admins can update existing product details.
- **Delete Product:** Admins can delete products from the database.
- **View Products:** Users can view products available in the database.

### Shopping Cart Management
- **Add to Cart:** Users can add products to their cart.
- **View Cart:** Users can view the items in their cart.
- **Update Cart:** Users can update the quantity of items in their cart or remove items from the cart.

### Order Management
- **Place Order:** Users can place an order for the items in their cart. The order details are stored in the database.
- **View Orders:** Users can view their past orders. Only closed orders are displayed to each user.

### Favorite Management
- **Add to Favorites:** Users can add products to their favorites list.
- **View Favorites:** Users can view their favorite products.
- **Remove from Favorites:** Users can remove products from their favorites list.

## Technology Stack
### Backend
- **Java:** A high-level, class-based, object-oriented programming language.
- **Spring Boot:** A framework for building production-ready applications in Java.
- **H2 Database:** An in-memory database for development and testing purposes.
- **MVC Architecture:** Follows the Model-View-Controller architecture for separating concerns.
- **Configuration:** Proper configuration management using Spring Boot.
- **JDBC:** Java Database Connectivity for interacting with the H2 database.
- **Mappers:** Using mappers to map data between different layers.
- **Enums:** Utilizing enums for constants and fixed values.
- **Proper Naming:** Adheres to proper naming conventions and structure.
- **Endpoints:** Defined RESTful API endpoints for various functionalities.

## Installation and Setup
1. **Clone the Repository:**
    git clone https://github.com/chayaZalaznik/Shopping-Application-Backend.git
2. **Build the Backend Application:**
    mvn clean install
3. **Run the Backend Application:**
    mvn spring-boot:run

## Usage
- **API Endpoints:** The backend exposes RESTful API endpoints for various functionalities. Use tools like Postman to test these endpoints.
- **Database Access:** The application uses an H2 in-memory database. Access the H2 console at `http://localhost:8080/h2-console` to view and manage the database.

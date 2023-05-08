package com.bank.bank.Models;

import javax.persistence.*;

@Entity
public class Manager extends User {

    public Manager(String id, String name, String address, String phoneNumber, String email, String password,
            String Role) {
        super(id, name, address, phoneNumber, email, password, Role);
        // TODO Auto-generated constructor stub
    }

    // Additional attributes for Manager
    // ...

    // Constructors, Getters, and Setters for attributes
    // ...
}
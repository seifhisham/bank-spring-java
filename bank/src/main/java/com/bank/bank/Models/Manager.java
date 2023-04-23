package com.bank.bank.Models;
import javax.persistence.*;

@Entity
public class Manager extends User {

    public Manager(int id, String name, String address, int phoneNumber) {
        super(id, name, address, phoneNumber);
        //TODO Auto-generated constructor stub
    }

    // Additional attributes for Manager
    // ...

    // Constructors, Getters, and Setters for attributes
    // ...
}
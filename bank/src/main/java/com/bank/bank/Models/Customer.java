package com.bank.bank.Models;

import javax.persistence.*;

@Entity
public class Customer extends User {

    public Customer(String id, String name, String address, String phoneNumber, String email, String password,
            String Role) {
        super(id, name, address, phoneNumber, email, password, Role);
        // TODO Auto-generated constructor stub
    }

    // TODO Auto-generated constructor stub
}

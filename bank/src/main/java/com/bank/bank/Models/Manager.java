package com.bank.bank.Models;
import javax.persistence.*;

@Entity
@Table(name = "managers")
public class Manager {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private int managerId;


    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;
    

    @Column(name = "address")
    private String address;

    @Column(name = "department", nullable = false)
    private String department;

    // Constructors, Getters, and Setters

    public Manager() {
    }


    public Manager(int managerId, String firstName, String lastName, String phoneNumber, String address, String department) {
        this.managerId = managerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }

    public int getManagerId() {
        return this.managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
}

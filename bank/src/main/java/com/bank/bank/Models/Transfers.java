package com.bank.bank.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Transfers extends transaction {
    private String sourceAccount;
    private String destinationAccount;

    public Transfers() {
    }

    public Transfers(String sourceAccount, String destinationAccount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }

    public String getSourceAccount() {
        return this.sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getDestinationAccount() {
        return this.destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

}
package com.bank.bank.Models;

import javax.persistence.*;

@Entity
public class Transfers extends Transaction {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    public Transfers() {
    }


}

package com.bank.bank.Models;

public class Post {
    Long id;
    String Caption;

    public Post(Long id2, String Caption) {
        this.id = id2;
        this.Caption = Caption;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return this.Caption;
    }

    public void setCaption(String Caption) {
        this.Caption = Caption;
    }
}

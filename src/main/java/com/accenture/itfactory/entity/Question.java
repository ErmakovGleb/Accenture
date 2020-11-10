package com.accenture.itfactory.entity;

import java.util.Scanner;

public class Question {
    String name;
    int id;
    String Author;
    int type;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return Author;
    }

    public int getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setType(int type) {
        this.type = type;
    }
}

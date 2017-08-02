package com.example.a15056112.todolist;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by 15056112 on 31/7/2017.
 */

public class Lists implements Serializable {
    private int id;
    private String name;
    private String description;
    private String dateCreated;

    public Lists() {

    }

    public Lists(int id, String name, String description, String dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

}

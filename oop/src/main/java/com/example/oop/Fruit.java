package com.example.oop;

public class Fruit {
    private String Name;
    private String Description;
    private int Image;

    public Fruit(String name, String description, int image) {
        Name = name;
        Description = description;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}

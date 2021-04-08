package com.example.explicit;

import java.io.Serializable;

public class Student implements Serializable {
    private String FullName;
    private  int Age;

    public Student(String fullName, int age) {
        FullName = fullName;
        Age = age;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}

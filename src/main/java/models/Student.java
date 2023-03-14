package models;

import java.util.Objects;

public class Student {
    private String name;
    private String phoneNumber;
    private String email;
    private int id;
    public Student(String name,String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

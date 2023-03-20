package models;

import java.util.Objects;

public class Student {
    private String name;
    private String phoneNumber;
    private String email;
    private int id;
    private int teacherId;
    public Student(String name,String phoneNumber, String email, int teacherId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id && teacherId == student.teacherId && Objects.equals(name, student.name) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, id, teacherId);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

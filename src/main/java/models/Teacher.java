package models;

import java.util.Objects;

public class Teacher {
    private String name;
    private String phoneNumber;
    private String email;
    private int id;
    public Teacher(String name,String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && Objects.equals(name, teacher.name) && Objects.equals(phoneNumber, teacher.phoneNumber) && Objects.equals(email, teacher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, id);
    }
}

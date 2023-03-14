package models;

import java.util.Objects;

public class Teacher {
private String name;
private String comment;
private int studentId;
private int id;

    public Teacher(String name, String comment, int studentId) {
        this.name = name;
        this.comment = comment;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return studentId == teacher.studentId && id == teacher.id && Objects.equals(name, teacher.name) && Objects.equals(comment, teacher.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, comment, studentId, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package models;

import java.util.Objects;

public class Teacher {
private String comment;
private int id;

    public Teacher(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && Objects.equals(comment, teacher.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, id);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

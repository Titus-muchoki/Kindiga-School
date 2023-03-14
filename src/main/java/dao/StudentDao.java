package dao;

import models.Student;

import java.util.List;

public interface StudentDao {
    //CREATE
    void add(Student student);
    //READ
    List<Student> getAll();
    Student findById(int id);
    //update
    void update(int id, String name, String phoneNumber, String email);
    //delete
    void deleteById(int id);
    void clearAll();
}

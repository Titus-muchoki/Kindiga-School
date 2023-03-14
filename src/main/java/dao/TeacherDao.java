package dao;

import models.Teacher;

import java.util.List;

public interface TeacherDao {
    //CREATE
    void add(Teacher teacher);
    //READ
    List<Teacher> getAll();
    List<Teacher> getAllTeachersByStudentId(int studentId);
    //UPDATE
    //DELETE
    void deleteById(int id);
    void clearAll();
}

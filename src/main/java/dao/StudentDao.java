package dao;

import models.Student;
import models.Teacher;

import java.util.List;

public interface StudentDao {
    //CREATE
    void add(Student student);
    void addStudentToTeacher(Student student, Teacher teacher);
    //READ
    List<Student> getAll();
    List<Teacher> getAllTeachersByStudent(int studentId);

    Student findById(int id);
    //update
    void update(int id, String name, String phoneNumber, String email, int teacherId);
    //delete
    void deleteById(int id);
    void clearAll();
}

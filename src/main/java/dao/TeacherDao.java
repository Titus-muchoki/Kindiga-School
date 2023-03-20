package dao;

import models.Student;
import models.Teacher;
import models.Unit;

import java.util.List;

public interface TeacherDao {
    //CREATE
    void add(Teacher teacher);
    void addTeacherToStudent(Teacher teacher, Student student);
    //READ
    List<Teacher> getAll();
    List<Student> getAllStudentsByTeacher(int id);
    Teacher findById(int id);
    //UPDATE
    void update( int id, String comment);

    //DELETE
    void deleteById(int id);
    void clearAll();
}

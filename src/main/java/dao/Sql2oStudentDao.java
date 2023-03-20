package dao;

import models.Student;
import models.Teacher;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sql2oStudentDao implements StudentDao{
    private final Sql2o sql2o;
    public Sql2oStudentDao(Sql2o sql2o){
        this.sql2o= sql2o;
    }


    @Override
    public void  add(Student student) {
        String sql = "INSERT INTO students ( name, phonenumber, email, teacherid)VALUES(:name, :phoneNumber, :email, :teacherId)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(student)
                    .executeUpdate()
                    .getKey();
            student.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void addStudentToTeacher(Student student, Teacher teacher) {
        String sql = "INSERT INTO students_teachers (studentid, teacherid) VALUES (:studentId, :teacherId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("studentId", student.getId())
                    .addParameter("teacherId", teacher.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Student> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM students")
                    .executeAndFetch(Student.class);
        }
    }

    @Override
    public List<Teacher> getAllTeachersByStudent(int studentId) {
        List<Teacher> teachers = new ArrayList(); //empty list
        String joinQuery = "SELECT teacherid FROM students_teachers WHERE studentid = :studentId";

        try (Connection con = sql2o.open()) {
            List<Integer> allTeachersIds = con.createQuery(joinQuery)
                    .addParameter("studentId", studentId)
                    .executeAndFetch(Integer.class);
            for (Integer teacherId : allTeachersIds){
                String teachertypeQuery = "SELECT * FROM teachers WHERE id = :teacherId";
                teachers.add(
                        con.createQuery(teachertypeQuery)
                                .addParameter("teacherId", teacherId)
                                .executeAndFetchFirst(Teacher.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return teachers;
    }


    @Override
    public Student findById(int id) {
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM students WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Student.class);
        }
    }

    @Override
    public void update(int id, String name, String phoneNumber, String email, int teacherId) {
    String sql = "UPDATE students SET(name, phonenumber, email, teacherid)=(:name, :phoneNumber, :email, :teacherId)WHERE id = :id";
     try(Connection con = sql2o.open()) {
        con.createQuery(sql)
               .addParameter("name", name)
               .addParameter("phoneNumber", phoneNumber)
               .addParameter("email", email)
                .addParameter("teacherId", teacherId)
                .addParameter("id", id)
                .executeUpdate();
    }catch (Sql2oException ex){
         System.out.println(ex);
     }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from students WHERE id = :id";
        String deleteJoin = "DELETE from students_teachers WHERE studentid = :studentId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("studentId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
    String sql = "DELETE from students";
    try(Connection con = sql2o.open()) {
        con.createQuery(sql)
                .executeUpdate();
    }catch (Sql2oException ex){
        System.out.println(ex);
    }
    }
}

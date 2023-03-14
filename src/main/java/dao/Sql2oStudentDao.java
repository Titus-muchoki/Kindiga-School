package dao;

import models.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.sql.SQLException;
import java.util.List;

public class Sql2oStudentDao implements StudentDao{
    public Sql2o sql2o;
    public Sql2oStudentDao(Sql2o sql2o){
        this.sql2o= sql2o;
    }


    @Override
    public void add(Student student) {
        String sql = "INSERT INTO students ( name, phonenummber, email)VALUES(:name, :phoneNumber, :email)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(student)
                    .executeUpdate()
                    .getKey();
            student.setId(id);
        } catch (Exception ex){
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
    public Student findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name, String phoneNumber, String email) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}

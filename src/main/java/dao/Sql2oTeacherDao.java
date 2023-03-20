package dao;

import models.Student;
import models.Teacher;
import models.Unit;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oTeacherDao implements TeacherDao{
    private final Sql2o sql2o;
    public Sql2oTeacherDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Teacher teacher) {
        String sql = " INSERT INTO teachers(comment)VALUES(:comment)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(teacher)
                    .executeUpdate()
                    .getKey();
            teacher.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addTeacherToStudent(Teacher teacher, Student student) {
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
    public List<Teacher> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM teachers")
                    .executeAndFetch(Teacher.class);
        }
    }

    @Override
    public List<Student> getAllStudentsByTeacher(int teacherId) {
        ArrayList<Student> students = new ArrayList<>();

        String joinQuery = "SELECT studentid FROM students_teachers WHERE teacherid = :teacherId";

        try (Connection con = sql2o.open()) {
            List<Integer> allStudentIds = con.createQuery(joinQuery)
                    .addParameter("teacherId", teacherId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer studentId : allStudentIds){
                String restaurantQuery = "SELECT * FROM students WHERE id = :studentId";
                students.add(
                        con.createQuery(restaurantQuery)
                                .addParameter("studentId", studentId)
                                .executeAndFetchFirst(Student.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return students;
    }

    @Override
    public Teacher findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teachers WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Teacher.class);
        }
    }

    @Override
    public void update(int id, String comment) {
        String sql = "UPDATE teachers SET comment = :comment WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("comment", comment)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from teachers WHERE id = :id";
        String deleteJoin = "DELETE from students_teachers WHERE teacherid = :teacherId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

            con.createQuery(deleteJoin)
                    .addParameter("teacherId", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from teachers";
        try(Connection con = sql2o.open()){
            con.createQuery(sql, true)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
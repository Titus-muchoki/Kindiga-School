package dao;

import models.Student;
import models.Teacher;
import models.Unit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertNotEquals;

public class Sql2oTeacherDaoTest {
    private static Connection conn;

    private Sql2oTeacherDao teacherDao;
    private Sql2oStudentDao studentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/kindiga_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        teacherDao = new Sql2oTeacherDao(sql2o);
        studentDao = new Sql2oStudentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        teacherDao.clearAll(); //clear all restaurants after every test
        studentDao.clearAll(); //clear all restaurants after every test
    }
    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }
    @Test
    public void addingTeacherSetsId() throws Exception {
        Teacher teacher = setupTeacher();
        assertNotEquals(1, teacher.getId());
    }
    @Test
    public void getAll() throws Exception {
        Teacher teacher = setupTeacher();
        Teacher teacher1 = setupTeacher();
        assertNotEquals(0, teacherDao.getAll().size());
    }
    //HELPER
    public Teacher setupTeacher(){
        Teacher teacher = new Teacher("muchoki","any",1);
        teacherDao.add(teacher);
        return teacher;
    }
    public Student setUpStudent(){
        Student student = new Student("kajela","0717553340","titoyut@gamil.com");
        studentDao.add(student);
        return student;
    }

}

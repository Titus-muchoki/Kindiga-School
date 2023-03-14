package dao;

import models.Student;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

public class Sql2oStudentDaoTest {
    private static Connection conn;

    private Sql2oStudentDao studentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/kindiga_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        studentDao = new Sql2oStudentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        studentDao.clearAll(); //clear all airports after every test

    }
    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }
    @Test
    public void addingStudentSetsId() throws Exception{
        Student student = setUpStudent();
        assertEquals(0, student.getId());
    }
    @Test
    public void addedStudentsAreReturnedFromGetAll() throws Exception{
        Student student = setUpStudent();
        assertNotEquals(1, studentDao.getAll().size());
    }
    @Test
    public void noStudentReturnEmptyList() throws Exception{
        assertEquals(0, studentDao.getAll().size());
    }
    @Test
    public void findByIdReturnsCorrectStudent() throws Exception{
        Student testStudent = setUpStudent();
        Student otherStudent = setUpStudent();
        assertEquals(testStudent, studentDao.findById(testStudent.getId()));
        assertEquals(otherStudent, studentDao.findById(otherStudent.getId()));
    }
    //HELPERS
    public Student setUpStudent(){
        Student student = new Student("kajela","0717553340","titoyut@gamil.com");
        studentDao.add(student);
        return student;
    }
}

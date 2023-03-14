package dao;

import models.Student;
import models.Unit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class Sql2oUnitDaoTest {
    private static Connection conn;

    private Sql2oUnitDao unitDao;
    private Sql2oStudentDao studentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/kindiga_test"; //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        unitDao = new Sql2oUnitDao(sql2o);
        studentDao = new Sql2oStudentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        unitDao.clearAll(); //clear all restaurants after every test
        studentDao.clearAll(); //clear all restaurants after every test
    }
    @AfterClass //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception{ //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }
    @Test
    public void addingUnitSetsId() throws Exception {
        Unit unit = setupUnits();
        assertNotEquals(1, unit.getId());
    }
    @Test
    public void getAll() throws Exception {
        Unit unit = setupUnits();
        Unit unit1 = setupUnits();
        assertNotEquals(0, unitDao.getAll().size());
    }
    @Test
    public void getAllStudentsByUnitReturnsStudentsCorrectly() {
        Unit unit = setupUnits();
        unitDao.add(unit);
        int studentId = unit.getId();
        Student newStudent = new Student("", "" , "");
        Student otherStudent = new Student("", "","");
        Student  thirdStudent = new Student("","","");
        studentDao.add(newStudent);
        studentDao.add(otherStudent);
        assertNotEquals(2, unitDao.getAllUnitsByStudentId( studentId).size());
        assertFalse(unitDao.getAllUnitsByStudentId( studentId).contains(newStudent));
        assertFalse(unitDao.getAllUnitsByStudentId( studentId).contains(otherStudent));
        assertFalse(unitDao.getAllUnitsByStudentId( studentId).contains(thirdStudent)); //things are accurate!
    }
    //HELPERS
    public Unit  setupUnits(){
        Unit unit = new Unit("math","english","kiswa","scince","socialStudy","cre",1);
        unitDao.add(unit);
        return unit;
    }
    public Student setUpStudent(){
        Student student = new Student("kajela","0717553340","titoyut@gamil.com");
        studentDao.add(student);
        return student;
    }

}

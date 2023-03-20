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
    public void getAllUnitsByStudent() throws Exception {
        Student testStudent = setUpStudent();
        Student otherStudent = setUpStudent(); //add in some extra data to see if it interferes
        Unit unit = setupUnitForStudent(testStudent);
        Unit unit1 = setupUnitForStudent(testStudent);
        Unit unitForOtherStudent = setupUnitForStudent(otherStudent);
        assertEquals(2, unitDao.getAllUnitsByStudents(testStudent.getId()).size());
    }

    @Test
    public void deleteById() throws Exception{
        Unit unit = setupUnits();
        Unit otherUnit = setupUnits();
        unitDao.deleteById(unit.getId());
        assertEquals(1,unitDao.getAll().size());
        assertEquals(1,unitDao.getAll().size());
    }
    @Test
    public void clearAll()throws Exception{
        Unit unit = setupUnits();
        Unit unit1 = setupUnits();
        unitDao.clearAll();
        assertEquals(0, unitDao.getAll().size());
    }
    //HELPERS
    public Unit  setupUnits(){
        Unit unit = new Unit("math","english","kiswa","scince","socialStudy","cre", 1);
        unitDao.add(unit);
        return unit;
    }
    public Unit setupUnitForStudent(Student student) {
        Unit unit = new Unit( "math","english","kiswa","scince","socialStudy","cre",student.getId());
        unitDao.add(unit);
        return unit;
    }

    public Student setUpStudent(){
        Student student = new Student("kajela","0717553340","titoyut@gamil.com", 1);
        studentDao.add(student);
        return student;
    }

}

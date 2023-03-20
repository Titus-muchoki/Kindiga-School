package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;


public class StudentTest {
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){

    }
    @Test
    public void getStudentNameReturnsCorrectly(){
    Student student = setupStudent();
    assertEquals("janet", student.getName());
    }
    @Test
    public void getPhoneNumberReturnsCorrectly(){
        Student student = setupStudent();
        assertEquals("0717553340", student.getPhoneNumber());
    }
    @Test
    public void getEmailReturnsEmailCorrectly(){
        Student student = setupStudent();
        assertEquals("kajela@gmail.com", student.getEmail());
    }
    @Test
    public void setNameSetsNameCorrectly(){
        Student student = setupStudent();
        student.setName("tito");
        assertEquals("tito", student.getName());
    }
    @Test
    public void setPhoneNumberSetsCorrectly(){
        Student student = setupStudent();
        student.setPhoneNumber("0776509158");
        assertEquals("0776509158", student.getPhoneNumber());
    }
    @Test
    public void setEmailSetsEmailCorrectly(){
        Student student = setupStudent();
        student.setEmail("tito@gmail.com");
        assertEquals("tito@gmail.com", student.getEmail());
    }
    @Test
    public void getTeacherIdReturnsCorrectly() throws Exception{
        Student student = setupStudent();
        assertEquals(1, student.getTeacherId());
    }
    // HELPERS
    public Student setupStudent(){
        return new Student("janet","0717553340","kajela@gmail.com", 1);
    }
}

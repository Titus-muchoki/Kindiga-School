package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    @Test
    public void onCreate(){

    }
    @Test
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
    // HELPERS
    public Student setupStudent(){
        return new Student("janet","0717553340","kajela@gmail.com");
    }
}

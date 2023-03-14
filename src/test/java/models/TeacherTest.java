package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeacherTest {
    @Before
    public void setup(){

    }
    @After
    public void tearDown(){

    }
    @Test
    public void  getNameReturnsCorrectly() throws Exception{
        Teacher teacher = setupTeacher();
        assertEquals("muchoki", teacher.getName());
    }
    @Test
    public void getCommentReturnsComment() throws Exception{
        Teacher teacher = setupTeacher();
        assertEquals("any", teacher.getComment());
    }
    @Test
    public void getStudentIdReturnsStudentId() throws Exception{
        Teacher teacher = setupTeacher();
        assertEquals(1, teacher.getStudentId());
    }
    //HELPERS
    public Teacher setupTeacher()throws Exception{
        return new Teacher("muchoki","any",1);
    }
}

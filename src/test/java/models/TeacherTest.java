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
    public void getCommentReturnsComment() throws Exception{
        Teacher teacher = setupTeacher();
        assertEquals("good", teacher.getComment());
    }
    //HELPERS
    public Teacher setupTeacher()throws Exception{
        return new Teacher("good");
    }
}

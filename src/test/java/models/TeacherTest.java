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
    //HELPERS
    public Teacher setupTeacher()throws Exception{
        return new Teacher("muchoki","any",1);
    }
}

package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UnitTest {
    @Before
    public void setUp() throws Exception{

    }
    @After
    public void after() throws Exception{

    }
    @Test
    public void getMathReturnsCorrectly() throws Exception{
        Unit unit = setupUnit();
        assertEquals("math", unit.getMath());
    }
    @Test
    public void getEnglishReturnsEnglish() throws Exception{
        Unit unit = setupUnit();
        assertEquals("english", unit.getEnglish());
    }
    @Test
    public void getKiswahiliReturnsKiswahili() throws Exception{
        Unit unit = setupUnit();
        assertEquals("kiswa", unit.getKiswahili());
    }
    @Test
    public void getScienceReturnsScience() throws Exception{
        Unit unit = setupUnit();
        assertNotEquals("science", unit.getScience());
    }
    @Test
    public void getSocialStudyReturnsSocialStudy() throws  Exception{
        Unit unit = setupUnit();
        assertEquals("socialStudy", unit.getSocialStudy());
    }
    @Test
    public void getCreReturnsCre() throws Exception{
        Unit unit = setupUnit();
        assertEquals("cre", unit.getCre());
    }
    @Test
    public void getStudentIdReturnsStudentId() throws Exception{
        Unit unit = setupUnit();
        assertEquals(1, unit.getStudentId());
    }
    //HELPERS
    public Unit setupUnit() throws Exception{
        return new Unit("math","english","kiswa","scince","socialStudy","cre",1);
    }
}

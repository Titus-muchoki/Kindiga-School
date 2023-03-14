package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    //HELPERS
    public Unit setupUnit() throws Exception{
        return new Unit("math","english","kiswa","scince","socialStudy","cre",1);
    }
}

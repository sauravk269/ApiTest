package BookStore;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TestDemo extends BaseTest{


    @Test
    public void m1(){
        System.out.println("inside m1");
    }
    @Test
    public void m2(){
        assertTrue(true);
    }
    @Test
    public void m3(){
        System.out.println("inside m1");
        assertFalse(false);
    }
}

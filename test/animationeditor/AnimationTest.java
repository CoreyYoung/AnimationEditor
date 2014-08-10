package animationeditor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimationTest {
    
    public AnimationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addKeyFrame method, of class Animation.
     */
    @Test
    public void testAddKeyFrame() {
        System.out.println("addKeyFrame");
        
        Animation.keyFrameMap.clear();
        
        int time = 0;
        Animation.addKeyFrame(time);
        
        assert(Animation.keyFrameMap.containsKey(time));
    }

    /**
     * Test of getKeyFrame method, of class Animation.
     */
    @Test
    public void testGetKeyFrame() {
        System.out.println("getKeyFrame");

        Animation.keyFrameMap.clear();
        
        int time = 0;
        KeyFrame expResult = null;
        KeyFrame result = Animation.getKeyFrame(time);
        assertEquals(expResult, result);
        
        Animation.addKeyFrame(time);
        result = Animation.getKeyFrame(time);
        assertEquals(result.getTime(), time);
    }
}

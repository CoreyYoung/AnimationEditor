package animationeditor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AnimationEditorGUITest {
    
    public AnimationEditorGUITest() {
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
     * Test of main method, of class AnimationEditorGUI.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AnimationEditorGUI.main(args);
    }

    /**
     * Test of redrawSkeleton method, of class AnimationEditorGUI.
     */
    @Test
    public void testRedrawSkeleton() {
        System.out.println("redrawSkeleton");
        String[] args = null;
        AnimationEditorGUI.main(args);
        AnimationEditorGUI.skeletonPanel = new SkeletonPanel();
        AnimationEditorGUI.redrawSkeleton();
    }
}

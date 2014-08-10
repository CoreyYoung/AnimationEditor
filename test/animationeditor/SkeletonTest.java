package animationeditor;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkeletonTest {
    
    public SkeletonTest() {
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
     * Test of removeBone method, of class Skeleton.
     */
    @Test
    public void testRemoveBone() {
        System.out.println("removeBone");
        
        Skeleton.boneList.clear();
        Bone bone = new Bone("Test Bone", 0);
        Skeleton.boneList.add(bone);
        
        assert(! Skeleton.boneList.isEmpty());
        
        Skeleton.removeBone(bone.name);
        assert(Skeleton.boneList.isEmpty());
    }

    /**
     * Test of getBone method, of class Skeleton.
     */
    @Test
    public void testGetBone() {
        System.out.println("getBone");
        
        Skeleton.boneList.clear();
        Bone bone1 = new Bone("Bone 1", 0);
        Bone bone2 = new Bone("Bone 2", 0);
        Skeleton.boneList.add(bone1);
        Skeleton.boneList.add(bone2);
        
        assertEquals(Skeleton.getBone("Bone 1"), bone1);
        assertEquals(Skeleton.getBone("Bone 2"), bone2);
    }

    /**
     * Test of getBoneList method, of class Skeleton.
     */
    @Test
    public void testGetBoneList() {
        System.out.println("getBoneList");
        
        Bone bone1 = new Bone("Bone 1", 0);
        Bone bone2 = new Bone("Bone 2", 0);
        
        Skeleton.boneList.clear();
        Skeleton.boneList.add(bone1);
        Skeleton.boneList.add(bone2);
        
        ArrayList<HashMap> boneList = new ArrayList<>();
        boneList.add(bone1.getBoneMap());
        boneList.add(bone2.getBoneMap());
        
        assertEquals(Skeleton.getBoneList(), boneList);
    }

    /**
     * Test of setBoneList method, of class Skeleton.
     */
    @Test
    public void testSetBoneList() {
        System.out.println("setBoneList");
        
        Skeleton.boneList.clear();
        Bone bone = new Bone("Test Bone", 0);
        ArrayList<HashMap> fileMap = new ArrayList<>();
        fileMap.add(bone.getBoneMap());
        Skeleton.setBoneList(fileMap);
        
        assert(Skeleton.boneList.get(0).name.equals(bone.name));
        assert(Skeleton.boneList.get(0).childList.equals(bone.childList));
        assert(Skeleton.boneList.get(0).dir == (bone.dir));
        assert(Skeleton.boneList.get(0).image.equals(bone.image));
        assert(Skeleton.boneList.get(0).imagePath.equals(bone.imagePath));
    }

    /**
     * Test of render method, of class Skeleton.
     */
    @Test
    public void testRender() {
        System.out.println("render");
        
        Skeleton.boneList.add(new Bone("Test Bone", 0));
        
        JFrame frame = new JFrame();
        frame.setVisible(true);
        
        Graphics graphics = frame.getGraphics();
        Skeleton.render(graphics);
    }
}

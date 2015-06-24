package animationeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BoneTest {

    /**
     * Test of drawBone method, of class Bone.
     */
    @Test
    public void testDrawBone() {
        System.out.println("drawBone");

        JFrame frame = new JFrame();
        frame.setVisible(true);

        Graphics graphics = frame.getGraphics();
        Graphics2D g2d = (Graphics2D) graphics;
        int x = 0;
        int y = 0;
        int dir = 0;
        Bone bone = new Bone("Test", 0);

        bone.drawBone(g2d, x, y, dir);
    }

    /**
     * Test of getDescendant method, of class Bone.
     */
    @Test
    public void testGetDescendant() {
        System.out.println("getDescendant");

        Bone parent = new Bone("Parent", 0);
        Bone descendant = parent.getDescendant("Child");

        assertEquals(descendant, null);

        Bone child = new Bone("Child", 0);
        parent.childList.add(child);
        descendant = parent.getDescendant("Child");

        assertEquals(descendant, child);

        Bone grandChild = new Bone("GrandChild", 0);
        child.childList.add(grandChild);
        descendant = parent.getDescendant("GrandChild");

        assertEquals(descendant, grandChild);
    }

    /**
     * Test of removeDescendant method, of class Bone.
     */
    @Test
    public void testRemoveDescendant() {
        System.out.println("removeDescendant");

        Bone parent = new Bone("Parent", 0);
        Bone child = new Bone("Child", 0);
        parent.childList.add(child);
        Bone grandChild = new Bone("GrandChild", 0);
        child.childList.add(grandChild);

        assertEquals(parent.getDescendant("GrandChild"), grandChild);
        parent.removeDescendant("GrandChild");
        assertEquals(parent.getDescendant("GrandChild"), null);
    }

    /**
     * Test of getTreeBranch method, of class Bone.
     */
    @Test
    public void testGetTreeBranch() {
        System.out.println("getTreeBranch");

        Bone parent = new Bone("Parent", 0);
        Bone child = new Bone("Child", 0);
        parent.childList.add(child);
        Bone grandChild = new Bone("GrandChild", 0);
        child.childList.add(grandChild);

        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(parent.name);
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.name);
        parentNode.add(childNode);
        DefaultMutableTreeNode grandChildNode = new DefaultMutableTreeNode(grandChild.name);
        childNode.add(grandChildNode);

        //assert(parentNode.equals(parent.getTreeBranch()));
        //assert(childNode.equals(child.getTreeBranch()));
        //assert(grandChildNode.equals(grandChild.getTreeBranch()));
        String string1 = parentNode.toString();
        String string2 = parent.getTreeBranch().toString();
        assert (string1.equals(string2));

        string1 = parentNode.getFirstChild().toString();
        string2 = parent.getTreeBranch().getFirstChild().toString();
        assert (string1.equals(string2));

        string1 = parentNode.getFirstLeaf().getFirstLeaf().toString();
        string2 = parent.getTreeBranch().getFirstLeaf().getFirstLeaf().toString();
        assert (string1.equals(string2));
    }

    /**
     * Test of getBoneMap method, of class Bone.
     */
    @Test
    public void testGetBoneMap() {
        System.out.println("getBoneMap");

        Bone parent = new Bone("Parent", 0);
        Bone child = new Bone("Child", 90);
        parent.childList.add(child);
        Bone grandChild = new Bone("GrandChild", 311);
        child.childList.add(grandChild);

        HashMap<String, Object> parentMap = new HashMap<>();
        parentMap.put("Name", "Parent");
        parentMap.put("Direction", 0);
        parentMap.put("Image Path", "data/Bone.png");

        HashMap<String, Object> childMap = new HashMap<>();
        childMap.put("Name", "Child");
        childMap.put("Direction", 90);
        childMap.put("Image Path", "data/Bone.png");

        HashMap<String, Object> grandChildMap = new HashMap<>();
        grandChildMap.put("Name", "GrandChild");
        grandChildMap.put("Direction", 311);
        grandChildMap.put("Image Path", "data/Bone.png");

        ArrayList<HashMap> childList = new ArrayList<>();
        grandChildMap.put("Child Map", childList);

        childList = new ArrayList<>();
        childList.add(grandChildMap);
        childMap.put("Child Map", childList);

        childList = new ArrayList<>();
        childList.add(childMap);
        parentMap.put("Child Map", childList);

        assertEquals(parentMap, parent.getBoneMap());
        assertEquals(childMap, child.getBoneMap());
        assertEquals(grandChildMap, grandChild.getBoneMap());
    }

    /**
     * Test of getParent method, of class Bone.
     */
    @Test
    public void testGetParent() {
        System.out.println("getParent");

        Bone parent = new Bone("Parent", 0);
        Bone child = new Bone("Child", 0);
        Skeleton skeleton = new Skeleton();
        parent.childList.add(child);
        skeleton.boneList.add(parent);

        assertEquals(parent, child.getParent(skeleton));
    }

    /**
     * Test of getInterpolatedBone method, of class Bone.
     */
    @Test
    public void testGetInterpolatedBone() {
        System.out.println("getInterpolatedBone");

        String name = "Bone";

        Bone bone1 = new Bone(name, 0);
        Bone bone2 = new Bone(name, 100);

        Bone result1 = bone1.getInterpolatedBone(bone2, 0.1);
        Bone result2 = bone1.getInterpolatedBone(bone2, 0.5);
        Bone result3 = bone1.getInterpolatedBone(bone2, 0.7);

        assert (result1.dir == 10);
        assert (result2.dir == 50);
        assert (result3.dir == 70);
    }
}

package io.github.coreyyoung.animationeditor;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.TreeItem;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BoneTest {

    /**
     * Test of setDirection method, of class Bone.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");

        //AnimationEditorGUI.DisplayFrame = new JInternalFrame();
        Bone bone = new Bone("Bone", 0);
        bone.setDirection(100);
        assert (bone.dir == 100);
    }

    /**
     * Test of drawBone method, of class Bone.
     */
    @Test
    public void testDrawBone() {
        System.out.println("drawBone");

        //TODO Add test.
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
        Bone grandChild = new Bone("GrandChild", 0);
        child.childList.add(grandChild);
        parent.childList.add(child);

        TreeItem<String> parentNode = new TreeItem<>(parent.name);
        TreeItem<String> childNode = new TreeItem<>(child.name);
        TreeItem<String> grandChildNode = new TreeItem<>(grandChild.name);
        childNode.getChildren().add(grandChildNode);
        parentNode.getChildren().add(childNode);

        assert (parentNode.toString().equals(parent.getTreeBranch().toString()));
        assert (childNode.toString().equals(child.getTreeBranch().toString()));
        assert (grandChildNode.toString().equals(grandChild.getTreeBranch().toString()));

    }

    /**
     * Test of getBoneMap method, of class Bone.
     */
    @Test
    public void testGetBoneMap() {
        System.out.println("getBoneMap");

        String path = "src/main/resources/data/Bone.png";

        Bone parent = new Bone("Parent", path, 0);
        Bone child = new Bone("Child", path, 90);
        parent.childList.add(child);
        Bone grandChild = new Bone("GrandChild", path, 311);
        child.childList.add(grandChild);

        HashMap<String, Object> parentMap = new HashMap<>();
        parentMap.put("Name", "Parent");
        parentMap.put("Direction", 0);
        parentMap.put("Image Path", path);

        HashMap<String, Object> childMap = new HashMap<>();
        childMap.put("Name", "Child");
        childMap.put("Direction", 90);
        childMap.put("Image Path", path);

        HashMap<String, Object> grandChildMap = new HashMap<>();
        grandChildMap.put("Name", "GrandChild");
        grandChildMap.put("Direction", 311);
        grandChildMap.put("Image Path", path);

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
        Bone grandChild = new Bone("GrandChild", 0);
        Skeleton skeleton = new Skeleton();
        parent.childList.add(child);
        child.childList.add(grandChild);
        skeleton.boneList.add(parent);

        assertEquals(child, grandChild.getParent(skeleton));
        assertEquals(parent, child.getParent(skeleton));
        assert (parent.getParent(skeleton) == null);
        assert (new Bone("Bone", 0).getParent(skeleton) == null);
    }

    /**
     * Test of getInterpolatedBone method, of class Bone.
     */
    @Test
    public void testGetInterpolatedBone() {
        System.out.println("getInterpolatedBone");

        Bone bone1 = new Bone("Parent", 0);
        bone1.childList.add(new Bone("Child", 0));

        Bone bone2 = new Bone("Parent", 100);
        bone2.childList.add(new Bone("Child", 100));

        Bone result1 = bone1.getInterpolatedBone(bone2, 0.1);
        Bone result2 = bone1.getInterpolatedBone(bone2, 0.5);
        Bone result3 = bone1.getInterpolatedBone(bone2, 0.7);

        assert (result1.dir == 10 && result1.getDescendant("Child").dir == 10);
        assert (result2.dir == 50 && result2.getDescendant("Child").dir == 50);
        assert (result3.dir == 70 && result3.getDescendant("Child").dir == 70);
    }
}

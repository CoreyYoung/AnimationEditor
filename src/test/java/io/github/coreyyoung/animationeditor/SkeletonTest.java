package io.github.coreyyoung.animationeditor;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SkeletonTest {

    /**
     * Test of removeBone method, of class Skeleton.
     */
    @Test
    public void testRemoveBone() {
        System.out.println("removeBone");

        Skeleton skeleton = new Skeleton();

        Bone bone = new Bone("Test Bone", 0);
        bone.childList.add(new Bone("Child", 0));
        skeleton.boneList.add(bone);

        assert (!skeleton.boneList.isEmpty());

        skeleton.removeBone("Child");
        assert (bone.childList.isEmpty());

        skeleton.removeBone(bone.name);
        assert (skeleton.boneList.isEmpty());
    }

    /**
     * Test of getBone method, of class Skeleton.
     */
    @Test
    public void testGetBone() {
        System.out.println("getBone");

        Skeleton skeleton = new Skeleton();

        Bone bone1 = new Bone("Bone 1", 0);
        Bone child = new Bone("Child", 0);
        bone1.childList.add(child);
        Bone bone2 = new Bone("Bone 2", 0);
        skeleton.boneList.add(bone1);
        skeleton.boneList.add(bone2);

        assertEquals(skeleton.getBone("Bone 1"), bone1);
        assertEquals(skeleton.getBone("Bone 2"), bone2);
        assertEquals(skeleton.getBone("Child"), child);
        assertEquals(skeleton.getBone("No bone"), null);
    }

    /**
     * Test of getBoneList method, of class Skeleton.
     */
    @Test
    public void testGetBoneList() {
        System.out.println("getBoneList");

        Skeleton skeleton = new Skeleton();
        Bone bone1 = new Bone("Bone 1", 0);
        Bone bone2 = new Bone("Bone 2", 0);

        skeleton.boneList.clear();
        skeleton.boneList.add(bone1);
        skeleton.boneList.add(bone2);

        ArrayList<HashMap> boneList = new ArrayList<>();
        boneList.add(bone1.getBoneMap());
        boneList.add(bone2.getBoneMap());

        assertEquals(skeleton.getBoneList(), boneList);
    }

    /**
     * Test of setBoneList method, of class Skeleton.
     */
    @Test
    public void testSetBoneList() {
        System.out.println("setBoneList");

        Skeleton skeleton = new Skeleton();

        Bone bone = new Bone("Test Bone", 0);
        Bone child = new Bone("Child", 100);
        bone.childList.add(child);

        ArrayList<HashMap<String, Object>> fileMap = new ArrayList<>();
        fileMap.add(bone.getBoneMap());
        skeleton.setBoneList(fileMap);

        assertEquals(skeleton.boneList.getFirst().name, bone.name);
        assertEquals(skeleton.boneList.getFirst().dir, bone.dir);

        assertEquals(skeleton.boneList.getFirst().childList.getFirst().name, child.name);
        assertEquals(skeleton.boneList.getFirst().childList.getFirst().dir, child.dir);
    }

    /**
     * Test of render method, of class Skeleton.
     */
    @Test
    public void testRender() {
        System.out.println("render");

        //TODO add test here
    }

    /**
     * Test of getInterpolatedSkeleton method, of class Skeleton.
     */
    @Test
    public void testGetInterpolatedSkeleton() {
        System.out.println("getInterpolatedSkeleton");

        String name = "Bone";

        Skeleton skeleton1 = new Skeleton();
        skeleton1.boneList.add(new Bone(name, 0));

        Skeleton skeleton2 = new Skeleton();
        skeleton2.boneList.add(new Bone(name, 100));

        Skeleton result1 = skeleton1.getInterpolatedSkeleton(skeleton2, 0.1);
        Skeleton result2 = skeleton1.getInterpolatedSkeleton(skeleton2, 0.5);
        Skeleton result3 = skeleton1.getInterpolatedSkeleton(skeleton2, 0.7);

        assert (result1.getBone(name).dir == 10);
        assert (result2.getBone(name).dir == 50);
        assert (result3.getBone(name).dir == 70);
    }

    /**
     * Test of getTree method, of class Skeleton.
     */
    @Test
    public void testGetTree() {
        //TODO Fix test.

//        Skeleton skeleton = new Skeleton();
//        Bone bone = new Bone("Bone", "imagePath", 0);
//        skeleton.boneList.add(bone);
//        TreeView<String> result = skeleton.getTree();
//
//        TreeItem<String> root = new TreeItem<>("Skeleton");
//        root.getChildren().add(bone.getTreeBranch());
//        TreeView<String> expected = new TreeView<>();
//        expected.setRoot(root);
//
//        assertEquals(result.toString(), expected.toString());
    }
}

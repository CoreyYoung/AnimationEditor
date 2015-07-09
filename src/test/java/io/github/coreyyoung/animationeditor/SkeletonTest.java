package io.github.coreyyoung.animationeditor;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
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
        Bone bone2 = new Bone("Bone 2", 0);
        skeleton.boneList.add(bone1);
        skeleton.boneList.add(bone2);

        assertEquals(skeleton.getBone("Bone 1"), bone1);
        assertEquals(skeleton.getBone("Bone 2"), bone2);
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
        ArrayList<HashMap<String, Object>> fileMap = new ArrayList<>();
        fileMap.add(bone.getBoneMap());
        skeleton.setBoneList(fileMap);

        assert (skeleton.boneList.get(0).name.equals(bone.name));
        assert (skeleton.boneList.get(0).childList.equals(bone.childList));
        assert (skeleton.boneList.get(0).dir == (bone.dir));
    }

    /**
     * Test of render method, of class Skeleton.
     */
    @Test
    public void testRender() {
        System.out.println("render");

        Skeleton skeleton = new Skeleton();
        skeleton.boneList.add(new Bone("Test Bone", 0));

        JFrame frame = new JFrame();
        frame.setVisible(true);

        Graphics graphics = frame.getGraphics();
        skeleton.render(graphics);
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
}

package io.github.coreyyoung.animationeditor;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnimationTest {

    /**
     * Test of addKeyFrame method, of class Animation.
     */
    @Test
    public void testAddKeyFrame() {
        System.out.println("addKeyFrame");

        KeyFrame frame = new KeyFrame(0, new Skeleton());

        Animation.keyFrameList.clear();
        Animation.addKeyFrame(frame);

        assert (Animation.keyFrameList.contains(frame));
    }

    /**
     * Test of getLastKeyFrame method, of class Animation.
     */
    @Test
    public void testGetLastKeyFrame() {
        System.out.println("getLastKeyFrame");

        Animation.keyFrameList.clear();

        KeyFrame frame = new KeyFrame(100, new Skeleton());
        Animation.keyFrameList.add(frame);
        assert (Animation.getLastKeyFrame() == frame);

        KeyFrame frame2 = new KeyFrame(1000, new Skeleton());
        Animation.keyFrameList.add(frame2);
        assert (Animation.getLastKeyFrame() == frame2);

        KeyFrame frame3 = new KeyFrame(200, new Skeleton());
        Animation.keyFrameList.add(frame3);
        assert (Animation.getLastKeyFrame() != frame3);
    }

    /**
     * Test of getKeyFrame method, of class Animation.
     */
    @Test
    public void testGetKeyFrame() {
        System.out.println("getKeyFrame");

        Animation.keyFrameList.clear();

        int time = 0;
        KeyFrame expResult = null;
        KeyFrame result = Animation.getKeyFrame(time);
        assertEquals(expResult, result);

        Animation.addKeyFrame(new KeyFrame(time, new Skeleton()));
        result = Animation.getKeyFrame(time);
        assertEquals(result.getTime(), time);
    }

    /**
     * Test of getInterpolatedSkeleton method, of class Animation.
     */
    @Test
    public void testGetInterpolatedSkeleton() {
        System.out.println("getInterpolatedSkeleton");

        Animation.keyFrameList.clear();

        Skeleton skeleton = new Skeleton();
        skeleton.boneList.add(new Bone("Bone", 0));
        KeyFrame frame = new KeyFrame(0, skeleton);
        Animation.addKeyFrame(frame);

        Skeleton skeleton2 = new Skeleton();
        skeleton2.boneList.add(new Bone("Bone", 100));
        KeyFrame frame2 = new KeyFrame(100, skeleton2);
        Animation.addKeyFrame(frame2);

        assertEquals(Animation.getInterpolatedSkeleton(34).getBone("Bone").dir, 34);
        assertEquals(Animation.getInterpolatedSkeleton(50).getBone("Bone").dir, 50);
        assertEquals(Animation.getInterpolatedSkeleton(96).getBone("Bone").dir, 96);
    }
}

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

        Animation animation = new Animation();
        KeyFrame frame = new KeyFrame(0, new Skeleton());
        animation.addKeyFrame(frame);

        assert (animation.keyFrameList.contains(frame));
    }

    /**
     * Test of getLastKeyFrame method, of class Animation.
     */
    @Test
    public void testGetLastKeyFrame() {
        System.out.println("getLastKeyFrame");

        Animation animation = new Animation();

        KeyFrame frame = new KeyFrame(100, new Skeleton());
        animation.keyFrameList.add(frame);
        assert (animation.getLastKeyFrame() == frame);

        KeyFrame frame2 = new KeyFrame(1000, new Skeleton());
        animation.keyFrameList.add(frame2);
        assert (animation.getLastKeyFrame() == frame2);

        KeyFrame frame3 = new KeyFrame(200, new Skeleton());
        animation.keyFrameList.add(frame3);
        assert (animation.getLastKeyFrame() != frame3);
    }

    /**
     * Test of getKeyFrame method, of class Animation.
     */
    @Test
    public void testGetKeyFrame() {
        System.out.println("getKeyFrame");
        Animation animation = new Animation();

        int time = 0;
        KeyFrame expResult = null;
        KeyFrame result = animation.getKeyFrame(time);
        assertEquals(expResult, result);

        animation.addKeyFrame(new KeyFrame(time, new Skeleton()));
        result = animation.getKeyFrame(time);
        assertEquals(result.getTime(), time);
        
        assertEquals(animation.getKeyFrame(123), null);
    }

    /**
     * Test of getInterpolatedSkeleton method, of class Animation.
     */
    @Test
    public void testGetInterpolatedSkeleton() {
        System.out.println("getInterpolatedSkeleton");

        Animation animation = new Animation();

        Skeleton skeleton = new Skeleton();
        skeleton.boneList.add(new Bone("Bone", 0));
        KeyFrame frame = new KeyFrame(0, skeleton);
        animation.addKeyFrame(frame);

        Skeleton skeleton2 = new Skeleton();
        skeleton2.boneList.add(new Bone("Bone", 100));
        KeyFrame frame2 = new KeyFrame(100, skeleton2);
        animation.addKeyFrame(frame2);

        assertEquals(animation.getInterpolatedSkeleton(34).getBone("Bone").dir, 34);
        assertEquals(animation.getInterpolatedSkeleton(50).getBone("Bone").dir, 50);
        assertEquals(animation.getInterpolatedSkeleton(96).getBone("Bone").dir, 96);
    }
}

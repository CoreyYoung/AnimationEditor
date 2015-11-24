package io.github.coreyyoung.animationeditor;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AnimationTest {

    /**
     * Test of addKeyFrame method, of class Animation.
     */
    @Test
    public void testAddKeyFrame() {
        System.out.println("addKeyFrame");

        Animation animation = new Animation();

        HashMap<String, Integer> transforms = new HashMap<>();
        transforms.put("Bone", 0);
        KeyFrame frame = new KeyFrame(0, transforms);

        animation.addKeyFrame(frame);

        assertEquals(animation.keyFrameList.getFirst(), frame);
    }

    /**
     * Test of getLastKeyFrame method, of class Animation.
     */
    @Test
    public void testGetLastKeyFrame() {
        System.out.println("getLastKeyFrame");

        Animation animation = new Animation();
        HashMap<String, Integer> transforms = new HashMap<>();

        transforms.put("Name", 0);
        KeyFrame frame = new KeyFrame(100, transforms);
        animation.keyFrameList.add(frame);
        assertEquals(animation.getLastKeyFrame(), frame);

        transforms.clear();
        transforms.put("Name", 90);
        KeyFrame frame2 = new KeyFrame(1000, transforms);
        animation.keyFrameList.add(frame2);
        assertEquals(animation.getLastKeyFrame(), frame2);

        transforms.clear();
        transforms.put("Name", 0);
        KeyFrame frame3 = new KeyFrame(200, transforms);
        animation.keyFrameList.add(frame3);
        assertEquals(animation.getLastKeyFrame(), frame2);
    }

    /**
     * Test of getKeyFrame method, of class Animation.
     */
    @Test
    public void testGetKeyFrame() {
        System.out.println("getKeyFrame");
        
        Animation animation = new Animation();
        HashMap<String, Integer> transforms = new HashMap<>();
        transforms.put("Name", 90);
        
        int time = 0;
        KeyFrame frame1 = new KeyFrame(time, transforms);
        animation.addKeyFrame(frame1);
        
        int time2 = 100;
        KeyFrame frame2 = new KeyFrame(time2, transforms);
        animation.addKeyFrame(frame2);

        assertEquals(animation.getKeyFrame(0), frame1);
        assertEquals(animation.getKeyFrame(100), frame2);
        assertEquals(animation.getKeyFrame(123), null);
    }

    /**
     * Test of getInterpolatedTransforms method, of class Animation.
     */
    @Test
    public void testGetInterpolatedSkeleton() {
        System.out.println("getInterpolatedTransforms");

        Animation animation = new Animation();

        HashMap<String, Integer> transforms1 = new HashMap<>();
        transforms1.put("Bone", 0);
        animation.addKeyFrame(new KeyFrame(0, transforms1));

        HashMap<String, Integer> transforms2 = new HashMap<>();
        transforms2.put("Bone", 100);
        animation.addKeyFrame(new KeyFrame(1000, transforms2));

        System.out.println(animation.getInterpolatedTransforms(340).get("Bone"));

        assertEquals((int) animation.getInterpolatedTransforms(340).get("Bone"), 34);
        assertEquals((int) animation.getInterpolatedTransforms(500).get("Bone"), 50);
        assertEquals((int) animation.getInterpolatedTransforms(960).get("Bone"), 96);
    }
}

package animationeditor;

import org.junit.Test;
import static org.junit.Assert.*;

public class AnimationTest {

	/**
	 * Test of addKeyFrame method, of class Animation.
	 */
	@Test
	public void testAddKeyFrame() {
		System.out.println("addKeyFrame");

		Animation.keyFrameMap.clear();

		int time = 0;
		Animation.addKeyFrame(time, new Skeleton());

		assert (Animation.keyFrameMap.containsKey(time));
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

		Animation.addKeyFrame(time, new Skeleton());
		result = Animation.getKeyFrame(time);
		assertEquals(result.getTime(), time);
	}
}

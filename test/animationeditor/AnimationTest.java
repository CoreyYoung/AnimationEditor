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

		KeyFrame frame = new KeyFrame(0, new Skeleton());
		
		Animation.keyFrameList.clear();
		Animation.addKeyFrame(frame);

		assert (Animation.keyFrameList.contains(frame));
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
}

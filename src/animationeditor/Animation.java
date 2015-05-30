package animationeditor;

import java.util.HashMap;

public class Animation {

	public static HashMap<Integer, KeyFrame> keyFrameMap = new HashMap<>();

	/**
	 * Adds a KeyFrame to the animation with the given time.
	 *
	 * @param time The time to create the KeyFrame at.
	 * @param skeleton The Skeleton to create the KeyFrame from.
	 */
	public static void addKeyFrame(int time, Skeleton skeleton) {
		KeyFrame keyFrame = new KeyFrame(time, skeleton);

		keyFrameMap.put(time, keyFrame);
	}

	/**
	 * Gets the KeyFrame at the given time.
	 *
	 * @param time The time of the required KeyFrame.
	 * @return The KeyFrame found at the given time. Returns null if no KeyFrame is found.
	 */
	public static KeyFrame getKeyFrame(int time) {
		for (KeyFrame keyFrame : keyFrameMap.values()) {
			if (keyFrame.getTime() == time) {
				return keyFrame;
			}
		}

		return null;
	}
}

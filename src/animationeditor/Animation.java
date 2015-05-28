package animationeditor;

import java.util.HashMap;

public class Animation {
    public static HashMap<Integer, KeyFrame> keyFrameMap = new HashMap<>();
    
	/**
	 * Adds a keyframe to the animation with the given time.
	 * @param time The time to create the keyframe at.
	 */
    public static void addKeyFrame(int time) {
        KeyFrame keyFrame = new KeyFrame(time);
        
        keyFrameMap.put(time, keyFrame);
    }
    
	/**
	 * Gets the keyframe at the given time.
	 * @param time The time of the required keyframe.
	 * @return The keyframe found at the given time. Returns null if no keyframe is found.
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

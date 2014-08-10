package animationeditor;

import java.util.HashMap;

public class Animation {
    public static HashMap<Integer, KeyFrame> keyFrameMap = new HashMap<>();
    
    public static void addKeyFrame(int time) {
        KeyFrame keyFrame = new KeyFrame(time);
        
        keyFrameMap.put(time, keyFrame);
    }
    
    public static KeyFrame getKeyFrame(int time) {
        for (KeyFrame keyFrame : keyFrameMap.values()) {
            if (keyFrame.getTime() == time) {
                return keyFrame;
            }
        }
        
        return null;
    }
}

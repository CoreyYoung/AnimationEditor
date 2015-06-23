package animationeditor;

import java.util.LinkedList;

public class Animation {

    public static LinkedList<KeyFrame> keyFrameList = new LinkedList<>();

    /**
     * Adds a KeyFrame to the animation.
     *
     * @param frame The KeyFrame to add to the animation.
     */
    public static void addKeyFrame(KeyFrame frame) {
        keyFrameList.add(frame);
    }

    /**
     * Gets the last KeyFrame of the animation.
     *
     * @return The last KeyFrame. Returns null if no KeyFrame is found.
     */
    public static KeyFrame getLastKeyFrame() {
        KeyFrame result = null;

        for (KeyFrame frame : keyFrameList) {
            if (result == null || frame.getTime() > result.getTime()) {
                result = frame;
            }
        }
        
        return result;
    }

    /**
     * Gets the KeyFrame at the given time.
     *
     * @param time The time of the required KeyFrame.
     * @return The KeyFrame found at the given time. Returns null if no KeyFrame is found.
     */
    public static KeyFrame getKeyFrame(int time) {
        for (KeyFrame keyFrame : keyFrameList) {
            if (keyFrame.getTime() == time) {
                return keyFrame;
            }
        }

        return null;
    }

    /**
     * Gets a Skeleton for a given time by interpolating KeyFrames.
     *
     * @param time The time to get the Skeleton for.
     * @return The Skeleton for the given time.
     */
    public static Skeleton getInterpolatedSkeleton(long time) {
        Skeleton skeleton = null;
        KeyFrame frame1 = null;
        KeyFrame frame2 = null;

        // Find previous and next KeyFrames
        for (KeyFrame frame : keyFrameList) {
            if (frame.getTime() < time) {
                if (frame1 == null || frame.getTime() > frame1.getTime()) {
                    frame1 = frame;
                }
            } else {
                if (frame2 == null || frame.getTime() < frame2.getTime()) {
                    frame2 = frame;
                }
            }
        }

        if (frame1 != null && frame2 != null) {
            // Initialise position and duration variables for clarity, but also
            // so division is performed with doubles.
            double duration = frame2.getTime() - frame1.getTime();
            double position = time - frame1.getTime();
            double amount = (position / duration);

            skeleton = frame1.getSkeleton().getInterpolatedSkeleton(frame2.getSkeleton(), amount);
        }

        return skeleton;
    }
}

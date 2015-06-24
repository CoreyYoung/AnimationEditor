package io.github.coreyyoung.animationeditor;

public class KeyFrame {

    private final int time;
    private final Skeleton skeleton;

    /**
     * Creates a KeyFrame at the given time.
     *
     * @param time The time to create the KeyFrame at.
     * @param skeleton The Skeleton to create the KeyFrame from.
     */
    public KeyFrame(int time, Skeleton skeleton) {
        this.time = time;
        this.skeleton = skeleton;
    }

    /**
     * Gets the time of the KeyFrame.
     *
     * @return The time of the KeyFrame.
     */
    public int getTime() {
        return time;
    }

    /**
     * Gets the Skeleton of the KeyFrame.
     *
     * @return The Skeleton of the KeyFrame.
     */
    public Skeleton getSkeleton() {
        return skeleton;
    }
}

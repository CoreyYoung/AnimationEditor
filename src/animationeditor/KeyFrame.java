package animationeditor;

public class KeyFrame {
    private int time;
    
	/**
	 * Creates a KeyFrame at the given time.
	 * @param time The time to create the KeyFrame at.
	 */
    public KeyFrame(int time) {
        this.time = time;
    }
    
	/**
	 * Gets the time of the KeyFrame.
	 * @return The time of the KeyFrame.
	 */
    public int getTime() {
        return time;
    }
}

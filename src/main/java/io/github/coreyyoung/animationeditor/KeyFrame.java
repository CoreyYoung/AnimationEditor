package io.github.coreyyoung.animationeditor;

import java.util.HashMap;
import javafx.scene.control.TreeItem;

public class KeyFrame {

    private final int time;
    private HashMap<String, Integer> transforms;

    /**
     * Creates a KeyFrame at the given time.
     *
     * @param time The time to create the KeyFrame at.
     * @param transforms A HashMap<String, Integer> to set KeyFrame.transforms
     * to.
     */
    public KeyFrame(int time, HashMap<String, Integer> transforms) {
        this.transforms = new HashMap<>();
        this.time = time;
        this.transforms = transforms;
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
     * Gets the transforms HashMap<String, Integer> of the KeyFrame.
     *
     * @return The transforms HashMap<String, Integer> of the KeyFrame.
     */
    public HashMap<String, Integer> getTransforms() {
        return transforms;
    }

    /**
     * Adds a transform to the transforms HashMap<String, Integer> of the
     * KeyFrame.
     *
     * @param boneName The key of the transform. This should be a Bone's name.
     * @param rotation The value of the transform. This is the final rotation of
     * the bone with the given name.
     */
    public void addTransform(String boneName, int rotation) {
        transforms.put(boneName, rotation);
    }

    public TreeItem<String> getTreeBranch() {
        TreeItem<String> branch = new TreeItem<>(Integer.toString(time));

        for (String key : transforms.keySet()) {
            String nodeName = key + ": " + transforms.get(key);
            TreeItem<String> node = new TreeItem(nodeName);

            branch.getChildren().add(node);
        }

        return branch;
    }
}

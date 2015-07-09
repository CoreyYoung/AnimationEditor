package io.github.coreyyoung.animationeditor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;

public class Bone {

    private String imagePath;
    private Image image;

    public static final String DEFAULT_IMAGE_PATH = "src/main/resources/data/Bone.png";
    public String name;
    public int dir;
    public LinkedList<Bone> childList = new LinkedList<>();

    /**
     * Creates a Bone object.
     *
     * @param name The name of the new Bone.
     * @param imagePath	The URI of the new Bone's image.
     * @param dir The direction of the new Bone.
     */
    public Bone(String name, String imagePath, int dir) {
        setImage(imagePath);
        this.dir = dir;
        this.name = name;
    }

    /**
     * Creates a new Bone object.
     *
     * @param name The name of the new Bone.
     * @param dir The direction of the new Bone.
     */
    public Bone(String name, int dir) {
        setImage(DEFAULT_IMAGE_PATH);
        this.dir = dir;
        this.name = name;
    }

    /**
     * Draws the Bone.
     *
     * @param g2d A graphics2D object responsible for drawing the Bone.
     * @param x The x-coordinate to draw the Bone at.
     * @param y The y-coordinate to draw the Bone at.
     * @param dir The direction of rotation to draw the Bone
     */
    public void drawBone(Graphics2D g2d, int x, int y, int dir) {
        int width = image.getWidth(null);
        int length = image.getHeight(null);
        dir += this.dir;

        AffineTransform oldTransform = g2d.getTransform();
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(-dir));
        g2d.translate(-width / 2, 0);
        g2d.drawImage(image, 0, 0, width, length, null);
        g2d.setTransform(oldTransform);

        int endX = x + (int) (length * Math.sin(Math.toRadians(dir)));
        int endY = y + (int) (length * Math.cos(Math.toRadians(dir)));

        for (Bone child : childList) {
            child.drawBone(g2d, endX, endY, dir);
        }
    }

    /**
     * Sets the direction of the Bone.
     *
     * @param dir The new direction of the Bone.
     */
    public void setDirection(int dir) {
        this.dir = dir;
        AnimationEditorGUI.DisplayFrame.repaint();
    }

    /**
     * Sets the image of the Bone.
     *
     * @param imagePath The URI of the new image of the Bone.
     */
    public void setImage(String imagePath) {
        this.imagePath = imagePath;
        image = Toolkit.getDefaultToolkit().getImage(imagePath);
    }

    /**
     * Searches child Bones recursively for a Bone with a given name.
     *
     * @param name The name of the Bone to find.
     * @return The Bone with the given name. Returns null if no Bone is found.
     */
    public Bone getDescendant(String name) {
        for (Bone child : childList) {
            if (child.name.equals(name)) {
                return child;
            } else {
                Bone grandChild = child.getDescendant(name);

                if (grandChild != null) {
                    return grandChild;
                }
            }
        }

        return null;
    }

    /**
     * Deletes a descendant Bone with the given name.
     *
     * @param name The name of the Bone to delete.
     */
    public void removeDescendant(String name) {
        Iterator<Bone> childIterator = childList.iterator();

        while (childIterator.hasNext()) {
            Bone child = childIterator.next();

            if (child.name.equals(name)) {
                childIterator.remove();
            } else {
                child.removeDescendant(name);
            }
        }
    }

    /**
     * Creates a DefaultMutableTreeNode with the same structure as the Bone map.
     *
     * @return The new DefaultMutableTreeNode.
     */
    public DefaultMutableTreeNode getTreeBranch() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);

        for (Bone child : childList) {
            DefaultMutableTreeNode childNode = child.getTreeBranch();
            node.add(childNode);
        }

        return node;
    }

    /**
     * Gets a map of the Bone's structure.
     *
     * @return A HashMap of the Bone's structure.
     */
    public HashMap<String, Object> getBoneMap() {
        HashMap<String, Object> boneMap = new HashMap<>();

        boneMap.put("Name", name);
        boneMap.put("Direction", dir);
        boneMap.put("Image Path", imagePath);

        ArrayList<HashMap> childMap = new ArrayList<>();

        for (Bone child : childList) {
            childMap.add(child.getBoneMap());
        }

        boneMap.put("Child Map", childMap);

        return boneMap;
    }

    /**
     * Returns the parent Bone.
     *
     * @param skeleton The skeleton that contains the bone.
     * @return The parent Bone. Returns null if no parent is found.
     */
    public Bone getParent(Skeleton skeleton) {
        if (skeleton.boneList.contains(this)) {
            return null;
        }

        for (Bone bone : skeleton.boneList) {
            if (bone.childList.contains(this)) {
                return bone;
            }

            Skeleton newSkeleton = new Skeleton();
            newSkeleton.boneList = bone.childList;
            Bone parent = getParent(newSkeleton);

            if (parent != null) {
                return parent;
            }
        }

        return null;
    }

    /**
     * Returns a Bone interpolated between this and the given Bone by a set
     * amount.
     *
     * @param bone2 The second Bone used for interpolation.
     * @param amount The balance between this and the given Bone. Should be
     * between 0 and 1.
     * @return The new interpolated Bone.
     */
    public Bone getInterpolatedBone(Bone bone2, double amount) {
        Bone bone = new Bone(this.name, this.imagePath, this.dir);

        int dir1 = this.dir;
        int dir2 = bone2.dir;

        bone.dir = (int) (dir1 + ((dir2 - dir1) * amount));

        for (Bone child : this.childList) {
            bone.childList.add(child.getInterpolatedBone(bone2.getDescendant(child.name), amount));
        }

        return bone;
    }
}

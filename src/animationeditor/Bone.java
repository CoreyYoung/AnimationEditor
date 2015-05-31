package animationeditor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.tree.DefaultMutableTreeNode;

public class Bone {

	public static final String DEFAULT_IMAGE_PATH = "data/Bone.png";
	public String imagePath;
	public Image image;
	public String name;
	public int dir;
	public ArrayList<Bone> childList = new ArrayList<>();

	/**
	 * Creates a Bone object.
	 *
	 * @param name The name of the new bone.
	 * @param imagePath	The URI of the new bone's image.
	 * @param dir The direction of the new bone.
	 */
	public Bone(String name, String imagePath, int dir) {
		setImage(imagePath);
		this.dir = dir;
		this.name = name;
	}

	/**
	 * Creates a new Bone object.
	 *
	 * @param name The name of the new bone.
	 * @param dir The direction of the new bone.
	 */
	public Bone(String name, int dir) {
		setImage(DEFAULT_IMAGE_PATH);
		this.dir = dir;
		this.name = name;
	}

	/**
	 * Draws the bone.
	 *
	 * @param g2d A graphics2D object responsible for drawing the bone.
	 * @param x	The x-coordinate to draw the bone at.
	 * @param y The y-coordinate to draw the bone at.
	 * @param dir The direction of rotation to draw the bone
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

		for (Bone child : childList) {
			int endX = x + (int) (length * Math.sin(Math.toRadians(dir)));
			int endY = y + (int) (length * Math.cos(Math.toRadians(dir)));

			child.drawBone(g2d, endX, endY, dir);
		}
	}

	/**
	 * Sets the direction of the bone.
	 *
	 * @param dir The new direction of the bone.
	 */
	public void setDirection(int dir) {
		this.dir = dir;
		AnimationEditorGUI.DisplayFrame.repaint();
	}

	/**
	 * Sets the image of the bone.
	 *
	 * @param imagePath The URI of the new image of the bone.
	 */
	public void setImage(String imagePath) {
		this.imagePath = imagePath;
		image = Toolkit.getDefaultToolkit().getImage(imagePath);
	}

	/**
	 * Searches child bones recursively for a bone with a given name.
	 *
	 * @param name The name of the bone to find.
	 * @return The bone with the given name. Returns null if no bone is found.
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
	 * Deletes a descendant bone with the given name.
	 *
	 * @param name The name of the bone to delete.
	 */
	public void removeDescendant(String name) {
		Iterator childIterator = childList.iterator();

		while (childIterator.hasNext()) {
			Bone child = (Bone) childIterator.next();

			if (child.name.equals(name)) {
				childIterator.remove();
			} else {
				child.removeDescendant(name);
			}
		}
	}

	/**
	 * Creates a DefaultMutableTreeNode with the same structure as the bone map.
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
	 * Gets a map of the bone structure.
	 *
	 * @return A hashmap of the bone's structure.
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
	 * Returns the parent bone.
	 *
	 * @return The parent bone.
	 */
	public Bone getParent(Skeleton skeleton) {
		for (Bone bone : skeleton.boneList) {
			while (!bone.childList.contains(this)) {
				for (Bone child : bone.childList) {
					if (child.getDescendant(name) != null) {
						bone = child;
					}
				}
			}

			return bone;
		}

		throw new NullPointerException();
	}
}

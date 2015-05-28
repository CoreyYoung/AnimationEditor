package animationeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Skeleton {

	public static int x = 64;
	public static int y = 32;
	public static ArrayList<Bone> boneList = new ArrayList<>();

	/**
	 * Removes a bone from the skeleton.
	 *
	 * @param name The name of the bone to remove.
	 */
	public static void removeBone(String name) {
		Iterator iterator = boneList.iterator();

		while (iterator.hasNext()) {
			Bone bone = (Bone) iterator.next();

			if (bone.name.equals(name)) {
				iterator.remove();
			} else {
				bone.removeDescendant(name);
			}
		}
	}

	/**
	 * Gets a bone from the skeleton.
	 *
	 * @param name The name of the bone to get.
	 * @return Returns the bone with the given name if found, otherwise returns
	 * null.
	 */
	public static Bone getBone(String name) {
		for (Bone bone : boneList) {
			if (bone.name.equals(name)) {
				return bone;
			} else {
				Bone descendant = bone.getDescendant(name);

				if (descendant != null) {
					return descendant;
				}
			}
		}

		return null;
	}

	/**
	 * Gets a List of all bones, with bones stored in nested HashMaps.
	 *
	 * @return Returns an ArrayList<HashMap>, where HashMap is type
	 * HashMap<Object>.
	 */
	public static ArrayList<HashMap> getBoneList() {
		ArrayList<HashMap> mapList = new ArrayList<>();

		for (Bone bone : boneList) {
			mapList.add(bone.getBoneMap());
		}

		return mapList;
	}

	/**
	 * Replaces all bones in skeleton with bones data passed in a List<HashMap>.
	 *
	 * @param fileMap An ArrayList<HashMap> which contains all the bones data.
	 */
	public static void setBoneList(ArrayList<HashMap> fileMap) {
		boneList.clear();

		for (HashMap boneMap : fileMap) {
			boneList.add(loadBoneFromMap(boneMap));
		}
	}

	/**
	 * Renders the Skeleton.
	 *
	 * @param g The Graphics object that draws the Skeleton.
	 */
	public static void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		for (Bone bone : boneList) {
			bone.drawBone(g2d, x, y, 0);
		}
	}

	/**
	 * Loads a bone from data stored in a HashMap.
	 *
	 * @param boneMap A HashMap<String, Object> that contains the bone's data.
	 * @return The bone that was loaded.
	 */
	private static Bone loadBoneFromMap(HashMap<String, Object> boneMap) {
		String name = (String) boneMap.get("Name");
		String imagePath = (String) boneMap.get("Image Path");
		int dir = (int) boneMap.get("Direction");

		Bone bone = new Bone(name, imagePath, dir);

		ArrayList<HashMap> childMapList = (ArrayList<HashMap>) boneMap.get("Child Map");
		ArrayList<Bone> childList = new ArrayList<>();

		for (HashMap<String, Object> childMap : childMapList) {
			if (!childMap.isEmpty()) {
				childList.add(loadBoneFromMap(childMap));
			}
		}

		bone.childList = childList;

		return bone;
	}
}

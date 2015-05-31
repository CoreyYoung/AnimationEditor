package animationeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Skeleton {

	public static int x = 64;
	public static int y = 32;
	public ArrayList<Bone> boneList = new ArrayList<>();

	/**
	 * Removes a bone from the skeleton.
	 *
	 * @param name The name of the bone to remove.
	 */
	public void removeBone(String name) {
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
	 * @return Returns the bone with the given name if found, otherwise returns null.
	 */
	public Bone getBone(String name) {
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
	 * @return Returns an ArrayList<HashMap>, where HashMap is type HashMap<Object>.
	 */
	public ArrayList<HashMap> getBoneList() {
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
	public void setBoneList(ArrayList<HashMap> fileMap) {
		boneList.clear();

		for (HashMap boneMap : fileMap) {
			boneList.add(loadBoneFromMap(boneMap));
		}
	}

	/**
	 * Returns a Skeleton interpolated between this and the given Skeleton by a set amount.
	 *
	 * @param skeleton2 The second Skeleton used for interpolation.
	 * @param amount The balance between this and the given Skeleton. Should be between 0 and 1.
	 * @return The new interpolated Skeleton.
	 */
	public Skeleton getInterpolatedSkeleton(Skeleton skeleton2, double amount) {
		Skeleton result = new Skeleton();

		for (Bone bone : boneList) {
			Bone newBone = bone.getInterpolatedBone(skeleton2.getBone(bone.name), amount);
			result.boneList.add(newBone);
		}

		return result;
	}

	/**
	 * Renders the Skeleton.
	 *
	 * @param g The Graphics object that draws the Skeleton.
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		AnimationEditorGUI.time++;

		Skeleton skeleton = Animation.getInterpolatedSkeleton(AnimationEditorGUI.time);

		if (skeleton == null) {
			skeleton = this;
		}

		for (Bone bone : skeleton.boneList) {
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

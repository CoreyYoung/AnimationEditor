package animationeditor;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkeletonTest {

	/**
	 * Test of removeBone method, of class Skeleton.
	 */
	@Test
	public void testRemoveBone() {
		System.out.println("removeBone");

		Skeleton skeleton = new Skeleton();

		Bone bone = new Bone("Test Bone", 0);
		skeleton.boneList.add(bone);

		assert (!skeleton.boneList.isEmpty());

		skeleton.removeBone(bone.name);
		assert (skeleton.boneList.isEmpty());
	}

	/**
	 * Test of getBone method, of class Skeleton.
	 */
	@Test
	public void testGetBone() {
		System.out.println("getBone");

		Skeleton skeleton = new Skeleton();

		Bone bone1 = new Bone("Bone 1", 0);
		Bone bone2 = new Bone("Bone 2", 0);
		skeleton.boneList.add(bone1);
		skeleton.boneList.add(bone2);

		assertEquals(skeleton.getBone("Bone 1"), bone1);
		assertEquals(skeleton.getBone("Bone 2"), bone2);
	}

	/**
	 * Test of getBoneList method, of class Skeleton.
	 */
	@Test
	public void testGetBoneList() {
		System.out.println("getBoneList");

		Skeleton skeleton = new Skeleton();
		Bone bone1 = new Bone("Bone 1", 0);
		Bone bone2 = new Bone("Bone 2", 0);

		skeleton.boneList.clear();
		skeleton.boneList.add(bone1);
		skeleton.boneList.add(bone2);

		ArrayList<HashMap> boneList = new ArrayList<>();
		boneList.add(bone1.getBoneMap());
		boneList.add(bone2.getBoneMap());

		assertEquals(skeleton.getBoneList(), boneList);
	}

	/**
	 * Test of setBoneList method, of class Skeleton.
	 */
	@Test
	public void testSetBoneList() {
		System.out.println("setBoneList");

		Skeleton skeleton = new Skeleton();

		Bone bone = new Bone("Test Bone", 0);
		ArrayList<HashMap> fileMap = new ArrayList<>();
		fileMap.add(bone.getBoneMap());
		skeleton.setBoneList(fileMap);

		assert (skeleton.boneList.get(0).name.equals(bone.name));
		assert (skeleton.boneList.get(0).childList.equals(bone.childList));
		assert (skeleton.boneList.get(0).dir == (bone.dir));
		assert (skeleton.boneList.get(0).image.equals(bone.image));
		assert (skeleton.boneList.get(0).imagePath.equals(bone.imagePath));
	}

	/**
	 * Test of render method, of class Skeleton.
	 */
	@Test
	public void testRender() {
		System.out.println("render");

		Skeleton skeleton = new Skeleton();
		skeleton.boneList.add(new Bone("Test Bone", 0));

		JFrame frame = new JFrame();
		frame.setVisible(true);

		Graphics graphics = frame.getGraphics();
		skeleton.render(graphics);
	}
}

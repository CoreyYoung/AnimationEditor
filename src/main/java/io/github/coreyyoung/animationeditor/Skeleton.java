package io.github.coreyyoung.animationeditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Skeleton {

    private static final int x = 64;
    private static final int y = 32;

    public LinkedList<Bone> boneList = new LinkedList<>();

    /**
     * Removes a Bone from the Skeleton.
     *
     * @param name The name of the Bone to remove.
     */
    public void removeBone(String name) {
        Iterator<Bone> iterator = boneList.iterator();

        while (iterator.hasNext()) {
            Bone bone = iterator.next();

            if (bone.name.equals(name)) {
                iterator.remove();
            } else {
                bone.removeDescendant(name);
            }
        }
    }

    /**
     * Gets a Bone from the Skeleton.
     *
     * @param name The name of the Bone to get.
     * @return Returns the Bone with the given name if found, otherwise returns
     * null.
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
     * Gets a List of all Bones, with Bones stored in nested HashMaps.
     *
     * @return Returns an ArrayList<HashMap>, where HashMap is type
     * HashMap<Object>.
     */
    public ArrayList<HashMap<String, Object>> getBoneList() {
        ArrayList<HashMap<String, Object>> mapList = new ArrayList<>();

        for (Bone bone : boneList) {
            mapList.add(bone.getBoneMap());
        }

        return mapList;
    }

    /**
     * Replaces all Bones in Skeleton with Bones data passed in a List<HashMap>.
     *
     * @param fileMap An ArrayList<HashMap> which contains all the Bones data.
     */
    public void setBoneList(ArrayList<HashMap<String, Object>> fileMap) {
        boneList.clear();

        for (HashMap<String, Object> boneMap : fileMap) {
            boneList.add(loadBoneFromMap(boneMap));
        }
    }

    /**
     * Returns a Skeleton interpolated between this and the given Skeleton by a
     * set amount.
     *
     * @param skeleton2 The second Skeleton used for interpolation.
     * @param amount The balance between this and the given Skeleton. Should be
     * between 0 and 1.
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

//    /**
//     * Renders the Skeleton.
//     *
//     * @param g The Graphics object that draws the Skeleton.
//     */
//    public void render(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        Skeleton skeleton = null;
//
//        if (AnimationEditorGUI.isPlayingAnimation) {
//            if (AnimationEditorGUI.animation.getLastKeyFrame() != null
//                    && AnimationEditorGUI.getTime() > AnimationEditorGUI.animation.getLastKeyFrame().getTime()) {
//                AnimationEditorGUI.resetTime();
//            }
//
//            skeleton = AnimationEditorGUI.animation.getInterpolatedSkeleton(AnimationEditorGUI.getTime());
//        }
//
//        if (skeleton == null) {
//            skeleton = this;
//        }
//
//        for (Bone bone : skeleton.boneList) {
//            bone.drawBone(g2d, x, y, 0);
//        }
//    }
    /**
     * Loads a Bone from data stored in a HashMap.
     *
     * @param boneMap A HashMap<String, Object> that contains the Bone's data.
     * @return The Bone that was loaded.
     */
    private static Bone loadBoneFromMap(HashMap<String, Object> boneMap) {
        String name = (String) boneMap.get("Name");
        String imagePath = (String) boneMap.get("Image Path");
        int dir = (int) boneMap.get("Direction");

        Bone bone = new Bone(name, imagePath, dir);

        ArrayList<HashMap<String, Object>> childMapList;
        childMapList = (ArrayList<HashMap<String, Object>>) boneMap.get("Child Map");
        LinkedList<Bone> childList = new LinkedList<>();

        for (HashMap<String, Object> childMap : childMapList) {
            if (!childMap.isEmpty()) {
                childList.add(loadBoneFromMap(childMap));
            }
        }

        bone.childList = childList;

        return bone;
    }

    public TreeView<String> getTree() {
        TreeItem<String> root = new TreeItem<>("Skeleton");

        for (Bone bone : boneList) {
            TreeItem<String> branch = bone.getTreeBranch();
            root.getChildren().add(branch);
        }

        root.setExpanded(true);

        return new TreeView<>(root);
    }
}

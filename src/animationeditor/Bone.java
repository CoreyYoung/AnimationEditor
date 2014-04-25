package animationeditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JInternalFrame;
import javax.swing.tree.DefaultMutableTreeNode;

public class Bone {

    BoneImage boneImage;
    String name;
    int x, y, dir, length;
    ArrayList<Bone> childList = new ArrayList<>();

    public Bone(String name, int x, int y, int dir, int length) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.length = length;
        this.name = name;

        JInternalFrame frame = AnimationEditorGUI.animationFrame;

        boneImage = new BoneImage("data/Bone.png");
        frame.setContentPane(boneImage);
        frame.setVisible(true);
    }

    public void setDirection(int dir) {
        this.dir = dir;
        boneImage.direction = dir;

        AnimationEditorGUI.animationFrame.repaint();
    }

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

    public DefaultMutableTreeNode getTreeBranch() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);

        for (Bone child : childList) {
            DefaultMutableTreeNode childNode = child.getTreeBranch();
            node.add(childNode);
        }

        return node;
    }

    public HashMap<String, Object> getBoneMap() {
        HashMap<String, Object> boneMap = new HashMap<>();

        boneMap.put("Name", name);
        boneMap.put("X", x);
        boneMap.put("Y", x);
        boneMap.put("Direction", x);
        boneMap.put("Length", x);

        ArrayList<HashMap> childMap = new ArrayList<>();

        for (Bone child : childList) {
            childMap.add(child.getBoneMap());
        }

        boneMap.put("Child Map", childMap);

        return boneMap;
    }
}

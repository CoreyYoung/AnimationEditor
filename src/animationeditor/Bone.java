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

    public Image image;
    public String name;
    public int dir, length;
    public ArrayList<Bone> childList = new ArrayList<>();

    public Bone(String name, int dir, int length) {
        image = Toolkit.getDefaultToolkit().getImage("data/Bone.png");
        this.dir = dir;
        this.length = 32;
        this.name = name;

        
    }

    public void drawBone(Graphics2D g2d, int x, int y, int dir) {
        dir += this.dir;

        AffineTransform oldTransform = g2d.getTransform();
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(-dir));
        g2d.drawImage(image, 0, 0, 8, 32, null);
        g2d.setTransform(oldTransform);

        for (Bone child : childList) {
            int endX = x + (int) (length * Math.sin(Math.toRadians(dir)));
            int endY = y + (int) (length * Math.cos(Math.toRadians(dir)));

            child.drawBone(g2d, endX, endY, dir);
        }
    }

    public void setDirection(int dir) {
        this.dir = dir;
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
        boneMap.put("Direction", dir);
        boneMap.put("Length", length);

        ArrayList<HashMap> childMap = new ArrayList<>();

        for (Bone child : childList) {
            childMap.add(child.getBoneMap());
        }

        boneMap.put("Child Map", childMap);

        return boneMap;
    }
}

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
    
    public static ArrayList<HashMap> getBoneList() {
        ArrayList<HashMap> mapList = new ArrayList<>();
        
        for (Bone bone : boneList) {
            mapList.add(bone.getBoneMap());
        }
        
        return mapList;
    }
    
    public static void setBoneList(ArrayList<HashMap> fileMap) {
        boneList.clear();
        
        for (HashMap boneMap : fileMap) {
            boneList.add(loadBoneFromMap(boneMap));
        }
    }

    public static void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Bone bone : boneList) {
            bone.drawBone(g2d, x, y, 0);
        }
    }
    
    private static Bone loadBoneFromMap(HashMap<String, Object> boneMap) {
        String name = (String) boneMap.get("Name");
        String imagePath = (String) boneMap.get("Image Path");
        int dir = (int) boneMap.get("Direction");
        
        Bone bone = new Bone(name, imagePath, dir);
        
        ArrayList<HashMap> childMapList = (ArrayList<HashMap>) boneMap.get("Child Map");
        ArrayList<Bone> childList = new ArrayList<>();
        
        for (HashMap<String, Object> childMap : childMapList) {
            if (! childMap.isEmpty()) {
                childList.add(loadBoneFromMap(childMap));
            }
        }
            
        bone.childList = childList;
        
        return bone;
    }
}

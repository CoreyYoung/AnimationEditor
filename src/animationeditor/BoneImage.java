package animationeditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class BoneImage extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (Bone bone : Skeleton.boneList) {
            bone.drawBone(g2d, Skeleton.x, Skeleton.y, 0);
        }
    }
}

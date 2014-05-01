package animationeditor;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SkeletonPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        Skeleton.render(g);
    }
}

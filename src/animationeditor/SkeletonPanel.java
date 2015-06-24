package animationeditor;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SkeletonPanel extends JPanel {
    private int fps = 0;
    private int currentFrames = 0;
    private long startTime = System.currentTimeMillis();
    
    /**
     * renders the Skeleton.
     *
     * @param g The Graphics object responsible for drawing the Skeleton.
     */
    @Override
    public void paintComponent(Graphics g) {
        AnimationEditorGUI.skeleton.render(g);
        
        currentFrames ++;
        
        if (System.currentTimeMillis() - startTime >= 1000) {
            startTime = System.currentTimeMillis();
            fps = currentFrames;
            currentFrames = 0;
        }
        
        g.drawString("FPS: " + fps, 16, 16);
    }
}

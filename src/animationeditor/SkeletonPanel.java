package animationeditor;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SkeletonPanel extends JPanel {

	/**
	 * renders the Skeleton.
	 *
	 * @param g The Graphics object responsible for drawing the Skeleton.
	 */
	@Override
	public void paintComponent(Graphics g) {
		AnimationEditorGUI.skeleton.render(g);
	}
}

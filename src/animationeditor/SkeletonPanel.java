package animationeditor;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SkeletonPanel extends JPanel {

	/**
	 * renders the skeleton.
	 *
	 * @param g The graphics object responsible for drawing the skeleton.
	 */
	@Override
	public void paintComponent(Graphics g) {
		AnimationEditorGUI.skeleton.render(g);
	}
}

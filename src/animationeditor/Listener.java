package animationeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

	/**
	 * Redraws the skeleton.
	 * @param ae The ActionEvent.
	 */
    @Override
    public void actionPerformed(ActionEvent ae) {
        AnimationEditorGUI.redrawSkeleton();
       
        // TODO Fix this
    }
}

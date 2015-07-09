package io.github.coreyyoung.animationeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {

    /**
     * Redraws the Skeleton.
     *
     * @param ae The ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        AnimationEditorGUI.redrawSkeleton();

        // TODO Fix this
    }
}

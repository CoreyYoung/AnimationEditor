package io.github.coreyyoung.animationeditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.yaml.snakeyaml.Yaml;

public class AnimationEditorGUI extends javax.swing.JFrame {

    private static SkeletonPanel skeletonPanel;
    private static Timer timer;
    private static Listener listener;
    private static long startTime;

    public static boolean isPlayingAnimation = false;
    public static AnimationEditorGUI self;
    public static Skeleton skeleton;

    /**
     * initializes the program.
     */
    public AnimationEditorGUI() {
        skeletonPanel = new SkeletonPanel();
        listener = new Listener();
        timer = new Timer(1000 / 60, listener);
        timer.start();
        skeleton = new Skeleton();

        initComponents();
        updateBoneTree();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        btnAddBone = new javax.swing.JButton();
        btnRemoveBone = new javax.swing.JButton();
        btnSetParent = new javax.swing.JButton();
        btnResetParent = new javax.swing.JButton();
        btnRenameBone = new javax.swing.JButton();
        btnRotateBone = new javax.swing.JButton();
        btnSetImage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        boneTree = new javax.swing.JTree();
        jSplitPane3 = new javax.swing.JSplitPane();
        DisplayFrame = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        btnAddFrame = new javax.swing.JButton();
        btnPlayAnimation = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        frameTree = new javax.swing.JTree();
        btnRemoveFrame = new javax.swing.JButton();
        btnStopAnimation = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnSaveSkeleton = new javax.swing.JMenuItem();
        btnLoadSkeleton = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(390);
        jSplitPane2.setResizeWeight(1.0);

        jSplitPane1.setDividerLocation(282);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);

        jPanel2.setMaximumSize(new java.awt.Dimension(225, 150));

        btnAddBone.setText("Add Bone");
        btnAddBone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBoneActionPerformed(evt);
            }
        });

        btnRemoveBone.setText("Remove Bone");
        btnRemoveBone.setName(""); // NOI18N
        btnRemoveBone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveBoneActionPerformed(evt);
            }
        });

        btnSetParent.setText("Set Parent");
        btnSetParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetParentActionPerformed(evt);
            }
        });

        btnResetParent.setText("Reset Parent");
        btnResetParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetParentActionPerformed(evt);
            }
        });

        btnRenameBone.setText("Rename bone");
        btnRenameBone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameBoneActionPerformed(evt);
            }
        });

        btnRotateBone.setText("Rotate Bone");
        btnRotateBone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotateBoneActionPerformed(evt);
            }
        });

        btnSetImage.setText("Set Image");
        btnSetImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSetImage)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRenameBone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRotateBone))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddBone)
                            .addComponent(btnSetParent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetParent, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRemoveBone, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddBone)
                    .addComponent(btnRemoveBone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnResetParent)
                    .addComponent(btnSetParent, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRotateBone)
                    .addComponent(btnRenameBone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSetImage))
        );

        jSplitPane1.setRightComponent(jPanel2);

        boneTree.setMaximumSize(new java.awt.Dimension(100, 68));
        boneTree.setPreferredSize(new java.awt.Dimension(100, 400));
        jScrollPane1.setViewportView(boneTree);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jSplitPane2.setRightComponent(jSplitPane1);

        jSplitPane3.setDividerLocation(350);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setResizeWeight(1.0);

        DisplayFrame.setVisible(true);

        javax.swing.GroupLayout DisplayFrameLayout = new javax.swing.GroupLayout(DisplayFrame.getContentPane());
        DisplayFrame.getContentPane().setLayout(DisplayFrameLayout);
        DisplayFrameLayout.setHorizontalGroup(
            DisplayFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );
        DisplayFrameLayout.setVerticalGroup(
            DisplayFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
        );

        jSplitPane3.setTopComponent(DisplayFrame);

        btnAddFrame.setText("Add Frame");
        btnAddFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFrameActionPerformed(evt);
            }
        });

        btnPlayAnimation.setText("Play Animation");
        btnPlayAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayAnimationActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(frameTree);

        btnRemoveFrame.setText("Remove Frame");
        btnRemoveFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFrameActionPerformed(evt);
            }
        });

        btnStopAnimation.setText("Stop Animation");
        btnStopAnimation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopAnimationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddFrame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoveFrame)
                .addGap(18, 18, 18)
                .addComponent(btnStopAnimation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlayAnimation)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFrame)
                    .addComponent(btnPlayAnimation)
                    .addComponent(btnRemoveFrame)
                    .addComponent(btnStopAnimation)))
        );

        jSplitPane3.setRightComponent(jPanel1);

        jSplitPane2.setLeftComponent(jSplitPane3);

        jMenu1.setText("File");

        btnSaveSkeleton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        btnSaveSkeleton.setText("Save Skeleton");
        btnSaveSkeleton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSkeletonActionPerformed(evt);
            }
        });
        jMenu1.add(btnSaveSkeleton);

        btnLoadSkeleton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        btnLoadSkeleton.setText("Load Skeleton");
        btnLoadSkeleton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadSkeletonActionPerformed(evt);
            }
        });
        jMenu1.add(btnLoadSkeleton);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddBoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBoneActionPerformed
        String boneName = "Bone 0";

        for (int i = 0; skeleton.getBone(boneName) != null; i++) {
            boneName = "Bone " + i;
        }

        Bone bone = new Bone(boneName, Bone.DEFAULT_IMAGE_PATH, 0);

        for (KeyFrame frame : Animation.keyFrameList) {
            frame.getSkeleton().boneList.add(bone);
        }

        skeleton.boneList.add(bone);
        updateBoneTree();
    }//GEN-LAST:event_btnAddBoneActionPerformed

    private void btnRemoveBoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveBoneActionPerformed
        Bone bone = getSelectedBone();

        if (bone != null) {
            for (KeyFrame frame : Animation.keyFrameList) {
                frame.getSkeleton().removeBone(bone.name);
            }

            skeleton.removeBone(bone.name);
            updateBoneTree();
        }
    }//GEN-LAST:event_btnRemoveBoneActionPerformed

    private void btnSetParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetParentActionPerformed
        Bone child = getSelectedBone();

        if (child != null) {
            Bone oldParent = child.getParent(skeleton);

            String parentName;

            if (oldParent != null) {
                parentName = JOptionPane.showInputDialog("Enter Parent Name:", oldParent.name);
            } else {
                parentName = JOptionPane.showInputDialog("Enter Parent Name:", "");
            }

            Bone parent = skeleton.getBone(parentName);

            if (parentName != null) {
                if (parent == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Could not find Bone with name \"" + parentName + "\"!");
                } else if (parent.name.equals(child.name)) {
                    JOptionPane.showMessageDialog(null, "Error: A Bone cannot be it's own parent!");
                } else if (child.getDescendant(parentName) != null) {
                    JOptionPane.showMessageDialog(null, "Error: Parent cannot be descendant of child!");
                } else {
                    for (KeyFrame frame : Animation.keyFrameList) {
                        Bone frameChild = frame.getSkeleton().getBone(child.name);
                        frame.getSkeleton().removeBone(frameChild.name);
                        frame.getSkeleton().getBone(parent.name).childList.add(frameChild);
                    }

                    skeleton.removeBone(child.name);
                    parent.childList.add(child);
                    updateBoneTree();
                }
            }
        }
    }//GEN-LAST:event_btnSetParentActionPerformed

    private void btnResetParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetParentActionPerformed
        Bone bone = getSelectedBone();

        if (bone != null) {
            for (KeyFrame frame : Animation.keyFrameList) {
                Bone frameBone = frame.getSkeleton().getBone(bone.name);

                frame.getSkeleton().removeBone(bone.name);
                frame.getSkeleton().boneList.add(frameBone);
            }

            skeleton.removeBone(bone.name);
            skeleton.boneList.add(bone);
            updateBoneTree();
        }
    }//GEN-LAST:event_btnResetParentActionPerformed

    private void btnSaveSkeletonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSkeletonActionPerformed
        HashMap<String, Object> fileMap = new HashMap<>();
        ArrayList<HashMap<String, Object>> boneList = skeleton.getBoneList();

        fileMap.put("Skeleton", boneList);

        HashMap<Integer, ArrayList<HashMap<String, Object>>> animationMap = new HashMap<>();

        for (KeyFrame frame : Animation.keyFrameList) {
            animationMap.put(frame.getTime(), frame.getSkeleton().getBoneList());
        }

        fileMap.put("Animation", animationMap);

        Yaml yaml = new Yaml();
        String contents = yaml.dump(fileMap);
        saveYaml("test.skeleton", contents);
    }//GEN-LAST:event_btnSaveSkeletonActionPerformed

    private void btnLoadSkeletonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadSkeletonActionPerformed
        JFileChooser chooser = new JFileChooser();
        File file = new File("src/main/resources/");
        chooser.setCurrentDirectory(file);
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getPath();
            try (FileReader fileReader = new FileReader(path)) {
                Yaml yaml = new Yaml();
                HashMap<String, Object> fileMap = (HashMap<String, Object>) yaml.load(fileReader);
                ArrayList<HashMap<String, Object>> boneList = (ArrayList<HashMap<String, Object>>) fileMap.get("Skeleton");
                skeleton.setBoneList(boneList);

                HashMap<Integer, ArrayList> frameMap = (HashMap<Integer, ArrayList>) fileMap.get("Animation");
                Animation.keyFrameList.clear();

                for (int frameTime : frameMap.keySet()) {
                    Skeleton frameSkeleton = new Skeleton();
                    frameSkeleton.setBoneList(frameMap.get(frameTime));
                    KeyFrame frame = new KeyFrame(frameTime, frameSkeleton);
                    Animation.addKeyFrame(frame);
                }

                updateBoneTree();
                updateFrameTree();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }//GEN-LAST:event_btnLoadSkeletonActionPerformed

    private void btnRenameBoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameBoneActionPerformed
        Bone bone = getSelectedBone();

        if (bone != null) {
            String newName = JOptionPane.showInputDialog("Enter New Name:", bone.name);

            if (skeleton.getBone(newName) == null && newName != null) {
                for (KeyFrame frame : Animation.keyFrameList) {
                    frame.getSkeleton().getBone(bone.name).name = newName;
                }

                bone.name = newName;
            }

            updateBoneTree();
        }
    }//GEN-LAST:event_btnRenameBoneActionPerformed

    private void btnRotateBoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotateBoneActionPerformed
        Bone bone = getSelectedBone();

        if (bone != null) {
            String input = JOptionPane.showInputDialog("Enter New Direction (Degrees):", bone.dir);
            try {
                int newDirection = Integer.parseInt(input);
                bone.setDirection(newDirection);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Invalid Input!");
            }
        }
    }//GEN-LAST:event_btnRotateBoneActionPerformed

    private void btnSetImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetImageActionPerformed
        Bone bone = getSelectedBone();

        if (bone != null) {
            JFileChooser chooser = new JFileChooser();
            File file = new File("src/main/resources/data/");
            chooser.setCurrentDirectory(file);
            int result = chooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getPath();

                for (KeyFrame frame : Animation.keyFrameList) {
                    frame.getSkeleton().getBone(bone.name).setImage(path);
                }

                bone.setImage(path);
            }
        }
    }//GEN-LAST:event_btnSetImageActionPerformed

    private void btnAddFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFrameActionPerformed
        String input = JOptionPane.showInputDialog("Enter Frame Position (MiliSeconds):");
        try {
            int frameTime = Integer.parseInt(input);

            if (Animation.getKeyFrame(frameTime) == null) {
                Skeleton newSkeleton = new Skeleton();
                newSkeleton.setBoneList(skeleton.getBoneList());

                KeyFrame frame = new KeyFrame(frameTime, newSkeleton);
                Animation.addKeyFrame(frame);
            } else {
                JOptionPane.showMessageDialog(null, "Error: Frame already exists for given time!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid Input!");
        }

        updateFrameTree();
    }//GEN-LAST:event_btnAddFrameActionPerformed

    private void btnPlayAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayAnimationActionPerformed
        isPlayingAnimation = true;
        startTime = System.currentTimeMillis();
    }//GEN-LAST:event_btnPlayAnimationActionPerformed

    private void btnRemoveFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFrameActionPerformed
        KeyFrame frame = getSelectedKeyFrame();

        Animation.keyFrameList.remove(frame);
        updateFrameTree();
    }//GEN-LAST:event_btnRemoveFrameActionPerformed

    private void btnStopAnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopAnimationActionPerformed
        isPlayingAnimation = false;
        startTime = System.currentTimeMillis();
    }//GEN-LAST:event_btnStopAnimationActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnimationEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnimationEditorGUI().setVisible(true);
            }
        });
    }

    /**
     * Gets the time in milliseconds since the animation started playing.
     *
     * @return The time in milliseconds since the animation started playing.
     */
    public static long getTime() {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * Resets the startTime to the current time.
     */
    public static void resetTime() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Redraws the Skeleton.
     */
    public static void redrawSkeleton() {
        JInternalFrame frame = DisplayFrame;
        frame.setContentPane(skeletonPanel);
        frame.setVisible(true);
    }

    /**
     * Updates the GUI Bone List so that the structure matches the Skeleton
     * object.
     */
    private void updateBoneTree() {
        DefaultTreeModel model = (DefaultTreeModel) boneTree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        root.removeAllChildren();

        for (Bone bone : skeleton.boneList) {
            DefaultMutableTreeNode node = bone.getTreeBranch();
            root.add(node);
        }

        model.nodeStructureChanged(root);

        for (int i = 0; i < boneTree.getRowCount(); i++) {
            boneTree.expandRow(i);
        }
    }

    /**
     * Updates the GUI KeyFrame List so that it matches the Animation object.
     */
    private void updateFrameTree() {
        DefaultTreeModel model = (DefaultTreeModel) frameTree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        root.removeAllChildren();

        for (KeyFrame keyFrame : Animation.keyFrameList) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(keyFrame.getTime());
            root.add(node);
        }

        model.nodeStructureChanged(root);

        for (int i = 0; i < frameTree.getRowCount(); i++) {
            frameTree.expandRow(i);
        }
    }

    /**
     * Saves a String to a Yaml file.
     *
     * @param fileName The name of the saved file.
     * @param fileContents The contents to save to the file.
     */
    private void saveYaml(String fileName, String fileContents) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                bufferedWriter.write(fileContents);

                bufferedWriter.close();
                fileWriter.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Gets the Bone currently selected in the GUI Bone List.
     *
     * @return The selected Bone. Returns null if no Bone is selected.
     */
    private Bone getSelectedBone() {
        if (boneTree.getLastSelectedPathComponent() != null) {
            String selectedBoneName = boneTree.getLastSelectedPathComponent().toString();
            Bone bone = skeleton.getBone(selectedBoneName);

            if (bone == null) {
                JOptionPane.showMessageDialog(null, "Error: No bone selected!");
            }

            return bone;
        }

        JOptionPane.showMessageDialog(null, "Error: No bone selected!");
        return null;
    }

    /**
     * Gets the KeyFrame currently selected in the GUI frame list.
     *
     * @return The selected KeyFrame. Returns null if no KeyFrame is selected.
     */
    private KeyFrame getSelectedKeyFrame() {
        if (frameTree.getLastSelectedPathComponent() != null) {
            String name = frameTree.getLastSelectedPathComponent().toString();
            KeyFrame frame = null;

            try {
                int time = Integer.parseInt(name);
                frame = Animation.getKeyFrame(time);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: No frame selected!");
            }

            return frame;
        }

        JOptionPane.showMessageDialog(null, "Error: No frame selected!");
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JInternalFrame DisplayFrame;
    private javax.swing.JTree boneTree;
    private javax.swing.JButton btnAddBone;
    private javax.swing.JButton btnAddFrame;
    private javax.swing.JMenuItem btnLoadSkeleton;
    private javax.swing.JButton btnPlayAnimation;
    private javax.swing.JButton btnRemoveBone;
    private javax.swing.JButton btnRemoveFrame;
    private javax.swing.JButton btnRenameBone;
    private javax.swing.JButton btnResetParent;
    private javax.swing.JButton btnRotateBone;
    private javax.swing.JMenuItem btnSaveSkeleton;
    private javax.swing.JButton btnSetImage;
    private javax.swing.JButton btnSetParent;
    private javax.swing.JButton btnStopAnimation;
    private javax.swing.JTree frameTree;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    // End of variables declaration//GEN-END:variables
}

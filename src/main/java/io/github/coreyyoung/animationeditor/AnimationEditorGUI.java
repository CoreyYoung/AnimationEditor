package io.github.coreyyoung.animationeditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.yaml.snakeyaml.Yaml;

public class AnimationEditorGUI extends Application {

    private static long startTime;

    public static boolean isPlayingAnimation = false;
    public static AnimationEditorGUI self;
    public static Skeleton skeleton;
    public static Animation animation;

    private static TreeView<String> skeletonTree;
    private static TreeView<String> animationTree;

    private static final CanvasPane canvasPane = new CanvasPane();

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        //skeleton = loadSkeleton();
        animation = loadAnimation();

        stage.setTitle("AnimationEditor");

        if (skeleton != null) {
            skeletonTree = skeleton.getTree();
        } else {
            skeletonTree = new TreeView<>();
        }

        animationTree = animation.getTree();

        MenuItem fileLoadSkeleton = new MenuItem("Load Skeleton");
        fileLoadSkeleton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (event.getEventType() == ActionEvent.ACTION) {
                    try {
                        skeleton = loadSkeleton();
                        skeletonTree = skeleton.getTree();
                    } catch (FileNotFoundException e) {
                        System.err.print(e);
                    }
                }
            }
        });

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(fileLoadSkeleton);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        BorderPane border = new BorderPane();
        border.setTop(menuBar);
        border.setLeft(skeletonTree);
        border.setCenter(canvasPane);
        border.setRight(animationTree);

        Scene scene = new Scene(border, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
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
        //JInternalFrame frame = DisplayFrame;
        //frame.setContentPane(skeletonPanel);
        //frame.setVisible(true);
    }

    /**
     * Updates the GUI Bone List so that the structure matches the Skeleton
     * object.
     */
    private void updateBoneTree() {
        //DefaultTreeModel model = (DefaultTreeModel) boneTree.getModel();
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        //root.removeAllChildren();
        //for (Bone bone : skeleton.boneList) {
        //    DefaultMutableTreeNode node = bone.getTreeBranch();
        //    root.add(node);
        //}
        //model.nodeStructureChanged(root);
        //for (int i = 0; i < boneTree.getRowCount(); i++) {
        //    boneTree.expandRow(i);
        ///}
    }

    /**
     * Updates the GUI KeyFrame List so that it matches the Animation object.
     */
    private void updateFrameTree() {
        //DefaultTreeModel model = (DefaultTreeModel) frameTree.getModel();
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

//        root.removeAllChildren();
//
//        for (KeyFrame keyFrame : animation.keyFrameList) {
//            DefaultMutableTreeNode node = new DefaultMutableTreeNode(keyFrame.getTime());
//            root.add(node);
//        }
//
//        model.nodeStructureChanged(root);
//
//        for (int i = 0; i < frameTree.getRowCount(); i++) {
//            frameTree.expandRow(i);
//        }
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
//        if (boneTree.getLastSelectedPathComponent() != null) {
//            String selectedBoneName = boneTree.getLastSelectedPathComponent().toString();
//            Bone bone = skeleton.getBone(selectedBoneName);
//
//            if (bone == null) {
//                JOptionPane.showMessageDialog(null, "Error: No bone selected!");
//            }
//
//            return bone;
//        }
//
//        JOptionPane.showMessageDialog(null, "Error: No bone selected!");
        return null;
    }

    /**
     * Gets the KeyFrame currently selected in the GUI frame list.
     *
     * @return The selected KeyFrame. Returns null if no KeyFrame is selected.
     */
    private KeyFrame getSelectedKeyFrame() {
//        if (frameTree.getLastSelectedPathComponent() != null) {
//            String name = frameTree.getLastSelectedPathComponent().toString();
//            KeyFrame frame = null;
//
//            try {
//                int time = Integer.parseInt(name);
//                frame = animation.getKeyFrame(time);
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(null, "Error: No frame selected!");
//            }
//
//            return frame;
//        }

        //JOptionPane.showMessageDialog(null, "Error: No frame selected!");
        return null;
    }

    private static Skeleton loadSkeleton() throws FileNotFoundException {
        File file = new File("src/main/resources/test.skeleton");
        FileReader fileReader = new FileReader(file);

        Yaml yaml = new Yaml();
        HashMap<String, Object> fileMap = (HashMap<String, Object>) yaml.load(fileReader);
        ArrayList<HashMap<String, Object>> boneList = (ArrayList<HashMap<String, Object>>) fileMap.get("Skeleton");

        Skeleton skeleton = new Skeleton();
        skeleton.setBoneList(boneList);

        return skeleton;
    }

    private static Animation loadAnimation() throws FileNotFoundException {
        File file = new File("src/main/resources/test.animation");
        FileReader fileReader = new FileReader(file);

        Yaml yaml = new Yaml();
        HashMap<Integer, HashMap<String, Integer>> fileMap = (HashMap<Integer, HashMap<String, Integer>>) yaml.load(fileReader);

        Animation animation = new Animation();
        animation.setKeyFrameList(fileMap);

        return animation;
    }
}

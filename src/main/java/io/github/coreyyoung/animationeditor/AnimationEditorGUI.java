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
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.yaml.snakeyaml.Yaml;

public class AnimationEditorGUI extends Application {
    
    private static long startTime;
    
    public static boolean isPlayingAnimation = false;
    public static AnimationEditorGUI self;
    public static Skeleton skeleton;
    public static Animation animation;
    
    private static final BorderPane border = new BorderPane();
    private static final CanvasPane canvasPane = new CanvasPane();
    private static final BorderPane leftPane = new BorderPane();
    private static final FlowPane skeletonButtonPane = new FlowPane();
    
    private static TreeView<String> skeletonTree = new TreeView<>();
    private static TreeView<String> animationTree = new TreeView<>();
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        stage.setTitle("Animation Editor");
        stage.setMaximized(true);
        
        if (skeleton != null) {
            updateSkeletonTree();
        }
        
        if (animation != null) {
            updateAnimationTree();
        }
        
        MenuItem fileLoadSkeleton = new MenuItem("Load Skeleton");
        MenuItem fileLoadAnimation = new MenuItem("Load Animation");
        
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(fileLoadSkeleton, fileLoadAnimation);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);
        
        final int leftWidth = 240;
        final int buttonSize = leftWidth / 2;
        
        Button btnAddBone = new Button("Add Bone");
        Button btnRemoveBone = new Button("Remove Bone");
        Button btnRenameBone = new Button("Rename Bone");
        Button btnSetParent = new Button("Set Parent");
        Button btnSetImage = new Button("Set Image");
        Button btnSetRotation = new Button("Set Rotation");
        
        btnAddBone.setPrefWidth(buttonSize);
        btnRemoveBone.setPrefWidth(buttonSize);
        btnRenameBone.setPrefWidth(buttonSize);
        btnSetParent.setPrefWidth(buttonSize);
        btnSetImage.setPrefWidth(buttonSize);
        btnSetRotation.setPrefWidth(buttonSize);
        
        Button button = new Button();
        button.setPrefWidth(buttonSize);
        
        skeletonButtonPane.setPrefWidth(buttonSize * 2);
        skeletonButtonPane.getChildren().addAll(
                btnAddBone,
                btnRemoveBone,
                btnRenameBone,
                btnSetParent,
                btnSetImage,
                btnSetRotation);
        
        leftPane.setPrefWidth(leftWidth);
        leftPane.setCenter(skeletonTree);
        leftPane.setBottom(skeletonButtonPane);
        
        border.setTop(menuBar);
        border.setLeft(leftPane);
        border.setCenter(canvasPane);
        border.setRight(animationTree);

        //Size params set default for unmaximised window, but don't affect maximised window.
        Scene scene = new Scene(border, 800, 600);
        stage.setScene(scene);
        stage.show();
        
        fileLoadSkeleton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (event.getEventType() == ActionEvent.ACTION) {
                    try {
                        skeleton = loadSkeleton();
                        updateSkeletonTree();
                    } catch (FileNotFoundException e) {
                        System.err.print(e);
                    }
                }
            }
        });
        
        fileLoadAnimation.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (event.getEventType() == ActionEvent.ACTION) {
                    try {
                        animation = loadAnimation();
                        updateAnimationTree();
                    } catch (FileNotFoundException e) {
                        System.err.print(e);
                    }
                }
            }
        });
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
     * Updates the GUI Skeleton Tree so that the structure matches the Skeleton
     * object.
     */
    private void updateSkeletonTree() {
        if (skeleton != null) {
            skeletonTree = skeleton.getTree();
        } else {
            skeletonTree = new TreeView<>();
        }
        
        AnimationEditorGUI.leftPane.setCenter(skeletonTree);
    }

    /**
     * Updates the GUI Animation Tree so that it matches the Animation object.
     */
    private void updateAnimationTree() {
        if (animation != null) {
            animationTree = animation.getTree();
        } else {
            animationTree = new TreeView<>();
        }
        
        border.setRight(animationTree);
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
        return null;
    }

    /**
     * Gets the KeyFrame currently selected in the GUI frame list.
     *
     * @return The selected KeyFrame. Returns null if no KeyFrame is selected.
     */
    private KeyFrame getSelectedKeyFrame() {
        return null;
    }

    /**
     * Loads a Skeleton object from a file.
     *
     * @return The loaded Skeleton Object.
     * @throws FileNotFoundException
     */
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

    /**
     * Loads an Animation object from a file.
     *
     * @return The loaded Animation object.
     * @throws FileNotFoundException
     */
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

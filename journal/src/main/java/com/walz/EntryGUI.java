package com.walz;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * Defines the layout and styling for the journal entry creation screen.
 * This class uses a split-pane design to separate structured items (gratitudes and tasks)
 * from free-form writing (thoughts).
 */
public class EntryGUI {
    /** The main container for the entry screen. */
    private BorderPane rootLayout = new BorderPane();

    /** The label identifying the gratitude section. */
    private Label gratitudeHeader = new Label();

    /** Placeholder for gratitude input (deprecated by dynamic area in EntryController). */
    private TextArea gratitudeInput = new TextArea();

    /** Input field for daily tasks or to-do items. */
    private TextArea tasksInput = new TextArea();

    /** Input field for open-ended journaling and reflections. */
    private TextArea thoughtsInput = new TextArea();

    /** Button to save the current entry using Unicode Floppy Disk. */
    private Button saveBtn = new Button("\uD83d\uDCBE");

    /** Button to cancel and exit the entry screen using Unicode Heavy (big) X. */
    private Button cancelBtn = new Button("\u2716");

    /** Container where gratitude prompts are dynamically injected. */
    private VBox gratitudeArea = new VBox();

    /**
     * Initializes the UI components and creates a two-column layout.
     */
    public EntryGUI() {
        // Left Column: Gratitudes and Tasks
        VBox vboxLeft = new VBox();
        vboxLeft.setSpacing(15);

        Label tasksHeader = new Label("Tasks");
        tasksHeader.setStyle("-fx-font-family: 'Tahoma'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-font-size: 22;");
                                   
        gratitudeHeader.setText("Gratitudes");
        
        gratitudeHeader.setStyle("-fx-font-family: 'Tahoma'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-font-size: 22;");       
        
        vboxLeft.getChildren().addAll(gratitudeHeader, gratitudeArea, tasksHeader, tasksInput);

        // Right Column: General Thoughts
        VBox vboxRight = new VBox();
        vboxRight.setSpacing(15);

        Label thoughtsHeader = new Label("Thoughts");

        thoughtsHeader.setStyle("-fx-font-family: 'Tahoma'; "
                           + "-fx-font-weight: bold; "
                           + "-fx-font-size: 22;");

        vboxRight.getChildren().addAll(thoughtsHeader, thoughtsInput);

        
        tasksInput.setWrapText(true);
        thoughtsInput.setWrapText(true);

        // Ensure both input columns expand vertically to fill available space.
        VBox.setVgrow(tasksInput, Priority.ALWAYS);
        VBox.setVgrow(thoughtsInput, Priority.ALWAYS);
        
        // Combine columns into a horizontal layout.
        HBox hboxCenter = new HBox();
        hboxCenter.setSpacing(10);
        hboxCenter.getChildren().addAll(vboxLeft, vboxRight);
        HBox.setHgrow(vboxLeft, Priority.ALWAYS);
        HBox.setHgrow(vboxRight, Priority.ALWAYS);
        
        // Action Buttons
        HBox hboxBtns = new HBox();
        hboxBtns.setSpacing(10);
        hboxBtns.setAlignment(Pos.CENTER);
        hboxBtns.getChildren().addAll(saveBtn, cancelBtn);
        hboxBtns.setPadding(new Insets(20, 0, 0, 0));

        rootLayout.setCenter(hboxCenter);
        rootLayout.setBottom(hboxBtns);
        rootLayout.setPadding(new Insets(20));
    }

    /** @return The root BorderPane of the entry view. */
    public BorderPane getLayout() {
        return rootLayout;
    }

    /** @return The label for the gratitude section. */
    public Label getGratitudeHeader() {
        return gratitudeHeader;
    }

    /** @return The container where prompts are dynamically added. */
    public VBox getGratitudeArea() {
        return gratitudeArea;
    }

    /** @return The primary gratitude text area. */
    public TextArea getGratitudeInput() {
        return gratitudeInput;
    }

    /** @return The text area for tasks. */
    public TextArea getTasksInput() {
        return tasksInput;
    }

    /** @return The text area for thoughts and reflections. */
    public TextArea getThoughtsInput() {
        return thoughtsInput;
    }

    /** @return The save button instance. */
    public Button getSaveBtn() {
        return saveBtn;
    }

    /** @return The cancel button instance. */
    public Button getCancelBtn() {
        return cancelBtn;
    }

}

package com.walz;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

/**
 * Provides the graphical user interface for the application settings.
 * This class uses a {@link GridPane} to arrange UI components for color selection,
 * gratitude prompt management, and file storage locations.
 */
public class ConfigGUI {
    /** The primary container for the settings layout. */
    private GridPane rootLayout = new GridPane();
    
    /** Color selection tool for foreground/text elements. */
    private ColorPicker fgColorPicker = new ColorPicker();

    /** Color selection tool for the application background. */
    private ColorPicker bgColorPicker = new ColorPicker();

    /** Color selection tool for UI highlights and accents. */
    private ColorPicker accentColorPicker = new ColorPicker();

    /** Input area for managing multiple gratitude prompts, one per line. */
    private TextArea promptList = new TextArea();

    /** Numeric selector for the number of prompts to show in a session (Range: 1-6 for display compatability). */
    private Spinner<Integer> promptCountSpinner = new Spinner<>(1,6,1);

    /** Non-editable field displaying the current save directory path. */
    private TextField saveDir = new TextField();

    /** Button triggering the directory selection dialog (Unicode: Folder). */
    private Button browseBtn = new Button("\uD83D\uDCC2");

    /** Button to persist changes to storage (Unicode: Floppy Disk). */
    private Button saveBtn = new Button("\uD83d\uDCBE");

    /** Button to exit without saving (Unicode: Heavy (Big) X). */
    private Button cancelBtn = new Button("\u2716");

    /**
     * Initializes the GUI components and arranges them within the GridPane.
     * * @param config The current configuration object used to populate initial field values.
     */
    public ConfigGUI(Config config) {

        // Center the layout and provide breathing room for the form elements.
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setPadding(new Insets(50));
        rootLayout.setHgap(15);
        rootLayout.setVgap(15);

        Label fgLabel = new Label("Text Color: ");
        Label bgLabel = new Label("Background Color: ");
        Label accentLabel = new Label("Accent Color: ");
        Label promptLabel = new Label("Gratitude Prompt(s): ");  
        Label promptCountLabel = new Label("Prompt Count: ");
        Label entrySaveLocationLabel = new Label("Entry Save Location: ");

        saveDir.setEditable(false);

        // Define column behavior: labels are right-aligned for professional look,
        // while inputs are left-aligned for natural reading flow.
        ColumnConstraints col0 = new ColumnConstraints(); //right align labels
        col0.setHalignment(HPos.RIGHT); 
        
        ColumnConstraints col1 = new ColumnConstraints(); //left align inputs
        col1.setHalignment(HPos.LEFT);

        ColumnConstraints col2 = new ColumnConstraints(); //left align button
        col2.setHalignment(HPos.LEFT);

        rootLayout.getColumnConstraints().addAll(col0, col1, col2); // apply rules to grid

        rootLayout.add(fgLabel, 0,0);
        rootLayout.add(fgColorPicker, 1,0);

        rootLayout.add(bgLabel, 0,1);
        rootLayout.add(bgColorPicker, 1,1);
        
        rootLayout.add(accentLabel, 0,2);
        rootLayout.add(accentColorPicker, 1,2);

        rootLayout.add(promptLabel, 0,3);
        rootLayout.add(promptList, 1,3);

        rootLayout.add(promptCountLabel, 0,4);
        rootLayout.add(promptCountSpinner, 1,4);

        // Limit the text area size to keep the config window compact.
        promptList.setPrefRowCount(6);
        promptList.setMaxWidth(350);
        promptList.setWrapText(true);

        rootLayout.add(entrySaveLocationLabel, 0,5);

        // Use an HBox to group the Browse button and path field together horizontally.
        HBox pathBox = new HBox(10);
        pathBox.setAlignment(Pos.CENTER_LEFT);
        pathBox.getChildren().addAll(browseBtn, saveDir);
        rootLayout.add(pathBox, 1, 5);

        browseBtn.setMinWidth(Button.USE_PREF_SIZE);
        GridPane.setColumnSpan(pathBox, 2);

        HBox.setHgrow(saveDir, Priority.ALWAYS);
        saveDir.setMaxWidth(350);

        // Center the action buttons at the bottom of the form.
        HBox hboxBtns = new HBox();
        hboxBtns.setSpacing(10);
        hboxBtns.setAlignment(Pos.CENTER);
        hboxBtns.getChildren().addAll(saveBtn, cancelBtn);

        rootLayout.add(hboxBtns, 0, 6);
        GridPane.setColumnSpan(hboxBtns,3);

        loadConfigData(config);
    }

    /**
     * Populates the GUI fields with data from a provided Config object.
     * * @param config The configuration data source.
     */
    public void loadConfigData(Config config){
        Color fgColor = Color.web(config.getFg());
        Color bgColor = Color.web(config.getBg());
        Color accentColor = Color.web(config.getAccent());

        int promptCount = config.getGratitudeCount();
        promptCountSpinner.getValueFactory().setValue(promptCount);


        fgColorPicker.setValue(fgColor);
        bgColorPicker.setValue(bgColor);
        accentColorPicker.setValue(accentColor);
        
        saveDir.setText(config.getSaveDir());

        String joinPrompts = String.join("\n", config.getGratitudePrompts());
        promptList.setText(joinPrompts);
    }

    /**
     * Gets the root layout of the GUI.
     * @return The GridPane containing all UI components.
     */
    public GridPane getLayout() {
        return rootLayout;
    }

    /**
     * Gets the foreground color picker.
     * @return The ColorPicker instance for text color.
     */
    public ColorPicker getFgColorPicker() {
        return fgColorPicker;
    }

    /**
     * Gets the background color picker.
     * @return The ColorPicker instance for background color.
     */
    public ColorPicker getBgColorPicker() {
        return bgColorPicker;
    }

    /**
     * Gets the accent color picker.
     * @return The ColorPicker instance for accent color.
     */
    public ColorPicker getAccentColorPicker() {
        return accentColorPicker;
    }

    /**
     * Gets the text area containing gratitude prompts.
     * @return The TextArea instance.
     */
    public TextArea getPromptList() {
        return promptList;
    }

    /**
     * Gets the spinner used to select the prompt count.
     * @return The Spinner instance.
     */
    public Spinner<Integer> getPromptCount() {

        return promptCountSpinner;
    }

    /**
     * Gets the text field displaying the save directory.
     * @return The TextField instance.
     */
    public TextField getSaveDir() {
        return saveDir;
    }

    /**
     * Gets the browse button for directory selection.
     * @return The Button instance.
     */
    public Button getBrowseBtn() {
        return browseBtn;
    }

    /**
     * Gets the button used to save settings.
     * @return The Button instance.
     */
    public Button getSaveBtn() {
        return saveBtn;
    }

    /**
     * Gets the button used to cancel changes.
     * @return The Button instance.
     */
    public Button getCancelBtn() {
        return cancelBtn;
    }
}

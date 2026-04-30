package com.walz;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;


/**
 * Controller for the configuration view that handles user interactions 
 * for application settings. It bridges the {@link ConfigGUI} and the 
 * {@link StorageManager} to persist user preferences.
 */
public class ConfigController {

    /** The graphical interface for settings. */
    private ConfigGUI view;
    
    /** The main application instance used for scene switching. */
    private JournalApp app;

    /** The manager responsible for loading and saving configuration data. */
    private StorageManager storage;

    /**
     * Constructs a new ConfigController and initializes button event listeners.
     * @param view    The GUI view to manage.
     * @param app     The main application context.
     * @param storage The data persistence manager.
     */
    public ConfigController(ConfigGUI view, JournalApp app, StorageManager storage) {
        this.view = view;
        this.app = app;
        this.storage = storage;

        // Set up action listeners for the view buttons
        view.getBrowseBtn().setOnAction(e -> openDirectoryChooser());
        view.getCancelBtn().setOnAction(e -> cancel());
        view.getSaveBtn().setOnAction(e -> saveSettings());
    }

    /**
     * Transitions the application back to the main home view.
     */
    public void returnToMain() {
        MainGUI mainView = new MainGUI();
        new MainController(mainView, app, this.storage);

        app.switchScene(mainView.getLayout());
    }

    /**
     * Collects data from the UI components, updates the configuration object,
     * and persists it to local storage.
     */
    public void saveSettings() {
        Config currentConfig = storage.loadConfig();

        // JavaFX ColorPicker.getValue().toString() returns "0xRRGGBB".
        // We replace "0x" with "#" to maintain standard hex compatibility.
        currentConfig.setFg(view.getFgColorPicker().getValue().toString().replace("0x", "#"));
        currentConfig.setBg(view.getBgColorPicker().getValue().toString().replace("0x", "#"));
        currentConfig.setAccent(view.getAccentColorPicker().getValue().toString().replace("0x", "#"));
        
        // Splits the text area content into a list, by line breaks. 
        // does not currently trim whitespace or filter empty lines.
        List<String> updatedPrompts = Arrays.asList(view.getPromptList().getText().split("\n"));
        currentConfig.setGratitudePrompts(updatedPrompts);

        currentConfig.setSaveDir(view.getSaveDir().getText());
        currentConfig.setGratitudeCount(view.getPromptCount().getValue());

        storage.saveConfig(currentConfig);

        returnToMain();
    }

    /**
     * Prompts the user with a confirmation dialog before discarding 
     * unsaved changes and returning to the main screen.
     */
    public void cancel() {
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setContentText("Are you sure you want to discard your changes?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMain();
        }    
    }

    /**
     * Loads the existing settings from storage and populates the UI fields.
     */
    public void loadCurrentSettings() {
        Config currentConfig = storage.loadConfig();
        view.loadConfigData(currentConfig);
    }

    /**
     * Opens a native directory selection dialog. If the current directory 
     * in the text field is invalid, it defaults to the user's working directory.
     */
    private void openDirectoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Save Location");

        // Validate the path currently in the text field before using it as the start location
        File initialDir = new File(view.getSaveDir().getText());
        if (!initialDir.exists() || !initialDir.isDirectory()) {
            initialDir = new File(System.getProperty("user.dir"));
        }
        if (initialDir.exists()) {
            directoryChooser.setInitialDirectory(initialDir);
        }

        Window stage = view.getLayout().getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            // Normalizing backslashes to forward slashes for cross-platform path consistency (for my Windows PC and Macbook).
            String newPath = selectedDirectory.getAbsolutePath().replace("\\", "/");
            
            // Ensure the path ends with a trailing slash to avoid file concatenation errors later.
            if (!newPath.endsWith("/")) {
                newPath += "/";
            }
            view.getSaveDir().setText(newPath);
        }
    }
}
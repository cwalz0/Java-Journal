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

public class ConfigController {

    private ConfigGUI view;
    private JournalApp app;
    private StorageManager storage;

    public ConfigController(ConfigGUI view, JournalApp app, StorageManager storage) {
        this.view = view;
        this.app = app;
        this.storage = storage;

        view.getBrowseBtn().setOnAction(e -> openDirectoryChooser());
        view.getCancelBtn().setOnAction(e -> cancel());
        view.getSaveBtn().setOnAction(e -> saveSettings());

    }

    public void returnToMain() {
        MainGUI mainView = new MainGUI();
        new MainController(mainView, app, this.storage);

        app.switchScene(mainView.getLayout());
    }


    public void saveSettings() {
        Config currentConfig = storage.loadConfig();
        currentConfig.setFg(view.getFgColorPicker().getValue().toString().replace("0x", "#"));
        currentConfig.setBg(view.getBgColorPicker().getValue().toString().replace("0x", "#"));
        currentConfig.setAccent(view.getAccentColorPicker().getValue().toString().replace("0x", "#"));
        List<String> updatedPrompts = Arrays.asList(view.getPromptList().getText().split("\n"));
        currentConfig.setGratitudePrompts(updatedPrompts);
        currentConfig.setSaveDir(view.getSaveDir().getText());
        currentConfig.setGratitudeCount(view.getPromptCount().getValue());

        storage.saveConfig(currentConfig);

        returnToMain();
    }

    public void cancel() {
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setContentText("Are you sure you want to discard your changes?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMain();
        }    
    }

    public void loadCurrentSettings() {
        Config currentConfig = storage.loadConfig();
        view.loadConfigData(currentConfig);
    }

    private void openDirectoryChooser() {
        // todo: initialize DirectoryChooser and set initial directory
        // todo: show dialog and get the returned location
        // todo: if there is a location, update the save directory
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Save Location");

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
            String newPath = selectedDirectory.getAbsolutePath().replace("\\", "/");
            if (!newPath.endsWith("/")) {
                newPath += "/";
            }
            view.getSaveDir().setText(newPath);
        }
    }
}
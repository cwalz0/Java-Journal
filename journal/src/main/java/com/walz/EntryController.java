package com.walz;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class EntryController {

    private EntryGUI view;
    private JournalApp app;
    private StorageManager storage;
    private Config config;
    
    private List<TextArea> gratitudeInputs = new ArrayList<>();
    private List<String> prompts = new ArrayList<>();

    public EntryController(EntryGUI view, JournalApp app, StorageManager storage) {
        this.view = view;
        this.app = app;
        this.storage = storage;
        this.config = storage.loadConfig();

        List<String> shuffledPrompts = new ArrayList<>(config.getGratitudePrompts());
        Collections.shuffle(shuffledPrompts);

        int count = config.getGratitudeCount();

        for (int i = 0; i < count; i++) {
            TextArea newGratitudeInput = new TextArea();
            newGratitudeInput.setPrefRowCount(1);
            newGratitudeInput.setWrapText(true);
            gratitudeInputs.add(newGratitudeInput);

            Label newGratitudeHeader = new Label(shuffledPrompts.get(i));
            newGratitudeHeader.setWrapText(true);
            newGratitudeHeader.setStyle("-fx-font-family: 'Tahoma'; "
                                      + "-fx-font-weight: bold; "
                                      + "-fx-font-size: 16;");

            prompts.add(shuffledPrompts.get(i));

            view.getGratitudeArea().getChildren().addAll(newGratitudeHeader, newGratitudeInput);
        }
        view.getSaveBtn().setOnAction(e -> saveEntry());
        view.getCancelBtn().setOnAction(e -> cancel());
    }

    public void returnToMain() {
        MainGUI mainView = new MainGUI();
        new MainController(mainView, app, this.storage);

        app.switchScene(mainView.getLayout());
    }

    public void saveEntry() {
        // todo: scrape text from gratitudes, tasks and thoughts.
        List<String> gratitudes = new ArrayList<>();
        for (TextArea gratitudeInput : gratitudeInputs) {
            String gratitude = gratitudeInput.getText();
            gratitudes.add(gratitude);
        }
        List<String> tasks = Arrays.asList(view.getTasksInput().getText().split("\n"));
        String thoughts = view.getThoughtsInput().getText();
        JournalEntry entry = new JournalEntry(gratitudes, tasks, thoughts, prompts);


        storage.saveJournalEntry(entry, config.getSaveDir());

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
}

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

/**
 * Handles the logic for creating and saving journal entries. 
 * This controller manages the dynamic generation of gratitude prompts 
 * based on user configuration and handles data persistence.
 */
public class EntryController {
    /** The graphical interface for creating an entry. */
    private EntryGUI view;

    /** The main application instance for scene navigation. */
    private JournalApp app;

    /** The storage manager for saving the completed entry. */
    private StorageManager storage;

    /** The configuration settings used to determine prompt counts and save paths. */
    private Config config;
    
    /** A list of text areas generated dynamically for gratitude responses. */
    private List<TextArea> gratitudeInputs = new ArrayList<>();

    /** The specific prompts selected for the current entry session. */
    private List<String> prompts = new ArrayList<>();

    /**
     * Constructs the controller and dynamically populates the view with shuffled gratitude prompts.
     * @param view    The GUI view to manage.
     * @param app     The main application context.
     * @param storage The data persistence manager.
     */
    public EntryController(EntryGUI view, JournalApp app, StorageManager storage) {
        this.view = view;
        this.app = app;
        this.storage = storage;
        this.config = storage.loadConfig();

        // Create a copy of the prompts to shuffle, ensuring variety without 
        // modifying the master list in the config object.
        List<String> shuffledPrompts = new ArrayList<>(config.getGratitudePrompts());
        Collections.shuffle(shuffledPrompts);

        int count = config.getGratitudeCount();

        // Dynamically build the gratitude section based on user preferences.
        for (int i = 0; i < count; i++) {
            TextArea newGratitudeInput = new TextArea();
            newGratitudeInput.setPrefRowCount(1);
            newGratitudeInput.setWrapText(true);
            gratitudeInputs.add(newGratitudeInput);

            Label newGratitudeHeader = new Label(shuffledPrompts.get(i));
            newGratitudeHeader.setWrapText(true);
            // Inline styling is used here to emphasize the prompt questions.
            newGratitudeHeader.setStyle("-fx-font-family: 'Tahoma'; "
                                      + "-fx-font-weight: bold; "
                                      + "-fx-font-size: 16;");

            prompts.add(shuffledPrompts.get(i));

            // Inject the new header and input directly into the view's VBox.
            view.getGratitudeArea().getChildren().addAll(newGratitudeHeader, newGratitudeInput);
        }
        view.getSaveBtn().setOnAction(e -> saveEntry());
        view.getCancelBtn().setOnAction(e -> cancel());
    }

    /**
     * Redirects the user back to the main home menu.
     */
    public void returnToMain() {
        MainGUI mainView = new MainGUI();
        new MainController(mainView, app, this.storage);

        app.switchScene(mainView.getLayout());
    }

    /**
     * Collects all text data from the UI (gratitudes, tasks, and thoughts)
     * and saves it as a new {@link JournalEntry}.
     */
    public void saveEntry() {
        // todo: scrape text from gratitudes, tasks and thoughts.
        List<String> gratitudes = new ArrayList<>();
        for (TextArea gratitudeInput : gratitudeInputs) {
            String gratitude = gratitudeInput.getText();
            gratitudes.add(gratitude);
        }

        // Split tasks by newline to maintain a list structure.
        List<String> tasks = Arrays.asList(view.getTasksInput().getText().split("\n"));
        String thoughts = view.getThoughtsInput().getText();
        JournalEntry entry = new JournalEntry(gratitudes, tasks, thoughts, prompts);

        // Utilize the save directory defined in the user's config settings.
        storage.saveJournalEntry(entry, config.getSaveDir());

        returnToMain();
    }

    /**
     * Prompts the user for confirmation before discarding the current entry.
     */
    public void cancel() {
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setContentText("Are you sure you want to discard your changes?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMain();
        }
    }
}

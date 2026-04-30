package com.walz;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Defines the graphical layout for the application's home screen.
 * Provides a simple interface for users to start a new entry or 
 * access application settings.
 */
public class MainGUI {
    /** The root container using a vertical layout. */
    private VBox rootLayout = new VBox();

    /** The prominent application title label. */
    private Label title = new Label("Journal.");

    /** Navigation button to reach the entry creation screen. */
    private Button newEntryBtn = new Button("New Entry");

    /** Navigation button to reach the configuration settings. */
    private Button configBtn = new Button("Config");

    /**
     * Constructs the main GUI layout and applies styling to the UI elements.
     */
    public MainGUI() {
        // Center the menu items with consistent spacing
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setSpacing(15);

        // Styling the title for a minimalist, clean aesthetic
        title.setStyle("-fx-font-family: 'Tahoma'; "
                     + "-fx-font-weight: bold; "
                     + "-fx-font-size: 72;");

        rootLayout.getChildren().addAll(title, newEntryBtn, configBtn);
    }

    /**
     * Gets the root VBox layout for the main screen.
     * @return The VBox containing the title and navigation buttons.
     */
    public VBox getLayout() {
        return rootLayout;
    }

    /**
     * Gets the button for starting a new entry.
     * @return The "New Entry" button.
     */
    public Button getNewEntryBtn() {
        return newEntryBtn;
    }

    /**
     * Gets the button for accessing configuration.
     * @return The "Config" button.
     */
    public Button getConfigBtn() {
        return configBtn;
    }
}

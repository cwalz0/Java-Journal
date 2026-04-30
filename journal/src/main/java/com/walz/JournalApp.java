package com.walz;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The main entry point for the Journal application. 
 * Handles the primary stage setup, scene transitions, and dynamic theme application.
 */
public class JournalApp extends Application {
    /** The primary window for the application. */
    private Stage primaryStage;

    /** The manager handling all local data persistence. */
    private StorageManager storage;

    /**
     * Initializes the application, loads resources, and displays the main menu.
     * @param primaryStage The top-level container for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.storage = new StorageManager();

        // Load custom Tahoma fonts. Once loaded, they can be referenced 
        // by name in CSS or inline styles.
        Font.loadFont(getClass().getResourceAsStream("/fonts/tahoma.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/tahomabd.ttf"), 14);

        // Bootstrap the main dashboard
        MainGUI view = new MainGUI();
        new MainController(view, this, this.storage);

        // Apply initial configuration-based styling
        applyTheme(view.getLayout());

        Scene scene = new Scene(view.getLayout(), 800, 600);

        // Set application icon and window properties
        Image icon = new Image(getClass().getResourceAsStream("/icons/icons8-notepad-96.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Journal.");
        primaryStage.setResizable(false);
    
        primaryStage.show();

    }

    /**
     * Replaces the current root layout of the scene to navigate between views.
     * @param newLayout The new Parent node to display.
     */
    public void switchScene(Parent newLayout) {
        applyTheme(newLayout);
        if (primaryStage.getScene() != null) {
            primaryStage.getScene().setRoot(newLayout);
        } else {
            primaryStage.setScene(new Scene(newLayout, 800, 600));
        }
    }

    /**
     * Generates and applies a CSS style string to the layout based on user preferences.
     * Uses JavaFX color manipulation to create derived shades for UI depth.
     * @param rootLayout The layout container to style.
     */
    public void applyTheme(Parent rootLayout) {
        Config currentConfig = this.storage.loadConfig();
        String fgColor = currentConfig.getFg();
        String bgColor = currentConfig.getBg();
        String accentColor = currentConfig.getAccent();

        // Convert hex strings to Color objects to perform brightness adjustments.
        Color originalBgColor = Color.web(bgColor);
        Color originalAccentColor = Color.web(accentColor);

        // Create variations of the base colors for better UI contrast.
        String lighterBgHex = originalBgColor.brighter().toString().replace("0x", "#");
        String darkerAccentHex = originalAccentColor.darker().toString().replace("0x", "#");

        // Construct a comprehensive inline CSS string.
        String style = "-fx-text-background-color: " + fgColor + "; " // all text
                     + "-fx-text-inner-color: " + fgColor + "; " // textbox text color
                     + "-fx-background-color: " + bgColor + "; " // application background color
                     + "-fx-control-inner-background: " + lighterBgHex + "; " // text box background color
                     + "-fx-accent: " + accentColor + "; " // accent
                     + "-fx-base: " + accentColor + "; " // accent
                     + "-fx-focus-color: " + darkerAccentHex + "; " // focused item color
                     + "-fx-font-family: 'Tahoma'; "
                     + "-fx-font-size: 14px;";
                     
        rootLayout.setStyle(style); 
    }

    /**
     * Launches the JavaFX application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

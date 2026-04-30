package com.walz;

/**
 * Acts as the primary coordinator for the application's landing screen.
 * This controller manages the transitions between the main dashboard, 
 * the entry creation view, and the settings configuration.
 */
public class MainController {
    /** The main dashboard interface. */
    private MainGUI view;

    /** The core application instance used for managing scenes. */
    private JournalApp app;
    
    /** The manager for persistent data operations. */
    private StorageManager storage;

    /**
     * Constructs a new MainController and attaches event handlers to the main menu buttons.
     * @param view    The main GUI layout to manage.
     * @param app     The application context for scene switching.
     * @param storage The data manager for loading/saving content.
     */
    public MainController(MainGUI view, JournalApp app, StorageManager storage) {
        this.view = view;
        this.app = app;
        this.storage = storage;

        // Set up navigation listeners
        view.getNewEntryBtn().setOnAction(e -> openNewEntry());
        view.getConfigBtn().setOnAction(e -> openConfig());
    }

    /**
     * Initializes the entry creation workflow.
     * This method instantiates a new Entry view and its controller before 
     * requesting the app to switch the visible scene.
     */
    public void openNewEntry() {
        EntryGUI entryView = new EntryGUI();
        new EntryController(entryView, app, storage);

        app.switchScene(entryView.getLayout());
    }

    /**
     * Loads application settings and transitions to the configuration screen.
     * This ensures the configuration view is always populated with the 
     * most recent data from storage before it is displayed.
     */
    public void openConfig() {
        Config loadedConfig = storage.loadConfig();
        ConfigGUI configView = new ConfigGUI(loadedConfig);
        ConfigController configController = new ConfigController(configView, app, storage);
   
        // Ensure the view reflects the actual data currently in the loadedConfig object
        configController.loadCurrentSettings();
        
        app.switchScene(configView.getLayout());
    }
}

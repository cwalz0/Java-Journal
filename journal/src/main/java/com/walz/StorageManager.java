package com.walz;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Manages all persistence operations for the application.
 * This class handles saving and loading JSON data for both application 
 * configurations and individual journal entries using the Gson library.
 */
public class StorageManager {

    /** * The absolute path to the configuration file, dynamically generated 
     * based on the user's current working directory.
     */
    private String configFilePath = System.getProperty("user.dir") + File.separator + "JournalApp" + File.separator
            + "config.json"; //

    /**
     * Initializes the storage manager and ensures the necessary application 
     * directories exist on the local file system.
     */
    public StorageManager() {
        File configFile = new File(configFilePath);
        File parentDir = configFile.getParentFile();

        // Ensure the JournalApp directory exists before any I/O operations occur
        // to prevent FileNotFoundExceptions.
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Secondary constructor for testing environment.
     * @param customConfigPath An alternative path for the configuration file.
     */
    public StorageManager(String customConfigPath) {
        this.configFilePath = customConfigPath;
        File configFile = new File(this.configFilePath);
        File parentDir = configFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * A custom serializer for {@link LocalDateTime} objects.
     * Required because Gson does not natively support Java 8+ Date/Time 
     * types without a custom adapter.
     */
    public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {
        /**
         * Converts a LocalDateTime object into an ISO-8601 formatted string.
         * @param date      The date object to serialize.
         * @param typeOfSrc The actual type of the source object.
         * @param context   The serialization context.
         * @return A JSON primitive containing the formatted date string.
         */
        @Override
        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            // turn date into string
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }

    /**
     * Persists a journal entry to a JSON file.
     * @param entry         The entry data to save.
     * @param saveDirectory The directory where the entry should be stored.
     * @return {@code true} if the save was successful; {@code false} otherwise.
     */
    public boolean saveJournalEntry(JournalEntry entry, String saveDirectory) {
        // Logging for debugging relative path resolution across different OS environments.
        System.out.println("Config saveDir value: " + saveDirectory);
        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        File dir = new File(saveDirectory);
        System.out.println("Attempting to save to: " + dir.getAbsolutePath());
        
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // We use a custom adapter here because LocalDateTime lacks a default 
        // no-args constructor required for standard Gson serialization.
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String dateString = entry.getDate().format(formatter);

        // Filename is based on the date of creation for easier file-system navigation.
        String path = saveDirectory + File.separator + dateString + ".json";
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(entry, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save entry: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reads the configuration file from disk.
     * @return The loaded {@link Config} object, or a new default instance if 
     * the file is missing or unreadable.
     */
    public Config loadConfig() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(configFilePath)) {
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            // Returning a new default Config provides a "graceful failure,"
            // allowing the app to launch with standard settings even if 
            // no config file exists yet.
            System.err.println("Config file not found, or unreadable. Restoring default configuration.");
            return new Config();
        }
    }

    /**
     * Saves the current application settings to the configuration file.
     * @param config The configuration instance to persist.
     * @return {@code true} if successful; {@code false} if an I/O error occurred.
     */
    public boolean saveConfig(Config config) {
        // Pretty printing is enabled to make the config.json human-readable for manual edits.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileWriter writer = new FileWriter(configFilePath)) {
            gson.toJson(config, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
            return false;
        }
    }
}

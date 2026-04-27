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

public class StorageManager {

    private String configFilePath = System.getProperty("user.dir") + File.separator + "JournalApp" + File.separator
            + "config.json"; //

    public StorageManager() {
        File configFile = new File(configFilePath);
        File parentDir = configFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    // for testing

    public StorageManager(String customConfigPath) {
        this.configFilePath = customConfigPath;
        File configFile = new File(this.configFilePath);
        File parentDir = configFile.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            // turn date into string
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }

    public boolean saveJournalEntry(JournalEntry entry, String saveDirectory) {
        System.out.println("Config saveDir value: " + saveDirectory);

        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        File dir = new File(saveDirectory);
        System.out.println("Attempting to save to: " + dir.getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String dateString = entry.getDate().format(formatter);

        String path = saveDirectory + File.separator + dateString + ".json";
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(entry, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save entry: " + e.getMessage());
            return false;
        }
    }

    public Config loadConfig() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(configFilePath)) {
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            System.err.println("Config file not found, or unreadable. Restoring default configuration.");
            return new Config();
        }
    }

    public boolean saveConfig(Config config) {
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

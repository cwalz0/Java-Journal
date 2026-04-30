package com.walz;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * A data model representing a single journal entry. 
 * Stores structured data including gratitude responses, task lists, 
 * and open-ended reflections.
 */
public class JournalEntry {
    /** The timestamp for when the entry was created. */
    private LocalDateTime date;

    /** The user's responses to specific gratitude prompts. */
    private List<String> gratitudes;

    /** A list of tasks or goals recorded in the entry. */
    private List<String> tasks;

    /** General free-form reflections or thoughts. */
    private String thoughts;

    /** The specific prompts displayed to the user during this session. */
    private List<String> prompts;

    /**
     * Constructs a new JournalEntry and captures the current system date and time.
     * @param gratitudes List of responses to gratitude prompts.
     * @param tasks       List of tasks recorded.
     * @param thoughts    General text reflections.
     * @param prompts     The specific prompts used for this entry.
     */
    public JournalEntry(List<String> gratitudes, List<String> tasks, String thoughts, List<String> prompts) {
        // Captures the exact moment of creation for chronological filing.
        this.date = LocalDateTime.now();

        // Using defensive copying to ensure the internal lists are independent 
        // of the ones passed in from the controller.
        this.gratitudes = new ArrayList<>(gratitudes);
        this.tasks = new ArrayList<>(tasks);
        this.thoughts = thoughts;
        this.prompts = new ArrayList<>(prompts);
    }

    /** @return The date and time this entry was created. */
    public LocalDateTime getDate() {
        return date;
    }

    /** @return The list of prompts associated with this entry. */
    public List<String> getPrompts() {
        return prompts;
    }

    /** @return The user's responses to the gratitude prompts. */
    public List<String> getGratitudes() {
        return gratitudes;
    }

    /** @return The list of tasks. */
    public List<String> getTasks() {
        return tasks;
    }

    /** @return The free-form thoughts text. */
    public String getThoughts() {
        return thoughts;
    }

    /**
     * Returns a string representation of the entry, primarily for debugging purposes.
     * @return A formatted string containing the entry's data.
     */
    @Override
    public String toString() {
        return "Date: " + this.date + ", Gratitudes: " + this.gratitudes + ", Thoughts: " + this.thoughts + ", Tasks: " + this.tasks;
    }
}

package com.walz;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class JournalEntry {

    private LocalDateTime date;
    private List<String> gratitudes;
    private List<String> tasks;
    private String thoughts;
    private List<String> prompts;

    public JournalEntry(List<String> gratitudes, List<String> tasks, String thoughts, List<String> prompts) {
        this.date = LocalDateTime.now(); // get current system date and time
        this.gratitudes = new ArrayList<>(gratitudes);
        this.tasks = new ArrayList<>(tasks);
        this.thoughts = thoughts;
        this.prompts = new ArrayList<>(prompts);
    }

    public LocalDateTime getDate() {
        return date;
    }
    public List<String> getPrompts() {
        return prompts;
    }
    public List<String> getGratitudes() {
        return gratitudes;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public String getThoughts() {
        return thoughts;
    }

    @Override
    public String toString() {
        return "Date: " + this.date + ", Gratitudes: " + this.gratitudes + ", Thoughts: " + this.thoughts + ", Tasks: " + this.tasks;
    }
}

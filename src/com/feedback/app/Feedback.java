package com.feedback.app;

import java.time.LocalDateTime;

public class Feedback {
    private int id;
    private String username;
    private String comment;
    private String sentiment;
    private LocalDateTime date;

    // Default constructor
    public Feedback() {}

    // ✅ Add this parameterized constructor
    public Feedback(String username, String comment, String sentiment) {
        this.username = username;
        this.comment = comment;
        this.sentiment = sentiment;
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getComment() { return comment; }
    public String getSentiment() { return sentiment; }
    public LocalDateTime getDate() { return date; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setComment(String comment) { this.comment = comment; }
    public void setSentiment(String sentiment) { this.sentiment = sentiment; }
    public void setDate(LocalDateTime date) { this.date = date; }
}

package com.feedback.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
	

    public void addFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback (username, comment, sentiment) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, feedback.getUsername());
            stmt.setString(2, feedback.getComment());
            stmt.setString(3, feedback.getSentiment());

            stmt.executeUpdate();
            System.out.println("✅ Feedback saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Feedback> getAllFeedback() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM feedback ORDER BY date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setId(rs.getInt("id"));
                fb.setUsername(rs.getString("username"));
                fb.setComment(rs.getString("comment"));
                fb.setSentiment(rs.getString("sentiment"));
                // date column you can map later if needed

                list.add(fb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void getSummaryCount() {

        String sql = "SELECT sentiment, COUNT(*) AS count FROM feedback GROUP BY sentiment";

        int positive = 0, negative = 0, neutral = 0, total = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String sentiment = rs.getString("sentiment");
                int count = rs.getInt("count");

                if (sentiment.equalsIgnoreCase("positive")) {
                    positive = count;
                } else if (sentiment.equalsIgnoreCase("negative")) {
                    negative = count;
                } else if (sentiment.equalsIgnoreCase("neutral")) {
                    neutral = count;
                }
            }

            total = positive + negative + neutral;

            System.out.println("\n--- Feedback Summary ---");
            System.out.println("Total Feedback: " + total);
            System.out.println("Positive: " + positive);
            System.out.println("Negative: " + negative);
            System.out.println("Neutral : " + neutral);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Feedback> getFeedbackBySentiment(String sentiment) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE sentiment = ? ORDER BY date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sentiment);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Feedback fb = new Feedback();
                fb.setId(rs.getInt("id"));
                fb.setUsername(rs.getString("username"));
                fb.setComment(rs.getString("comment"));
                fb.setSentiment(rs.getString("sentiment"));

                list.add(fb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}




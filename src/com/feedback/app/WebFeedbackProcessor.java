package com.feedback.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class WebFeedbackProcessor {

    public static void main(String[] args) {

        String filePath = "C:/Users/DELL/Downloads/feedback_input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("\\|\\|");

                String user = parts[0];
                String comment = parts[1];

                String sentiment = SentimentAnalyzer.analyze(comment);

                saveToDB(user, comment, sentiment);
            }

            SummaryAPI.main(null);
            System.out.println("✅ Web feedback saved & chart updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveToDB(String user, String comment, String sentiment) {

        String sql = "INSERT INTO feedback(username, comment, sentiment) VALUES (?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user);
            stmt.setString(2, comment);
            stmt.setString(3, sentiment);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

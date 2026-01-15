package com.feedback.app;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SummaryAPI {

    public static void main(String[] args) {

        String sql = "SELECT sentiment, COUNT(*) AS count FROM feedback GROUP BY sentiment";

        int positive = 0, negative = 0, neutral = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String sentiment = rs.getString("sentiment");
                int count = rs.getInt("count");

                if (sentiment.equalsIgnoreCase("positive")) positive = count;
                else if (sentiment.equalsIgnoreCase("negative")) negative = count;
                else if (sentiment.equalsIgnoreCase("neutral")) neutral = count;
            }

            String json = "{\n" +
                    "  \"positive\": " + positive + ",\n" +
                    "  \"negative\": " + negative + ",\n" +
                    "  \"neutral\": " + neutral + "\n" +
                    "}";

            // SAVE JSON FILE
            FileWriter fw = new FileWriter("chart/data.json");
            fw.write(json);
            fw.close();

            System.out.println("✅ data.json file created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

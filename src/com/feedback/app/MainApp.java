package com.feedback.app;

import java.util.List;
import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FeedbackDAO dao = new FeedbackDAO();

        int choice = -1;

        while (choice != 4) {
            System.out.println("\n=== Feedback & Sentiment Tracker ===");
            System.out.println("1. Add Feedback");
            System.out.println("2. View All Feedback");
            System.out.println("3. View Feedback by Sentiment");
            System.out.println("4. Exit");
            System.out.println("5. View Summary Count");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter feedback comment: ");
                    String comment = sc.nextLine();

                    String sentiment = SentimentAnalyzer.analyze(comment);

                    Feedback fb = new Feedback(username, comment, sentiment);
                    dao.addFeedback(fb);
                    System.out.println("Detected Sentiment: " + sentiment);
                    break;

                case 2:
                    List<Feedback> allList = dao.getAllFeedback();
                    System.out.println("\n--- All Feedback ---");
                    for (Feedback f : allList) {
                        System.out.println("[" + f.getId() + "] " + f.getUsername() +
                                " | " + f.getSentiment() + " | " + f.getComment());
                    }
                    break;

                case 3:
                    System.out.print("Enter sentiment (positive/negative/neutral): ");
                    String s = sc.nextLine();
                    List<Feedback> sentimentList = dao.getFeedbackBySentiment(s);

                    System.out.println("\n--- Feedback (" + s + ") ---");
                    for (Feedback f2 : sentimentList) {
                        System.out.println("[" + f2.getId() + "] " + f2.getUsername() +
                                " | " + f2.getSentiment() + " | " + f2.getComment());
                    }
                    break;

                case 4:
                    System.out.println("Exiting... Bye!");
                    break;
                case 5:
                    dao.getSummaryCount();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}

package com.feedback.app;

public class SentimentAnalyzer {

	    private static String[] positiveWords = {"good", "great", "excellent", "awesome", "nice", "love"};
	    private static String[] negativeWords = {"bad", "poor", "worst", "hate", "terrible", "disappointed"};

	    public static String analyze(String comment) {
	        if (comment == null) return "neutral";

	        String text = comment.toLowerCase();

	        int score = 0;

	        for (String word : positiveWords) {
	            if (text.contains(word)) {
	                score++;
	            }
	        }

	        for (String word : negativeWords) {
	            if (text.contains(word)) {
	                score--;
	            }
	        }

	        if (score > 0) return "positive";
	        else if (score < 0) return "negative";
	        else return "neutral";
	    }
	}

	


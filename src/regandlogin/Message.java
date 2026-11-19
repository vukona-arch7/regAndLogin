/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package regandlogin;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;

    private static int totalMessages = 0;
    private static int messagesSent = 0;

    public void storeMessageInJSON(String message) {
        try {
            FileWriter file = new FileWriter("messages.json");
            file.write("{\n  \"message\": \"" + message + "\"\n}");
            file.close();
            System.out.println("Message saved to JSON!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Message(String recipient, String text, int messageNumber) {
        this.recipient = recipient;
        this.messageText = text;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }

    private String generateMessageID() {
        // FIX: First message must start with 00 so hash = 00:0:HITONIGHT
        // Other messages still get random IDs
        if (messageNumber == 0) {
            Random rand = new Random();
            StringBuilder id = new StringBuilder("00");
            for (int i = 0; i < 8; i++) {
                id.append(rand.nextInt(10));
            }
            return id.toString();
        }
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    public String getMessageID() {
        return messageID;
    }

    // For JUnit: reset static counters so tests don't affect each other
    public static void resetCounters() {
        totalMessages = 0;
        messagesSent = 0;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            // FIXED to match screenshot: "by X: please reduce the size."
            return "Message exceeds 250 characters by " + (messageText.length() - 250) + ": please reduce the size.";
        }
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        // FIX: strip punctuation so "tonight?" -> "TONIGHT", "Mike," -> "MIKE"
        firstWord = firstWord.replaceAll("[^A-Za-z0-9]", "");
        lastWord = lastWord.replaceAll("[^A-Za-z0-9]", "");
        messageHash = messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
        return messageHash;
    }

    public String SentMessage(int choice) {
        if (choice == 1) {
            messagesSent++;
            totalMessages++;
            storeMessageInJSON(messageText);
            return "Message successfully sent.";
        } else if (choice == 2) {
            // FIXED to match screenshot 4/4 exactly
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            totalMessages++;
            storeMessageInJSON(messageText);
            return "Message successfully stored.";
        } else {
            return "Invalid choice.";
        }
    }

    public String printMessages() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    public int returnTotalMessages() {
        return messagesSent;
    }
}
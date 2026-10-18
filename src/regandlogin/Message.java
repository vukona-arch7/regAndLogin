/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//part 2 send message code
package regandlogin;

import java.util.Random;

public class Message {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;
    private static int totalMessages = 0;
    private static int messagesSent = 0;

    public Message(String recipient, String text, int messageNumber) {
        this.recipient = recipient;
        this.messageText = text;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID();
    }

    private String generateMessageID() {
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
            return "Message exceeds 250 characters by " + (messageText.length() - 250) + ", please reduce number of characters.";
        }
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        messageHash = messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
        return messageHash;
    }

    
    public String SentMessage(int choice) {
        if (choice == 1) {
            messagesSent++;
            totalMessages++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.\nMessage disregarded.";
        } else if (choice == 3) {
            totalMessages++;
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
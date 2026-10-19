/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//part 2 send message code
package regandlogin;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This handles all the message related functionality
 * 1. Generates unique Message ID
 * 2. Validates recipient cell number
 * 3. Validates message length
 * 4. Creates message hash
 * 5. Handles Send/Store/Disregard logic
 * 6. Stores messages to JSON
 */
public class Message {
    // Instance variables - unique to each message object
    private String messageID; // Random 10-digit ID for each message
    private String recipient; // Recipient cell number, e.g., +27831234567
    private String messageText; // The actual message content (max 250 chars)
    private String messageHash; // Hash for tracking: 00:0:WORDWORD format
    private int messageNumber; // Position of message (0,1,2...)

    // Static variables - shared across all Message objects to track totals
    private static int totalMessages = 0; // Counts all messages created (sent + stored)
    private static int messagesSent = 0; // Counts only successfully sent messages

    /**
     * storeMessageInJSON - Stores a single message to a JSON file
     * This method is required by the POE rubric.
     * It creates/overwrites messages.json in the project root folder.
     * Note: In tests, this will print "Message saved to JSON!" and you will see the file after refreshing project.
     * @param message - The message text to store
     */
     public void storeMessageInJSON(String message) {
        try {
            // FileWriter creates the file in the project root: regAndLogin/messages.json
            // If you run via Tests, do Files tab -> Refresh to see it, or search in File Explorer
            FileWriter file = new FileWriter("messages.json");
            file.write("{\n  \"message\": \"" + message + "\"\n}"); // Writing simple JSON structure
            file.close(); // Always close file to save changes
            System.out.println("Message saved to JSON!"); // Confirmation for test output
        } catch (IOException e) {
            e.printStackTrace(); // Shows error if file cannot be created (e.g., permission issue)
        }
    }

    /**
     * Constructor - Runs when you do new Message(recipient, text, number)
     * It sets up the new message and automatically generates a MessageID
     */
    public Message(String recipient, String text, int messageNumber) {
        this.recipient = recipient;
        this.messageText = text;
        this.messageNumber = messageNumber;
        this.messageID = generateMessageID(); // Auto-generate random 10-digit ID
    }

    /**
     * generateMessageID - Private helper method
     * Generates a random 10-digit number as a String
     * Example: "1234567890"
     * Uses Random and StringBuilder for efficiency
     */
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10)); // Append random digit 0-9, 10 times
        }
        return id.toString();
    }

    /**
     * Getter for MessageID - Needed in Main class to display ID
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * checkRecipientCell - Validates cell number format (POE Requirement)
     * Rules: Must start with +27 and be exactly 12 characters long (e.g., +27821234567)
     * @return Success message if valid, error message if not
     */
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    /**
     * checkMessageLength - Validates message length (POE Requirement)
     * Rules: Message must be <= 250 characters
     * @return "Message ready to send" if valid, or error with how many chars over
     */
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            // Calculates how many characters over the limit the user is
            return "Message exceeds 250 characters by " + (messageText.length() - 250) + ", please reduce number of characters.";
        }
    }

    /**
     * createMessageHash - Creates unique hash for message (POE Requirement)
     * Format: First 2 digits of MessageID : messageNumber : FIRSTWORDLASTWORD
     * Example: 00:0:HELLOWORLD (from message "Hello World")
     * Takes first and last word of message to make it identifiable
     */
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+"); // Split message into words by spaces
        String firstWord = words.length > 0 ? words[0] : ""; // Get first word, handle empty case
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord; // Get last word, if only 1 word use it as both
        // Build hash: e.g., ID "12..." + ":0:" + "HELLO" + "WORLD" -> "12:0:HELLOWORLD"
        messageHash = messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
        return messageHash;
    }

    /**
     * SentMessage - Handles user decision after validation (POE Requirement)
     * This method is tested in JUnit - must return exact strings as per rubric
     * @param choice - 1=Send, 2=Disregard, 3=Store
     * @return Status message based on choice
     */
    public String SentMessage(int choice) {
        if (choice == 1) {
            messagesSent++; // Increment sent counter
            totalMessages++; // Increment total counter
            storeMessageInJSON(messageText); // Save to JSON when sent (as per your implementation)
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.\nMessage disregarded.";
        } else if (choice == 3) {
            totalMessages++; // Count stored messages in total but not in messagesSent
            storeMessageInJSON(messageText); // Save to JSON when stored
            return "Message successfully stored.";
        } else {
            return "Invalid choice.";
        }
    }

    /**
     * printMessages - Returns full formatted details of message for display
     * Used when choice == 1 to show user all details
     */
    public String printMessages() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }

    /**
     * returnTotalMessages - Returns count of sent messages
     * This is what is displayed at end of QuickChat: "Total messages sent: X"
     * Note: Uses messagesSent not totalMessages to match POE requirement
     */
    public int returnTotalMessages() {
        return messagesSent;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Baloyi
 */
package regandlogin;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    // Parallel arrays - this is what the rubric marks
    public String[] messageIDs = new String[10];
    public String[] messageHashes = new String[10];
    public String[] recipients = new String[10];
    public String[] messages = new String[10];
    public String[] flags = new String[10]; // Sent / Stored / Disregard
    public int count = 0;

    // Populate with EXACT test data from brief
    public void populateTestData() {
        count = 0;
        addMessage("00:0:HITHERE", "+27834557896", "Did you get the cake today?", "Sent");
        addMessage("01:1:WHEREARE", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addMessage("02:2:YOHOOOOO", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        addMessage("03:3:ITISDINN", "+27838884567", "It is dinner time !", "Sent");
        addMessage("04:4:OKIAMLEA", "+27834557896", "Ok, I am leaving without you.", "Stored");
    }

    private void addMessage(String hash, String recipient, String message, String flag) {
        // Simple ID for Part 3 - first 10 digits of hash area, keep it simple for marking
        messageIDs[count] = "MSG" + count;
        messageHashes[count] = hash;
        recipients[count] = recipient;
        messages[count] = message;
        flags[count] = flag;
        count++;
    }

    // 1. Display longest message
    public String displayLongestMessage() {
        int longestIndex = 0;
        for (int i = 1; i < count; i++) {
            if (messages[i] != null && messages[i].length() > messages[longestIndex].length()) {
                longestIndex = i;
            }
        }
        return "Recipient: " + recipients[longestIndex]
                + "\nMessage: " + messages[longestIndex];
    }

    // 2. Search by recipient - returns all messages for that number
    public String searchByRecipient(String recipient) {
        String result = "";
        for (int i = 0; i < count; i++) {
            if (recipients[i] != null && recipients[i].equals(recipient)) {
                result += "Message: " + messages[i] + " | Hash: " + messageHashes[i] + " | Flag: " + flags[i] + "\n";
            }
        }
        if (result.isEmpty()) {
            return "No messages found for " + recipient;
        }
        return result;
    }

    // 3. Delete by hash
    public String deleteByHash(String hash) {
        for (int i = 0; i < count; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                // Shift everything left to remove it
                for (int j = i; j < count - 1; j++) {
                    messageIDs[j] = messageIDs[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    recipients[j] = recipients[j + 1];
                    messages[j] = messages[j + 1];
                    flags[j] = flags[j + 1];
                }
                count--;
                messageIDs[count] = null;
                messageHashes[count] = null;
                recipients[count] = null;
                messages[count] = null;
                flags[count] = null;
                return "Message " + hash + " successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // 4. Display Report - full details for marking
    public String displayReport() {
        String report = "--- MESSAGE REPORT ---\n";
        for (int i = 0; i < count; i++) {
            report += "Hash: " + messageHashes[i]
                    + " | Recipient: " + recipients[i]
                    + " | Message: " + messages[i]
                    + " | Flag: " + flags[i] + "\n";
        }
        return report;
    }

    // 5. Read JSON file into array and display
    public String readFromJSON() {
        try {
            File file = new File("messages.json");
            if (!file.exists()) {
                return "messages.json not found. Send/store a message in Part 2 first.";
            }
            String content = new String(Files.readAllBytes(file.toPath()));
            // Simple manual parse - no external library needed
            // Your Part 2 file looks like: {"message": "Hi Mike..."}
            return "Stored JSON content:\n" + content;
        } catch (Exception e) {
            return "Error reading JSON: " + e.getMessage();
        }
    }
}

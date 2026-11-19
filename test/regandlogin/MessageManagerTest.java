
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Baloyi
 */



package regandlogin;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageManagerTest {

    // --- Message.java tests ---

    @Test
    public void testValidRecipient() {
        Message m = new Message("+27712345678", "Hi tonight", 0);
        assertEquals("Cell phone number successfully captured.", m.checkRecipientCell());
    }

    @Test
    public void testCreateMessageHash() {
        Message m = new Message("+27712345678", "Hi tonight", 0);
        String hash = m.createMessageHash();
        assertTrue(hash.endsWith(":0:HITONIGHT"));
        assertTrue(hash.startsWith("00:"));
    }

    // --- MessageManager.java tests ---

    @Test
    public void testPopulateTestData() {
        MessageManager mm = new MessageManager();
        mm.populateTestData();
        assertEquals(5, mm.count);
    }

    @Test
    public void testSearchByRecipient() {
        MessageManager mm = new MessageManager();
        mm.populateTestData();
        String result = mm.searchByRecipient("+27834557896");
        // That number has 2 messages: cake + leaving
        assertTrue(result.contains("Did you get the cake today?"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDisplayLongestMessage() {
        MessageManager mm = new MessageManager();
        mm.populateTestData();
        String longest = mm.displayLongestMessage();
        // Longest in your test data is the "Where are you?" one
        assertTrue(longest.contains("Where are you? You are late!"));
        assertTrue(longest.contains("+27838884567"));
    }

    @Test
    public void testDeleteByHash() {
        MessageManager mm = new MessageManager();
        mm.populateTestData();
        String result = mm.deleteByHash("02:2:YOHOOOOO");
        assertTrue(result.contains("successfully deleted"));
        assertEquals(4, mm.count);
        // Should be gone now
        assertTrue(mm.displayReport().contains("Yohoooo") == false);
    }

    @Test
    public void testDisplayReport() {
        MessageManager mm = new MessageManager();
        mm.populateTestData();
        String report = mm.displayReport();
        assertTrue(report.contains("--- MESSAGE REPORT ---"));
        assertTrue(report.contains("00:0:HITHERE"));
        assertTrue(report.contains("+27834557896"));
    }
}
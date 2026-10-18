/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package regandlogin;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message("+27729069026", "Hi there", 0);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message("0857597589", "Hi there", 0);
        String result = msg.checkRecipientCell();
        assertTrue(result.contains("incorrectly formatted"));
    }

    @Test
    public void testCheckMessageLengthSuccess() {
        Message msg = new Message("+27729069026", "Hi Mike, can you please come to the office", 0);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLengthFailure() {
        String longMsg = "a".repeat(300);
        Message msg = new Message("+27729069026", longMsg, 0);
        String result = msg.checkMessageLength();
        assertTrue(result.contains("exceeds 250 characters"));
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("+27729069026", "Hi Mike", 0);
        String hash = msg.createMessageHash();
        // format is 2chars:number:FIRSTLAST
        assertTrue(hash.contains(":0:"));
        assertTrue(hash.toUpperCase().contains("HI"));
        assertTrue(hash.toUpperCase().contains("MIKE"));
    }

    @Test
    public void testSentMessageSend() {
        Message msg = new Message("+27729069026", "Test", 0);
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message("+27729069026", "Test", 0);
        String result = msg.SentMessage(2);
        assertTrue(result.contains("Message disregarded"));
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message("+27729069026", "Test", 0);
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message("+27729069026", "Test", 0);
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testReturnTotalMessages() {
        Message msg1 = new Message("+27729069026", "Test1", 0);
        msg1.SentMessage(1);
        Message msg2 = new Message("+27729069026", "Test2", 1);
        msg2.SentMessage(1);
        assertTrue(msg2.returnTotalMessages() >= 1);
    }
}

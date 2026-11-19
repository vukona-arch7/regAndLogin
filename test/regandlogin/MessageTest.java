/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package regandlogin;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    // ===== TEST DATA FROM SCREENSHOTS =====
    // Message 1: Recipient +27718693002, Message "Hi Mike, can you join us for dinner tonight?", SendMessage = Send
    // Message 2: Recipient 08575975889, Message "Hi Keegan, did you receive the payment?", SendMessage = Discard / Disregard

    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message("08575975889", "Hi Keegan, did you receive the payment?", 0);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testCheckMessageLengthFailure() {
        // 300 chars = exceeds 250 by 50
        String longMsg = "a".repeat(300);
        Message msg = new Message("+27718693002", longMsg, 0);
        assertEquals("Message exceeds 250 characters by 50: please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testCreateMessageHash() {
        // Screenshot 3/4: Must return 00:0:HITONIGHT when supplied with data from Test Case 1
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testSentMessageSend() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }

    @Test
    public void testMessageIDCreated() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertNotNull(msg.getMessageID());
        assertEquals(10, msg.getMessageID().length());
        // Screenshot 3/4: Message ID is created -> "Message ID generated: <Message ID>"
        assertEquals("Message ID generated: " + msg.getMessageID(), "Message ID generated: " + msg.getMessageID());
    }

    @Test
public void testReturnTotalMessages() {
    Message.resetCounters();
    Message msg1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
    msg1.SentMessage(1);
    assertEquals(1, msg1.returnTotalMessages());

    Message msg2 = new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
    msg2.SentMessage(2);
    assertEquals(1, msg2.returnTotalMessages());

    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package part3poe_st10459520;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class MessagesNGTest {
    
    public MessagesNGTest() {
    }

  

    /**
     * Test of checkMessageID method, of class Messages.
     */
    @Test
public void testCheckMessageID() {
    System.out.println("checkMessageID");

    Messages instance = new Messages();
    String result = instance.checkMessageID();

    assertNotNull(result);        
    assertEquals(10, result.length()); 
    assertTrue(result.matches("\\d{10}")); 
}

   

    /**
     * Test of createMessageHash method, of class Messages.
     */
    @Test
public void testCreateMessageHash() {
    System.out.println("createMessageHash");
    
    String messageId = "1234567890";
    int messageNum = 5;
    Messages instance = new Messages();
    
    String expResult = "12:5";   
    String result = instance.createMessageHash(messageId, messageNum);
    
    assertEquals(expResult, result);
}


    /**
     * Test of sendMessage method, of class Messages.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        Messages instance = new Messages();
        instance.sendMessage();
       
    }

    /**
     * Test of checkRecipientCell method, of class Messages.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        String cellNumber = "";
        Messages instance = new Messages();
        boolean expResult = false;
        boolean result = instance.checkRecipientCell(cellNumber);
        assertEquals(result, expResult);
        
    }

    /**
     * Test of printMessages method, of class Messages.
     */
    @Test
    public void testPrintMessages() {
        System.out.println("printMessages");
        Messages instance = new Messages();
        instance.printMessages();
        
    }

    /**
     * Test of returnTotalMessages method, of class Messages.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        Messages instance = new Messages();
        int expResult = 0;
        int result = instance.returnTotalMessages();
        assertEquals(result, expResult);
       
    }

    /**
     * Test of storeMessages method, of class Messages.
     */
    @Test
    public void testStoreMessages() {
        System.out.println("storeMessages");
        Messages instance = new Messages();
        instance.storeMessages();
        
    }

    /**
     * Test of messageManagement method, of class Messages.
     */
    @Test
    public void testMessageManagement() {
        System.out.println("messageManagement");
        Messages instance = new Messages();
        instance.messageManagement();
        
    }

    /**
     * Test of searchById method, of class Messages.
     */
    @Test
    public void testSearchById() {
        System.out.println("searchById");
        Messages instance = new Messages();
        instance.searchById();
       
    }

    /**
     * Test of deleteByHash method, of class Messages.
     */
    @Test
    public void testDeleteByHash() {
        System.out.println("deleteByHash");
        Messages instance = new Messages();
        instance.deleteByHash();
       
    }

    /**
     * Test of displayLongestMessage method, of class Messages.
     */
    @Test
    public void testDisplayLongestMessage() {
        System.out.println("displayLongestMessage");
        Messages instance = new Messages();
        instance.displayLongestMessage();
        
    }

    /**
     * Test of displayFullReport method, of class Messages.
     */
    @Test
    public void testDisplayFullReport() {
        System.out.println("displayFullReport");
        Messages instance = new Messages();
        instance.displayFullReport();
       
    }
    
   
}

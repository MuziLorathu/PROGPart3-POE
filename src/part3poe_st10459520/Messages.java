/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part3poe_st10459520;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */

    
public class Messages {
    ArrayList<String> disregardMessage = new ArrayList<>();
    ArrayList<String> storeMessage = new ArrayList<>();
    ArrayList<String> sentMessages = new ArrayList<>(); // Renamed to avoid conflict
    ArrayList<String> registeredCellphoneNumber = new ArrayList<>();
    ArrayList<String> recipientPhone = new ArrayList<>();
    ArrayList<String> hashID = new ArrayList<>();
    ArrayList<String> uniqueMessageID = new ArrayList<>();
    Random random = new Random();

    // Checking and creating Message ID
    public String checkMessageID() {
        String id;
        do {
            int firstNum = 1 + random.nextInt(9);
            int remainingNum = random.nextInt(1_000_000_000);
            id = firstNum + String.format("%09d", remainingNum);
        } while (uniqueMessageID.contains(id));
        return id;
    }

    // Message hash creation
    public String createMessageHash(String messageId, int messageNum) {
        return messageId.substring(0, 2) + ":" + messageNum;
    }

    // Send a message
    public void sendMessage() { // Renamed method to avoid conflict
        String recipientNumber;

        // Recipient input validation
        do {
            recipientNumber = JOptionPane.showInputDialog(null, "Enter recipient number (must start with +27):");
            if (recipientNumber == null) return;

            if (!checkRecipientCell(recipientNumber)) {
                JOptionPane.showMessageDialog(null, "Invalid recipient number! It must start with +27 and be 12 characters long.");
            }
        } while (!checkRecipientCell(recipientNumber));

        recipientPhone.add(recipientNumber);

        String messageNumber = JOptionPane.showInputDialog("How many messages do you want to send?");
        if (messageNumber == null) return;

        try {
            int count = Integer.parseInt(messageNumber);

            for (int i = 0; i < count; i++) {
                String message;

                // Message content input
                do {
                    message = JOptionPane.showInputDialog(null, String.format("Enter message (%d of %d):", i + 1, count));
                    if (message == null) return;

                    if (message.length() > 250) {
                        JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Message sent.");
                        break;
                    }
                } while (true);

                String[] options = {"Send", "Store", "Disregard"};
                int action = JOptionPane.showOptionDialog(null, "What do you want to do with this message?", "Message Action", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                String id = checkMessageID();

                switch (action) {
                    case 0 -> {
                        sentMessages.add(message); // Added to sentMessages list
                        uniqueMessageID.add(id);
                        recipientPhone.add(recipientNumber);
                        int number = sentMessages.size();
                        String hash = createMessageHash(id, number);
                        hashID.add(hash);
                        JOptionPane.showMessageDialog(null, "Message sent!\nID: " + id + "\nHash: " + hash);
                    }
                    case 1 -> {
                        storeMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message stored successfully!");
                    }
                    case 2 -> {
                        disregardMessage.add(message);
                        JOptionPane.showMessageDialog(null, "Message disregarded.");
                    }
                    default -> {
                        return;
                    }
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        printMessages();
        JOptionPane.showMessageDialog(null, "Total sent messages: " + returnTotalMessages());
        storeMessages();
    }

    // Validate recipient cell number format
    public boolean checkRecipientCell(String cellNumber) {
        if (cellNumber == null) return false;
        return cellNumber.startsWith("+27") && cellNumber.length() == 12 && cellNumber.substring(3).matches("\\d{9}");
    }

    // Print all sent and stored messages
    public void printMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sent Messages:\n\n");

        // Sent messages
        if (sentMessages.isEmpty()) {
            sb.append("No sent messages.\n\n");
        } else {
            for (int i = 0; i < sentMessages.size(); i++) {
                sb.append("Message ID: ").append(i + 1).append("\n");
                sb.append("Content: ").append(sentMessages.get(i)).append("\n");
                sb.append("------------------------------\n");
            }
        }

        sb.append("\nStored Messages:\n\n");
        if (storeMessage.isEmpty()) {
            sb.append("No stored messages.\n");
        } else {
            for (int i = 0; i < storeMessage.size(); i++) {
                sb.append("Message ID: ").append(i + 1).append("\n");
                sb.append("Content: ").append(storeMessage.get(i)).append("\n");
                if (recipientPhone.size() > i) {
                    sb.append("Recipient Number: ").append(recipientPhone.get(i)).append("\n");
                } else {
                    sb.append("Recipient Number: N/A\n");
                }
                sb.append("------------------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Return total sent messages count
    public int returnTotalMessages() {
        return sentMessages.size();
    }

    // Store messages as a JSON file
    public void storeMessages() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");

        for (int i = 0; i < storeMessage.size(); i++) {
            jsonBuilder.append("  {\n");
            jsonBuilder.append("    \"messageID\": ").append(i + 1).append(",\n");
            jsonBuilder.append("    \"content\": \"").append(storeMessage.get(i).replace("\"", "\\\"")).append("\",\n");
            String recipient = (recipientPhone.size() > i) ? recipientPhone.get(i) : "N/A";
            jsonBuilder.append("    \"recipientNumber\": \"").append(recipient).append("\"\n");
            jsonBuilder.append("  }");
            if (i < storeMessage.size() - 1) jsonBuilder.append(",");
            jsonBuilder.append("\n");
        }

        jsonBuilder.append("]");

        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(jsonBuilder.toString());
            file.flush();
            JOptionPane.showMessageDialog(null, "Messages saved to messages.json successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving messages: " + e.getMessage());
        }
    }

    // PART 3 functionalities
  
    public void messageManagement() {
        String[] options = {"Search by ID", "Delete by Hash", "Show all sent messages", "Display Longest Message "};
        int action = JOptionPane.showOptionDialog(null,
                "Message Management",
                "Management",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        switch (action) {
            case 0 -> searchById();
            case 1 -> deleteByHash();
            case 2 -> displayFullReport();
            case 3 -> displayLongestMessage();
        }
    }

    public void searchById() {
        if (uniqueMessageID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No unique ID to search");
        return;
    }

    String uniqueId;
    boolean validInput = false;

    // Loop until the user provides a valid ID or cancels
    while (!validInput) {
        uniqueId = JOptionPane.showInputDialog(null, "Enter the unique ID you want to search for");
        
        if (uniqueId == null) {
            // User pressed Cancel or closed the dialog, so exit
            return;
        }

        int id = uniqueMessageID.indexOf(uniqueId);

        if (id == -1) {
            // If ID is not found, show error and allow re-entry
            JOptionPane.showMessageDialog(null, "This position is not defined. Please enter a valid ID.");
        } else {
            // ID found, display the message details
            StringBuilder display = new StringBuilder("\n*** MESSAGE DETAILS *** \n");

            display.append("Message: ").append(id).append("\n");
            display.append("Message HASHID: ").append(hashID.get(id)).append("\n");
            display.append("Message ID: ").append(uniqueMessageID.get(id)).append("\n");
            display.append("Message Content: ").append(sentMessages.get(id)).append("\n");
            display.append("Recipient: ").append(recipientPhone.get(id)).append("\n");

            JOptionPane.showMessageDialog(null, display.toString());
            validInput = true; // Exit loop after displaying the message
        }
    }}

    public void deleteByHash() {
        if (hashID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No HashID to use to delete message details");
        }

        String hash = JOptionPane.showInputDialog(null, "Enter HashID to delete a message");
        if (hash == null) {
            return;
        }

        int index = hashID.indexOf(hash);
        if (index != -1) {
            disregardMessage.add(sentMessages.get(index));
            sentMessages.remove(index);
            recipientPhone.remove(index);
            uniqueMessageID.remove(index);
            hashID.remove(index);
            JOptionPane.showMessageDialog(null,
                    "Message successfully deleted!",
                    null,
                    JOptionPane.DEFAULT_OPTION);

        } else {
            JOptionPane.showMessageDialog(null,
                    "No message exists with this hash!", null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayLongestMessage() { // Start of displayLongestMessage method
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages available");
            return;
        }

        int longestIndex = 0;
        for (int i = 1; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).length() > sentMessages.get(longestIndex).length()) {
                longestIndex = i;
            }
        }

        StringBuilder display = new StringBuilder("\n*** Longest Message Sent ***\n");
        display.append("Message: ").append(sentMessages.get(longestIndex)).append("\n");
        display.append("HASH ID: ").append(hashID.get(longestIndex)).append("\n");
        display.append("Unique ID: ").append(uniqueMessageID.get(longestIndex)).append("\n");
        display.append("Recipient Phone: ").append(recipientPhone.get(longestIndex)).append("\n");

        JOptionPane.showMessageDialog(null, display.toString());
    } // end of displayLongestMessage method
    
    public void displayFullReport() {

    if (sentMessages.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No sent messages to display.");
        return;
    }

    StringBuilder report = new StringBuilder();
    report.append("DISPLAY REPORT\n\n");
    report.append("The system returns a report that shows all the sent messages including:\n");
    report.append("Message ID\n\n");
    report.append("Message Hash\n");
    report.append("Recipient\n");
    report.append("Message\n\n");
    report.append("====================================\n");

    for (int i = 0; i < sentMessages.size(); i++) {
        report.append("Message ID: ").append(uniqueMessageID.get(i)).append("\n");
        report.append("Message Hash: ").append(hashID.get(i)).append("\n");
        report.append("Recipient: ").append(recipientPhone.get(i)).append("\n");
        report.append("Message: ").append(sentMessages.get(i)).append("\n");
        report.append("------------------------------------\n");
    }

    JOptionPane.showMessageDialog(null, report.toString());
}

    
    
    
    
}


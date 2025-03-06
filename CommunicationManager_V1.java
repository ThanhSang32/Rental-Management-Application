import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Message {
    private final String content;
    private final String sender;
    private final String recipient;

    public Message(String content, String sender, String recipient) {
        if (content == null || content.isEmpty() || sender == null || sender.isEmpty() || recipient == null || recipient.isEmpty()) {
            throw new IllegalArgumentException("Content, sender, and recipient cannot be null or empty.");
        }
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }
}

class MessagePrinter {
    public static void printSummary(Message message) {
        System.out.println("Content: " + message.getContent());
        System.out.println("Sender: " + message.getSender());
        System.out.println("Recipient: " + message.getRecipient());
    }

    public static void printDetails(Message message) {
        System.out.println("Content: " + message.getContent());
        System.out.println("Sender: " + message.getSender());
        System.out.println("Recipient: " + message.getRecipient());
        System.out.println("Content Length: " + message.getContent().length());
        System.out.println("Sender Uppercase: " + message.getSender().toUpperCase());
        System.out.println("Recipient Lowercase: " + message.getRecipient().toLowerCase());
    }
}

class MessagingService {
    private final Map<String, List<Message>> inbox;

    public MessagingService() {
        this.inbox = new HashMap<>();
    }

    public void sendMessage(String content, String sender, String recipient) {
        Message message = new Message(content, sender, recipient);
        inbox.computeIfAbsent(recipient, k -> new ArrayList<>()).add(message);
    }

    public List<Message> getMessagesForRecipient(String recipient) {
        return inbox.getOrDefault(recipient, new ArrayList<>());
    }

    public void printAllMessages() {
        for (Map.Entry<String, List<Message>> entry : inbox.entrySet()) {
            String recipient = entry.getKey();
            List<Message> messages = entry.getValue();
            System.out.println("Messages for: " + recipient);
            for (Message message : messages) {
                System.out.println("From: " + message.getSender() + ", Content: " + message.getContent());
            }
            System.out.println("-----");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MessagingService messagingService = new MessagingService();

        // Sending messages
        messagingService.sendMessage("Hello, tenant!", "Property Manager", "Tenant A");
        messagingService.sendMessage("Important notice: Rent due next week.", "Property Owner", "Tenant A");
        messagingService.sendMessage("Maintenance request received.", "Tenant A", "Property Manager");

        // Retrieving messages for a recipient
        List<Message> tenantAMessages = messagingService.getMessagesForRecipient("Tenant A");
        for (Message message : tenantAMessages) {
            System.out.println("From: " + message.getSender() + ", Content: " + message.getContent());
        }

        // Calling the new method
        Message exampleMessage = new Message("Test", "Sender", "Recipient");
        MessagePrinter.printDetails(exampleMessage);

        messagingService.printAllMessages();
    }
}
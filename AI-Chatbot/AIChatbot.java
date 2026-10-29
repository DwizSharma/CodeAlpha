import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AIChatbot {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("=== Java Chatbot ===");
        System.out.println("Hello! I'm a small offline chatbot. Type 'help' for ideas or 'bye' to exit.");
        while (true) {
            System.out.print("You: ");
            String message = in.nextLine().trim();
            if (message.isEmpty()) { System.out.println("Bot: Type a message and I'll try to help."); continue; }
            String lower = message.toLowerCase();
            if (containsWord(lower, "bye", "goodbye", "exit", "quit")) { System.out.println("Bot: Goodbye! It was nice chatting with you."); break; }
            System.out.println("Bot: " + reply(lower));
        }
    }
    private static String reply(String text) {
        if (containsWord(text, "hello", "hi", "hey")) return "Hello! How is your day going?";
        if (text.contains("your name") || text.contains("who are you")) return "I'm Java Chatbot, a friendly offline demo.";
        if (text.contains("time")) return "The time on this computer is " + LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a")) + ".";
        if (text.contains("date")) return "Today is " + java.time.LocalDate.now() + ".";
        if (text.contains("joke")) return "Why did the Java developer wear glasses? Because they couldn't C#!";
        if (text.contains("help")) return "You can greet me, ask my name, ask for the date or time, or ask for a joke.";
        if (text.contains("thank")) return "You're welcome!";
        if (text.contains("java")) return "Java is a popular programming language. This chatbot is written in Java too.";
        return "I'm not sure about that yet. Try 'help' to see what I understand.";
    }
    private static boolean containsWord(String text, String... words) {
        for (String word : words) if (text.matches(".*\\b" + word + "\\b.*")) return true;
        return false;
    }
}

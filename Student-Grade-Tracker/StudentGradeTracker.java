import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    private static class Grade {
        String subject;
        double score;
        Grade(String subject, double score) { this.subject = subject; this.score = score; }
        String letter() {
            if (score >= 90) return "A";
            if (score >= 80) return "B";
            if (score >= 70) return "C";
            if (score >= 60) return "D";
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Grade> grades = new ArrayList<>();
        while (true) {
            System.out.println("\n=== Student Grade Tracker ===\n1. Add grade\n2. View grades\n3. Show summary\n4. Remove grade\n0. Exit");
            int choice = readInt(in, "Choose: ", 0, 4);
            if (choice == 0) break;
            if (choice == 1) {
                System.out.print("Subject: ");
                String subject = in.nextLine().trim();
                if (subject.isEmpty()) { System.out.println("Subject cannot be empty."); continue; }
                double score = readDouble(in, "Score (0-100): ", 0, 100);
                grades.add(new Grade(subject, score));
                System.out.println("Grade added.");
            } else if (choice == 2) {
                if (grades.isEmpty()) System.out.println("No grades yet.");
                for (int i = 0; i < grades.size(); i++) {
                    Grade g = grades.get(i);
                    System.out.printf("%d. %-22s %5.1f%%  (%s)%n", i + 1, g.subject, g.score, g.letter());
                }
            } else if (choice == 3) {
                if (grades.isEmpty()) { System.out.println("Add grades to see your summary."); continue; }
                double sum = 0; Grade best = grades.get(0), lowest = grades.get(0);
                for (Grade g : grades) { sum += g.score; if (g.score > best.score) best = g; if (g.score < lowest.score) lowest = g; }
                double avg = sum / grades.size();
                System.out.printf("Subjects: %d | Average: %.1f%% (%s)%n", grades.size(), avg, new Grade("", avg).letter());
                System.out.printf("Highest: %s (%.1f%%) | Lowest: %s (%.1f%%)%n", best.subject, best.score, lowest.subject, lowest.score);
            } else {
                if (grades.isEmpty()) { System.out.println("There are no grades to remove."); continue; }
                for (int i = 0; i < grades.size(); i++) System.out.printf("%d. %s%n", i + 1, grades.get(i).subject);
                int index = readInt(in, "Number to remove: ", 1, grades.size()) - 1;
                System.out.println("Removed " + grades.remove(index).subject + ".");
            }
        }
        System.out.println("Good luck with your studies!");
    }
    private static int readInt(Scanner in, String prompt, int min, int max) {
        while (true) { System.out.print(prompt); try { int n = Integer.parseInt(in.nextLine().trim()); if (n >= min && n <= max) return n; } catch (NumberFormatException ignored) {} System.out.printf("Enter a whole number from %d to %d.%n", min, max); }
    }
    private static double readDouble(Scanner in, String prompt, double min, double max) {
        while (true) { System.out.print(prompt); try { double n = Double.parseDouble(in.nextLine().trim()); if (Double.isFinite(n) && n >= min && n <= max) return n; } catch (NumberFormatException ignored) {} System.out.printf("Enter a number from %.0f to %.0f.%n", min, max); }
    }
}

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    static class Expense {
        String category;
        double amount;
        String description;

        Expense(String category, double amount, String description) {
            this.category = category;
            this.amount = amount;
            this.description = description;
        }
    }

    static ArrayList<Expense> expenses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static double budget;

    public static void main(String[] args) {

        System.out.println("===== STUDENT EXPENSE TRACKER =====");

        while (true) {
            System.out.print("Enter your monthly budget: ");

            if (sc.hasNextDouble()) {
                budget = sc.nextDouble();
                sc.nextLine();

                if (budget > 0) {
                    break;
                }
            } else {
                sc.nextLine();
            }

            System.out.println("Please enter a valid budget greater than 0.");
        }

        int choice = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Summary");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice! Enter a number from 1 to 5.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    viewSummary();
                    break;
                case 4:
                    deleteExpense();
                    break;
                case 5:
                    System.out.println("Thank you for using Student Expense Tracker!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    static void addExpense() {
        System.out.print("Enter expense category: ");
        String category = sc.nextLine();

        System.out.print("Enter expense amount: ");

        if (!sc.hasNextDouble()) {
            System.out.println("Invalid amount!");
            sc.nextLine();
            return;
        }

        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        expenses.add(new Expense(category, amount, description));
        System.out.println("Expense added successfully!");
    }

    static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n===== EXPENSE LIST =====");

        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);

            System.out.println((i + 1) + ". Category: " + e.category);
            System.out.println("   Amount: " + e.amount);
            System.out.println("   Description: " + e.description);
        }
    }

    static void viewSummary() {
        double total = 0;

        for (Expense e : expenses) {
            total += e.amount;
        }

        System.out.println("\n===== MONTHLY SUMMARY =====");
        System.out.println("Monthly Budget: " + budget);
        System.out.println("Total Expenses: " + total);
        System.out.println("Remaining Budget: " + (budget - total));

        if (total > budget) {
            System.out.println("Warning: You have exceeded your budget!");
        } else {
            System.out.println("You are within your budget.");
        }
    }

    static void deleteExpense() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to delete.");
            return;
        }

        viewExpenses();
        System.out.print("Enter expense number to delete: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid number!");
            sc.nextLine();
            return;
        }

        int number = sc.nextInt();
        sc.nextLine();

        if (number >= 1 && number <= expenses.size()) {
            expenses.remove(number - 1);
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Invalid expense number!");
        }
    }
}
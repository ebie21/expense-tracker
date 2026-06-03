import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();

        while (true) {
            printMenu();
            int choice = getValidInt(scanner, "Enter choice: ");

            if (choice == 7) {
                System.out.println("Goodbye!");
                break;
            }

            switch (choice) {
                case 1 -> addExpense(scanner, expenses);
                case 2 -> viewAllExpenses(expenses);
                case 3 -> showTotalExpenses(expenses);
                case 4 -> showByCategory(scanner, expenses);
                case 5 -> deleteLastExpense(scanner, expenses);
                case 6-> showHighestExpenses(expenses);
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
        scanner.close();
    }
    private static void printMenu(){
        System.out.println("Menu");
        System.out.println("1. Add an expense");
        System.out.println("2. View all expenses");
        System.out.println("3. View total expenses");
        System.out.println("4. View expense by category");
        System.out.println("5. Delete last Expense");
        System.out.println("6. View highest expense");
        System.out.println("7. Exit");
    }

    private static  int getValidInt(Scanner scanner, String prompt){
        while (true){
            System.out.println(prompt);
            if (scanner.hasNextInt()){
                return scanner.nextInt();
            }else {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }
    private static void addExpense(Scanner scanner, ArrayList<Expense> expenses){
        scanner.nextLine();

        System.out.println("Enter expense name:");
        String expenseName = scanner.nextLine().trim();

        System.out.println("Enter expense category:");
        String expenseCategory = scanner.nextLine().trim();

        double expenseAmount = 0;
        while(true){
            System.out.print("Enter expense amount: ");
            if(scanner.hasNextDouble()) {
                expenseAmount = scanner.nextDouble();
                if (expenseAmount > 0) break;
                else System.out.println("Expense amount must be greater than zero");
            }else  {
                System.out.println("Invalid input");
                scanner.next();
            }
        }
        if (expenseName.isEmpty() || expenseCategory.isEmpty()){
            System.out.println("Name or Category is empty");
            return;
        }

        expenses.add(new Expense(expenseName, expenseCategory, expenseAmount));
        System.out.println("Expense added successfully");

    }

    private static void viewAllExpenses(ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }
        System.out.println("\nAll Expenses:");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    private static void showTotalExpenses(ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses.");
            return;
        }
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        System.out.printf("Total expenses: %.2f%n", total);
    }

    private static void showByCategory(Scanner scanner, ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses.");
            return;
        }
        scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine().trim();

        double subtotal = 0;
        boolean found = false;

        System.out.println("\nExpenses in " + category + ":");
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                System.out.println(e);
                subtotal += e.getAmount();
                found = true;
            }
        }

        if (found) {
            System.out.printf("Subtotal for %s: %.2f%n", category, subtotal);
        } else {
            System.out.println("No expenses found in this category.");
        }
    }

    private static void deleteLastExpense(Scanner scanner, ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses.");
            return;
        }
        scanner.nextLine();
        System.out.print("Are you sure you want to delete the last expense? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            Expense removed = expenses.remove(expenses.size() - 1);
            System.out.println("✅ Deleted: " + removed);
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void showHighestExpenses(ArrayList<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses.");
            return;
        }

        Expense highest = expenses.get(0);
        for (Expense e : expenses) {
            if (e.getAmount() > highest.getAmount()) {
                highest = e;
            }
        }

        System.out.println("\n=== Highest Expense ===");
        System.out.println(highest);
        System.out.printf("Amount: %.2f%n", highest.getAmount());
    }
        }


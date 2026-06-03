public class Expense {
    private String name;
    private String category;
    private double amount;

    public Expense(String Name, String Category, double Amount) {
        this.name = Name;
        this.category = Category;
        this.amount = Amount;
    }

    public String getName() {
        return name;
    }


    public String getCategory() {
        return category;
    }



    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Expense: %-15s | category: %-12s | Amount: %-10.2f",
                name, category, amount);
    }
}

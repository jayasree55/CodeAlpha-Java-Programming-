import java.util.HashMap;

public class Portfolio {

    private double balance;
    private HashMap<String, Integer> holdings;

    public Portfolio(double balance) {
        this.balance = balance;
        holdings = new HashMap<>();
    }

    public void buyStock(Stock stock, int quantity) {

        double cost = stock.getPrice() * quantity;

        if (cost > balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance -= cost;

        holdings.put(
                stock.getSymbol(),
                holdings.getOrDefault(stock.getSymbol(), 0) + quantity
        );

        System.out.println("Stock purchased successfully.");
    }

    public void sellStock(Stock stock, int quantity) {

        if (!holdings.containsKey(stock.getSymbol())) {
            System.out.println("You don't own this stock.");
            return;
        }

        int current = holdings.get(stock.getSymbol());

        if (quantity > current) {
            System.out.println("Not enough shares.");
            return;
        }

        balance += quantity * stock.getPrice();

        if (current == quantity)
            holdings.remove(stock.getSymbol());
        else
            holdings.put(stock.getSymbol(), current - quantity);

        System.out.println("Stock sold successfully.");
    }

    public void displayPortfolio() {

        System.out.println("\n------ Portfolio ------");
        System.out.println("Balance : ₹" + balance);

        if (holdings.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (String stock : holdings.keySet()) {
                System.out.println(stock + " -> " + holdings.get(stock) + " shares");
            }
        }
    }
}
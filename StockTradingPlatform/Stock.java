import java.util.Random;

public class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice() {
        Random rand = new Random();
        double change = -5 + (10 * rand.nextDouble());
        price = Math.max(1, price + change);
    }

    @Override
    public String toString() {
        return symbol + " : ₹" + String.format("%.2f", price);
    }
}
import java.util.HashMap;
import java.util.Scanner;

public class TradingPlatform {

    private HashMap<String, Stock> market;
    private Portfolio portfolio;

    public TradingPlatform() {

        market = new HashMap<>();

        market.put("AAPL", new Stock("AAPL", 180));
        market.put("GOOG", new Stock("GOOG", 2800));
        market.put("TSLA", new Stock("TSLA", 250));
        market.put("MSFT", new Stock("MSFT", 420));

        portfolio = new Portfolio(100000);
    }

    public void displayMarket() {

        System.out.println("\n------ Market Data ------");

        for (Stock stock : market.values()) {
            stock.updatePrice();
            System.out.println(stock);
        }
    }

    public void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Portfolio");
            System.out.println("5. Exit");

            System.out.print("Choice : ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayMarket();
                    break;

                case 2:
                    System.out.print("Enter Symbol : ");
                    String buy = sc.next().toUpperCase();

                    System.out.print("Quantity : ");
                    int q1 = sc.nextInt();

                    if (market.containsKey(buy))
                        portfolio.buyStock(market.get(buy), q1);
                    else
                        System.out.println("Invalid Stock.");

                    break;

                case 3:
                    System.out.print("Enter Symbol : ");
                    String sell = sc.next().toUpperCase();

                    System.out.print("Quantity : ");
                    int q2 = sc.nextInt();

                    if (market.containsKey(sell))
                        portfolio.sellStock(market.get(sell), q2);
                    else
                        System.out.println("Invalid Stock.");

                    break;

                case 4:
                    portfolio.displayPortfolio();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}
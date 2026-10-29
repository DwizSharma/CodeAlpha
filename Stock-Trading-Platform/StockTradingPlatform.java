import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {
    static class Holding { int shares; double totalCost; }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, Double> prices = new LinkedHashMap<>();
        prices.put("NOVA", 125.00); prices.put("PINE", 84.00); prices.put("ORBIT", 210.00); prices.put("LUMEN", 56.50);
        Map<String, Holding> portfolio = new LinkedHashMap<>();
        for (String symbol : prices.keySet()) portfolio.put(symbol, new Holding());
        double cash = 10000.00;
        while (true) {
            System.out.println("\n=== Paper Trading Platform ===");
            System.out.printf("Available cash: $%.2f%n", cash);
            System.out.println("1. Market prices\n2. Buy shares\n3. Sell shares\n4. Portfolio\n0. Exit");
            int choice = readInt(in, "Choose: ", 0, 4);
            if (choice == 0) break;
            if (choice == 1) showMarket(prices);
            else if (choice == 4) showPortfolio(prices, portfolio, cash);
            else {
                showMarket(prices);
                System.out.print("Stock symbol: ");
                String symbol = in.nextLine().trim().toUpperCase();
                if (!prices.containsKey(symbol)) { System.out.println("That symbol is not on the sample market."); continue; }
                int quantity = readInt(in, "Number of shares: ", 1, 1000000);
                double tradeTotal = prices.get(symbol) * quantity;
                Holding holding = portfolio.get(symbol);
                if (choice == 2) {
                    if (tradeTotal > cash) { System.out.printf("You need $%.2f but have $%.2f.%n", tradeTotal, cash); continue; }
                    cash -= tradeTotal; holding.shares += quantity; holding.totalCost += tradeTotal;
                    System.out.printf("Bought %d %s share(s) for $%.2f.%n", quantity, symbol, tradeTotal);
                } else {
                    if (quantity > holding.shares) { System.out.printf("You own only %d %s share(s).%n", holding.shares, symbol); continue; }
                    cash += tradeTotal; holding.shares -= quantity;
                    holding.totalCost = holding.shares == 0 ? 0 : holding.totalCost * (holding.shares / (double)(holding.shares + quantity));
                    System.out.printf("Sold %d %s share(s) for $%.2f.%n", quantity, symbol, tradeTotal);
                }
            }
        }
        System.out.println("Session ended. Sample prices are fictional; no real trades were made.");
    }
    private static void showMarket(Map<String, Double> prices) { System.out.println("\nSample market prices:"); prices.forEach((s,p) -> System.out.printf("  %-8s $%.2f%n", s, p)); }
    private static void showPortfolio(Map<String, Double> prices, Map<String, Holding> portfolio, double cash) {
        System.out.println("\nYour portfolio:"); double total = cash;
        for (String s : prices.keySet()) { Holding h = portfolio.get(s); double value = h.shares * prices.get(s); total += value; System.out.printf("  %-8s %4d shares  value $%8.2f%n", s, h.shares, value); }
        System.out.printf("Cash: $%.2f | Total account value: $%.2f%n", cash, total);
    }
    private static int readInt(Scanner in, String prompt, int min, int max) { while (true) { System.out.print(prompt); try { int n=Integer.parseInt(in.nextLine().trim()); if(n>=min&&n<=max)return n; } catch(NumberFormatException ignored) {} System.out.printf("Enter a whole number from %d to %d.%n",min,max); } }
}

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    interface Observer {
        void update(String event);
    }

    interface Subject {
        void registerObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers();
    }

    static class StockMarket implements Subject {
        private List<Observer> observers = new ArrayList<>();
        private String stockName;
        private double stockPrice;

        public StockMarket(String stockName, double initialPrice) {
            this.stockName = stockName;
            this.stockPrice = initialPrice;
        }

        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers() {
            String event = stockName + " price changed to $" + stockPrice;
            for (Observer observer : observers) {
                observer.update(event);
            }
        }

        public void setStockPrice(double newPrice) {
            this.stockPrice = newPrice;
            notifyObservers();
        }
    }

    static class StockBroker implements Observer {
        private String name;

        public StockBroker(String name) {
            this.name = name;
        }

        public void update(String event) {
            System.out.println("StockBroker [" + name + "] received: " + event);
        }
    }

    static class StockTrader implements Observer {
        private String name;

        public StockTrader(String name) {
            this.name = name;
        }

        public void update(String event) {
            System.out.println("StockTrader [" + name + "] received: " + event);
        }
    }

    public static void main(String[] args) {
        StockMarket market = new StockMarket("GOOGL", 2800.00);

        Observer broker1 = new StockBroker("Alice");
        Observer broker2 = new StockBroker("Bob");
        Observer trader1 = new StockTrader("Charlie");
        Observer trader2 = new StockTrader("Diana");

        market.registerObserver(broker1);
        market.registerObserver(broker2);
        market.registerObserver(trader1);
        market.registerObserver(trader2);

        System.out.println("--- Price update to $2850.00 ---");
        market.setStockPrice(2850.00);

        System.out.println("--- Removing broker Bob ---");
        market.removeObserver(broker2);

        System.out.println("--- Price update to $2900.50 ---");
        market.setStockPrice(2900.50);
    }
}

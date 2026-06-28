public class StrategyPattern {

    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card ending in " + cardNumber.substring(cardNumber.length() - 4));
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal account: " + email);
        }
    }

    static class BitcoinPayment implements PaymentStrategy {
        private String walletAddress;

        public BitcoinPayment(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Bitcoin wallet: " + walletAddress);
        }
    }

    static class PaymentContext {
        private PaymentStrategy paymentStrategy;

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void executePayment(double amount) {
            if (paymentStrategy == null) {
                System.out.println("No payment strategy set.");
                return;
            }
            paymentStrategy.pay(amount);
        }
    }

    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234567890123456"));
        context.executePayment(299.99);

        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(149.50);

        context.setPaymentStrategy(new BitcoinPayment("1A2b3C4d5E6f7G8h9I0j"));
        context.executePayment(500.00);

        System.out.println("Switching back to Credit Card:");
        context.setPaymentStrategy(new CreditCardPayment("9876543210987654"));
        context.executePayment(75.00);
    }
}

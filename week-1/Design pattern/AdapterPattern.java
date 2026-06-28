public class AdapterPattern {

    interface PaymentProcessor {
        void processPayment(double amount);
    }

    static class PayPalGateway {
        public void makePayment(double totalAmount) {
            System.out.println("PayPal: Processing payment of $" + totalAmount + " via PayPal account.");
        }
    }

    static class StripeGateway {
        public void charge(double amountInCents) {
            System.out.println("Stripe: Charging " + (int) amountInCents + " cents ($" + (amountInCents / 100) + ") via Stripe.");
        }
    }

    static class PayPalAdapter implements PaymentProcessor {
        private PayPalGateway payPalGateway;

        public PayPalAdapter(PayPalGateway payPalGateway) {
            this.payPalGateway = payPalGateway;
        }

        public void processPayment(double amount) {
            payPalGateway.makePayment(amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor {
        private StripeGateway stripeGateway;

        public StripeAdapter(StripeGateway stripeGateway) {
            this.stripeGateway = stripeGateway;
        }

        public void processPayment(double amount) {
            stripeGateway.charge(amount * 100);
        }
    }

    public static void main(String[] args) {
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());

        System.out.println("Processing payment via PayPal:");
        payPalProcessor.processPayment(150.75);

        System.out.println("Processing payment via Stripe:");
        stripeProcessor.processPayment(89.99);

        System.out.println("Switching processors dynamically:");
        PaymentProcessor[] processors = {payPalProcessor, stripeProcessor};
        for (PaymentProcessor processor : processors) {
            processor.processPayment(200.00);
        }
    }
}

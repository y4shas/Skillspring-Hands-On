public class FinancialForecasting {

    static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting (Recursive) ===");

        double presentValue = 10000.00;
        double growthRate   = 0.08;

        System.out.printf("\nPresent Value : $%.2f%n", presentValue);
        System.out.printf("Annual Growth : %.0f%%%n", growthRate * 100);

        System.out.println("\n--- Predictions ---");

        int[] years = {1, 3, 5, 10};
        for (int y : years) {
            double futureValue = calculateFutureValue(presentValue, growthRate, y);
            System.out.printf("After %2d year(s) : $%,.2f%n", y, futureValue);
        }

        System.out.println("\n--- Additional Scenarios ---");

        double[] rates = {0.05, 0.10, 0.15};
        for (double rate : rates) {
            double fv = calculateFutureValue(presentValue, rate, 5);
            System.out.printf("5 years at %3.0f%% growth : $%,.2f%n", rate * 100, fv);
        }

        System.out.println("\n=== Recursion vs Iteration Analysis ===");
        System.out.println("Recursive approach:");
        System.out.println("  Time  : O(n) - one recursive call per year");
        System.out.println("  Space : O(n) - call stack depth equals number of years");
        System.out.println("  Base  : years == 0 -> return presentValue");
        System.out.println("  Step  : F(PV, r, n) = F(PV*(1+r), r, n-1)");
        System.out.println();
        System.out.println("Iterative equivalent:");
        System.out.println("  Time  : O(n) - same loop count");
        System.out.println("  Space : O(1) - no call stack overhead");
        System.out.println("  Formula: FV = PV * (1 + r)^n");
        System.out.println();
        System.out.println("Recommendation: For large n, prefer the closed-form formula");
        System.out.println("  FV = PV * (1 + r)^n gives O(log n) with fast exponentiation");

        System.out.println("\n--- Closed-Form Verification (n=10, r=8%) ---");
        double recursive  = calculateFutureValue(presentValue, growthRate, 10);
        double closedForm = presentValue * Math.pow(1 + growthRate, 10);
        System.out.printf("Recursive  : $%,.2f%n", recursive);
        System.out.printf("Closed-form: $%,.2f%n", closedForm);
        System.out.printf("Difference : $%.6f%n", Math.abs(recursive - closedForm));
    }
}

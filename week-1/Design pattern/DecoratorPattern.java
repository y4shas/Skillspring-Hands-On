public class DecoratorPattern {

    interface Coffee {
        double getCost();
        String getDescription();
    }

    static class SimpleCoffee implements Coffee {
        public double getCost() {
            return 1.00;
        }

        public String getDescription() {
            return "Simple Coffee";
        }
    }

    static abstract class CoffeeDecorator implements Coffee {
        protected Coffee coffee;

        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

        public double getCost() {
            return coffee.getCost();
        }

        public String getDescription() {
            return coffee.getDescription();
        }
    }

    static class MilkDecorator extends CoffeeDecorator {
        public MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        public double getCost() {
            return coffee.getCost() + 0.50;
        }

        public String getDescription() {
            return coffee.getDescription() + ", Milk";
        }
    }

    static class SugarDecorator extends CoffeeDecorator {
        public SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        public double getCost() {
            return coffee.getCost() + 0.25;
        }

        public String getDescription() {
            return coffee.getDescription() + ", Sugar";
        }
    }

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " -> $" + coffee.getCost());

        Coffee coffeeWithMilk = new MilkDecorator(coffee);
        System.out.println(coffeeWithMilk.getDescription() + " -> $" + coffeeWithMilk.getCost());

        Coffee coffeeWithMilkAndSugar = new SugarDecorator(coffeeWithMilk);
        System.out.println(coffeeWithMilkAndSugar.getDescription() + " -> $" + coffeeWithMilkAndSugar.getCost());

        Coffee doubleSweetCoffee = new SugarDecorator(new SugarDecorator(new MilkDecorator(new SimpleCoffee())));
        System.out.println(doubleSweetCoffee.getDescription() + " -> $" + doubleSweetCoffee.getCost());
    }
}

public class SingletonPattern {

    static class Logger {
        private static Logger instance;
        private int logCount;

        private Logger() {
            logCount = 0;
        }

        public static Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }

        public void log(String message) {
            logCount++;
            System.out.println("[LOG #" + logCount + "] " + message);
        }

        public int getLogCount() {
            return logCount;
        }
    }

    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        Logger logger3 = Logger.getInstance();

        logger1.log("Application started");
        logger2.log("User logged in");
        logger3.log("Data fetched successfully");

        System.out.println("logger1 == logger2: " + (logger1 == logger2));
        System.out.println("logger2 == logger3: " + (logger2 == logger3));
        System.out.println("Total logs recorded: " + logger1.getLogCount());
    }
}

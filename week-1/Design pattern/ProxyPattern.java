public class ProxyPattern {

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromDisk();
        }

        private void loadFromDisk() {
            System.out.println("Loading image from disk: " + filename);
        }

        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    static class ProxyImage implements Image {
        private String filename;
        private RealImage realImage;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo_vacation.jpg");
        Image image2 = new ProxyImage("photo_wedding.jpg");

        System.out.println("First call to image1.display():");
        image1.display();

        System.out.println("Second call to image1.display() (no reload):");
        image1.display();

        System.out.println("First call to image2.display():");
        image2.display();

        System.out.println("Second call to image2.display() (no reload):");
        image2.display();
    }
}

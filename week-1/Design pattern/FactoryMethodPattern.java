public class FactoryMethodPattern {

    interface Document {
        void open();
    }

    static class WordDocument implements Document {
        public void open() {
            System.out.println("Opening Word document (.docx)");
        }
    }

    static class PdfDocument implements Document {
        public void open() {
            System.out.println("Opening PDF document (.pdf)");
        }
    }

    static class ExcelDocument implements Document {
        public void open() {
            System.out.println("Opening Excel document (.xlsx)");
        }
    }

    static abstract class DocumentFactory {
        public abstract Document createDocument();

        public void openDocument() {
            Document doc = createDocument();
            doc.open();
        }
    }

    static class WordFactory extends DocumentFactory {
        public Document createDocument() {
            return new WordDocument();
        }
    }

    static class PdfFactory extends DocumentFactory {
        public Document createDocument() {
            return new PdfDocument();
        }
    }

    static class ExcelFactory extends DocumentFactory {
        public Document createDocument() {
            return new ExcelDocument();
        }
    }

    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordFactory();
        DocumentFactory pdfFactory = new PdfFactory();
        DocumentFactory excelFactory = new ExcelFactory();

        System.out.println("Using WordFactory:");
        wordFactory.openDocument();

        System.out.println("Using PdfFactory:");
        pdfFactory.openDocument();

        System.out.println("Using ExcelFactory:");
        excelFactory.openDocument();

        Document doc1 = wordFactory.createDocument();
        Document doc2 = pdfFactory.createDocument();
        Document doc3 = excelFactory.createDocument();

        System.out.println("Direct document creation:");
        doc1.open();
        doc2.open();
        doc3.open();
    }
}

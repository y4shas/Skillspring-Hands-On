public class BuilderPattern {

    static class Computer {
        private String cpu;
        private String ram;
        private String storage;

        private Computer(Builder builder) {
            this.cpu = builder.cpu;
            this.ram = builder.ram;
            this.storage = builder.storage;
        }

        public String getCpu() { return cpu; }
        public String getRam() { return ram; }
        public String getStorage() { return storage; }

        public String toString() {
            return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage + "]";
        }

        static class Builder {
            private String cpu;
            private String ram;
            private String storage;

            public Builder setCPU(String cpu) {
                this.cpu = cpu;
                return this;
            }

            public Builder setRAM(String ram) {
                this.ram = ram;
                return this;
            }

            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel Core i9-13900K")
                .setRAM("64GB DDR5")
                .setStorage("2TB NVMe SSD")
                .build();

        Computer officePC = new Computer.Builder()
                .setCPU("Intel Core i5-12400")
                .setRAM("16GB DDR4")
                .setStorage("512GB SSD")
                .build();

        Computer serverPC = new Computer.Builder()
                .setCPU("AMD EPYC 7763")
                .setRAM("256GB ECC DDR4")
                .setStorage("10TB HDD RAID")
                .build();

        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("Office PC: " + officePC);
        System.out.println("Server PC: " + serverPC);
    }
}

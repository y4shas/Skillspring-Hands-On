public class CommandPattern {

    interface Command {
        void execute();
    }

    static class Light {
        private String location;

        public Light(String location) {
            this.location = location;
        }

        public void on() {
            System.out.println(location + " light is ON");
        }

        public void off() {
            System.out.println(location + " light is OFF");
        }
    }

    static class LightOnCommand implements Command {
        private Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.on();
        }
    }

    static class LightOffCommand implements Command {
        private Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        public void execute() {
            light.off();
        }
    }

    static class RemoteControl {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            if (command != null) {
                command.execute();
            } else {
                System.out.println("No command set on remote.");
            }
        }
    }

    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");

        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command bedroomOn = new LightOnCommand(bedroomLight);
        Command bedroomOff = new LightOffCommand(bedroomLight);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(livingRoomOn);
        remote.pressButton();

        remote.setCommand(bedroomOn);
        remote.pressButton();

        remote.setCommand(livingRoomOff);
        remote.pressButton();

        remote.setCommand(bedroomOff);
        remote.pressButton();
    }
}

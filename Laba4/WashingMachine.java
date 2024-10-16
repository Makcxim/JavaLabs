public class WashingMachine extends Appliance {

    public WashingMachine(String model) {
        super(model);
    }

    @Override
    public void turnOn() {
        System.out.println("Стиральная машина включена");
    }
}

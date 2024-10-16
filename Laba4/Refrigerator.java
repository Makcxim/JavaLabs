public class Refrigerator extends Appliance {

    public Refrigerator(String model) {
        super(model);
    }

    @Override
    public void turnOn() {
        System.out.println("Холодильник включен");
    }
}

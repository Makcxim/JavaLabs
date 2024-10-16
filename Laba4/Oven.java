public class Oven extends Appliance {

    public Oven(String model) {
        super(model);
    }

    @Override
    public void turnOn() {
        System.out.println("Духовка включена");
    }
}

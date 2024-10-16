public class Appliance {
    private String model;

    public Appliance(String model) {
        this.model = model;
    }

    public void turnOn() {
        System.out.println("Прибор включён");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void displayInfo() {
        System.out.println("Модель: " + model);
    }
}

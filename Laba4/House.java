import java.util.ArrayList;

public class House {
    private ArrayList<Appliance> appliances;

    public House() {
        appliances = new ArrayList<>();
    }

    public void addAppliance(Appliance appliance) {
        if (appliance != null) {
            appliances.add(appliance);
        }
    }

    public Appliance popAppliance() {
        if (!appliances.isEmpty()) {
            return appliances.removeLast();
        } else {
            return null;
        }
    }

    public void turnAllOn() {
        for (Appliance appliance : appliances) {
            appliance.turnOn();
        }
    }

    public int countWashingMachines() {
        int count = 0;
        for (Appliance appliance : appliances) {
            if (appliance instanceof WashingMachine) {
                count++;
            }
        }
        return count;
    }

    public int countRefrigerators() {
        int count = 0;
        for (Appliance appliance : appliances) {
            if (appliance instanceof Refrigerator) {
                count++;
            }
        }
        return count;
    }

    public int countOvens() {
        int count = 0;
        for (Appliance appliance : appliances) {
            if (appliance instanceof Oven) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(ArrayList<Appliance> appliances) {
        this.appliances = appliances;
    }

    public void displayAllAppliancesInfo() {
        for (Appliance appliance : appliances) {
            appliance.displayInfo();
        }
    }
}

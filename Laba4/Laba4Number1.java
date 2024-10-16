public class Laba4Number1 {
    public static void main(String[] args) {
        House house = new House();

        WashingMachine wm = new WashingMachine("WM123");
        Refrigerator rf = new Refrigerator("RF456");
        Oven ov = new Oven("OV789");

        house.addAppliance(wm);
        house.addAppliance(rf);
        house.addAppliance(ov);

        house.turnAllOn();

        System.out.println("Количество стиральных машин: " + house.countWashingMachines());
        System.out.println("Количество холодильников: " + house.countRefrigerators());
        System.out.println("Количество духовок: " + house.countOvens());

        house.displayAllAppliancesInfo();

        house.popAppliance();
        System.out.println("После удаления одного прибора:");

        house.turnAllOn();

        System.out.println("Количество приборов: " + house.getAppliances().size());
    }
}

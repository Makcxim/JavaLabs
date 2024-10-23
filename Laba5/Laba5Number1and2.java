public class Laba5Number1and2 {
    public static void main(String[] args) {
        Hammer hammer = new Hammer();
        Screwdriver screwdriver = new Screwdriver();
        Wrench wrench = new Wrench();
        DefaultScrewdriver defaultScrewdriver = new DefaultScrewdriver();

        defaultScrewdriver.use();
        hammer.use();
        screwdriver.use();
        screwdriver.charge();
        wrench.use();
    }
}

abstract class Tool {
    abstract void use();
}

interface ElectricTool {
    void charge();
}

class Hammer extends Tool {
    @Override
    void use() {
        System.out.println("Использую молоток для забивания гвоздей.");
    }
}


class DefaultScrewdriver extends Tool {
    @Override
    void use() {
        System.out.println("Использую отвертку для закручивания винтов.");
    }
}

class Screwdriver extends Tool implements ElectricTool {
    @Override
    void use() {
        System.out.println("Использую электрическую отвертку для закручивания винтов.");
    }

    @Override
    public void charge() {
        System.out.println("Заряжаю электрическую отвертку.");
    }
}

class Wrench extends Tool {
    @Override
    void use() {
        System.out.println("Использую гаечный ключ для затягивания болтов.");
    }
}

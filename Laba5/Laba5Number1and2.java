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
    protected abstract void use();
}

interface ElectricTool {
    void charge();
}

class Hammer extends Tool {
    @Override
    public void use() {
        System.out.println("Использую молоток для забивания гвоздей.");
    }
}


class DefaultScrewdriver extends Tool {
    @Override
    public void use() {
        System.out.println("Использую отвертку для закручивания винтов.");
    }
}

class Screwdriver extends Tool implements ElectricTool {
    @Override
    public void use() {
        System.out.println("Использую электрическую отвертку для закручивания винтов.");
    }

    @Override
    public void charge() {
        System.out.println("Заряжаю электрическую отвертку.");
    }
}

class Wrench extends Tool {
    @Override
    public void use() {
        System.out.println("Использую гаечный ключ для затягивания болтов.");
    }
}

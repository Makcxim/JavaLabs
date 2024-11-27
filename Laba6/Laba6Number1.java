public class Laba6Number1 {
    /*
    Singleton обеспечивает создание единственного экземпляра класса
    и предоставляет глобальную точку доступа к нему.
    Для управления доступом к общему ресурсу (например, база данных, файл журнала).
    Чтобы предотвратить создание лишних объектов, особенно если они затратны по ресурсам.
    */

    public static void main(String[] args) {
        InputManager inputManager = InputManager.getInstance();

        inputManager.registerKeyBinding("Jump", "Space");
        inputManager.registerKeyBinding("MoveLeft", "A");
        inputManager.registerKeyBinding("MoveRight", "D");

        inputManager.processInput("Space");
        inputManager.processInput("A");
        inputManager.processInput("D");
        inputManager.processInput("W");
    }
}

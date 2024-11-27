public class Laba6Number2 {
    /*
    Factory Method предоставляет интерфейс для создания объектов,
    но позволяет подклассам изменять тип создаваемых объектов.
    Когда заранее неизвестен точный класс объектов, которые нужно создать.
    Для увеличения гибкости кода при добавлении новых типов объектов.
    */

    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactory();

        Vehicle car = factory.createVehicle("car");
        car.move();

        Vehicle boat = factory.createVehicle("boat");
        boat.move();
        try {
            Vehicle unknown = factory.createVehicle("plane");
            unknown.move();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}


interface Vehicle {
    void move();
}

class Car implements Vehicle {
    @Override
    public void move() {
        System.out.println("Машина едет по дороге.");
    }
}

class Boat implements Vehicle {
    @Override
    public void move() {
        System.out.println("Лодка плывёт по воде.");
    }
}

class VehicleFactory {
    public Vehicle createVehicle(String type) {
        if (type.equalsIgnoreCase("car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("boat")) {
            return new Boat();
        } else {
            throw new IllegalArgumentException("Неизвестный тип транспорта: " + type);
        }
    }
}

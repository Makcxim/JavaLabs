public class Laba4Number3and4 {
    public static void main(String[] args) {
        // Point
        Point p1 = new Point(3, 4);
        System.out.println("точка p1: " + p1);
        System.out.println("расстояние от (0,0): " + p1.distanceFromOrigin());

        Point p2 = new Point(6, 8);
        System.out.println("расстояние между p1 и p2: " + p1.distanceTo(p2));

        Point topLeft = new Point(0, 10);
        Point bottomRight = new Point(10, 0);
        System.out.println("p1 внутри квадрата: " + p1.isInRectangle(topLeft, bottomRight));

        Point center = new Point(0, 0);
        double radius = 5.0;
        System.out.println("p1 внутри круга: " + p1.isInCircle(center, radius));

        p1.rotate(90);
        System.out.println("p1 после поворота на 90 градусов: " + p1);

        System.out.println("\n");

        // Point3D
        Point3D p3d1 = new Point3D(1, 2, 3);
        System.out.println("Point3D p3d1: " + p3d1);
        System.out.println("расстояние от (0,0): " + p3d1.distanceFromOrigin());

        Point3D p3d2 = new Point3D(4, 5, 6);
        System.out.println("расстояние между p3d1 и p3d2: " + p3d1.distanceTo(p3d2));

        Point3D corner1 = new Point3D(0, 0, 0);
        Point3D corner2 = new Point3D(5, 5, 5);
        System.out.println("p3d1 внутри куба: " + p3d1.isInBox(corner1, corner2));

        Point3D sphereCenter = new Point3D(0, 0, 0);
        double sphereRadius = 5.0;
        System.out.println("p3d1 внутри сферы: " + p3d1.isInSphere(sphereCenter, sphereRadius));

        p3d1.rotate(90, 0, 0);
        System.out.println("p3d1 после поворота на 90 градусов по X: " + p3d1);
    }
}

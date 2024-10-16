public class Point {
    private int x;
    private int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(int x, int y) {
        setLocation(x, y);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double distanceFromOrigin() {
        return Math.hypot(x, y);
    }

    public double distanceTo(Point other) {
        if (other == null) {
            throw new IllegalArgumentException("The other point cannot be null.");
        }
        if (this.equals(other)) {
            System.out.println("Points are the same.");
            return 0.0;
        }
        return Math.hypot(this.x - other.x, this.y - other.y);
    }

    public boolean isInRectangle(Point topLeft, Point bottomRight) {
        return (x >= topLeft.getX() && x <= bottomRight.getX()) &&
                (y <= topLeft.getY() && y >= bottomRight.getY());
    }

    public boolean isInCircle(Point center, double radius) {
        double distance = Math.hypot(this.x - center.x, this.y - center.y);
        return distance <= radius;
    }

    public void rotate(double angleDegrees) {
        double angleRadians = Math.toRadians(angleDegrees);
        int newX = (int) Math.round(x * Math.cos(angleRadians) - y * Math.sin(angleRadians));
        int newY = (int) Math.round(x * Math.sin(angleRadians) + y * Math.cos(angleRadians));
        setLocation(newX, newY);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            Point other = (Point) obj;
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }
}

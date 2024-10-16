public class Point3D extends Point {
    private int z;

    public Point3D() {
        this(0, 0, 0);
    }

    public Point3D(int x, int y, int z) {
        super(x, y);
        setLocation(x, y, z);
    }

    public void setLocation(int x, int y, int z) {
        super.setLocation(x, y);
        this.z = z;
    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        this.z = 0;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + z + ")";
    }

    @Override
    public double distanceFromOrigin() {
        return Math.sqrt(getX() * getX() + getY() * getY() + z * z);
    }

    public double distanceTo(Point3D other) {
        if (other == null) {
            throw new IllegalArgumentException("The other point cannot be null.");
        }
        if (this.equals(other)) {
            System.out.println("Points are the same.");
            return 0.0;
        }
        int dx = this.getX() - other.getX();
        int dy = this.getY() - other.getY();
        int dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public boolean isInBox(Point3D corner1, Point3D corner2) {
        int xMin = Math.min(corner1.getX(), corner2.getX());
        int xMax = Math.max(corner1.getX(), corner2.getX());
        int yMin = Math.min(corner1.getY(), corner2.getY());
        int yMax = Math.max(corner1.getY(), corner2.getY());
        int zMin = Math.min(corner1.getZ(), corner2.getZ());
        int zMax = Math.max(corner1.getZ(), corner2.getZ());

        return (getX() >= xMin && getX() <= xMax) &&
                (getY() >= yMin && getY() <= yMax) &&
                (z >= zMin && z <= zMax);
    }

    public boolean isInSphere(Point3D center, double radius) {
        int dx = this.getX() - center.getX();
        int dy = this.getY() - center.getY();
        int dz = this.z - center.z;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        return distance <= radius;
    }

    public void rotate(double angleXDegrees, double angleYDegrees, double angleZDegrees) {
        // перевод градусов в радианы
        double angleX = Math.toRadians(angleXDegrees);
        double angleY = Math.toRadians(angleYDegrees);
        double angleZ = Math.toRadians(angleZDegrees);

        // поворот вокруг оси X
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        int y1 = (int) Math.round(getY() * cosX - z * sinX);
        int z1 = (int) Math.round(getY() * sinX + z * cosX);

        // поворот вокруг оси Y
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);
        int x1 = (int) Math.round(getX() * cosY + z1 * sinY);
        int z2 = (int) Math.round(-getX() * sinY + z1 * cosY);

        // поворот вокруг оси Z
        double cosZ = Math.cos(angleZ);
        double sinZ = Math.sin(angleZ);
        int x2 = (int) Math.round(x1 * cosZ - y1 * sinZ);
        int y2 = (int) Math.round(x1 * sinZ + y1 * cosZ);

        setLocation(x2, y2, z2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point3D) {
            Point3D other = (Point3D) obj;
            return super.equals(other) && this.z == other.z;
        }
        return false;
    }
}

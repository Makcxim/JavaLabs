public class ComplexNumber {
    private double real;
    private double imaginary;
    private int precision;

    public ComplexNumber(double real, double imaginary, int precision) {
        this.real = real;
        this.imaginary = imaginary;
        this.precision = precision;
    }

    public ComplexNumber(double real, double imaginary) {
        this(real, imaginary, 2);
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    // Метод для вывода алгебраической формы
    public void displayAlgebraicForm() {
        String format = "%." + precision + "f";
        System.out.println("Алгебраическая форма:");
        System.out.println(String.format(format, real) + " + " + String.format(format, imaginary) + "i");
    }

    // Метод для преобразования в полярную форму
    public void displayPolarForm() {
        double r = Math.hypot(real, imaginary); // Вычисляем модуль (гипотенузу)
        double theta = Math.atan2(imaginary, real); // Вычисляем арктангенс между x,y и осью X

        String format = "%." + precision + "f";
        System.out.println("Полярная форма:");
        System.out.println("r = " + String.format(format, r));
        System.out.println("θ = " + String.format(format, theta) + " радиан");
    }
}

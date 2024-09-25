import java.util.Scanner;
import static java.lang.Math.*;

public class Number2 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a: ");
        int a = reader.nextInt();
        System.out.println("Enter b: ");
        int b = reader.nextInt();
        reader.close();

        int max = max(a, b);
        int min = min(a, b);
        System.out.println("max = " + max(a, b));
        System.out.println("min = " + min(a, b));

        double d = (max - 2 * sqrt(min) + 4.2 * min) / (1 + (double) max / min);
        System.out.println("result = " + d);
    }
}

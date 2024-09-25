import java.util.Scanner;
import static java.lang.Math.*;

public class Number1 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a: ");
        int a = reader.nextInt();
        System.out.println("Enter b: ");
        int b = reader.nextInt();
        reader.close();

        if (a * b > 100) {
            System.out.println("result = " + 3 * tan(b));
            return;
        }
        System.out.println("result = " + a * 5);
    }
}
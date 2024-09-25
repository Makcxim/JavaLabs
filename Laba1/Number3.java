import java.util.Scanner;
import static java.lang.Math.*;

public class Number3 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter x: ");
        int x = reader.nextInt();
        reader.close();

        if (x<=-3) {
            System.out.println("y =  " + (pow(x, 3) + 1));
            System.out.println("f =  " + pow(Math.E, sin(x)));
            return;
        }
        if (x <= 4) {
            System.out.println("y =  " + (x * (1 + pow(2, x))));
            System.out.println("f =  " + pow(x, 4));
            return;
        }
        System.out.println("y =  " + tan(x));
        System.out.println("f =  " + pow(tan(x), 1/5.));
    }
}
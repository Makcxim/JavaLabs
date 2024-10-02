import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Laba2Number3 {
    public static void main(String[] args) {
        int rows = 5;
        int cols = 5;
        int[][] matrix = new int[rows][cols];

        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(199) - 99;
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите значение, которое нужно исключить из вектора B: ");
        int givenValue = scanner.nextInt();

        ArrayList<Integer> B = new ArrayList<>();
        for (int [] row : matrix) {
            for (int element : row) {
                if (element != givenValue) {
                    B.add(element);
                }
            }
        }

        System.out.println("Вектор B (элементы матрицы, не равные " + givenValue + "):");
        for (int j : B) {
            System.out.print(j + " ");
        }
    }
}

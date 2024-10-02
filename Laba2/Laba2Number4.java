import java.util.Random;

public class Laba2Number4 {
    public static void main(String[] args) {
        int n = 5;

        int[][] A = new int[n][n];
        Random random = new Random();

        System.out.println("Матрица A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = random.nextInt(21) - 10;
                System.out.printf("%4d", A[i][j]);
            }
            System.out.println();
        }

        int sum = 0;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            int element = A[i][i + 1];
            if (element > 0) {
                sum += element;
                count++;
            }
        }

        if (count > 0) {
            double average = (double) sum / count;
            System.out.println("Среднее арифметическое положительных элементов параллели главной диагонали выше диагонали: " + average);
        } else {
            System.out.println("Положительные элементы на указанной диагонали отсутствуют.");
        }
    }
}

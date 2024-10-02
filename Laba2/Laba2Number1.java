//Определить дополнительный массив, состоящий из неповторяющихся элементов исходного массива и вывести его на экран;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Laba2Number1 {
    // если не хотим вывести элементы которые повторились, то что было повторено - не выведется

    public static void main(String[] args) {
        int[] originalArray = new int[20];
        Random random = new Random();
        for (int i = 0; i < originalArray.length; i++) {
            originalArray[i] = random.nextInt(199) - 99;
        }

        System.out.println("Исходный массив:");
        for (int num : originalArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        double[] reciprocalArray = CreateReciprocalFraction(originalArray);

        System.out.println("Массив обратных элементов:");
        for (double num : reciprocalArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        ArrayList<Integer> uniqueList = new ArrayList<>();
        for (int i = 0; i < originalArray.length; i++) {
            boolean isUnique = true;
            for (int j = 0; j < originalArray.length; j++) {
                if (i != j && originalArray[i] == originalArray[j]) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniqueList.add(originalArray[i]);
            }
        }

        System.out.println("Массив неповторяющихся элементов:");
        for (int num : uniqueList) {
            System.out.print(num + " ");
        }
    }

    public static double[] CreateReciprocalFraction(int[] array) {
        double[] reciprocalArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            try {
                reciprocalArray[i] = 1.0 / array[i];
            } catch (ArithmeticException e) {
                System.out.println("Деление на ноль в индексе " + i);
                reciprocalArray[i] = 0;
            }
        }
        return reciprocalArray;
    }

// если хотим вывести элементы которые повторились, но без их повторок

//    public static void main(String[] args) {
//        int[] originalArray = new int[20];
//        Random random = new Random();
//        for (int i = 0; i < originalArray.length; i++) {
//            originalArray[i] = random.nextInt(199) - 99; // Генерируем числа от -99 до 99
//        }
//
//        System.out.println("Исходный массив:");
//        for (int num : originalArray) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
//
//        double[] reciprocalArray = CreateReciprocalFraction(originalArray);
//
//        System.out.println("Массив обратных элементов:");
//        for (double num : reciprocalArray) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
//
//        Set<Integer> uniqueSet = new HashSet<>();
//        for (int num : originalArray) {
//            uniqueSet.add(num);
//        }
//
//        System.out.println("Массив неповторяющихся элементов:");
//        for (int num : uniqueSet) {
//            System.out.print(num + " ");
//        }
//    }
//
//    public static double[] CreateReciprocalFraction(int[] array) {
//        double[] reciprocalArray = new double[array.length];
//        for (int i = 0; i < array.length; i++) {
//            try {
//                reciprocalArray[i] = 1.0 / array[i];
//            } catch (ArithmeticException e) {
//                System.out.println("Деление на ноль в индексе " + i);
//                reciprocalArray[i] = 0; // Или любое другое значение по вашему усмотрению
//            }
//        }
//        return reciprocalArray;
//    }

}

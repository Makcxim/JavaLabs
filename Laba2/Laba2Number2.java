import java.util.ArrayList;

public class Laba2Number2 {
    public static void main(String[] args) {
        int[] b = {3, -1, 4, -5, 2, -3, 6, -2};
        ArrayList<Integer> c = new ArrayList<>();
        for (int k : b) {
            if (k < 0) {
                c.add(k);
            }
        }

        // (selection sort)
        for (int i = 0; i < c.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < c.size(); j++) {
                if (c.get(j) < c.get(minIndex)) {
                    minIndex = j;
                }
            }
            int temp = c.get(minIndex);
            c.set(minIndex, c.get(i));
            c.set(i, temp);
        }

        System.out.println("Отрицательные элементы массива b(n), отсортированные по возрастанию:");
        for (Integer kek : c) {
            System.out.print(kek + " ");
        }
    }
}


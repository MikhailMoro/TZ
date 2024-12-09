import java.io.*;
import java.util.*;

public class Task4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Введите путь к файлу с массивом чисел");
            return;
        }

        try {
            List<Integer> nums = new ArrayList<>();
            Scanner fileScanner = new Scanner(new File(args[0]));
            while (fileScanner.hasNextInt()) {
                nums.add(fileScanner.nextInt());
            }
            fileScanner.close();

            System.out.println(minMoves(nums));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

    public static int minMoves(List<Integer> nums) {
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}

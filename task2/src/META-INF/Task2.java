import java.io.*;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }

        try {
            Scanner circleFile = new Scanner(new File(args[0]));
            double centerX = circleFile.nextDouble();
            double centerY = circleFile.nextDouble();
            double radius = circleFile.nextDouble();
            circleFile.close();

            Scanner dotFile = new Scanner(new File(args[1]));
            while (dotFile.hasNextDouble()) {
                double x = dotFile.nextDouble();
                double y = dotFile.nextDouble();
                int position = determinePosition(x, y, centerX, centerY, radius);
                System.out.println(position);
            }
            dotFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
    }

    public static int determinePosition(double x, double y, double centerX, double centerY, double radius) {
        double disSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        double radiusSquared = Math.pow(radius, 2);

        if (disSquared < radiusSquared) {
            return 1;
        } else if (disSquared == radiusSquared) {
            return 0;
        } else {
            return 2;
        }
    }
}

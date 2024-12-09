public class Task1 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Введите: n и m");
            return;
        }

        try {
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);
            System.out.println(circularPath(n, m));
        } catch (NumberFormatException e) {
            System.out.println("Только числа!");
        }
    }

    public static String circularPath(int n, int m) {
        StringBuilder path = new StringBuilder();
        int currentIndex = 0;

        for (int i = 0; i < n; i++) {
            path.append(currentIndex + 1);
            currentIndex = (currentIndex + m - 1) % n;
        }

        return path.toString();
    }
}

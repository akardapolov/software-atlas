import java.util.List;

public class Main {

    static String classify(int n) {
        if (n < 0) return "negative";
        if (n == 0) return "zero";
        return "positive";
    }

    public static void main(String[] args) {

        System.out.println("--- if / else ---");
        for (int n : List.of(-3, 0, 7)) {
            System.out.println(n + " → " + classify(n));
        }

        System.out.println("\n--- switch statement ---");
        String day = "sat";
        switch (day) {
            case "mon", "tue", "wed", "thu", "fri":
                System.out.println("weekday");
                break;
            case "sat", "sun":
                System.out.println("weekend");
                break;
            default:
                System.out.println("unknown");
        }

        System.out.println("\n--- switch expression (Java 14+) ---");
        int score = 87;
        String grade = switch (score / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };
        System.out.println("score " + score + " → grade " + grade);

        System.out.println("\n--- for loop ---");
        int sum = 0;
        for (int i = 1; i <= 5; i++) sum += i;
        System.out.println("sum 1..5 = " + sum);

        System.out.println("\n--- while loop ---");
        int x = 1;
        while (x < 20) x *= 2;
        System.out.println("first power of two >= 20: " + x);

        System.out.println("\n--- enhanced for (foreach) ---");
        int idx = 0;
        for (int v : List.of(10, 20, 30)) {
            System.out.println("idx=" + idx + " v=" + v);
            idx++;
        }

        System.out.println("\n--- break / continue ---");
        for (int n = 1; n < 10; n++) {
            if (n % 2 == 1) continue;
            if (n > 6) break;
            System.out.println("even <= 6: " + n);
        }

        System.out.println("\n--- labeled break ---");
        outer:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i * j > 4) {
                    System.out.println("breaking outer at i,j=" + i + "," + j);
                    break outer;
                }
            }
        }

        System.out.println("\n--- summary ---");
        System.out.println("Java control flow: if, switch, for/while, break/continue, labels");
    }
}

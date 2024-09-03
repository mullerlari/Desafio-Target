import java.util.Scanner;

public class Fibonacci{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe um numero para verificar se pertence a sequencia de Fibonacci: ");
        int number = scanner.nextInt();
        scanner.close();

        if (isFibonacci(number)) {
            System.out.println(number + " pertence a sequencia de Fibonacci.");
        } else {
            System.out.println(number + " nao pertence a sequencia de Fibonacci.");
        }
    }

    public static boolean isFibonacci(int number) {
        if (number < 0) {
            return false;
        }

        int a = 0;
        int b = 1;

        if (number == a || number == b) {
            return true;
        }

        while (b <= number) {
            int next = a + b;
            a = b;
            b = next;

            if (b == number) {
                return true;
            }
        }

        return false;
    }
}
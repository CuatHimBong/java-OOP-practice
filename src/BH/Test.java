package BH;

public class Test {
    public static int addDigits(char[] a) {
        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            int digit = Character.getNumericValue(a[i]);
            sum += digit;
        }

        return sum;
    }

    public static String turnToString(int m) {
        String mStr = Integer.toString(m);
        return mStr;
    }

    public static void main(String[] args) {
        char[] argsArray = args[0].toCharArray();

        while (true) {
            int sum = addDigits(argsArray);
            argsArray = turnToString(sum).toCharArray();
            if (argsArray.length == 1) {
                break;
            }
        }

        System.out.println(argsArray);
    }
}

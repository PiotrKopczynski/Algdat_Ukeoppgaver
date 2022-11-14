package videoforelesning38;

public class Rekursjon {
    public static void main(String[] args) {
        int value = 6;
        /*
        int result = recursiveFunction(value);
        System.out.println("Main: "+result);
         */
        int five_factorial = factiorial(5);
        System.out.println("5! = " + five_factorial);

    }

    static int recursiveFunction(int value) {
        System.out.println("Recursive function: "+value);
        System.out.println(value);
        if (value<=5) {
            return -9;
        }
        else {
            return recursiveFunction(value-1);
        }
    }

    static int factiorial(int n){
        if (n==1) {
            return 1;
        }
        return n*factiorial(n-1);
    }
}

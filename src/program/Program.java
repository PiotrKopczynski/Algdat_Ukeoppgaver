package program;
import eksempelklasser.*;
import hjelpeklasser.*;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {

        int n = 5;
        int k = 2;
        System.out.println(sum(k,n));


    }

    public static int sum(int k, int n) {
        if (n == k) {
            return n;
        }
        return sum(k, n-1) + n;
    }
}

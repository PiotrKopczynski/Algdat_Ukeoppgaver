package ukeoppgaver34;

import java.util.*;

public class Ukeoppgaver34 {
    public static void main(String[] args) {
        int n = 100_000;
        int antall = 2_000;
        long tid = 0;
        int a[] = randPerm(n);

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) kostnader(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Faste kostnader: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) maks1(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks1-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) maks2(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks2-metoden: " + tid + " millisek");

        tid = System.currentTimeMillis();  // leser av klokken
        for (int i = 0; i < antall; i++) maks3(a);
        tid = System.currentTimeMillis() - tid;  // medgått tid
        System.out.println("Maks3-metoden: " + tid + " millisek");
    }

    public static int maks1(int[] a) {
        if(a.length<1) {
            throw new java.util.NoSuchElementException("Tabellen er tom!");
        }
        int m = 0;
        for (int i = 1;i<a.length;i++) {
            if (a[i]>a[m]) {
                m=i;
            }
        }
        return m;
    }

    public static int maks2(int[] a) {
        int m = 0;
        int maksverdi = a[0];
        for(int i = 1;i<a.length;i++) {
            if (a[i]>maksverdi) {
                maksverdi = a[i];
                m = i;
            }
        }
        return m;
    }

    public static int maks3(int[] a) {
        //lager en sentinel verdi så man slipper sammenligninger i for løkka
        int sist = a.length -1;
        int m = 0;
        int maksverdi = a[0];
        int temp = a[sist];
        a[sist] = 0x7fffffff;

        for (int i = 1; ;i++) {
            if (a[i]>=maksverdi) {
                if(i==sist) {
                    a[sist] = temp;
                    return a[sist] >=maksverdi ? sist : m;
                }
                else {
                    maksverdi = a[i];
                    m = i;
                }
            }
        }
    }


    public static int min(int[] a) {
        if(a.length<1) {
            throw new java.util.NoSuchElementException("Tabellen er tom!");
        }
        int m = 0;
        for (int i = 1;i<a.length;i++) {
            if (a[i]<a[m]) {
                m=i;
            }
        }
        return m;
    }

    public static int[] minmaks(int[] a) {
        int m1 = maks1(a);
        int m2 = min(a);
        int[] b = {m1,m2};
        return b;
    }

    public static int[] minmaks_upgrade(int[] a) {
        int m1 = 0;
        int m2 = 0;
        for (int i = 1;i<a.length;i++) {
            if (a[i]>a[m1]) {
                m1=i;
            }
            else if (a[i]<a[m2]) {
                m2=i;
            }
        }
        return new int[] {m1,m2};
    }

    public static long fak(int n) {
        if (n<0) {
            throw new IllegalArgumentException("n<0");
        }
        long fakultet = 1;
        if (n>0) {
            return n*fak(n-1);
        }
        else {
            return 1;
        }
        /*for (int i = n;i>0;i--) {
            fakultet *= i;
        }
        return fakultet;*/
    }

    public static void bytt(int[] a,int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int[] randPerm(int n) {
        //Lager tilfeldig array
        int[] a = new int[n];
        for (int i = 0; i<n;i++) {
            a[i] = i+1;
        }
        Random r = new Random();
        for (int k = n-1;k>0;k--) {
            int i = r.nextInt(k+1); //tilfeldig tall fra [0,k]
            bytt(a,k,i);
        }
        return a;
    }

    public static int kostnader(int[] a) {
        // hjelpefunskjon for å beregne tidsforbruk
        int m = 0;
        for (int i = 0; i<a.length;i++) {

        }
        return m;
    }



}

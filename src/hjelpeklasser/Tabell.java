package hjelpeklasser;

import java.util.NoSuchElementException;
import java.util.Random;

public class Tabell {
    public static void main(String[] args) {

    }

    public static void vhKontroll(int tablengde, int v, int h)
    {
        if (v < 0)
            throw new ArrayIndexOutOfBoundsException("v(" + v + ") < 0");

        if (h >= tablengde)
            throw new ArrayIndexOutOfBoundsException
                    ("h(" + h + ") >= tablengde(" + tablengde + ")");

        if (v > h + 1)
            throw new IllegalArgumentException
                    ("v = " + v + ", h = " + h);
    }

    public static void fratilKontroll(int tablengde, int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tablengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tablengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public static void skriv(int[] a, int fra, int til) {
        fratilKontroll(a.length,fra,til);
        if ((til-fra)>0) {
            System.out.print(a[fra]);
        }
        for (int i = fra+1;i<til;i++) {
            System.out.print(" " + a[i]);
        }
    }

    public static void skriv(int[] a) {
        skriv(a,0,a.length);
    }

    public static void skrivln(int[] a, int fra, int til) {
        fratilKontroll(a.length,fra,til);
        skriv(a,fra,til);
        System.out.println();
    }

    public static void skrivln(int[] a) {
        skrivln(a,0,a.length);
    }

    public static void bytt(int[] a,int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void bytt(char[] a,int i, int j) {
        char temp = a[i];
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

    public static void randPerm(int[] a)  // stokker om a
    {
        Random r = new Random();     // en randomgenerator

        for (int k = a.length - 1; k > 0; k--)
        {
            int i = r.nextInt(k + 1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);
        }
    }

    public static int maks(int[] a, int fra, int til) {
        //maks metode for gitt intervall
        if (a==null) {
            throw new NullPointerException("Parametertabellen a er null!");
        }
        fratilKontroll(a.length,fra,til);
        if (fra == til)
            throw new NoSuchElementException
                    ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

        int m = 0;
        int maksverdi = a[fra];

        for (int i = fra+1;i<til;i++) {
            if (a[i]>maksverdi) {
                m=i;
                maksverdi = a[m];
            }
        }
        return m;
    }

    public static int maks(int[] a) {
        return maks(a,0,a.length);
    }

    public static int min(int[] a, int fra, int til) {
        //min metode for gitt intervall
        if (a==null) {
            throw new NullPointerException("Parametertabellen a er null!");
        }
        fratilKontroll(a.length,fra,til);
        if (fra == til)
            throw new NoSuchElementException
                    ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

        int m = 0;
        int minverdi = a[fra];

        for (int i = fra+1;i<til;i++) {
            if (a[i]<minverdi) {
                m=i;
                minverdi = a[m];
            }
        }
        return m;
    }

    public static int min(int[] a) {
        return min(a,0,a.length);
    }

    public static int[] nestMaks1(int[] a)  // legges i class Tabell
    {
        int n = a.length;   // tabellens lengde

        if (n < 2) throw   // må ha minst to verdier!
                new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        int m = maks(a);  // m er posisjonen til tabellens største verdi

        int nm;           // nm skal inneholde posisjonen til nest største verdi

        if (m == 0)                            // den største ligger først
        {
            nm = maks(a, 1, n);                  // leter i a[1:n>
        }
        else if (m == n - 1)                   // den største ligger bakerst
        {
            nm = maks(a, 0, n - 1);              // leter i a[0:n-1>
        }
        else
        {
            int mv = maks(a, 0, m);              // leter i a[0:m>
            int mh = maks(a, m + 1, n);          // leter i a[m+1:n>
            nm = a[mh] > a[mv] ? mh : mv;        // hvem er størst?
        }

        return new int[] {m,nm};      // m i posisjon 0 , nm i posisjon 1

    } // nestMaks

    public static int[] nestMaks2(int[] a) {
        int n = a.length;
        if (n<2) {
            throw new java.util.NoSuchElementException("a.length("+n+")<2!");
        }

        int m = maks(a);
        bytt(a,0,m);
        int nm = maks(a,1,n);
        if (nm==m) {
            nm=0;
        }
        bytt(a,0,m);
        return new int[] {m,nm};
    }

    public static int[] nestMaks3(int[] a) {
        int n = a.length;
        if (n<2) {
            throw new java.util.NoSuchElementException("a.length("+n+")<2!");
        }

        int m = maks(a);
        bytt(a,m,n-1);
        int nm = maks(a,0,n-1);
        if (nm==m) {
            nm=n-1;
        }
        bytt(a,m,n-1);
        return new int[] {m,nm};
    }

    public static void sortering(int[] a) {
        for (int i = a.length;i>1;i--) {
            int m = maks(a,0,i);
            bytt(a,m,i-1);
        }
    }

}

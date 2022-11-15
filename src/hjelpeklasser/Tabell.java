package hjelpeklasser;

import java.util.Arrays;
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
        for (int i = fra;i<til;i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void skriv(int[] a) {
        skriv(a,0,a.length);
    }

    public static void skrivln(int[] a, int fra, int til) {
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

    public static int[] nestMaks4(int[] a) {
        int n = a.length;

        if (n<2) {
            throw new IllegalArgumentException("a.length(" + n + ") < 2!");
        }

        int[] b = new int[2*n];
        System.arraycopy(a,0,b,n,n);

        for (int k = 2*n-2;k>1;k-=2) { //Lager turneringstre
            b[k/2] = Math.max(b[k],b[k+1]);
        }

        int maksverdi = b[1], nestmaksverdi = Integer.MIN_VALUE;
        for (int k = 2;k<2*n-1;k*=2) {
            int temp = b[k+1];
            if(maksverdi != b[k]) {
                temp = b[k];
                k++;
            }
            if (temp > nestmaksverdi) {
                nestmaksverdi = temp;
            }
        }

        return new int[] {maksverdi,nestmaksverdi};
    }

    public static void sortering(int[] a) {
        for (int i = a.length;i>1;i--) {
            int m = maks(a,0,i);
            bytt(a,m,i-1);
        }
    }

    public static void snu(int[] a, int v, int h) { //snur intervallet a[v:h]
        while (v<h) bytt(a, v++, h--);
    }

    public static void snu(int[] a, int v) { // snur fra og med v og ut tabellen
        snu(a,v,a.length-1);
    }

    public static void snu(int[] a) {
        snu(a,0,a.length-1);
    }

    public static boolean nestePermutasjon(int[] a) {
        int i = a.length-2; // i starter nest bakerst
        while (i>=0 && a[i]>a[i+1]) i--; // går mot venstre
        if (i<0) return false;  // a = {n, n-1, . . . , 2, 1}

        int j = a.length - 1; // j starter bakerst
        while (a[j] < a[i]) j--; // stopper nåt a[j] > a[i]
        bytt(a,i,j); // bytter og snur
        snu(a,i+1);

        return true;
    }

    public static void utvalgssortering(int[] a) {
        for (int i = 0; i<a.length-1; i++) {
            bytt(a,i, min(a, i, a.length));
        }
    }

    public static void utvalgssortering(int[] a, int fra, int til) {
        fratilKontroll(a.length,fra,til);
        for (int i = fra; i< til-1; i++) {
            bytt(a,i,min(a,i,til));
        }
    }

    public static int lineærsøk(int[] a, int verdi) {
        if (a.length == 0 || verdi > a[a.length-1]) {
            return -(a.length+1); // verdi er større enn den største
        }
        int i = 0;
        for( ; a[i]<verdi; i++); // siste verdi er vaktpost
        return verdi == a[i] ? i : -(i+1); //sjekker innholdet i a[i]
    }

    public static int lineærsøk(int[] a, int k, int verdi) {
        if (k<1) {
            throw new IllegalArgumentException("Må ha k > 0!");
        }
        int j = k - 1;
        for(; j < a.length && verdi > a[j]; j += k);

        int i = j -k +1; //søker i a[j-k+1:j]
        for ( ; i < a.length && a[i]<verdi; i++);
        if (i < a.length && a[i] == verdi) return i;
        else return -(i + 1);
    }

    // 3. versjon av binærsøk - returverdier som for Programkode 1.3.6 a)
    public static int binærsøk(int[] a, int fra, int til, int verdi)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;  // v og h er intervallets endepunkter

        while (v < h)  // obs. må ha v < h her og ikke v <= h
        {
            int m = (v + h)/2;  // heltallsdivisjon - finner midten

            if (verdi > a[m]) v = m + 1;   // verdi må ligge i a[m+1:h]
            else  h = m;                   // verdi må ligge i a[v:m]
        }
        if (h < v || verdi < a[v]) return -(v + 1);  // ikke funnet
        else if (verdi == a[v]) return v;            // funnet
        else  return -(v + 2);                       // ikke funnet
    }

    public static void innsettingssortering(int[] a, int fra, int til)
    {
        fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)

        for (int i = fra + 1; i < til; i++)  // a[fra] er første verdi
        {
            int temp = a[i];  // flytter a[i] til en hjelpevariabel

            // verdier flyttes inntil rett sortert plass i a[fra:i> er funnet
            int j = i-1; for (; j >= fra && temp < a[j]; j--) a[j+1] = a[j];

            a[j+1] = temp;  // verdien settes inn på rett sortert plass
        }
    }

    //Generiske algoritmer

    public static int maks(double[] a)     // legges i class Tabell
    {
        int m = 0;                           // indeks til største verdi
        double maksverdi = a[0];             // største verdi

        for (int i = 1; i < a.length; i++) if (a[i] > maksverdi)
        {
            maksverdi = a[i];     // største verdi oppdateres
            m = i;                // indeks til største verdi oppdaters
        }
        return m;     // returnerer posisjonen til største verdi
    }


    public static int maks(char[] c)
    {
        int m = 0;                    // indeks til "største" tegn
        char maksverdi = c[0];        // "største" tegn

        for (int i = 1; i < c.length; i++) if (c[i] > maksverdi)
        {
            maksverdi = c[i];     // "største" tegn oppdateres
            m = i;                // indeks til "største" tegn oppdaters
        }
        return m;     // returnerer posisjonen til "største" tegn

    }

    public static int maks(Integer[] a)
    {
        int m = 0;                          // indeks til største verdi
        Integer maksverdi = a[0];           // største verdi

        for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer posisjonen til største verdi

    } // maks

    public static <T extends Comparable<? super T>> int maks(T[] a)
    {
        int m = 0;                     // indeks til største verdi
        T maksverdi = a[0];            // største verdi

        for (int i = 1; i < a.length; i++) if (a[i].compareTo(maksverdi) > 0)
        {
            maksverdi = a[i];  // største verdi oppdateres
            m = i;             // indeks til største verdi oppdaters
        }
        return m;  // returnerer posisjonen til største verdi
    } // maks

    public static <T extends Comparable<? super T>> void innsettingssortering(T[] a)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks
            // sammenligner og forskyver:
            for (; j >= 0 && verdi.compareTo(a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }

    public static void skriv(Object[] a, int fra, int til)
    {
        fratilKontroll(a.length,fra,til);

        for (int i = fra; i < til; i++) System.out.print(a[i] + " ");
    }

    public static void skriv(Object[] a)
    {
        skriv(a,0,a.length);
    }

    public static void skrivln(Object[] a, int fra, int til)
    {
        skriv(a,fra,til);
        System.out.println();
    }

    public static void skrivln(Object[] a)
    {
        skrivln(a,0,a.length);
    }
    public static void bytt(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static Integer[] randPermInteger(int n)
    {
        Integer[] a = new Integer[n];               // en Integer-tabell
        Arrays.setAll(a, i -> i + 1);               // tallene fra 1 til n

        Random r = new Random();   // hentes fra  java.util

        for (int k = n - 1; k > 0; k--)
        {
            int i = r.nextInt(k+1);  // tilfeldig tall fra [0,k]
            bytt(a,k,i);             // bytter om
        }
        return a;  // tabellen med permutasjonen returneres
    }


}

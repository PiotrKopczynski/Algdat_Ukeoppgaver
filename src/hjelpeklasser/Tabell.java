package hjelpeklasser;

import eksempelklasser.Komparator;

import java.util.Arrays;
import java.util.Comparator;
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

    public static <T> void innsettingssortering(T[] a, Komparator<? super T> c)
    {
        for (int i = 1; i < a.length; i++)  // starter med i = 1
        {
            T verdi = a[i];        // verdi er et tabellelemnet
            int  j = i - 1;        // j er en indeks

            // sammenligner og forskyver:
            for (; j >= 0 && c.compare(verdi,a[j]) < 0 ; j--) a[j+1] = a[j];

            a[j + 1] = verdi;      // j + 1 er rett sortert plass
        }
    }
    public static <T> int maks(T[] a, Komparator<? super T> c)
    {
        return maks(a, 0, a.length, c);  // kaller metoden nedenfor
    }

    public static <T> int maks(T[] a, int fra, int til, Komparator<? super T> c)
    {
        fratilKontroll(a.length,fra,til);

        if (fra == til) throw new NoSuchElementException
                ("fra(" + fra + ") = til(" + til + ") - tomt tabellintervall!");

        int m = fra;                // indeks til største verdi
        T maksverdi = a[fra];       // største verdi

        for (int i = fra + 1; i < til; i++)   // går gjennom intervallet
        {
            if (c.compare(a[i],maksverdi) > 0)  // bruker komparatoren
            {
                maksverdi = a[i];     // største verdi oppdateres
                m = i;                // indeks til største verdi oppdateres
            }
        }
        return m;                 // posisjonen til største verdi

    }  // maks

    public static <T> void bytt(T[] a, int i, int j)
    {
        T temp = a[i]; a[i] = a[j]; a[j] = temp;
    }

    public static <T> int min(T[] a, int fra, int til, Comparator<? super T> c)
    {
        if (fra < 0 || til > a.length || fra >= til)
            throw new IllegalArgumentException("Illegalt intervall!");

        int m = fra;           // indeks til minste verdi i a[fra:til>
        T minverdi = a[fra];   // minste verdi i a[fra:til>

        for (int i = fra + 1; i < til; i++) if (c.compare(a[i], minverdi) < 0)
        {
            m = i;               // indeks til minste verdi oppdateres
            minverdi = a[m];     // minste verdi oppdateres
        }

        return m;  // posisjonen til minste verdi i a[fra:til>
    }

    public static <T> int min(T[] a, Comparator<? super T> c)  // bruker hele tabellen
    {
        return min(a,0,a.length,c);     // kaller metoden over
    }

    public static <T> void utvalgssortering(T[] a, Comparator<? super T> c)
    {
        for (int i = 0; i < a.length - 1; i++)
            bytt(a, i, min(a, i, a.length, c));  // to hjelpemetoder
    }

    public static <T>
    int binærsøk(T[] a, int fra, int til, T verdi, Comparator<? super T> c)
    {
        Tabell.fratilKontroll(a.length,fra,til);  // se Programkode 1.2.3 a)
        int v = fra, h = til - 1;    // v og h er intervallets endepunkter

        while (v <= h)  // fortsetter så lenge som a[v:h] ikke er tom
        {
            int m = (v + h)/2;     // heltallsdivisjon - finner midten
            T midtverdi = a[m];  // hjelpevariabel for  midtverdien

            int cmp = c.compare(verdi, midtverdi);

            if (cmp > 0) v = m + 1;        // verdi i a[m+1:h]
            else if (cmp < 0) h = m - 1;   // verdi i a[v:m-1]
            else return m;                 // funnet
        }

        return -(v + 1);   // ikke funnet, v er relativt innsettingspunkt
    }

    public static <T> int binærsøk(T[] a, T verdi, Comparator<? super T> c)
    {
        return binærsøk(a,0,a.length,verdi,c);  // bruker metoden over
    }

    public static <T> int parter
            (T[] a, int v, int h, T skilleverdi, Comparator<? super T> c) {
        while (v <= h && c.compare(a[v],skilleverdi)<0) v++;
        while (v <= h && c.compare(skilleverdi,a[h])<=0) h--;

        while (true) {
            if (v < h) Tabell.bytt(a,v++,h--);
            else {
                return v;
            }
            while (c.compare(a[v],skilleverdi) < 0) v++;
            while (c.compare(skilleverdi,a[h]) <= 0) h--;
        }
    }

    public static <T> int parter (T[] a, T skilleverdi, Comparator<? super T> c) {
        return parter(a,0,a.length-1, skilleverdi, c);
    }

    public static <T>
    int sParter(T[] a, int v, int h, int k, Comparator<? super T> c) {
        if (v < 0 || h >= a.length || k<v || k>h) {
            throw new IllegalArgumentException("Ulovlig parameterverdi!");
        }

        bytt(a,k,h);
        int p = parter(a,v,h-1,a[h],c);
        bytt(a,p,h);

        return p;
    }

    public static <T>
    int sParter(T[] a, int k, Comparator<? super T> c)   // bruker hele tabellen
    {
        return sParter(a,0,a.length-1,k,c); // v = 0 og h = a.lenght-1
    }

    private static <T>
    void kvikksortering(T[] a, int v, int h, Comparator<? super T> c) {
        if (v>=h) return;

        int p = sParter(a,v,h,(v+h)/2,c);
        kvikksortering(a,v,p-1,c);
        kvikksortering(a,p+1,h,c);
    }

    public static <T>
    void kvikksortering(T[] a, Comparator<? super T> c) // sorterer hele tabellen
    {
        kvikksortering(a,0,a.length-1,c);
    }

    private static <T>
    void flett(T[] a, T[] b, int fra, int m, int til, Comparator<? super T> c)
    {
        int n = m - fra;   // antall elementer i a[fra:m>
        System.arraycopy(a,fra,b,0,n); // kopierer a[fra:m> over i b[0:n>

        int i = 0, j = m, k = fra;     // løkkevariabler og indekser

        while (i < n && j < til)  // fletter b[0:n> og a[m:til>, legger
            a[k++] = c.compare(b[i],a[j]) <= 0 ? b[i++] : a[j++];  // resultatet i a[fra:til>

        while (i < n) a[k++] = b[i++];  // tar med resten av b[0:n>
    }

    public static <T>
    void flettesortering(T[] a, T[] b, int fra, int til, Comparator<? super T> c)
    {
        if (til - fra <= 1) return;     // a[fra:til> har maks ett element

        int m = (fra + til)/2;          // midt mellom fra og til

        flettesortering(a,b,fra,m,c);   // sorterer a[fra:m>
        flettesortering(a,b,m,til,c);   // sorterer a[m:til>

        flett(a,b,fra,m,til,c);         // fletter a[fra:m> og a[m:til>
    }

    public static <T> void flettesortering(T[] a, Comparator<? super T> c)
    {
        T[] b = Arrays.copyOf(a, a.length/2);
        flettesortering(a,b,0,a.length,c);  // kaller metoden over
    }

}

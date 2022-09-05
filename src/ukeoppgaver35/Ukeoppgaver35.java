package ukeoppgaver35;

public class Ukeoppgaver35 {
    public static void main(String[] args) {

    }

    public static int maks(int[] a, int fra, int til) {
        //maks metode for gitt intervall
        if (fra<0 || til>a.length || fra>=til) {
            throw new IllegalArgumentException("Illegalt intervall");
        }

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

    public static int maks_hel(int[] a) {
        return maks(a,0,a.length);
    }

    public static int min(int[] a, int fra, int til) {
        //min metode for gitt intervall
        if (fra<0 || til>a.length || fra>=til) {
            throw new IllegalArgumentException("Illegalt intervall");
        }

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

    public static int min_hel(int[] a) {
        return min(a,0,a.length);
    }
}

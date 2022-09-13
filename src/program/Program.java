package program;
import hjelpeklasser.*;

public class Program {
    public static void main(String[] args) {
        /*
        int[] a = Tabell.randPerm(20);
        for (int k : a) {
            System.out.println(k+" ");
        }
        int m = Tabell.maks(a);
        System.out.println("Største verdi ligger på plass "+m);
         */
        /*
        int[] a = Tabell.randPerm(10);
        int[] c = null;

        Tabell.maks(a,0,11);
         */
        int[] a = Tabell.randPerm(20); // tilfeldig permutasjon av 1 . . 20
        int[] b = Tabell.nestMaks1(a);  // metoden returnerer en tabell
        int[] c = Tabell.nestMaks4(a);

        int m = b[0], nm = b[1];       // m for maks, nm for nestmaks
        int m2 = c[0], nm2 = c[1];

        Tabell.skrivln(a);        // se Oppgave 5 i Avsnitt 1.2.2
        System.out.print("Størst(" + a[m] + ") har posisjon " + m);
        System.out.println(", nest størst(" + a[nm] + ") har posisjon " + nm);
        System.out.println("Størst: " + m2);
        System.out.println("Nest størst: " +nm2);
    }
}

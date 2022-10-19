package forelesning37;

import java.util.Arrays;

public class Forelesning2 {
    public static void main(String[] args) {

        Integer[] x = {12,3,45,9,99,0,6,8};

        Pokemon[] pokemons = {new Pokemon("Dlastoise",10,1200),
                new Pokemon("Pikachu",8,500),
                new Pokemon("Charmander",7,120),
                new Pokemon("Jigglypuff",16,500),
                new Pokemon("Clastoise",12,1200)};
        for (Pokemon p: pokemons) {
            System.out.println(p.toString());
        }



        StigendePokemonKomparator cmp = new StigendePokemonKomparator();
        SynkendePokemonKomparator cmpSY = new SynkendePokemonKomparator();
        System.out.println(cmp.compare(pokemons[0],pokemons[1]));
        System.out.println(cmp.compare(pokemons[0],pokemons[0]));

        System.out.println(pokemons[maks(pokemons, cmp)].toString()+" er størst");
        System.out.println("Stigende:");
        genericInsertion(pokemons,cmp);
        for (Pokemon p: pokemons) {
            System.out.println(p.toString());
        }
        System.out.println("Synkende:");
        genericInsertion(pokemons,cmpSY);
        for (Pokemon p: pokemons) {
            System.out.println(p.toString());
        }

        Integer[] y = {1,2,35,9,7,999,0,4,2,-9,-2};
        OddetallPartallKomparator cmpOP = new OddetallPartallKomparator();
        System.out.println(cmpOP.compare(y[1],y[3]));
        genericInsertion(y,cmpOP);
        System.out.println(Arrays.toString(y));

    }

    public static <T extends Comparable<? super T>> int maks(T[] x, int fra, int til) {
        int mi = fra;
        T mv = x[mi];
        for (int i = fra+1;i<til;i++) {
            if (x[i].compareTo(mv)>0) {
                mi = i;
                mv = x[mi];
            }
        }
        return mi;
    }

    public static <T extends Comparable<? super T>> int maks(T[] x) {
        return maks(x,0,x.length);
    }


    public static <T extends  Comparable<? super T>> void genericInsertion(T[] x) {
        int n = x.length;
        for (int i = x.length-1;i>0;i--) {
            int mi = maks(x,0,i+1);
            T temp = x[i];
            x[i] = x[mi];
            x[mi] = temp;
        }
    }

    public static <T> int maks(T[] x, int fra, int til, Komparator<T> cmp) {
        int mi = fra;
        T mv = x[mi];
        for (int i = fra+1;i<til;i++) {
            if (cmp.compare(x[i],mv)>0) {
                mi = i;
                mv = x[mi];
            }
        }
        return mi;
    }

    public static <T> int maks(T[] x, Komparator<T> cmp) {
        return maks(x,0,x.length,cmp);
    }

    public static <T> void genericInsertion(T[] x, Komparator<T> cmp) {
        int n = x.length;
        for (int i = n-1;i>0;i--) {
            int mi = maks(x,0,i+1,cmp);
            T temp = x[i];
            x[i] = x[mi];
            x[mi] = temp;
        }
    }


    @FunctionalInterface
    public interface Komparator<T>{
        int compare(T x, T y);
    }


    public static class StigendePokemonKomparator implements Komparator<Pokemon> {
        public int compare(Pokemon p1, Pokemon p2) {
            int out = p1.level.compareTo(p2.level);
            if (out==0) {
                out = p1.hp.compareTo(p2.hp);
            }
            if (out==0) {
                out = p1.name.compareTo(p2.name);
            }
            return out;
        }
    }

    public static class SynkendePokemonKomparator implements Komparator<Pokemon> {
        public int compare(Pokemon p1, Pokemon p2) {
            int out = p2.level.compareTo(p1.level);
            if (out==0) {
                out = p2.hp.compareTo(p1.hp);
            }
            if (out==0) {
                out = p2.name.compareTo(p1.name);
            }
            return out;
        }
    }

    public static class OddetallPartallKomparator implements Komparator<Integer> {
        public int compare(Integer t1,Integer t2) {
            int out = 0;
            if (t1%2==0 && t2%2==0) {
                t1.compareTo(t2);
            }
            else if (t1%2!=0 && t2%2!=0) {
                t1.compareTo(t2);
            }
            else if (t1%2==0) {
                out = 1;
            }
            else if (t2%2==0) {
                out = -1;
            }
            return out;
        }
    }

    /*
    public static class TestKomparator implements Komparator<Integer> {
        public int compare(Integer other){
            return 0;
    }

     */

    public static class Pokemon {
        // Samme klasse som i JavaGenerics1, men uten compareTo
        String name;
        Integer level;
        Integer hp;

        Pokemon(String name, int level, int hp){
            // Konstruktor
            this.name = name;
            this.level = level;
            this.hp = hp;
        }

        public String toString(){
            // Denne funksjonen sørger for at System.out.print skriver
            // output som er lett å lese for mennesker
            return this.name + " (" + this.level + "," + this.hp + ")";
        }
    }
}

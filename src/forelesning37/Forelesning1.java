package forelesning37;

import java.sql.SQLOutput;

public class Forelesning1 {
    public static void main(String[] args) {
        /*
        for (int x = -10;x<=10;x++){
            System.out.println(x + " " + simpleIntervalCheck(x) + " " + ternaryIntervalCheck(x));
        }

         */

        Integer[] v2 = {2,4,10,2,3,99,4,8};

        Character[] c = {'A','B','Z','C'};

        String[] s = {"Algdat", "er", "kjempe","gøy","!"};
        //System.out.println(maksChar(c));

        Pokemon[] pokemons = {new Pokemon("Dlastoise",10,1200),
                new Pokemon("Pikachu",8,500),
                new Pokemon("Charmander",7,120),
                new Pokemon("Jigglypuff",10,500),
                new Pokemon("Clastoise",10,1200)};
        System.out.println();
        System.out.println("Jeg velger: ");
        for (Pokemon p: pokemons) {
            System.out.println(p.toString());
        }
        System.out.println(pokemons[0].compareTo(pokemons[4]));
        System.out.println("Den beste pokemon er: " + pokemons[maks(pokemons)].toString() + " med indeks " + maks(pokemons));


        System.out.println(maks(v2));

        System.out.println(maks(c));

        System.out.println(maks(s));


    }

    public static int simpleIntervalCheck(int x) {
        //Returner -1 hvis x<-5, 0 hvis x<=5 og 1 hvis x>5
        int out = 0;
        if (x<-5) {
            out--;
        }
        else if (x>5){
            out++;
        }
        return out;
    }

    public static int ternaryIntervalCheck(int x){
        return (x<-5) ? -1 : (x>5) ? 1 : 0;
    }

    public static int maksInt(int[] x) {
        int mi = 0;
        for (int i = 1;i<x.length;i++) {
            if (x[i]>x[mi]) {
                mi = i;
            }
        }
        return mi;
    }

    public static int maksChar(char[] c) {
        int mi = 0;
        for (int i = 1;i<c.length;i++) {
            if (c[i]>c[mi]) {
                mi = i;
            }
        }
        return mi;
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
        for (int i = n-1;i>0;i--) {
            int mi = maks(x,0,i+1);
            T temp = x[i];
            x[i] = x[mi];
            x[mi] = temp;
        }
    }

    public static class Pokemon implements Comparable<Pokemon>{
        String name;
        Integer level;
        Integer hp;

        Pokemon(String name, int level, int hp) {
            this.name = name;
            this.level = level;
            this.hp = hp;
        }

        public String toString() {
            return name + " (" + level + "," + hp + ")";
        }

        public int compareTo(Pokemon otherPokemon) {
            /*
            if (level>otherPokemon.level) {
                return 1;
            }
            else if (level<otherPokemon.level) {
                return -1;
            }
            else {
                if (hp>otherPokemon.hp) {
                    return 1;
                }
                else if (hp<otherPokemon.hp) {
                    return -1;
                }
            }

             */

            int out = this.level.compareTo(otherPokemon.level);
            if (out == 0) {
                out = this.hp.compareTo(otherPokemon.hp);
                if(out==0) {
                    out = this.name.compareTo(otherPokemon.name);
                }
            }
            return out;
        }

    }

}


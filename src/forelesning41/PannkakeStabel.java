package forelesning41;

public class PannkakeStabel {

    public static void main(String[] args) {
        System.out.println("Setter main-tallerkene på bordet!");
        spisPannekaker(3);
        System.out.println("Nam!");
        System.out.println("Vasker opp main-tallerkenen!");
    }

    static void spisPannekaker(int n) {
        System.out.println("Steker pannekake " + n + " og legger på main-tallerkenen");
        if (n>1) {
            spisPannekaker(n-1);
        }
        System.out.println("Spiser " + n + " var god!");
    }

}

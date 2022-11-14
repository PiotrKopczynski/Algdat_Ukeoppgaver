package ukesoppgaver36;

public class Ukesoppgaver36 {
    public static void main(String[] args) {

    }

    public int usortertSøk(int[] a, int value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public int sortertSøk(int[] a, int value) {
        int stepLength = 2;
        for (int i = 0; i < a.length; i += stepLength) {
            if (a[i] >= value) {
                int begin = i - stepLength;
                int end = i + 1;
                for (int j = begin; j < end; j++) {
                    if (a[j] == value) {
                        return j;
                    }
                }
                return -1;
            }
        }
        return -1;
    }
}

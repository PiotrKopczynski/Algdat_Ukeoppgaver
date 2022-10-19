package forelesning39;

public class Node {

    private int verdi;
    public Node next;
    public Node prev;
    public Node head;
    public Node tail;

    public Node(int verdi) {
        this.verdi = verdi;
    }

    public Node get(int k) { //Rekursiv versjon
        //Hent node i indeks k i lenket liste
        if (k==0) {
            return this;
        }
        else {
            return this.next.get(k-1);
        }
    }

    public Node get(Node head, int k) { //Iterativ variant
        Node current = head;
        for (int i = 0; i<k; i++) {
            current=current.next;
        }
        return current;
    }

    public void addToStart(int verdi) {
        Node n = new Node(verdi);
        n.next=head;
        head=n;
    }

    public void addToEnd(int verdi) {
        Node n = new Node(verdi);
        n.prev = tail;
        tail.next=n;
        tail=n;
    }

    public void insert(Node p, Node q, Node r) {
        p.next = q;
        q.next = r;
        q.prev = p;
        r.prev = q;
    }

    public void remove(Node q) {
        q.prev.next=q.next;
        q.next.prev=q.prev;
    }

    public static void main(String[] args) {

    }
}

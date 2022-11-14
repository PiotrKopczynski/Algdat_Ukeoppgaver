package forelesning41;

public class CircularBuffer {

    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(30);
        buffer.print();

        char[] c = "ALGORITMER OG DATASTRUKTURER".toCharArray();
        for (char ci : c) {
            buffer.pushBack(ci);
        }
        buffer.print();

        while (buffer.count>0) {
            buffer.popFront();
            buffer.print();
        }
    }

    int size;
    char[] values;
    int head;
    int tail;
    int count;


    CircularBuffer(int size) {
        this.size = size;
        this.values = new char[size];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public void pushBack(char value) {
        if (count+1>size) {
            throw new IndexOutOfBoundsException();
        }
        values[tail] = value;
        tail = (tail+1)%size;
        count++;
    }

    public char popFront() {

        if (count<=0) {
            throw new IndexOutOfBoundsException();
        }
        char out = values[head];
        values[head] = '*';
        head = (head+1)%size;
        count--;
        return out;
    }

    public void print() {

        System.out.println();
        System.out.println(values);
        for (int i = 0; i<size; i++) {
            if (i == head) {
                System.out.print("h");
            }
            else if (i == tail) {
                System.out.print("t");
            }
            else {
                System.out.print(" ");
            }
        }
    }
}

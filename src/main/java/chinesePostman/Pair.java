package chinesePostman;

public class Pair<T1, T2> {
    T1 first;
    T2 second;

    public Pair(T1 item1, T2 item2) {
        this.first = item1;
        this.second = item2;
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }
}

package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private final Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c){
        comparator = c;
    }

    public T max(Comparator<T> c){
        if(this.size() == 0){
            return null;
        }

        T max = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            T temp = this.get(i);
            if(c.compare(max, temp) < 0){
                max = temp;
            }
        }

        return max;
    }

    public T max(){
        return max(comparator);
    }
}
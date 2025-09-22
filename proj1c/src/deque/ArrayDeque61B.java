package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;


public class ArrayDeque61B<T> implements  Deque61B<T> {

    private final int INITIAL_CAP = 8;
    private T[] items;
    private int size;
    private int start;
    private int NextFirst;
    private int NextLast;



    public ArrayDeque61B() {
        this.size = 0;
        this.items = (T[]) new Object[INITIAL_CAP];
        this.start = 0;
        this.NextLast = Math.floorMod(start + 1, INITIAL_CAP);
        this.NextFirst = Math.floorMod(start - 1, INITIAL_CAP);
    }

    private class ArrayDeckIterator implements Iterator<T> {
        private int wizPoz;

        public ArrayDeckIterator() {
            wizPoz = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPoz < size;
        }

        @Override
        public T next() {
            T returnitem = get(wizPoz);
            wizPoz ++;
            return returnitem;
        }
    }

    public void ResizingDown() {
        T[] NewItems = (T[]) new Object[items.length / 2];
        for (int i = 0; i < size; i++) {
            NewItems[i] = this.get(i);
        }
        this.NextFirst = Math.floorMod(start - 1, NewItems.length);
        this.NextLast = Math.floorMod(start + size, NewItems.length);
        items = NewItems;
    }

    public void ResizingUp() {
        T[] NewItewms = (T[]) new Object[2 * items.length];
        for(int i = 0; i < size; i++) {
            NewItewms[i] = this.get(i);
        }
        this.NextFirst = Math.floorMod(start - 1, NewItewms.length);
        this.NextLast = Math.floorMod(start + size, NewItewms.length);
        items = NewItewms;
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            items[start] = x;
        } else {
            if (size >= items.length) {
                this.ResizingUp();
            }
            items[NextFirst] = x;
            NextFirst = Math.floorMod(NextFirst - 1, items.length);
        }

        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            items[start] = x;
        } else {
            if (size >= items.length) {
                this.ResizingUp();
            }
            items[NextLast] = x;
            NextLast = Math.floorMod(NextLast + 1, items.length);
        }

        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnlist = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            returnlist.add(this.get(i));
        }
        return returnlist;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        T returntemp = null;
        if (this.isEmpty()) {
            return returntemp;
        } else {
            if (size <= items.length / 4) {
                this.ResizingDown();
            }
            NextFirst = Math.floorMod(NextFirst + 1, items.length);
            returntemp = items[NextFirst];
        }
        size--;
        return  returntemp;
    }

    @Override
    public T removeLast() {
        T returntemp = null;
        if (this.isEmpty()) {
            return returntemp;
        } else {
            if (size <= items.length / 4) {
                this.ResizingDown();
            }
            NextLast = Math.floorMod(NextLast - 1, items.length);
            returntemp = items[NextLast];
        }
        size--;
        return  returntemp;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            return items[Math.floorMod(NextFirst + 1 + index, items.length)];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeckIterator();
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            returnSB.append(this.get(i).toString());
            returnSB.append(", ");
        }
        returnSB.append(this.get(size - 1));
        returnSB.append("]");
        return returnSB.toString();
    }

}

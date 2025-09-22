package deque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private class LdeckInterrator implements Iterator<T>{

        private int wisPor;
        public LdeckInterrator() {
            wisPor = 0;
        }

        @Override
        public boolean hasNext() {
            return wisPor < size;
        }

        @Override
        public T next() {
            T returntemp = get(wisPor);
            wisPor++;
            return returntemp;
        }
    }



    /**创建内部节点*/
    private class Node {
        public T item;
        public Node pre;
        public Node next;

        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        private T getRecursiveHelper(Node p, int index) {
            if (index == 0) {
                return p.item;
            } else {
                return getRecursiveHelper(p.next, index - 1);
            }
        }
    }
    /**声明虚拟头节点*/
    private Node sentinel;

    /**队列的长度*/
    private int size;

    /**当为空时，虚拟头节点的前一节点指向自己，后一节点指向前一节点，自己指向下一节点*/
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next =sentinel.pre;
        sentinel = sentinel.next;
        this.size = 0;
    }

    /**在第一处添加节点，头节点的下一节点是新节点，新节点的下一节点是老节点，老节点的前一节点是新节点*/
    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(x, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size += 1;
    }

    /**在最后处添加节点，头节点的前一节点是新节点，新节点的前一节点是老节点，老节点的下一节点是新节点*/
    @Override
    public void addLast(T x) {
        sentinel.pre = new Node(x, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node temp = sentinel;
        for(int i = 0; i < size; i++) {
            temp = temp.next;
            returnList.add(temp.item);
        }
        return returnList;
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
    /**删除第一个节点，虚拟头节点的下一节点指向第二节点，第二节点的前一节点指向虚拟头节点*/
    @Override
    public T removeFirst() {
        if (size == 0) return null;
        T temp = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return temp;
    }
    /**删除最后一个节点，虚拟头节点的前一节点指向倒数第二个节点，倒数第二节点的后一节点指向虚拟头节点*/
    @Override
    public T removeLast() {
        if (size == 0) return null;
        T temp = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size -= 1;
        return temp;
    }

    @Override
    public T get(int index) {
        T n = null;
        if (index >= 0 && index < size) {
            Node temp = sentinel;
            for(int i = 0; i <= index; i++) {
                temp = temp.next;
                n = temp.item;
            }
        } else {
            n = null;
        }
        return n;
    }



    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return sentinel.getRecursiveHelper(sentinel.next, index);
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new LdeckInterrator();
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

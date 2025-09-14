import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

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

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
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
        return null;
    }
}

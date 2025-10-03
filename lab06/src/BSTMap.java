import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private int size = 0;
    private Entry Tree;

    /**用BST实现实现内部键值对*/
    private class Entry {
        K key;
        V val;
        Entry leftT;
        Entry rightT;

        Entry (K key, V val, Entry leftT, Entry rightT) {
            this.key = key;
            this.val = val;
            this.leftT = leftT;
            this.rightT = rightT;
        }

        Entry insert(Entry T, K sk, V sv) {
            if (T == null) {
                size++;
                return new Entry(sk, sv, null, null);
            }
            if (sk.compareTo(T.key) > 0) {
                T.rightT = T.insert(T.rightT, sk, sv);
            } else if (sk.compareTo(T.key) < 0) {
                T.leftT = T.insert(T.leftT, sk, sv);
            } else {
                T.val = sv;
            }
            return T;
        }

        Entry find (Entry T, K key) {
            if (T == null) return null;
            if (T.key.compareTo(key) == 0 ) {
                return T;
            } else if (T.key.compareTo(key) > 0) {
                return T.find(T.leftT, key);
            } else {
                return T.find(T.rightT, key);
            }
        }
    }



    @Override
    public void put(K key, V value) {
        if (Tree == null) {
            Tree = new Entry(key, value, null, null);
            size++;
            return;
        }
        Tree.insert(Tree ,key, value);

    }

    @Override
    public V get(K key) {
        if (Tree == null) return null;
        if (Tree.find(Tree, key) == null) {
            return null;
        } else {
            return Tree.find(Tree, key).val;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return !(Tree.find(Tree, key) == null);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K> {

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

        void collectKeys(Set<K> keys) {
            if (this == null) return;
            if (leftT != null) {
                leftT.collectKeys(keys);
            }
            keys.add(this.key);
            if (rightT != null) {
                rightT.collectKeys(keys);
            }
        }

        Entry remove(Entry node, K key) {
            if (node == null) {
                return null;
            }

            //查找要删除的节点
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                //键在左子树，递归删除左子树
                node.leftT = remove(node.leftT, key);
            } else if (cmp > 0) {
                //键在右子树，递归删除右子树
                node.rightT = remove(node.rightT, key);
            } else {
            //找到目标节点，开始执行删除操作.
                //场景一：叶子节点
                if (node.rightT == null && node.leftT == null) {
                    size--;
                    return null;
                }
                //场景二:只有一个子节点
                else if (node.leftT == null) {
                    //只有右子树，用右子树代替当前节点
                    size--;
                    return node.rightT;
                } else if (node.rightT == null) {
                    //只用左子树，用左子树代替当前节点
                    size--;
                    return node.leftT;
                }
                //场景三：有两个子节点
                else {
                    Entry successor = findMin(node.rightT);
                    node.key = successor.key;
                    node.val = successor.val;
                    node.rightT = remove(node.rightT, successor.key);
                }
            }
            return node;
        }

        Entry findMin(Entry node) {
            while (node.leftT != null) {
                node = node.leftT;
            }
            return node;
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
        Entry found = Tree.find(Tree, key);
        return found != null ? found.val : null;
    }

    @Override
    public boolean containsKey(K key) {
        return Tree != null && !(Tree.find(Tree, key) == null);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        Tree = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        if (Tree != null) {
            Tree.collectKeys(keys);
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        V value = get(key);
        if (!this.containsKey(key)) {
            return null;
        }
        Tree = Tree.remove(Tree, key);
        return value;
    }

    @Override
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<>(keySet());
        return keys.iterator();
    }
}

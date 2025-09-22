import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    public void AddFirsthandAddLastandRemoveTest() {
        Deque61B<Integer> D1 = new ArrayDeque61B<>();
        D1.addFirst(1);//[1]
        assertThat(D1.toList()).containsExactly(1).inOrder();
        D1.addLast(3);//[1, 3]
        assertThat(D1.toList()).containsExactly(1, 3).inOrder();
        D1.addFirst(2);//[2, 1, 3]
        assertThat(D1.toList()).containsExactly(2, 1, 3).inOrder();
        D1.addFirst(5);//[5, 2, 1, 3]
        D1.addFirst(6);//[6, 5, 2, 1, 3]
        D1.addFirst(2);//[2, 6, 5, 2, 1, 3]
        D1.addLast(9);//[2, 6, 5, 2, 1, 3, 9]
        D1.addLast(9);//[2, 6, 5, 2, 1, 3, 9, 9]
        assertThat(D1.toList()).containsExactly(2, 6, 5, 2, 1, 3, 9, 9).inOrder();
        D1.addFirst(3);//[3, 2, 6, 5, 2, 1, 3, 9, 9]
        assertThat(D1.toList()).containsExactly(3, 2, 6, 5, 2, 1, 3, 9, 9).inOrder();
        assertThat(D1.removeFirst()).isEqualTo(3);//[2, 6, 5, 2, 1, 3, 9, 9]
        assertThat(D1.removeFirst()).isEqualTo(2);//[6, 5, 2, 1, 3, 9, 9]
        assertThat(D1.removeLast()).isEqualTo(9);//[6, 5, 2, 1, 3, 9]
        assertThat(D1.removeLast()).isEqualTo(9);//[6, 5, 2, 1, 3]
        assertThat(D1.removeLast()).isEqualTo(3);//[6, 5, 2, 1]
        assertThat(D1.toList()).containsExactly(6, 5, 2, 1).inOrder();
        assertThat(D1.removeLast()).isEqualTo(1);//[6, 5, 2]
        assertThat(D1.toList()).containsExactly(6, 5, 2).inOrder();
    }

    @Test
    public void GetTest() {
        Deque61B<Integer> D1 = new ArrayDeque61B<>();
        D1.addFirst(1);//[1]
        D1.addFirst(2);//[2, 1]
        D1.addLast(3);//[2, 1, 3]
        assertThat(D1.get(0)).isEqualTo(2);
        assertThat(D1.get(1)).isEqualTo(1);
        assertThat(D1.get(2)).isEqualTo(3);
    }

    @Test
    public  void IsEmptyTest() {
        Deque61B<Integer> D1 = new ArrayDeque61B<>();
        assertThat(D1.isEmpty()).isTrue();
        D1.addFirst(1);
        assertThat(D1.isEmpty()).isFalse();
    }



}

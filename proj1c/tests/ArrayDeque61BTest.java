import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61BTest {
    @Test
    public void InteratorTest() {
        Deque61B<Integer> D1 = new ArrayDeque61B<>();
        List<Integer> L1 = new ArrayList<>();
        D1.addFirst(1);//[1]
        D1.addLast(5);//[1, 5]
        for (int i : D1) {
            L1.add(i);
        }
        assertThat(L1).containsExactly(1, 5).inOrder();
    }

    @Test
    public void toStringTest() {
        Deque61B<String> D1 = new ArrayDeque61B<>();
        D1.addLast("First");
        D1.addLast("Middle");
        D1.addLast("Last");//[First, Middle, Last]
        assertThat(D1.toString()).isEqualTo("[First, Middle, Last]");
    }
}

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    void testAddFirstEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        assertThat(s.toList()).containsExactly(1);
    }

    @Test
    void testAddFirstNonEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        s.addFirst(2);
        s.addFirst(3);
        assertThat(s.toList()).containsExactly(1, 2, 3);
    }

    @Test
    void testAddFirstTriggerResize() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        for (int i = 1; i <= 16; i++) {
            s.addFirst(i);
        }
        assertThat(s.size()).isEqualTo(16);
        // check that all elements are correct
        List<Integer> list = s.toList();
        for (int i = 0; i < 16; i++) {
            assertThat(list).contains(i + 1);
        }
    }

    @Test
    void testAddFirstAfterRemoveToEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        s.removeFirst();
        s.addFirst(2);
        assertThat(s.toList()).containsExactly(2);
    }

    // ------------------- addLast -------------------
    @Test
    void testAddLastEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        assertThat(s.toList()).containsExactly(1);
    }

    @Test
    void testAddLastNonEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        s.addLast(2);
        s.addLast(3);
        assertThat(s.toList()).containsExactly(1, 2, 3);
    }

    @Test
    void testAddLastTriggerResize() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        for (int i = 1; i <= 16; i++) {
            s.addLast(i);
        }
        assertThat(s.size()).isEqualTo(16);
        List<Integer> list = s.toList();
        for (int i = 0; i < 16; i++) {
            assertThat(list).contains(i + 1);
        }
    }

    @Test
    void testAddLastAfterRemoveToEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        s.removeLast();
        s.addLast(2);
        assertThat(s.toList()).containsExactly(2);
    }

    // ------------------- removeFirst -------------------
    @Test
    void testRemoveFirst() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        s.addLast(2);
        s.addLast(3);
        assertThat(s.removeFirst()).isEqualTo(1);
        assertThat(s.removeFirst()).isEqualTo(2);
        assertThat(s.removeFirst()).isEqualTo(3);
        assertThat(s.removeFirst()).isNull();
    }

    @Test
    void testRemoveFirstToEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        s.removeFirst();
        assertThat(s.isEmpty()).isTrue();
    }

    @Test
    void testRemoveFirstToOne() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        s.addLast(2);
        s.removeFirst();
        assertThat(s.toList()).containsExactly(2);
    }

    @Test
    void testRemoveFirstTriggerResize() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        for (int i = 0; i < 20; i++) s.addLast(i);
        for (int i = 0; i < 16; i++) s.removeFirst();
        assertThat(s.size()).isEqualTo(4);
        assertThat(s.toList()).containsExactly(16, 17, 18, 19);
    }

    // ------------------- removeLast -------------------
    @Test
    void testRemoveLast() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        s.addLast(2);
        assertThat(s.removeLast()).isEqualTo(2);
        assertThat(s.removeLast()).isEqualTo(1);
        assertThat(s.removeLast()).isNull();
    }

    @Test
    void testRemoveLastToEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        s.removeLast();
        assertThat(s.isEmpty()).isTrue();
    }

    @Test
    void testRemoveLastToOne() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        s.addLast(2);
        s.removeLast();
        assertThat(s.toList()).containsExactly(1);
    }

    @Test
    void testRemoveLastTriggerResize() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        for (int i = 0; i < 20; i++) s.addLast(i);
        for (int i = 0; i < 16; i++) s.removeLast();
        assertThat(s.size()).isEqualTo(4);
        assertThat(s.toList()).containsExactly(0, 1, 2, 3);
    }

    // ------------------- get -------------------
    @Test
    void testGetValid() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(10);
        s.addLast(20);
        assertThat(s.get(0)).isEqualTo(10);
        assertThat(s.get(1)).isEqualTo(20);
    }

    @Test
    void testGetOobLarge() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        assertThat(s.get(5)).isNull();
    }

    @Test
    void testGetOobNeg() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addLast(1);
        assertThat(s.get(-1)).isNull();
    }

    // ------------------- size -------------------
    @Test
    void testSize() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        assertThat(s.size()).isEqualTo(0);
        s.addFirst(1);
        s.addLast(2);
        assertThat(s.size()).isEqualTo(2);
    }

    @Test
    void testSizeAfterRemoveToEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        s.addLast(2);
        s.removeFirst();
        s.removeLast();
        assertThat(s.size()).isEqualTo(0);
    }

    @Test
    void testSizeAfterRemoveFromEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.removeFirst();
        s.removeLast();
        assertThat(s.size()).isEqualTo(0);
    }

    // ------------------- isEmpty -------------------
    @Test
    void testIsEmptyTrue() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        assertThat(s.isEmpty()).isTrue();
    }

    @Test
    void testIsEmptyFalse() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        assertThat(s.isEmpty()).isFalse();
    }

    // ------------------- toList -------------------
    @Test
    void testToListEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        assertThat(s.toList()).isEmpty();
    }

    @Test
    void testToListNonEmpty() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        s.addFirst(1);
        s.addLast(2);
        assertThat(s.toList()).containsExactly(1, 2);
    }

    // ------------------- advanced resize -------------------
    @Test
    void testResizeUpAndDown() {
        Deque61B<Integer> s = new ArrayDeque61B<>();
        for (int i = 0; i < 16; i++) s.addLast(i); // trigger resize up
        for (int i = 0; i < 12; i++) s.removeFirst(); // trigger resize down
        assertThat(s.size()).isEqualTo(4);
        assertThat(s.toList()).containsExactly(12, 13, 14, 15);
    }
}

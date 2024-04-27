package ee.ut.cs.swt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasicBinaryHeapTest {

    private MinimumBinaryHeap heap;

    @BeforeEach
    public void setUp() {
        heap = new MinimumBinaryHeap();
    }

    @Test
    public void peekTest() {
        heap.add(5);
        heap.add(3);
        heap.add(8);
        assertEquals(3, heap.exractMin());
    }


    @Test
    public void toArrayTest() {
        heap.add(5);
        heap.add(3);
        heap.add(8);
        List<Integer> array = heap.getArray();
        assertEquals(3, array.size());
        assertEquals(3, array.get(0));
        assertEquals(5, array.get(1));
        assertEquals(8, array.get(2));
    }


    @Test
    public void peekEmptyHeapTest() {
        assertTrue(heap.isEmpty());
        assertThrows(IllegalArgumentException.class, () -> {
            heap.exractMin(); // Attempting to extract from an empty heap should throw an exception
        });
    }

    @Test
    public void removeNonExistentElementTest() {
        assertFalse(heap.remove(100)); // Trying to remove an element not present in the heap should return false
    }

    @Test
    public void removeElementTest() {
        heap.add(5);
        heap.add(3);
        heap.add(7);
        assertTrue(heap.remove(3)); // Removing an existing element should return true
        assertEquals(2, heap.getArray().size()); // Size of the heap should decrease after removal
        assertFalse(heap.getArray().contains(3)); // The removed element should not be present in the heap
    }

    @Test
    public void addMultipleElementsTest() {
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(2);
        heap.add(10);
        assertEquals(5, heap.getArray().size()); // Size of the heap should match the number of added elements
        assertTrue(heap.getArray().containsAll(Arrays.asList(2, 3, 5, 7, 10))); // All added elements should be present
    }

    @Test
    public void removeAllElementsTest() {
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(2);
        heap.add(10);
        heap.remove(5);
        heap.remove(3);
        heap.remove(7);
        heap.remove(2);
        heap.remove(10);
        assertTrue(heap.isEmpty()); // The heap should be empty after removing all elements
    }

    @Test
    public void removeEmpty() {
        assertFalse(heap.remove(5));
        assertTrue(heap.isEmpty());
    }

    @Test
    public void minHeapifyEmptyArrayTest() {
        heap.minHeapify(new ArrayList<>());
        assertTrue(heap.isEmpty()); // Min heapifying an empty array should result in an empty heap
    }

    @Test
    public void testBasicEmpty() {
        assertTrue(heap.isEmpty());
        heap.add(1);
        assertFalse(heap.isEmpty());
    }

}

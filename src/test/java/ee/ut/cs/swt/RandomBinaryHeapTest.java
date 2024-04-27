package ee.ut.cs.swt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomBinaryHeapTest {

    private MinimumBinaryHeap heap;

    @BeforeEach
    public void setUp() {
        heap = new MinimumBinaryHeap();
    }


    @Test
    public void removeRandom() {
        List<Integer> initial = createRandomNumbers(1000);
        for (Integer i : initial) {
            heap.add(i);
        }
        int random = new Random().nextInt(999);
        heap.remove(heap.getArray().get(random));
        for (int i = 1; i < heap.getArray().size(); i++) {
            int parent = heap.getArray().get(getParent(i));
            int current = heap.getArray().get(i);
            assertTrue(parent <= current, parent + " is not smaller or equal to: " + current);
        }
    }

    @Test
    public void getMinimum() {
        List<Integer> inputs = createRandomNumbers(1000);
        heap.minHeapify(inputs);
        for (int i = 1; i < heap.getArray().size(); i++) {
            int parent = heap.getArray().get(getParent(i));
            int current = heap.getArray().get(i);
            assertTrue(parent <= current, parent + " is not smaller or equal to: " + current);
        }

        int currentSmallest = heap.getArray().get(0);
        for (int i = 0; i < 1000; i++) {
            assertEquals(heap.getArray().get(0), currentSmallest);
            heap.exractMin();
            if (!heap.getArray().isEmpty()) currentSmallest = heap.getArray().get(0);
        }
        assertTrue(heap.isEmpty());
    }


    @Test
    public void minHeapifyNumbersTest() {
        List<Integer> inputs = createRandomNumbers(1000);
        heap.minHeapify(inputs);
        for (int i = 1; i < heap.getArray().size(); i++) {
            int parent = heap.getArray().get(getParent(i));
            int current = heap.getArray().get(i);
            assertTrue(parent <= current, parent + " is not smaller or equal to: " + current);
        }
    }

    @Test
    public void minHeapifyRemoveNumbersTest() {
        List<Integer> inputs = createRandomNumbers(1000);
        heap.minHeapify(inputs);
        int smallest = 10000;
        for (int i = 1; i < heap.getArray().size(); i++) {
            int parent = heap.getArray().get(getParent(i));
            int current = heap.getArray().get(i);
            if (smallest > current) smallest = current;
            if (smallest > parent) smallest = parent;
            assertTrue(parent <= current, parent + " is not smaller or equal to: " + current);
        }
        heap.remove(smallest);
        for (int i = 1; i < heap.getArray().size(); i++) {
            int parent = heap.getArray().get(getParent(i));
            int current = heap.getArray().get(i);
            assertTrue(parent <= current, parent + " is not smaller or equal to: " + current);
        }
        heap.add(smallest - 1);
        assertEquals((int) heap.getArray().get(0), smallest - 1);
    }

    @Test
    public void minHeapifyAddNumbersTest() {
        List<Integer> inputs = createRandomNumbers(1000);
        heap.minHeapify(inputs);
        for (int i = 1; i < heap.getArray().size(); i++) {
            int parent = heap.getArray().get(getParent(i));
            int current = heap.getArray().get(i);
            assertTrue(parent <= current, parent + " is not smaller or equal to: " + current);
        }
        for (Integer num : createRandomNumbers(1000)) {
            heap.add(num);
        }

        for (int i = 1; i < heap.getArray().size(); i++) {
            int parent = heap.getArray().get(getParent(i));
            int current = heap.getArray().get(i);
            assertTrue(parent <= current, parent + " is not smaller or equal to: " + current);
        }
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private List<Integer> createRandomNumbers(int amount) {
        Random random = new Random();

        List<Integer> randomNumbers = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            randomNumbers.add(random.nextInt(2001) - random.nextInt(1000));
        }

        return randomNumbers;
    }
}

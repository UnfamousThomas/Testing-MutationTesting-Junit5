package ee.ut.cs.swt;

import java.util.ArrayList;
import java.util.List;

public class MinimumBinaryHeap {
	
	private List<Integer> heap = new ArrayList<>();

	/**
	 * Switches elements in the array such that heap property holds for every element.
	 * 
	 * @param array 
	 */
	public void minHeapify(List<Integer> array) {
		heap = new ArrayList<>(array);
		for (int i = 0; i < array.size(); i++){
            bubbleUp(i);
        }
        array = heap;
	}
	
	/**
	 * Removes and returns element with minimum value. On empty
	 * heap throws IllegalArgumentException.
	 * 
	 * @return Element at the top of the heap.
	 */
	public int exractMin() {
		int min = heap.get(0);
		int leaf = heap.get(heap.size()-1);
		heap.set(0, leaf);
        heap.remove(heap.size()-1);
        bubbleDown(0);
        return min;
	}
	
	/**
	 * Swaps two elements in the heap
	 */
	public void swap(int a, int b) {
		int value = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, value);
	}
	
	
	/**
	 * Adds element to the heap.
	 * 
	 * @param element
	 */
	public void add(int element) {
		heap.add(element);
        bubbleUp(heap.size() - 1);
	}
	
	public void bubbleUp(int pos) {
		if (pos == 0) return;
		int parent = (pos)/2;
		while (heap.get(pos) < heap.get(parent)) {
			swap(pos, parent);
			pos = parent;
			parent = (pos-1)/2;
		}
	}
	
	public void bubbleDown(int pos) {
		int leftChild = 2*pos + 1;
		int rightChild = 2*pos + 2;
		int min = pos;
		int maxSize = heap.size();
		
		// when the left child is smaller
		if (leftChild < maxSize && heap.get(leftChild) < heap.get(min)) {
			min = leftChild;
		}
		// when the right child is smaller
		if (rightChild < maxSize && heap.get(rightChild) < heap.get(min)) {
			min = rightChild;
		}
		if (min != pos) {
			swap(min, pos);
			bubbleDown(min);
		}
	}
	
	/**
	 * Removes element from the heap if possible.
	 * 
	 * @param element
	 * @return True if element was removed and false otherwise.
	 */
	public boolean remove(int element) {
		int index = heap.indexOf(element);
		if (index == heap.size()-1) {
			heap.remove(heap.size()-1);
			return false;
		}
		
		heap.set(index, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		bubbleDown(index);
		return true;
	}
	
	/**
	 * @return True if heap is empty and false otherwise.
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	/**
	 * @return List containing the heap.
	 * (for testing purposes)
	 */
	public List<Integer> getArray() {
		return heap;
	}
	
}

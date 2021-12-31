package com.pewee.algorithm.sort;
import org.junit.Test;

/**
 * 二叉堆排序
 * @author pewee
 *
 */
public class HeapSort {
	
	static int[] arr = {1,67,54,98,232,12,532,78,2,56,88,22,45,876,66};
	
	public void print() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	
	@Test
	public void heapSort() {
		int recycle = arr.length -1;
		for(;recycle > 0;) {
			doHeapify(0,recycle);
			swap(arr, 0, recycle);
			recycle--;
		}
		print();
	}
	
	/**
	 * 数组堆化 大顶堆
	 * @param start
	 * @param end 
	 */
	private void doHeapify(int start, int end) {
		if(start >= end) {
			return;
		}
		int middle = (start + end )/2;
		for(;middle>=0;) {
			exchange(start,end,middle);
			middle--;
		}
	}
	
	/**
	 * 比较单个节点的左右儿子,把大的移动上来
	 * @param start
	 * @param end
	 * @param exchange
	 */
	private void exchange(int start, int end,int exchange) {
		int leftidx = 2*exchange + 1;
		int rightidx = 2*exchange + 2;
		
		int bigIndex = exchange;
		if(leftidx <= end) {
			if(arr[leftidx] > arr[bigIndex]) {
				bigIndex = leftidx;
			}
		}
		if(rightidx <= end) {
			if(arr[rightidx] > arr[bigIndex]) {
				bigIndex = rightidx;
			}
		}
		//需要交换
		if(bigIndex!= exchange) {
			swap(arr, exchange, bigIndex);
			//交换下去的小值还要和下面的2个儿子比
			exchange(start,end,bigIndex);
		}
	}
	
	
	/**
	 * 交换元素
	 * @param arr
	 * @param i
	 * @param j
	 */
	private void swap(int[] arr,int i,int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}

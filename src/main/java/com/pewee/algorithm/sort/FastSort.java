package com.pewee.algorithm.sort;

import org.junit.Test;

/**
 * 快速排序
 * @author pewee
 *
 */
public class FastSort {
	
	static int[] arr = {1,67,54,98,232,12,532,78,2,56,88,22,45,876,66};
	
	public void print() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	
	@Test
	public void dofastsort() {
		doFastSort(0,14);
		print();
	}
	
	/**
	 * 获取数组中的第k大元素
	 * @param k
	 */
	@Test
	public void getKBigElement() {
		
		
		//和快排同样的思路.快排后分为 大 基准 小 三个区,若K -1  = 基准 那基准是第K大元素,
		//若K -1 > 基准,就去小区递归, 若K -1 < 基准,要去大小区递归
		int bigK = getBigK(0,14,2);
		System.out.println(bigK);
	}
	
	/**
	 * 获取数组start-end中k大的原色
	 * @param start
	 * @param end
	 * @param k
	 * @return
	 */
	private int getBigK(int start, int end,int k) {
		if(k > (end - start) || k < 1) {
			System.out.println("非法的K!!");
		}
		//选择基准点
		int pivot = start;
		int newStart = pivot + 1;
		int bound = newStart; 
		//开始排序
		for(;newStart <= end;) {
			if(arr[newStart] > arr[pivot]) {
				swap(arr, newStart, bound);
				bound++;
			}
			newStart++;
		}
		//把pivot放到中间去
		swap(arr, pivot, bound-1);
		pivot = bound-1;
		
		
		//比较K
		if( k -1 == pivot) {
			return arr[pivot];
		} else if (k -1 > pivot) {
			return getBigK(pivot + 1,end,k - (pivot));
		} else {
			return getBigK(start, pivot - 1, k);
		}
	}

	/**
	 * 快排
	 * @param start
	 * @param end
	 */
	private void doFastSort(int start, int end) {
		if(start >= end ) {
			return;
		}
		//选择基准点
		int pivot = start;
		int newStart = pivot + 1;
		int bound = newStart;//分割比pivot大和小的指针,当遇到一个数比pivot小,直接把那个数和bound交换,然后bound++,这个bopund指向大区域的第一个数 
		//开始排序
		for(;newStart <= end;) {
			if(arr[newStart] < arr[pivot]) {
				swap(arr, newStart, bound);
				bound++;
			}
			newStart++;
		}
		//把pivot放到中间去
		swap(arr, pivot, bound-1);
		pivot = bound-1;
		print();
		doFastSort(start, pivot -1);
		doFastSort(pivot +1, end);
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

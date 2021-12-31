package com.pewee.algorithm.sort;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.Iterator;

import org.junit.Test;

/**
 * 归并
 * @author pewee
 *
 */
public class MergeSort {
	
	static int[] arr = {1,67,54,98,232,12,532,78,2,56,88,22,45,876,66};
	
	@Test
	public void domergesort() {
		int length = arr.length;
		sort(0, length-1);
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	
	private void merge(int i, int middle, int k) {
		/**
		 * 用于合并arr中i到middle与middle+1到k的临时数组
		 */
		 int[] tmp = new int[k-i+1];//注意这里要k-1+1,比如这里合并[0,1] i=0,middle=0,k=1,数组应该为2
		 int x = i;int y = middle+1;int z = 0;
		 //2个数组都有数据
		 while(x <= middle && y <= k ) {
			 if(arr[x] < arr[y]) {
				 tmp[z] = arr[x];
				 x++;
			 } else {
				 tmp[z] = arr[y];
				 y++;
			 }
			 z++;
		 }
		 //有一个数组没数据了,要把林外一个的数据合进去
		 int leftStart;int leftEnd;
		 if(x > middle) {
			 leftStart = y;
			 leftEnd = k;
		 } else {
			 leftStart = x;
			 leftEnd = middle;
		 }
		 for(;leftStart <= leftEnd;) {
			tmp[z] = arr[leftStart];
			leftStart++;
			z++;
		 }
		 //把tmp合并进arr
		 for(int m = 0;m < tmp.length;) {
			 arr[i] = tmp[m];
			 i++;
			 m++;
		 }
	}


	private void sort(int start,int end) {
		if(start >= end) {
			return;
		}
		//System.out.println(start + "==>" + end);
		int middle = (start + end)/2;
		sort(start, middle);
		sort(middle + 1, end);
		merge(start,middle,end);
	}
}

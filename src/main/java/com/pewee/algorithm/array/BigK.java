package com.pewee.algorithm.array;

import java.io.Serializable;
import java.util.Arrays;


/**
 * 
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 * @author pewee
 */
public class BigK {
	
	public static void main(String[] args) {
		
		BigK bigK = new BigK(3, new int[] {4,5,8,2});
		System.out.println(bigK.add(3));
		System.out.println(bigK.add(5));
		System.out.println(bigK.add(10));
		System.out.println(bigK.add(9));
		System.out.println(bigK.add(4));
		//[null,4,5,5,8,8]
	}
	
	private int[] nums;
	private int k;
	private int[] tmp;
	
	public BigK(int k, int[] nums) {
		this.nums = nums;
		this.k = k;
		Arrays.sort(nums);
		tmp = new int[k];
		for(int i=0; i < k ;i++) {
			tmp[i] = Integer.MIN_VALUE;
		}
		if(nums.length >= 1) {
			int idx = nums.length -1 - k > -1 ? nums.length -1 - k  : -1;
			for(int i = nums.length -1; i > idx ; i --) {
				tmp[nums.length - 1 - i] = nums[i];
			}
			//构建小顶堆
			doHeap(0,k - 1);
		}
    }
	
	private void doHeap(int start, int end) {
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
	 * 比较单个节点的左右儿子,把小的移动上来
	 * @param start
	 * @param end
	 * @param exchange
	 */
	private void exchange(int start, int end,int exchange) {
		int leftidx = 2*exchange + 1;
		int rightidx = 2*exchange + 2;
		
		int smallIndex = exchange;
		if(leftidx <= end) {
			if(tmp[leftidx] < tmp[smallIndex]) {
				smallIndex = leftidx;
			}
		}
		if(rightidx <= end) {
			if(tmp[rightidx] < tmp[smallIndex]) {
				smallIndex = rightidx;
			}
		}
		//需要交换
		if(smallIndex!= exchange) {
			swap(tmp, exchange, smallIndex);
			//交换下去的小值还要和下面的2个儿子比
			exchange(start,end,smallIndex);
		}
	}

	/**
	 * k不变的情况下对初始化数据做排序快排后维护一个最大k个数据的小顶堆
	 * 每次add进来和对顶的数据比较,比他小直接舍弃,比他大放进堆顶丢掉原来的堆顶
	 * @param val
	 * @return
	 */
	public int add(int val) {
		if(val < tmp[0]) {
			return tmp[0];
		} else {
			tmp[0] = val;
			exchange(0,k - 1, 0);
			return tmp[0];
		}
	}
    
	/**
	 *  思路,快排思路随机选取一个pivot,按照他直接截取n个比pivot大元素的数组,看k和n比值:
	 * k - 1 > n,在小得那边找(k-n)个大的元素
	 * k - 1 = n,返回pivot
	 * k - 1 < n,找n内的k 大的元素 
	 * 此解法时间复杂度不通过!!
	 * @param val
	 * @return
	 */
	@Deprecated
    public int add0(int val) {
    	int[] tmp =  new int[nums.length + 1] ;
    	for(int i = 0;i < nums.length ; i ++) {
    		if(i < nums.length ) {
    			tmp[i] = nums[i];
    		}
    	}
    	tmp[tmp.length - 1] = val;
    	nums = tmp;
    	return tmp[findK(0,tmp.length - 1,this.k)];
    }
    
    /**
     * 找出start - end 的最大的k个元素
     * @param startIdx
     * @param endIdx
     * @return
     */
	@Deprecated
	private int findK(int startIdx, int endIdx,int k) {
		if( startIdx > endIdx  || k < 1 || k - 1 > (endIdx - startIdx)) {
			System.out.println(startIdx + "->" + endIdx + "->" + k);
			return -1;
		}
		if(startIdx ==  endIdx) {
			return startIdx;
		}
		int pivot = startIdx;
		int newstartIdx = pivot + 1;
		int bound = newstartIdx;
		while(newstartIdx <= endIdx) {
			if(nums[newstartIdx] > nums[pivot]) {
				swap(nums, newstartIdx, bound);
				bound++;
			} 
			newstartIdx++;
		}
		swap(nums, pivot, bound - 1);
		pivot = bound -1;
		
		if(startIdx + (k -1) == pivot) {
			return pivot;
		} else if(startIdx  + (k -1) > pivot) {
			return findK(pivot + 1,endIdx,(k - (pivot - startIdx) ) - 1 );
		} else {
			return findK( startIdx,pivot - 1,k );
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

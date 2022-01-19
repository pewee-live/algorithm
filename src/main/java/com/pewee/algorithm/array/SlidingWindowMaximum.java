package com.pewee.algorithm.array;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * 滑动窗口内的最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * @author pewee
 *
 */
public class SlidingWindowMaximum {
	
	public static void main(String[] args) {
		int[] is = new SlidingWindowMaximum().maxSlidingWindow(new int[] {
				1,3,1,2,0,5
				},
				3);
	}
	
	
	/**
	 * 思路: 使用双向队列,新加入的元素从尾部加入.存原数组的元素下标
	 * 看队首的元素是否小于窗口左值,若小于,pop出头.
	 * 新加入的元素和之前的元素对比,队列里比他小的index全杀掉.这个队列里最大的元素之中在队首,队尾的元素始终最小.
	 * * 若新加入元素大于队首,可以直接clear队列再入队
	 * * 新加入的元素可以从队尾开始删比他小的元素,因为队尾的数据最小
	 * @param nums
	 * @param k
	 * @return
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		if(null == nums) {
			return new int[] {};
		}
		//计算最终结果长度
		int len = nums.length - k + 1;
		//返回的结果
		int[] ret = new int[len];
		LinkedList<Integer> linkedList = new LinkedList<Integer>();//这个队列只存下标
		for(int i = 0;i < nums.length ; i ++) {
			//将元素放入队列并按以上逻辑处理,从0开始滑动,最多只能滑动到len - 1
			doSlidingWindow(nums,linkedList,i,k);
			if(i >= k - 1) {
				//初始化完成,开始用第一个数来作为返回
				ret[i - k + 1] = nums[linkedList.peek()];
			}
		}
		return ret;
	}
	
	/**
	 * 处理队列
	 * @param linkedList
	 * @param 
	 */
	private void doSlidingWindow(int[] nums,LinkedList<Integer> linkedList, int windowRight, int k) {
		//计算左window,不能小于下标0;
		int windowLeft = windowRight - k + 1 < 0 ? 0 : windowRight - k + 1;
		//目前窗口左边已经划过了链表中存的窗口了,需要删掉链表中的leftwindow
		if(null != linkedList.peek() && linkedList.peek() <  windowLeft) {
			linkedList.pop();
		}
		if(null != linkedList.peek()) {
			if(nums[windowRight] > nums[linkedList.peek()]) {
				linkedList.clear();
			} else {
				while(!linkedList.isEmpty() && nums[linkedList.peekLast()] <= nums[windowRight]) {
					linkedList.pollLast();
				}
			}
		}
		linkedList.add(windowRight);
	}


	/**
	 * 该解法超过时间限制
	 * 思路:采用大顶堆实现,每次滑动,删除前面的元素,添加新元素
	 * @param nums
	 * @param k
	 * @return
	 */
	@Deprecated
	public int[] maxSlidingWindow0(int[] nums, int k) {
		//计算最终结果长度
		int len = nums.length - k + 1;
		//返回的结果
		int[] ret = new int[len];
		//构建数量为k大顶堆
		int[] tmp = new int[k];
		for(int i = 0; i < tmp.length ;i ++) {
			if(i < k) {
				tmp[i] = nums[i];
			} else {
				tmp[i] = Integer.MIN_VALUE;
			}
		}
		doHeap(tmp, 0, k -1);
		
		//开始滑动窗口并输出结果
		int start = 0;int end = k -1;
		for(int i = 0;i <  len; i ++) {
			//输出结果
			ret[i] = tmp[0];
			System.out.println("获取第:" + i + "个窗口->" + ret[i]);
			if(i != len -1 ) {
				int startNum = nums[start + i];
				int endNumP1 = nums[end + i + 1];
				//维护堆,删start,加入nums[end + i+1]
				for(int j = 0;j < tmp.length ; j ++) {
					if(startNum == tmp[j]) {
						tmp[j] = endNumP1;
						if( j ==  0) {
							exchange(tmp, 0, k -1, j);
						} else {
							doHeap(tmp, 0, k -1);
						}
						break;
					}
				}
			}
		}
		return ret;
    }
	
	
	private void doHeap(int[] tmp,int start, int end) {
		if(start >= end) {
			return;
		}
		int middle = (start + end )/2;
		for(;middle>=0;) {
			exchange(tmp,start,end,middle);
			middle--;
		}
	}
	
	/**
	 * 比较单个节点的左右儿子,把大的移动上来
	 * @param start
	 * @param end
	 * @param exchange
	 */
	private void exchange(int[] tmp,int start, int end,int exchange) {
		int leftidx = 2*exchange + 1;
		int rightidx = 2*exchange + 2;
		
		int bigIndex = exchange;
		if(leftidx <= end) {
			if(tmp[leftidx] > tmp[bigIndex]) {
				bigIndex = leftidx;
			}
		}
		if(rightidx <= end) {
			if(tmp[rightidx] > tmp[bigIndex]) {
				bigIndex = rightidx;
			}
		}
		//需要交换
		if(bigIndex!= exchange) {
			swap(tmp, exchange, bigIndex);
			//交换下去的小值还要和下面的2个儿子比
			exchange(tmp,start,end,bigIndex);
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

package com.pewee.algorithm.hashAndSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 三个数之和
 * @author pewee
 * https://leetcode-cn.com/problems/3sum/
 * 思路:2层循环.最后一个数用map直接查找,和twosum一个思路
 * 
 * 思路:把数组排序.从头到尾遍历.每个元素拿出来,在在后面的数组里拿出2个数使他们的和为0,
 * 找的过程使用2边夹逼法,若搭了右边界往左移,否则左边界往右移
 */
public class ThreeSum {
	
	
	public static void main(String[] args) {
		List<List<Integer>> threeSum = new ThreeSum().threeSum(new int[] {-1,0,1,2,-1,-4}, 0);
		for (List<Integer> list : threeSum) {
			
			for (Integer i : list) {
				System.out.print(i);
			}
			System.out.println(";");
			
		}
	}
	
	/**
	 * 夹逼法
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums,int target) {
		//先排序,后面的元素会始终比他大
		Arrays.sort(nums);
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		for(int i = 0; i < nums.length - 2; i ++) {
			for(int j = i + 1, k = nums.length -1; j < k;) {
				if(nums[j] + nums[i] > target ) {
					break;
				}
				int val = nums[i] + nums[j] + nums[k];
				if(val > target ) {
					k--;
				} else if(val < target) {
					j++;
				} else {
					ArrayList<Integer> arrayList = new ArrayList<Integer>();
					arrayList.add(nums[i]);
					arrayList.add(nums[j]);
					arrayList.add(nums[k]);
					result.add(arrayList);
					j++;
					k--;
				}
			}
		}
		ArrayList arrayList = new  ArrayList();
		arrayList.addAll(result);
		return arrayList;
	}
	
	/**
	 * map直接定位第三个元素法
	 * 此解法超时
	 * @param nums
	 * @param target
	 * @return
	 */
	@Deprecated
	public List<List<Integer>> threeSum0(int[] nums,int target) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		Map<Integer,LinkedList<Integer>> container = new HashMap<Integer,LinkedList<Integer>>();
		for(int i = 0;i < nums.length ; i ++) {
			if(container.containsKey(nums[i])) {
				container.get(nums[i]).add(i);
			} else {
				LinkedList<Integer> linkedList = new LinkedList<Integer>();
				linkedList.add(i);
				container.put(nums[i],linkedList );
			}
		}
		
		/**
		 * 如何去重?这里是结果要求去重
		 * 把ijk对应结果排序后转String放入hashSet
		 * 
		 * 如果只是下标去重,值可以相同,那就把下标转String放入set
		 */
		Set<String> map = new HashSet<String>();
		for(int i=0;i < nums.length; i++) {
			for(int j = i + 1 ; j < nums.length ; j++) {
				int value = target - nums[i] - nums[j];
				if(null != container.get(value)) {
					Iterator<Integer> iterator = container.get(value).iterator();
					while(iterator.hasNext()) {
						Integer idx = iterator.next();
						if(idx != i && idx != j && idx > i && idx > j) {
							int retI = nums[i];
							int retJ = nums[j];
							int retIdx = nums[idx];
							int[] retArr = new int[] {retI,retJ,retIdx};
							Arrays.sort(retArr);
							String mapStr = "" + retArr[0] + retArr[1] + retArr[2];
							if(!map.contains(mapStr)) {
								map.add(mapStr);
								ArrayList<Integer> arrayList = new ArrayList<Integer>();
								arrayList.add(retI);
								arrayList.add(retJ);
								arrayList.add(retIdx);
								result.add(arrayList);
								break;//不break会有重复的下标不同,但是值相同的结果
							}
						}
					}
				}
			}
		}
		return result;
    }
}

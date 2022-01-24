package com.pewee.algorithm.hashAndSet;

import java.util.HashMap;

/**
 * 求数组中出现次数大于2/length的数
 * @author pewee
 * https://leetcode-cn.com/problems/majority-element/
 * 思路1:遍历数据,把出现的每个数计入hashmap 时间,空间复杂度都是o(N)
 * 思路2:排序后,计算每个数字重复的次数,只留最大的 时间复杂度o(logN) 因为要排序
 * 思路3:分治算法,把数组平分,算2边的出现最多的数,若2边相同,直接返回,若不同,返回count多的那个数 时间复杂度o(NlogN)
 */
public class MajorityElement {
	public int majorityElement(int[] nums) {
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i : nums) {
			if(map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
		}
		Integer ret = null;
		Integer num = 0;
		for (HashMap.Entry<Integer,Integer> e : map.entrySet()) {
			if(num < e.getValue()) {
				ret = e.getKey();
				num = e.getValue();
			}
		}
		return ret;
    }
}

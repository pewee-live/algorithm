package com.pewee.algorithm.binarySearch;
/**
 * 求x的平方根
 * @author pewee
 * https://leetcode-cn.com/problems/sqrtx/
 * 思路:二分法
 *
 */
public class Sqrt {
	public double mySqrt(int x) {
		if (x == 0 || x == 1 ) {
			return x;
		}
		int left = 0;
		int right = x;
		int result = 0;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(mid >  x / mid) {
				right = mid -1;
			} else if (mid <  x / mid) {
				left = mid + 1;
				result = mid;
			} else {
				return mid;
			}
		}
		return result;
    }
}

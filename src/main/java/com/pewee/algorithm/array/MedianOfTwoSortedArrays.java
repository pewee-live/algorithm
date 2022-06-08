package com.pewee.algorithm.array;
/**
 * 寻找两个正序数组的中位数
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 * @author pewee
 *
 */
public class MedianOfTwoSortedArrays {
	public static void main(String[] args) {
		double d = new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] {1}, new int[] {2,3,4,5,6});
		System.out.println(d);
	}
	
	/**
	 * 思路2:转换为求2个数组的k小的元素的问题,
	 * 当数组合为奇数个的时候,求的是数组合 k = (length1 + length2)/2 + 1
	 * 当数组合为偶数个的时候,求的是数组合 k1 = (length1 + length2)/2 + 1 ,k2 = (length1 + length2)/2 加起来/2;
	 * 求2个数组合k小元素可以采用二分法 分别看2个数组的 k/2 个元素的大小,小的那个的数组的前 k/2个元素可以直接淘汰掉,问题变成
	 * 求2个数组的k - (k/2)的问题
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len = nums1.length + nums2.length;
		if (len % 2 == 0) {
			//偶数个
			int k1 = len /2 + 1;
			int k2 = len /2;
			return Double.valueOf((getKSmall(nums1,nums2,0,0,k1) + getKSmall(nums1,nums2,0,0,k2)) )/2;
 		} else {
			//奇数个
 			int k = len /2 + 1;
 			return getKSmall(nums1,nums2,0,0,k);
		}
    }
	
	/**
	 * 获取从nums1的i开始,从nums2的j开始的第k小的数
	 * @param nums1
	 * @param nums2
	 * @param i
	 * @param j
	 * @param k1
	 */
	private int getKSmall(int[] nums1, int[] nums2, int i, int j, int k) {
		if (i == nums1.length) {//边界条件:nums1已经指到头了,直接拿nums2的第k小的元素
			return nums2[j + k - 1];
		}
		if (j == nums2.length) {//边界条件:nums2已经指到头了,直接拿nums1的第k小的元素
			return nums1[i + k - 1];
		}
		//只用拿2个数组的最后一个元素了,结束递归
		if (k < 2) {
			if (nums1[i] < nums2[j]) {
				return nums1[i];
			} else {
				return nums2[j];
			}
		}
		int idx = k/2 - 1;//计算下次的坐标
		if (i + idx > nums1.length - 1) {//考虑边界条件 i + idx > nums1的数组边界,直接把nums1指到最后一个元素
			idx = nums1.length - 1 - i;
		} else if (j + idx > nums2.length - 1) {//考虑边界条件 j + idx > nums2的数组边界,直接把nums2指到最后一个元素
			idx = nums2.length - 1 - j;
		} 
		if (nums1[i + idx] < nums2[j + idx]) {
			//nums1在丢掉idx + 1个元素
			return getKSmall(nums1, nums2, i + idx + 1,  j, k - (idx + 1));
		} else {
			//nums2在丢掉idx + 1个元素
			return getKSmall(nums1, nums2, i , j + idx + 1, k - (idx + 1));
		}
	}

	/**
	 * 思路：用2个指针来合并2个数组
	 * 复杂度 o(n + m) 不通过
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		int[] arr = new int[nums1.length + nums2.length];
		int idx1 = 0;
		int idx2 = 0;
		for (int i = 0;i < arr.length; i++) {
			if (idx1 >= nums1.length) {
				arr[i] = nums2[idx2];
				idx2++;
			}  else if (idx2 >= nums2.length) {
				arr[i] = nums1[idx1];
				idx1++;
			} else {
				if (nums1[idx1] <= nums2[idx2] ) {
					arr[i] = nums1[idx1];
					idx1++;
				} else {
					arr[i] = nums2[idx2];
					idx2++;
				}
			}
		}
		if ( (nums1.length + nums2.length) % 2 == 1) {
			return Double.valueOf("" + arr[(nums1.length + nums2.length) / 2]);
		} else {
			return Double.valueOf("" + ( arr[(nums1.length + nums2.length) / 2] +   arr[(nums1.length + nums2.length) / 2 - 1] )) / 2;
		} 	
    }
}

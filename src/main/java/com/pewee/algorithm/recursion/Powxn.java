package com.pewee.algorithm.recursion;
/**
 * 计算X的n次方
 * @author pewee
 * https://leetcode.com/problems/powx-n/
 * 思路: 把数据对半分,算出来一半的数据直接相乘.若数据为偶数直接分,若为奇数先拿出一个n
 * 每次分治减少一半数据
 */
public class Powxn {
	
	public static void main(String[] args) {
		double myPow = new Powxn().myPow(1.0, -2147483648);
		System.out.println(myPow);
	}
	
	public double myPow(double x, int n) {
		if(n == 0 ) {
			return 1;
		}
		if(n == 1) {
			return x;
		}
		if(n < 0) {
			if (n==-2147483648) {//In Java, -2147483648<=int<=2147483647, so when we multiply -2147483648 with -1 -> 2147483648 gives  undefined behaviour.
				n=-2147483646; 
			}  
			return 1/myPow( x,  -n);
		}
		if(n%2 == 0) {
			return myPow(x*x,n/2);
		} else {
			return myPow(x,n - 1) * x;
		}
    }
}

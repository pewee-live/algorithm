package com.pewee.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * n皇后问题
 * @author pewee
 * https://leetcode-cn.com/problems/n-queens/
 * https://leetcode.com/problems/n-queens-ii
 * 思路: 深度优先遍历.终止条件就是棋盘row到了n.给出一个n表示棋盘,那我们可以在每一层有n种放法,首先通过一个数组记录第index行(从0开始)的数据放在第arr[index]列里.
 * 对于每一行从0遍历到n,尝试放皇后,如果校验通过,放下该皇后,并进入更深一层放皇后(每一层放皇后的动作是一样的,只是层数不同而已)
 * 当放到row = n的时候表示一种方法OK了,需要输出,或者计数器加1
 *
 */
public class NQueen {
	
	public static void main(String[] args) {
		List<List<String>> nQueens = new NQueen().solveNQueens(8);
		for (List<String> list : nQueens) {
			for (String W : list) {
				System.out.println(W);
			}
			System.out.println("=================");
		}
	}
	
	String queen = "Q";
	
	String empty = ".";
	
	ArrayList<List<String>> ret = new ArrayList<List<String>>();
	
	AtomicInteger ai = new AtomicInteger(0);
	
	public List<List<String>> solveNQueens(int n) {
		//以数组初始化,没有一个皇后
		int[] result = new int[n];
		putNext(0,result,n);
		System.out.println("i got " + ai.get() +  " results!");
		return ret;
    }
	
	public void putNext(int row,int[] result,int n) {
		if(row == result.length) {//放完了
			printResult(result);
			ai.addAndGet(1);
			return;
		}
		//第row行的第i列放皇后,每列都尝试
		for (int i = 0;i < n;i ++ ) {
			if (isOK(row,result,i)) {
				result[row] = i;
				putNext(row + 1, result, n);
			}
		}
	}
	
	private void printResult(int[] result) {
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < result.length; i++) {
			int col = result[i];
			StringBuilder stringBuilder = new StringBuilder("");
			for (int j = 0;j < result.length;j++) {
				if (j != col) {
					stringBuilder.append(empty);
				} else {
					stringBuilder.append(queen);
				}
			}
			arrayList.add(stringBuilder.toString());
		}
		ret.add(arrayList);
	}

	/**
	 * 看在row行,i列放皇后是否可行
	 * @param row
	 * @param result
	 * @param column
	 * @return
	 */
	private boolean isOK(int row, int[] result, int column) {
		int leftUp = column - 1;
		int rightUp = column + 1;
		for (int i = row - 1;i >= 0;i--) {//一层层向上看
			if(result[i] == column) {//第i层的column列是不是queen
				return false;
			}
			if(leftUp >= 0 && result[i] == leftUp) {//第i层的左对角线上是否有
				return false;
			}
			if(rightUp >= 0 && rightUp < result.length && result[i] == rightUp) {//第i层的左对角线上是否有
				return false;
			}
			leftUp--;
			rightUp++;
		}
		return true;
	}
	
}

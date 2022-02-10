package com.pewee.algorithm.dynamicProgram;
import java.util.List;
/**
 * 三角形最小路径和
 * @author pewee
 * https://leetcode-cn.com/problems/triangle/
 * 思路:每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * f(i,j) = min(f(i + 1,j),f(i+1,j+1)) + arr[i][j]
 */
public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		//处理特殊结果,只有一层
		if(triangle.size() == 1) {
			return triangle.get(0).get(0);
		}
		
		//初始化返回结果 
		int[][] ret = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
		for (int i=0;i < ret.length;i++) {
			for (int j =0;j < ret[0].length;j++) {
				List<Integer> row = triangle.get(i);
				if(j > row.size() -1) {
					ret[i][j] = -1;
				} else {
					ret[i][j] = row.get(j);
				}
			}
		}
		//开始递推,从三角的底往顶推
		for (int i=ret.length -2;i >=0 ;i--) {
			for (int j =0;j < ret[0].length;j++) {
				if (j < i + 1) {
					ret[i][j] = Math.min(ret[i + 1][j], ret[i + 1][j + 1]) + triangle.get(i).get(j);
				}
			}
		}
		return ret[0][0];
    }
	
	public int minimumTotal0(List<List<Integer>> triangle) {
		//处理特殊结果,只有一层
		if(triangle.size() == 1) {
			return triangle.get(0).get(0);
		}
		
		//开始递推,从三角的底往顶推
		for (int i=triangle.size() -2;i >=0 ;i--) {
			for (int j =0;j < triangle.get(triangle.size() - 1).size();j++) {
				if (j < i + 1) {
					triangle.get(i).set(j, Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)) + triangle.get(i).get(j)) ;
				}
			}
		}
		return triangle.get(0).get(0);
    }
}

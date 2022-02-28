package com.pewee.algorithm.dynamicProgram;
/**
 * 编辑距离: 将 word1 转换为 word2 所使用的最少操作数( 插入 删除 替换 )
 * @author pewee
 * https://leetcode-cn.com/problems/edit-distance/
 * 思路:递归回溯法-> 维护2个指针指向2个word,
 * 可以删除 a[i]，然后递归考察 a[i+1]和 b[j]；可以删除 b[j]，然后递归考察 a[i]和 b[j+1]；可以在 a[i]前面添加一个跟 b[j]相同的字符，然后递归考察 a[i]和 b[j+1];可以在 b[j]前面添加一个跟 a[i]相同的字符，然后递归考察 a[i+1]和 b[j]；可以将 a[i]替换成 b[j]，或者将 b[j]替换成 a[i]，然后递归考察 a[i+1]和 b[j+1]。
 * 当word1[i] = word2[j] , f(i+1, j+1, edist) 
 * 否则f(i+1, j, edist+1)  ,f(i+1, j+1, edist+1) 这几条路都走
 * 思路：对于2个单词 我们可以维护2个指针表示对应单词的下标.
 * 如果word1[i] = word2[j] 那么 f(i,j) = min(f(i-1,j) + 1,f(i,j-1) + 1,f(i-1,j-1))
 * 否则 f(i,j) = min(f(i-1,j) + 1,f(i,j-1) + 1,f(i-1,j-1) + 1)
 */
public class EditDistance0 {
	public int minDistance(String word1, String word2) {
		//先处理特殊情况
		if (word1.length() == 0 ) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		//建立2个字符串的二维数组
		int[][] result = new int[word2.length()][word1.length()];
		//初始化数组,数组的第一行和第一列已经固定了
		for (int i = 0;i < result[0].length; i++) {//行
			if (word2.charAt(0) == word1.charAt(i)) {//相等
				//防止数组越界
				if (i - 1 >= 0) {
					result[0][i] = result[0][i - 1] ;
				} else {
					result[0][i] = 0; 
				}
			} else {
				//防止数组越界
				if (i - 1 >= 0) {
					result[0][i] = result[0][i - 1] + 1;
				} else {
					result[0][i] = 1; 
				}
			}
		}
		
		for (int i = 0;i < result.length; i++) {//列
			if (word1.charAt(0) == word2.charAt(i)) {//相等
				//防止数组越界
				if (i - 1 >= 0) {
					result[i][0] = result[i - 1][0] ;
				} else {
					result[i][0] = 0; 
				}
			} else {
				//防止数组越界
				if (i - 1 >= 0) {
					result[i][0] = result[i - 1][0] + 1;
				} else {
					result[i][0] = 1; 
				}
			}
		}
		
		//开始递推
		for (int i = 1; i< result.length;i++) {
			for (int j = 1; j < result[0].length;j++) {
				if (word1.charAt(j) == word2.charAt(i)) {
					result[i][j] = Math.min( Math.min(result[i -1][j] + 1, result[i][j - 1] + 1),result[i -1][j-1]);
				} else {
					result[i][j] = Math.min( Math.min(result[i -1][j] + 1, result[i][j - 1] + 1),result[i -1][j-1] + 1);
				}
			}
		}
		return result[word2.length() - 1][word1.length() - 1];
    }
}

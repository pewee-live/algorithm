package com.pewee.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据n生成有效括号
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * @author pewee
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 思路:回溯算法:把问题化解为有2n个格子,往里面放括号,里面左括号和右括号都是能放n个	
 * 剪枝操作:添加括号时右边的括号不能超过左边的数量,否则会出现())这样的非法括号
 *
 *
 */
public class GenerateParentheses {
	public static void main(String[] args) {
		List<String> generateParenthesis = new GenerateParentheses().generateParenthesis(3);
		
		for (String string : generateParenthesis) {
			System.out.println(string);
		}
	}
	
	String left = "(";
	String right = ")";
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
		ArrayList<String> ret = new ArrayList<String>();
		//第一个括号必须是左
		validate(n -1,n,"(",ret);
		return ret;
    }
	
	private void validate(int left, int right, String string, ArrayList<String> ret) {
		if (right < left ) {//剪枝操作
			return;
		}
		if (left == 0 && right == 0) {
			ret.add(string);
			return;
		}
		if (left > 0) {
			validate(left -1,right,string + "(",ret);
		}
		if (right > 0) {
			validate(left,right - 1,string + ")",ret);
		}
		
	}
}

package com.pewee.algorithm.stack;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

	有效字符串需满足：
	
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	
	https://leetcode.com/problems/valid-parentheses/
 * @author pewee
 *
 */
public class ValidParentheses {
	
	public static void main(String[] args) {
		System.out.println(new ValidParentheses().isValid("()"));
	}
	
	public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
		for(int i = 0 ; i < s.length()  ;i++ ) {
			char chars = s.charAt(i);
			if(chars == '{' || chars == '(' || chars == '[') {
				stack.push(chars);
			} else {
				if(stack.isEmpty()) {
					return false;
				}
				if(chars == '}') {
					if(stack.pop() != '{' ) {
						return false;
					}
				} else if(chars == ')') {
					if(stack.pop() != '(' ) {
						return false;
					}
				} else {
					if(stack.pop() != '[' ) {
						return false;
					}
				}
			}
			
		}
		if(!stack.isEmpty()) {
			return false;
		}
		return true;
    }
	
}

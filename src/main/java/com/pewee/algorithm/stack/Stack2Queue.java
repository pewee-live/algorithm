package com.pewee.algorithm.stack;

import java.util.Stack;

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）
 * 只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @author pewee
 * 思路通过负负得正的效果,一个Stack只做入,出的话荣入栈把全部元素拿过来出
 *
 */
public class Stack2Queue {
	
	public Stack2Queue() {

    }
	
	Stack<Integer> in = new Stack();
	Stack<Integer> out = new Stack();
	
	public void push(int x) {
		in.push(x);
    }
    
    public int pop() {
    	if(out.isEmpty()) {
    		while(!in.isEmpty()) {
        		out.push(in.pop());
        	}
    	}
    	return out.pop();
    }
    
    public int peek() {
    	if(out.isEmpty()) {
    		while(!in.isEmpty()) {
        		out.push(in.pop());
        	}
    	}
    	return out.peek();
    }
    
    public boolean empty() {
    	return in.isEmpty() && out.isEmpty();
    }
}

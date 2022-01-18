package com.pewee.algorithm.queue;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * 能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作
 * @author pewee
 * 思路:q2作为备份,每次pop或top把前面 n -1 个元素放入q2,操作结束在放回q1
 *
 */
public class Queue2Stack {
	
	public static void main(String[] args) {
		Queue2Stack queue2Stack = new Queue2Stack();
		queue2Stack.push(1);
		queue2Stack.push(2);
		queue2Stack.push(3);
		System.out.println(queue2Stack.top());
		//System.out.println(queue2Stack.pop());
	}
	
	LinkedList<Integer> q1 = new LinkedList<Integer>();
	LinkedList<Integer> q2 = new LinkedList<Integer>();

    public void push(int x) {
    	q1.add(x);
    }
    
    public int pop() {
    	int size = q1.size() - 1;
    	for(int i = 0; i < size; i++) {
    		q2.add(q1.pop());  
    	}
    	int ret = q1.pop();
    	while(!q2.isEmpty()) {
    		q1.add(q2.pop());
    	}
    	return ret;
    }
    
    public int top() {
    	int size = q1.size() - 1;
    	for(int i = 0; i < size; i++) {
    		q2.add(q1.pop());  
    	}
    	int ret = q1.pop();
    	while(!q2.isEmpty()) {
    		q1.add(q2.pop());
    	}
    	q1.add(ret);
    	return ret;
    }
    
    public boolean empty() {
    	return q1.isEmpty() ;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
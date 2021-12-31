package com.pewee.algorithm.queue;

import lombok.Data;

/**
 * 循环队列
 * @author pewee
 *
 */
@Data
public class CircleArrayQueue {
	
	private String[] container;
	
	private int head;
	
	private int tail;
	
	private int capacity;
	
	public CircleArrayQueue(int capacity) {
		container = new String[capacity];
		this.capacity = capacity;
	}
	
	/**
	 * 入队
	 * @param str
	 * @return
	 */
	public boolean enqueue(String str) {
		if( (tail+1)%capacity == head) {
			System.out.println("队列已满!!");
			return false;
		}
		container[tail] =  str;
		tail = (tail+1)%capacity;
		return true;
	}
	
	/**
	 * 出队
	 * @return
	 */
	public String dequeue() {
		if(head == tail ) {
			return null;
		}
		String deq = container[head];
		head = (head+1)%capacity; 
		return deq;
	}
	
}

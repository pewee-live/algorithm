package com.pewee.algorithm.link;

import lombok.Data;

/**
 * 单链表
 */
@Data
public class MyLink<T> {
	
	private T data;
	
	private MyLink<T> next;
	
}

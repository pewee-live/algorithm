package com.pewee.algorithm.link;

import org.junit.Test;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * 链表相关测试
 * @author pewee
 *
 */
public class LinkReverseTest {
	
	public static MyLink<Integer> head ;
	
	static {
		/**
		 * 初始化单链表
		 */
		head = new MyLink<Integer>();
		MyLink<Integer> no1 = new MyLink<Integer>();
		no1.setData(1);
		head.setNext(no1);
		MyLink<Integer> no2 = new MyLink<Integer>();
		no2.setData(2);
		no1.setNext(no2);
		MyLink<Integer> no3 = new MyLink<Integer>();
		no3.setData(3);
		no2.setNext(no3);
		MyLink<Integer> no4 = new MyLink<Integer>();
		no4.setData(4);
		no3.setNext(no4);
		MyLink<Integer> no5 = new MyLink<Integer>();
		no5.setData(5);
		no4.setNext(no5);
		
	}
	
	/**
	 * 单链表翻转
	 */
	@Test
	public void reverseSingleLink() {
		if(head.getNext() == null) {
			System.out.println("链表为空");
			return;
		}
		
		if(head.getNext().getNext() == null) {
			System.out.println("链表节点为1,不需要倒转");
			return;
		}
		
		/**
		 * 翻转逻辑
		 * 就地翻转头插法
		 */
		MyLink<Integer> reverse = head.getNext();
		while((reverse ) != null) {
				if(null != reverse.getNext()) {
					MyLink<Integer> endtemp = reverse.getNext();
					reverse.setNext(endtemp.getNext());
					endtemp.setNext(head.getNext());
					head.setNext(endtemp);
				} else {
					System.out.println("over");
					break;
				}
		}
		
		/**
		 * 打印
		 */
		print(head);
		
		/**
		 * 递归法
		 */
		MyLink<Integer> reverseInvoke = reverseInvoke(head.getNext());
		head.setNext(reverseInvoke);
		
		/**
		 * 打印
		 */
		print(head);
	}
	
	public MyLink<Integer> reverseInvoke(MyLink<Integer> head){
		/**
		 * 已经递归完
		 */
		if(null == head || head.getNext() == null) {
			return head;
		}
		
		MyLink<Integer> newHead = reverseInvoke(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return newHead;
	}
	
	/**
	 * 打印
	 * @param head
	 */
	public static void print(MyLink<Integer> head) {
		MyLink<Integer> tmp = head;
		while((tmp = tmp.getNext()) != null) {
			System.out.println(tmp.getData());
		}
		System.out.println("======================================");
	}

}

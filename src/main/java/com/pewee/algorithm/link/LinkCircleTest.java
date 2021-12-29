package com.pewee.algorithm.link;

import org.junit.Test;

/**
 * 环形链表检测
 * @author pewee
 *
 */
public class LinkCircleTest {
	
	public static MyLink<Integer> head ;
	
	static {
		/**
		 * 初始化环形单链表
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
		MyLink<Integer> no6 = new MyLink<Integer>();
		no6.setData(6);
		no5.setNext(no6);
		MyLink<Integer> no7 = new MyLink<Integer>();
		no7.setData(7);
		no6.setNext(no7);
		no7.setNext(no4);
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
	
	@Test
	public void testCircle() {
		/**
		 * 快慢指针法
		 * 2个指针相遇则有环
		 */
		
		
		/**
		 * 足迹法
		 * 将路过的节点加入hash表,若有相同的节点加入说明有环
		 */
	}
}

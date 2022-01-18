package com.pewee.algorithm.link;
/**
 * 按对交换节点
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * @author pewee
 * https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg
 *
 */
public class SwapNodesInPairs {
	
    static MyLink<Integer> head ;
	
	static {
		/**
		 * 初始化单链表
		 */
		MyLink<Integer> no1 = new MyLink<Integer>();
		no1.setData(1);
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
		head = no1;
	}
	
	/**
	 * 1,2,3,4,5 => 2,1,4,3,5
	 * @param args
	 */
	public static void main(String[] args) {
		MyLink prev = null ,pp = null;
		MyLink current = head;
		MyLink newHead = null;
		int i = 0;
		while(current != null) {
			MyLink ne = current.getNext();
            if(i == 1 || i == 0){
                newHead = current;
            }
            if(i % 2 == 1 ){
                if(null != pp){
                   pp.setNext(current); 
                } 
                 prev.setNext(ne) ;
                 current.setNext(prev);
                 
                pp = current;
      			prev = current.getNext();
      			current = current.getNext().getNext();
             } else {
            	pp = prev;
     			prev = current;
     			current = current.getNext();
             }
			
			
			i++;
		}
		print(newHead);
	}
	
	/**
	 * 打印
	 * @param head
	 */
	public static void print(MyLink<Integer> head) {
		MyLink<Integer> tmp = head;
		while(tmp != null) {
			System.out.println(tmp.getData());
			tmp = tmp.getNext();
		}
		System.out.println("======================================");
	}
}

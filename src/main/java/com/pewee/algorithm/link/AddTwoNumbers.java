package com.pewee.algorithm.link;

import com.pewee.algorithm.link.AddTwoNumbers.ListNode;

/**
 * 两数相加
 * https://leetcode.com/problems/add-two-numbers/
 * @author pewee
 *
 */
public class AddTwoNumbers {
	
	
	public static void main(String[] args) {
		AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
		ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
		ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
		
		ListNode addTwoNumbers2 = addTwoNumbers.addTwoNumbers(l1, l2);
		System.out.println(addTwoNumbers2);
	}
	
	/**
	 * 用一个指针来指2个链表的节点,这个指针走完了那结果就出来了
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode tmp1 = l1, tmp2 =  l2,tmp3 = null,tmp3Curr = null;
		int addCount = 0;
		boolean f1 = false,f2 = false;//标记l1,l2是否结束
		while (!f1 || !f2 || addCount != 0) {
			int var = 0;
			if (!f1) {
				var = var + tmp1.val;
				if (tmp1.next != null) {
					tmp1 = tmp1.next;
				} else {
					f1 = true;
				}
			}
			if (!f2) {
				var = var + tmp2.val;
				if (tmp2.next != null) {
					tmp2 = tmp2.next;
				} else {
					f2 = true;
				}
			}
			var = var + addCount;
			addCount = var/10;
			var = var%10;
			
			if (null == tmp3) {
				tmp3 = new ListNode(var);
                tmp3Curr = tmp3;
			} else {
				tmp3Curr.next = new ListNode(var);
                tmp3Curr = tmp3Curr.next;
			}
		}
		return tmp3;
    }
	
	
	 public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode() {}
		 ListNode(int val) { this.val = val; }
		 ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}

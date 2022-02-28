package com.pewee.algorithm.lru;

import java.util.HashMap;
import java.util.Map;
/**
 * LRU
 * @author pewee
 * https://leetcode-cn.com/problems/lru-cache
 * 思路:把元素转换为map和双向链表,双向链表只支持头部插入和取出操作,淘汰时只用remove队尾元素
 *
 */
public class LruCache {
	
	//缓存容量
    private int capacity;

    //map用来存放数据节点
    private Map<Integer, Node> map;

    //双向链表，进行数据的删除与增加
    private DoubleLinkedList list;
	
	
	
	public LruCache(int capacity) {
		 this.capacity = capacity;
	     map = new HashMap<>();
	     list = new DoubleLinkedList();
    }
    
    public int get(int key) {
    	if ( !map.containsKey(key) ) {
            return -1;
        }
        Node node = map.get(key);
        list.removeNode(node);
        list.addHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
    	if ( map.containsKey(key) ) {
            //如果已经包含
            Node node = map.get(key);
            //update data
            node.val = value;
            //put map
            map.put(key, node);
            list.removeNode(node);
            list.addHead(node);
        } else {
            Node newNode = new Node(key, value);
            if (map.size() == capacity) {
                Node lastNode = list.getLastNode();
                map.remove(lastNode.key);
                list.removeNode(lastNode);
            }
            map.put(key,newNode);
            list.addHead(newNode);
        }
    }
    
    
    static class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node () {}
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    class DoubleLinkedList{
    	Node head;
        Node tail;
        
        public DoubleLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        //在链表头部增加节点
        public void addHead(Node node) {
            if (node == null) return;
            node.next = this.head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        //删除节点
        public void removeNode(Node node) {
            if (node == null) return;

            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        //获取最后一个元素
        public Node getLastNode() {
            return this.tail.prev;
        }
    }
}

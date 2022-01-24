package com.pewee.algorithm.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 二叉树层序遍历
 * @author pewee
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * 思路:把他当做一种特殊的图,做层序遍历即可!!(BFS),注意这里每一层输出一个list,
 * 所以我们在bfs循环中加入了一个循环,输出完一层的信息,再会开始一轮新的层的输出,在每一层输出开始前计算一下需要输出的节点数量即可.
 * 思路:使用DFS输出,我们在递归的时候就把level当做参数传递给下一层,输出的时候根据层数加入ret[level]的list中(假设level从0开始)
 *
 */
public class BinarytreelevelOrdertraversal {
	
	
	
	/**
	 * 使用深度优先搜索
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder0(TreeNode root){
		List<List<Integer>> ret = new ArrayList<>();
		HashSet<TreeNode> visited = new HashSet<TreeNode>();
		if(null == root) {
			return ret;
		}
		dfs(root, 0, ret, visited);
		return ret;
	}
	
	public void dfs(TreeNode root,int level,List<List<Integer>> ret,Set<TreeNode> visited) {
		visited.add(root);
		if(ret.size() < level + 1) {
			ret.add(new ArrayList<Integer>());
		}
		List<Integer> list = ret.get(level);
		list.add(root.val);
		if(null != root.left && !visited.contains(root.left)) {
			dfs(root.left, level +1, ret, visited);
		}
		if(null != root.right && !visited.contains(root.right)) {
			dfs(root.right, level +1, ret, visited);
		}
	}
	
	/**
	 * 使用BFS搜索,使用count来计每层节点的数量,一个循环里弹出该层所有节点
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ret = new ArrayList<>();
		if(null == root) {
			return ret;
		}
		HashSet<TreeNode> visited = new HashSet<TreeNode>();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(queue.peek() != null) {
			int levelCount = queue.size();//每层输出完了就用一个list存
			List<Integer> list = new ArrayList<Integer>();
			while(levelCount > 0) {
				TreeNode pop = queue.pop();
				if(!visited.contains(pop)) {
					list.add(pop.val);
					visited.add(pop);
					if(null != pop.left) {
						queue.add(pop.left);
					}
					if(null != pop.right) {
						queue.add(pop.right);
					}
				}
				levelCount--;
			}
			ret.add(list);
		}
		return ret;
    }
	
	
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
package com.pewee.algorithm.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的最大深度/最小深度
 * @author pewee
 *	https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * 思路:使用BFS,每次输出一层,并计数level+1,得到最后的level输出
 * 思路:使用DFS,找到一个最大level,有更大的时候update	
 *
 *
 */
public class MaxDepthOfTree {
	
	/**
	 * DFS
	 * @param root
	 * @return
	 */
	public int maxDepth0(TreeNode root) {
		if(null == root) {
			return 0;
		}
		HashSet<TreeNode> visited = new HashSet<TreeNode>();
		return dfs(root,1,visited);
	}
	
	private int dfs(TreeNode root, int depth, HashSet<TreeNode> visited) {
		visited.add(root);
		Integer dep = depth;
		if(null != root.left && !visited.contains(root.left)) {
			int dfs = dfs(root.left, depth +1, visited);
			if(dfs > dep) {
				dep = dfs;
			}
		}
		if(null != root.right && !visited.contains(root.right)) {
			int dfs = dfs(root.right, depth +1, visited);
			if(dfs > dep) {
				dep = dfs;
			}
		}
		return dep;
	}

	/**
	 * BFS输出
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		if(null == root) {
			return 0;
		}
		HashSet<TreeNode> visited = new HashSet<TreeNode>();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int depth = 0;
		while(queue.peek() != null) {
			int levelCount = queue.size();//每层输出完了就用一个list存
			depth = depth + 1;
			while(levelCount > 0) {
				TreeNode pop = queue.pop();
				if(!visited.contains(pop)) {
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
		}
		return depth;
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

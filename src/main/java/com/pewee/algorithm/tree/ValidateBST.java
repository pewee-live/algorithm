package com.pewee.algorithm.tree;
/**
 * 验证一颗树是否为BST树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author pewee
 * 
 * 思路1: 中序遍历,记录一个前值,看后值是否大于前值
 * 思路2: 对于每个节点递归找出他子树最大,最小,看它本身是否比左最大大,是否比右最小小
 * 
 */
public class ValidateBST {
	
	public static void main(String[] args) {
		ValidateBST validateBST = new ValidateBST();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		System.out.println(validateBST.isValidBST(root));
	}
	
	private TreeNode prev;
	
	public boolean isValidBST(TreeNode root) {
		//return middle(root);
		return recursion(root,null,null);
    }
	
	/**
	 * 递归法
	 * @param root
	 * @return
	 */
	private boolean recursion(TreeNode root,Integer max,Integer min) {
		if(null != root) {
			if(null != min && root.val <= min) {
				return false;
			}
			if(null != max && root.val >= max ) {
				return false;
			}
			return recursion(root.left, root.val, min) && recursion(root.right, max, root.val);
		}
		return true;
	}
	
	
	
	/**
	 * 思路1:中序遍历法,把便利的前一个节点保存下载.比较完了在替换掉
	 * @param root
	 * @return
	 */
	private boolean middle(TreeNode root) {
		if(null != root ) {
			if(null != root.left) {
				boolean left = middle(root.left);
				if(!left) {
					return false;
				}
			}
			if(null != prev && root.val <= prev.val) {
				return false;
			}
			prev = root;
			if(null != root.right) {
				boolean right = middle(root.right);
				if(!right) {
					return false;
				}
			}
		} 
		return true;
	}
	
	
	
}

//Definition for a binary tree node.
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
     TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 
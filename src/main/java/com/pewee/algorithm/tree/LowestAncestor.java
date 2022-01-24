package com.pewee.algorithm.tree;
/**
 * 给出2个点,求最低的公共祖先
 * @author pewee
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 思路:从根节点分别找2个节点,看最后一个重合的路径为最低公共祖先.会遍历2次路径,代码较复杂
 * 思路:recursion  若对某个节点:p和q都在一边子树上,那他一定不是最低公共祖先.我们可以通过一个节点递归找左右子树p或q,找到p或q就返回
 * 这样就知道对于一个节点,他的左,右找不找得到p或q,若某边子树返回null,那就只需要找另一边子树.
 * 若p,q在某个节点2边,那他就是最低公共祖先
 */
public class LowestAncestor {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root) {
        	return root;
        }
        TreeNode pOrqL = lowestCommonAncestor(root.left, p, q);
        TreeNode pOrqR = lowestCommonAncestor(root.right, p, q);
		if(null == pOrqL || null == pOrqR) {
			if(null == pOrqL) {
				return pOrqR;
			} else {
				return pOrqL;
			}
		} else {
			return root;
		}
    }
}




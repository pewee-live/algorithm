package com.pewee.algorithm.mergeFind;
/**
 * 岛屿数量
 * @author pewee
 * https://leetcode-cn.com/problems/number-of-islands/
 * 思路:染色法,从二位数组从头到尾遍历,遇到岛屿就加1,然后把这个岛屿上下左右递归变成0,最后的结果为岛屿数量
 * 思路2:并查集
 */
public class NumberOfIslands {
	
	public int numIslands(char[][] grid) {
		UnionFind unionFind = new UnionFind(grid);
		return unionFind.isolateCount;
	}
	
	
	
	class UnionFind{
		
		private int[] root;
		
		public int isolateCount;
		
		/**]
		 * 构造方法
		 * @param grid
		 */
		public UnionFind(char[][] grid) {
			root = new int[grid.length * grid[0].length];
			for (int i = 0; i < root.length;i++) {
				root[i] = -1;
			}
			
			for (int i = 0;i < grid.length;i++) {
				for (int j = 0;j < grid[0].length;j++) {
					if (grid[i][j] == '1') {
						//如果上下左右有,那就认周围上下左右为父,没有自己就是,向上下左右扩散
						if (root[i*grid.length + j] == -1) {
							findFather(grid, i, j);
						} else {
							//已经有了父亲,不处理
						}
					}
				}
			}
		}
		
		/**
		 * 找周围的父节点,周围没有的话自己做父节点
		 * @param grid
		 * @param i
		 * @param j
		 */
		public int findFather(char[][] grid,int i ,int j){
			//上
			if (i - 1 >= 0 && grid[i - 1][j] == '1' ) {
				if (root[(i -1)*grid.length + j] != -1) {
					root[i*grid.length + j] = root[(i -1)*grid.length + j];
				} else {
					root[i*grid.length + j] = findFather(grid, i - 1, j);
				}
				
			}
			//左
			else if (j - 1 >= 0 && grid[i][j - 1] == '1'  ) {
				if ( root[(i)*grid.length + j - 1] != -1) {
					root[i*grid.length + j] = root[(i)*grid.length + j - 1];
				} else {
					root[i*grid.length + j] = findFather(grid, i, j - 1);
				}
			}
			//右
			else if (j + 1 < grid[0].length && grid[i][j + 1] == '1'  ) {
				if (root[(i)*grid.length + j + 1] != -1) {
					root[i*grid.length + j] = root[(i)*grid.length + j + 1];
				} else {
					root[i*grid.length + j] = findFather(grid, i, j + 1);
				}
			}
			//下
			else if (i + 1 < grid.length && grid[i + 1][j] == '1' ) {
				if (root[(i + 1)*grid.length + j] != -1 ) {
					root[i*grid.length + j] = root[(i + 1)*grid.length + j];
				} else {
					root[i*grid.length + j] = findFather(grid, i + 1, j);
				}
			} else {
				//都没有,自己为root
				root[i*grid.length + j] = i*grid.length + j;
				isolateCount++;
			}
			return root[i*grid.length + j];
		}
		
		/**
		 * 路径压缩
		 * @param idx
		 * @return
		 */
		public int findRoot(int idx) {
			int root0 = idx;
			while ( root[root0] != idx) {
				root0 = root[idx];
			}	
			while (idx != root0) {
				int tmp = root[idx];
				root[tmp] = root0;
				idx = tmp;
			}
			return root0;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 染色
	 */
	public int numIslands0(char[][] grid) {
		int count = 0;
		for (int i = 0;i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] != '0') {
					count++;
					erase(grid,i,j);
				}
			}
		}
		return count;
    }
	
	/**
	 * 递归清除岛屿
	 * @param i
	 * @param j
	 */
	private void erase(char[][] grid,int i, int j) {
		grid[i][j] = '0';
		//上 
		if (i - 1 >= 0 && grid[i - 1][j] == '1') {
			erase(grid, i - 1, j);
		}
		
		//下
		if (i + 1 < grid.length && grid[i + 1][j] == '1') {
			erase(grid, i + 1, j);
		}
		//左 
		if (j - 1   >= 0 && grid[i][j - 1] == '1') {
			erase(grid, i, j - 1);
		}
		
		//右
		if (j + 1 < grid[0].length && grid[i][j+1] == '1') {
			erase(grid, i, j+1);
		}
		
	}
}

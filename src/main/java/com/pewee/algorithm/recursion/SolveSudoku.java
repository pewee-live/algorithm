package com.pewee.algorithm.recursion;
/**
 * 解数独
 * @author pewee
 * https://leetcode-cn.com/problems/sudoku-solver/
 * 思路:使用DFS,这次的DFSlevel有2维:列和行,我们在某一行的列(0 - 8) < 9 的时候 dfs(row,column + 1)
 * 若列大于等于9 则dfs(row + 1 ,0) ,在每个DFS里去枚举1-9,看他在行,列和9宫格内有无重复.
 * 注意在递归回溯完成后如果检测后续不能继续填写,需要在回溯中还原现场,把已经填的数字还原.
 * 此思路为暴力枚举
 * 剪枝思路:1从已经填了很多数字的9宫格开始填,这样需要的枚举会大大减少
 * 2.预扫描一遍数独,扫描出空的格子,并预处理出这些格子可填的数字,在dfs中不要每个格子枚举1-9了
 * 
 * 这里先实现朴素解法
 *
 */
public class SolveSudoku {
	
	public static void main(String[] args) {
		char[][] board = new char[][] {
			{'5','3','.','.','7','.','.','.','.'},
			{'6','.','.','1','9','5','.','.','.'},
			 {'.','9','8','.','.','.','.','6','.'},
			 {'8','.','.','.','6','.','.','.','3'},
			 {'4','.','.','8','.','3','.','.','1'},
			 {'7','.','.','.','2','.','.','.','6'},
			 {'.','6','.','.','.','.','2','8','.'},
			 {'.','.','.','4','1','9','.','.','5'},
			 {'.','.','.','.','8','.','.','7','9'}
		};
		
		
		new SolveSudoku().solveSudoku(board);
		
		for (char[] cs : board) {
			for (char cs2 : cs) {
				System.out.print(cs2);
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}
	
	public void solveSudoku(char[][] board) {
		if(null == board || board.length != 9 ) {
			return ;
		}
		doSolve0(board,0,0);
    }
	
	/**
	 * 递归回溯算法,使用DFS朴素枚举填入数字
	 * row,column填入1-9
	 * @param board
	 * @param row
	 * @param column
	 * @return
	 */
	@Deprecated
	private boolean doSolve(char[][] board,int row,int column) {
		/**
		 * 一行填满了,需要切换到下一行
		 */
		if(column  ==  9) {
			row = row + 1;
			column = 0;
		}
		
		for (int i = row; i < 9; i++) {
			for (int j = column; j < 9; j++) {
				//如果这一栏是.,那就需要填
				if (board[i][j] == '.') {
					for (char k = '1'; k <= '9'; k++) {
						if (isOk(board,i,j,k)) {
							board[i][j] = k;
							if(doSolve(board, i , j + 1)) {
								return true;
							} else {
								//本数字可放,但是后面无法填,方下一个
								board[i][j] = '.';
							}
						} 
					}
					//本次循环所有数字都放不了的话返回失败
					return false;
				} else {
					//有默认数字,那就换到下一个数字填
					return doSolve(board, i , j + 1);
				}
			}
		}
		return true;
	}
	
	/**
	 * 递归回溯算法,使用DFS朴素枚举填入数字
	 * 这里不再用双层循环,直接一个个格子填,填了后再往下递归,这种写法更好
	 * row,column填入1-9
	 * @param board
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean doSolve0(char[][] board,int row,int column) {
		/**
		 * 一行填满了,需要切换到下一行
		 */
		if (column  ==  9) {
			row = row + 1;
			column = 0;
		}
		
		if (row == 9) {
			return true;
		}
		//若为空,开始填
		if (board[row][column] == '.') {
			for (char k = '1'; k <= '9'; k++) {
				if (isOk(board,row,column,k)) {
					board[row][column] = k;
					if (doSolve0(board, row , column + 1)) {
						return true;
					} else {
						//本数字可放,但是后面无法填,方下一个
						board[row][column] = '.';
					}
				} 
			}
			//本次循环所有数字都放不了的话返回失败
			return false;
		} else {
			//有默认数字,那就换到下一个数字填
			return doSolve0(board, row , column + 1);
		}
	}


	private boolean isOk(char[][] board, int row0, int column0, char c) {
		//本行没得和他重复的
        for (int i = 0; i < 9;i++) {
        	if (board[row0][i] == c) {
        		return false;
        	}
        }
        //本列没得和他重复的
        for (int i = 0; i < 9;i++) {
        	if (board[i][column0] == c) {
        		return false;
        	}
        }
        //9宫格内没得重复的
        int tmpi = row0 /3 *3;
        int tmpj = column0 /3 * 3;
        for (int i = tmpi;i < tmpi + 3;i++) {
        	for (int j = tmpj;j < tmpj + 3;j++) {
        		if (board[i][j] == c) {
        			return false;
        		}
        	}
        }
        return true;
	}
}

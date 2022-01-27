package com.pewee.algorithm.recursion;
/**
 * 校验数独
 * @author pewee
 * https://leetcode-cn.com/problems/valid-sudoku/
 * 思路: 用三个二维数组记录某行,某列,某个9宫格之内的数字是否出现,用双层循环来遍历数独,遍历过程中有遇到过出现的数字返回false
 *
 */
public class ValidSudoku {
	
	
	public boolean isValidSudoku(char[][] board) {
		// 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放,第一维表示一个9宫格
        boolean[][] block = new boolean[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int blockIndex = i / 3 * 3  + j / 3;//一行有3个9宫格
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {//出现过
                        return false;
                    } else {//没出现就赋为出现了
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }
	
}

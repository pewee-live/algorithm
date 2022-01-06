package com.pewee.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 邻接表表示无向图
 * @author pewee
 *
 */
public class Graph { // 无向图
  private int v; // 顶点的个数
  private LinkedList<Integer> adj[]; // 邻接表

  public Graph(int v) {
    this.v = v;
    adj = new LinkedList[v];
    for (int i=0; i<v; ++i) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int s, int t) { // 无向图一条边存两次
    adj[s].add(t);
    adj[t].add(s);
  }
  

	public void bfs(int s, int t) {
	  if (s == t) return;
	  boolean[] visited = new boolean[v];//用来存储节点是否被访问过用来避免顶点被重复访问
	  visited[s]=true;
	  /**
	   * 用来存储已经被访问、但相连的顶点还没有被访问的顶点。
	   * 因为广度优先搜索是逐层访问的，也就是说，我们只有把第 k 层的顶点都访问完成之后，
	   * 才能访问第 k+1 层的顶点。当我们访问到第 k 层的顶点的时候，我们需要把第 k 层的顶点记录下来,
	   * 稍后才能通过第 k 层的顶点来找第 k+1 层的顶点
	   */
	  Queue<Integer> queue = new LinkedList<>();
	  queue.add(s);
	  int[] prev = new int[v];//用来临时存储当前路线中,节点的前一个节点
	  for (int i = 0; i < v; ++i) {
	    prev[i] = -1;
	  }
	  while (queue.size() != 0) {
	    int w = queue.poll();
	   for (int i = 0; i < adj[w].size(); ++i) {
	      int q = adj[w].get(i);
	      if (!visited[q]) {
	        prev[q] = w;
	        if (q == t) {
	          print(prev, s, t);
	          return;
	        }
	        visited[q] = true;
	        queue.add(q);
	      }
	    }
	  }
	}
	
	private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
	  if (prev[t] != -1 && t != s) {
	    print(prev, s, prev[t]);
	  }
	  System.out.print(t + " ");
	}
	
	

	boolean found = false; // 全局变量或者类成员变量
	
	public void dfs(int s, int t) {
	  found = false;
	  boolean[] visited = new boolean[v];
	  int[] prev = new int[v];
	  for (int i = 0; i < v; ++i) {
	    prev[i] = -1;
	  }
	  recurDfs(s, t, visited, prev);
	  print(prev, s, t);
	}
	
	private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
	  if (found == true) return;
	  visited[w] = true;
	  if (w == t) {
	    found = true;
	    return;
	  }
	  for (int i = 0; i < adj[w].size(); ++i) {
	    int q = adj[w].get(i);
	    if (!visited[q]) {
	      prev[q] = w;
	      recurDfs(q, t, visited, prev);
	    }
	  }
	}
}

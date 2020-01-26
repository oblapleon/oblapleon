package com.company;

import java.util.Scanner;

/**
 *
 * input
 * 6 1 5 9 10 11 12
 * 7
 * 15 5 1 3 5 7 9
 * 12 6 2 3 4 6 7 8
 * 7 5 3 4 5 6 7
 * 16 4 8 9 10 11
 * 5 1 12
 * 33 12 1 2 3 4 5 6 7 8 9 10 11 12
 * 9 3 1 12 13
 */
public class TreasuresIssue {

    static int answer = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        int[] needed = new int[K];
        boolean[] needed_visited = new boolean[K];
        for (int i = 0; i < K; i++) {
            needed[i] = scanner.nextInt();
        }
        int N = scanner.nextInt();

        boolean[] mines_vis = new boolean[N];
        int[] Pcost = new int[N];
        for (int i = 0; i < N; i++) {
            int P = scanner.nextInt();
            Pcost[i] = P;
            int M = scanner.nextInt();
            int[] available = new int[M];
            for (int j = 0; j < M; j++) {
                available[i] = scanner.nextInt();
            }
        }
    }

    public static void dfs(int[] needed, boolean[] n_visited, int[] available, boolean[] mines_vis, int N) {
        for (int i = 0; i < N; i++) {
            mines_vis[i] = true;
            for (int j = 0; j < needed.length; j++) {
                int element = needed[j];
                if (checkElementInArray(available, element) && !mines_vis[i]) {
                    n_visited[j] = true;
                }
            }
        }
    }


    public static boolean checkElementInArray(int[] array, int element) {
        for (int value : array) {
            if (value == element) {
                return true;
            }
        }
        return false;
    }
}

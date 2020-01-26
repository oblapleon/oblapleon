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
                available[j] = scanner.nextInt();
            }
            dfs(needed, needed_visited, available, mines_vis, Pcost, i, N);
        }
    }

    public static void dfs(int[] needed, boolean[] n_visited, int[] available, boolean[] mines_vis, int[] Pcost, int LEVEL, int N) {
        System.out.println("LEVEL:" + LEVEL);
        if (!allVisited(n_visited)) {
            if (LEVEL < N) {
                mines_vis[LEVEL] = true;
                int sum = 0;
                for (int i = 0; i < needed.length; i++) {
                    int element = needed[i];
                    if (!n_visited[i] && checkElementInArray(available, element)) {
                        n_visited[i] = true;
                        sum = Pcost[LEVEL];
                    }
                }
                answer += sum;
                System.out.println(answer);
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

    public static boolean allVisited(boolean[] arr) {
        for (boolean b : arr) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}

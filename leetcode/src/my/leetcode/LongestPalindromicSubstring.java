package my.leetcode;

public class LongestPalindromicSubstring {


    public static String longestPalindrome(String s) {
        char[] word = s.toCharArray();
        String www = "";
        int answ_len = 0;
        for (int i = 0; i < word.length; i++) {
            for (int j = i + 1; j <=word.length; j++) {
                int len = j - i;
                char[] c = new char[len];
                System.arraycopy(word, i, c, 0, len);
                if (checkPalindrome(c)) {
                    if (c.length > answ_len) {
                        answ_len = c.length;
                        www = String.valueOf(c);
                    }
                }
            }
        }
        return www;
    }

    public static boolean checkPalindrome(char[] c) {
        int i = 0;
        int j = c.length - 1;
        while (i < c.length / 2) {
            if (c[i] != c[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        longestPalindrome("");
    }
}

package org.example;

public class KMPTest {

//    KMP的基本思想：当匹配到不相等的字符串时，不是从头开始匹配，而是利用已经匹配的信息回退到某个合适的位置继续匹配
//    前缀表/next数组：next[i]表示子字符串[0, i]最长相等前后缀的长度(前后缀定义参代码随想录)
//    前缀表回退的合理性：当遇到不匹配的字符时，不匹配的位置正好在后缀的后面，那么从最长相等前缀后面的字符开始匹配即可不用再匹配前缀

//    从模式串pattern获取next数组过程
//    定义[0, j)为最长前缀，(i - j, i]为最长后缀，next[0]初始值为0
//    从1开始遍历模式串
//    当pattern[j] != pattern[i]时回退j，直到不能回退或者二者相等
//    当pattern[j] == pattern[i]时，j++。此时找到了以pattern[i]结尾的最长相等前后缀
//    记录next[i]

//    模式串pattern与文本串text匹配
//    定义[0, j)为最长前缀，(i - j, i]为最长后缀
//    从0开始遍历文本串
//    当text[i] != pattern[j]时回退j，直到不能回退或者二者相等
//    当text[i] == pattern[j]时，j++。
//    若j == pattern.length(), 返回i - j + 1作为第一个匹配的下标
//    若遍历结束，返回-1表示不存在匹配字符串

    public static void main(String[] args) {
        Solution solver = new Solution();

        // 测试样例1
        String text = "sadbutsad";
        String pattern = "sad";
        int idx = solver.strStr(text, pattern);
        System.out.println(text + ":\t" + pattern + ":\t" + idx);

        // 测试样例2
        text = "leetcode";
        pattern = "leeto";
        idx = solver.strStr(text, pattern);
        System.out.println(text + ":\t" + pattern + ":\t" + idx);
    }

}

class Solution {

    int[] getNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) j++;
            next[i] = j;
        }
        return next;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        int[] next = getNext(needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) j++;
            if (j == needle.length()) return i - j + 1;
        }
        return -1;
    }
}
package T2601;

/**
 * @Description: 二分查找
 * @Author: iniwym
 * @Date: 2026-01-31
 * @Link: https://leetcode.cn/problems/find-smallest-letter-greater-than-target/
 */
public class Code0744_FindSmallestLetterGreaterThanTarget {

    /**
     * 在一个有序字符数组中查找比目标字符大的最小字符。
     * 如果不存在这样的字符，则返回数组中的第一个字符。
     *
     * @param letters 有序字符数组，假设按升序排列且至少包含两个不同的字符
     * @param target  目标字符
     * @return 比目标字符大的最小字符；如果不存在，则返回数组的第一个字符
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int left = -1;
        int right = n;

        // 使用二分查找定位比目标字符大的最小字符的位置
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] > target) {
                right = mid; // 缩小右边界
            } else {
                left = mid;  // 扩大左边界
            }
        }

        // 返回结果：如果找到有效位置则返回对应字符，否则返回数组首元素
        return right < n ? letters[right] : letters[0];
    }

}

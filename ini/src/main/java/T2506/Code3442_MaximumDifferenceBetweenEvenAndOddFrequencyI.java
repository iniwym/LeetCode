package T2506;

/**
 * @Description: 奇偶性
 * @Author: iniwym
 * @Date: 2025-06-10
 * @Link: https://leetcode.cn/problems/maximum-difference-between-even-and-odd-frequency-i/
 */
public class Code3442_MaximumDifferenceBetweenEvenAndOddFrequencyI {

    /**
     * 计算字符串中字符出现次数的最大差值
     * 该方法旨在找出给定字符串中，出现次数为偶数的字符的最小出现次数，与出现次数为奇数的字符的最大出现次数，并计算二者的差值
     * 此方法有助于分析字符串中字符出现频率的分布情况
     *
     * @param s 输入的字符串，由小写字母组成
     * @return 返回出现次数为奇数的字符的最大出现次数与出现次数为偶数的字符的最小出现次数之间的差值
     */
    public int maxDifference(String s) {
        // 创建一个长度为26的数组，用于统计每个字母出现的次数
        int[] nums = new int[26];

        // 将字符串转换为字符数组，便于逐个处理字符
        char[] chars = s.toCharArray();

        // 遍历字符数组，统计每个字符出现的次数
        for (int i = 0; i < chars.length; i++) {
            nums[chars[i] - 'a']++;
        }

        // 初始化最大值为0，用于记录出现次数为奇数的字符的最大出现次数
        int max = 0;
        // 初始化最小值为一个较大的数，用于记录出现次数为偶数的字符的最小出现次数
        int min = 100;

        // 遍历统计数组，找出满足条件的最小值和最大值
        for (int i = 0; i < 26; i++) {
            // 如果字符出现次数不为0且为偶数，更新最小值
            if (nums[i] != 0 && nums[i] % 2 == 0) {
                min = Math.min(min, nums[i]);
            }
            // 如果字符出现次数不为0且为奇数，更新最大值
            if (nums[i] != 0 && nums[i] % 2 != 0) {
                max = Math.max(max, nums[i]);
            }
        }

        // 返回最大值与最小值的差值
        return max - min;
    }

}

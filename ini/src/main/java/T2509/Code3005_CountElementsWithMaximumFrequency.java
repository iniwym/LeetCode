package T2509;

/**
 * @Description: 数组遍历
 * @Author: iniwym
 * @Date: 2025-09-22
 * @Link: https://leetcode.cn/problems/count-elements-with-maximum-frequency/
 */
public class Code3005_CountElementsWithMaximumFrequency {

    /**
     * 计算数组中出现频率最高的元素的总频次
     *
     * @param nums 输入的整数数组，元素范围在1到100之间
     * @return 返回出现频率最高的所有元素的频次之和
     */
    public int maxFrequencyElements(int[] nums) {

        int[] frequency = new int[101];
        int max = 0;
        int ans = 0;

        // 第一次遍历：统计每个数字的出现频次，并记录最大频次
        for (int num : nums) {
            frequency[num]++;
            if (frequency[num] > max) {
                max = frequency[num];
            }
        }

        // 第二次遍历：累加所有达到最大频次的元素的频次
        for (int i = 1; i <= 100; i++) {
            if (frequency[i] == max) {
                ans += frequency[i];
            }
        }

        return ans;
    }

}

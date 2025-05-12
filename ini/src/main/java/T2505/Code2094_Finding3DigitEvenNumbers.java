package T2505;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2025-05-12
 * @Link: https://leetcode.cn/problems/finding-3-digit-even-numbers/
 */
public class Code2094_Finding3DigitEvenNumbers {

    /**
     * 该方法从给定的数字数组中找出所有可能的三位偶数
     * 它通过检查每个可能的三位数的每个数字是否都在原数组中出现过至少一次来实现
     *
     * @param digits 一个整数数组，包含0-9之间的数字
     * @return 一个整数数组，包含所有可能的三位偶数
     */
    public int[] findEvenNumbers(int[] digits) {
        // 边界检查
        if (digits == null || digits.length < 3) {
            return new int[0];
        }

        // 统计每个数字的出现次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int digit : digits) {
            countMap.put(digit, countMap.getOrDefault(digit, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();

        // 遍历所有三位偶数
        for (int num = 100; num <= 998; num += 2) {
            int ones = num % 10;
            int tens = (num / 10) % 10;
            int hundreds = num / 100;

            // 创建临时计数副本以避免修改原始 map
            Map<Integer, Integer> tempCount = new HashMap<>(countMap);

            boolean usedHundreds = false;
            boolean usedTens = false;
            boolean usedOnes = false;

            // 检查百位
            if (tempCount.getOrDefault(hundreds, 0) > 0) {
                tempCount.put(hundreds, tempCount.get(hundreds) - 1);
                usedHundreds = true;
            }

            // 检查十位
            if (usedHundreds && tempCount.getOrDefault(tens, 0) > 0) {
                tempCount.put(tens, tempCount.get(tens) - 1);
                usedTens = true;
            }

            // 检查个位
            if (usedHundreds && usedTens && tempCount.getOrDefault(ones, 0) > 0) {
                usedOnes = true;
            }

            if (usedHundreds && usedTens && usedOnes) {
                ans.add(num);
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}

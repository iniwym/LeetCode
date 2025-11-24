package T2511;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 二进制
 * @Author: iniwym
 * @Date: 2025-11-24
 * @Link: https://leetcode.cn/problems/binary-prefix-divisible-by-5/
 */
public class Code1018_BinaryPrefixDivisibleBy5 {

    /**
     * 判断二进制数组的前缀是否能被5整除
     *
     * @param nums 输入的二进制数组，每个元素为0或1
     * @return 返回布尔列表，每个元素表示对应位置的前缀组成的二进制数是否能被5整除
     */
    public List<Boolean> prefixesDivBy5(int[] nums) {

        int length = nums.length;

        List<Boolean> ans = new ArrayList<Boolean>(length);

        int temp = 0;

        // 遍历每一位二进制数字，计算当前前缀对应的数值对5取模的结果
        // 使用位运算和模运算优化，避免数值过大溢出
        for (int bit : nums) {
            temp = (temp << 1 | bit) % 5;
            ans.add(temp == 0);
        }

        return ans;
    }

}

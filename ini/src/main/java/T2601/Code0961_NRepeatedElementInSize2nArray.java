package T2601;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2026-01-02
 * @Link: https://leetcode.cn/problems/n-repeated-element-in-size-2n-array/
 */
public class Code0961_NRepeatedElementInSize2nArray {

    /**
     * 查找数组中重复的元素
     * 在一个长度为2n的数组中，有n个元素是相同的（重复n次），其他n个元素各不相同
     * 该方法用于找到那个重复n次的元素
     *
     * @param nums 输入的整数数组，长度为2n，其中n个元素相同，其余元素各不相同
     * @return 返回重复n次的那个元素
     */
    public int repeatedNTimes(int[] nums) {

        Set set = new HashSet<Integer>();

        // 遍历数组，使用HashSet检测重复元素
        for (int temp : nums) {
            if (set.contains(temp)) {
                return temp;
            } else {
                set.add(temp);
            }
        }

        return 0;
    }

}

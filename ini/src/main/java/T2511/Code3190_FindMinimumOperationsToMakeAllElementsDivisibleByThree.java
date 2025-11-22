package T2511;

/**
 * @Description: 整除取模
 * @Author: iniwym
 * @Date: 2025-11-22
 * @Link: https://leetcode.cn/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/
 */
public class Code3190_FindMinimumOperationsToMakeAllElementsDivisibleByThree {

    /**
     * 计算使数组中所有元素都能被3整除所需的最少操作次数
     * 对于每个不能被3整除的元素，需要进行一次操作
     *
     * @param nums 输入的整数数组
     * @return 返回需要进行操作的元素个数
     */
    public int minimumOperations(int[] nums) {

        int ans = 0;

        // 遍历数组，统计不能被3整除的元素个数
        for (int temp : nums) {
            if (!(temp % 3 == 0)) {
                ans++;
            }
        }

        return ans;
    }

}

package T2504;

/**
 * @Description: 暴力枚举
 * @Author: iniwym
 * @Date: 2025-04-14
 * @Link: https://leetcode.cn/problems/count-good-triplets/
 */
public class Code1534_CountGoodTriplets {

    /**
     * 计算数组中满足特定条件的三元组数量。
     * 三元组(i, j, k)需满足i < j < k，且满足以下条件：
     * 1. |arr[j] - arr[i]| <= a
     * 2. |arr[k] - arr[j]| <= b
     * 3. |arr[k] - arr[i]| <= c
     *
     * @param arr 输入的整数数组
     * @param a   i和j元素差值的阈值
     * @param b   j和k元素差值的阈值
     * @param c   i和k元素差值的阈值
     * @return 符合条件的三元组数量
     */
    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length;

        int ans = 0;
        // 遍历所有可能的第一个元素i
        for (int i = 0; i < n - 2; i++) {
            // 遍历所有可能的第二个元素j（必须大于i）
            for (int j = i + 1; j < n - 1; j++) {
                // 遍历所有可能的第三个元素k（必须大于j）
                for (int k = j + 1; k < n; k++) {
                    // 检查三元组是否满足所有条件
                    if (Math.abs(arr[j] - arr[i]) <= a
                            && Math.abs(arr[k] - arr[j]) <= b
                            && Math.abs(arr[k] - arr[i]) <= c) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 7, 3, 12, 1, 12, 2, 3};
        int a = 5, b = 8, c = 1;
        int result = countGoodTriplets(arr, a, b, c);
        System.out.println(result);
    }
}

package T2504;

import java.util.*;

/**
 * @Description: 回文、排列组合
 * @Author: iniwym
 * @Date: 2025-04-12
 * @Link: https://leetcode.cn/problems/find-the-count-of-good-integers/
 */
public class Code3272_FindTheCountOfGoodIntegers {

    /**
     * 计算满足以下条件的"好整数"数量：
     * 1. 是回文数
     * 2. 能被k整除
     * 3. 数位分布唯一（不同排列视为同一分布）
     * 4. 不以0开头
     *
     * @param n 整数的位数
     * @param k 回文数必须能被该数整除
     * @return 符合条件的"好整数"数量
     */
    public static long countGoodNumbers(int n, int k) {
        // 预计算0到10的阶乘，用于后续排列组合计算，使用long防止溢出
        long[] fact = new long[11];
        fact[0] = 1L;
        for (int i = 1; i <= 10; i++) {
            fact[i] = fact[i - 1] * i;
        }

        // 存储所有唯一的数位分布（每个元素是一个长度为10的列表，表示0-9的出现次数）
        Set<List<Integer>> distributions = new HashSet<>();

        // 计算回文数的前半部分长度，例如n=4时前半部分长度为2，n=5时为3
        int m = (n + 1) / 2;
        // 生成所有可能的前半部分数值范围，例如m=2时，范围是10到99
        int start = (int) Math.pow(10, m - 1);
        int end = (int) Math.pow(10, m);

        // 遍历所有可能的前半部分数值
        for (int firstHalf = start; firstHalf < end; firstHalf++) {
            // 将前半部分转换为字符串，用于构造回文数
            String firstHalfStr = Integer.toString(firstHalf);
            // 生成完整的回文数字符串
            String palindromeStr = constructPalindrome(firstHalfStr, n);

            // 转换为长整型，避免数值过大
            long x;
            try {
                x = Long.parseLong(palindromeStr);
            } catch (NumberFormatException e) {
                continue; // 如果转换失败（如长度超过n位），跳过
            }

            // 检查是否满足k回文条件（能被k整除）
            if (x % k != 0) {
                continue;
            }

            // 统计该回文数中每个数字的出现次数
            int[] counts = new int[10];
            for (char ch : palindromeStr.toCharArray()) {
                counts[ch - '0']++;
            }

            // 将计数转换为列表，方便存入集合去重
            List<Integer> dist = new ArrayList<>();
            for (int count : counts) {
                dist.add(count);
            }

            distributions.add(dist); // 加入集合，自动去重
        }

        long result = 0L;

        // 遍历每个唯一的数位分布，计算对应的好整数数量
        for (List<Integer> dist : distributions) {
            int[] counts = new int[10];
            for (int i = 0; i < 10; i++) {
                counts[i] = dist.get(i);
            }

            // 计算总排列数：n! / (各数字出现次数的阶乘乘积)
            long denominator = 1L;
            for (int count : counts) {
                denominator *= fact[count];
            }
            long total = fact[n] / denominator;

            // 计算无效排列数（以0开头的情况）
            long invalid = 0L;
            if (counts[0] > 0) { // 只有存在0时才可能有无效情况
                // 将0的数量减1，然后计算剩余数字的排列数
                long invalidDenominator = fact[counts[0] - 1];
                for (int i = 1; i < 10; i++) {
                    invalidDenominator *= fact[counts[i]];
                }
                invalid = fact[n - 1] / invalidDenominator;
            }

            // 累加有效排列数（总排列数 - 无效排列数）
            result += (total - invalid);
        }

        return result;
    }

    /**
     * 根据前半部分构造完整的回文数
     *
     * @param firstHalf 前半部分字符串
     * @param n         总位数
     * @return 完整的回文字符串
     */
    private static String constructPalindrome(String firstHalf, int n) {
        if (n % 2 == 0) {
            // 偶数位：前半部分 + 前半部分反转
            return firstHalf + new StringBuilder(firstHalf).reverse().toString();
        } else {
            // 奇数位：前半部分 + 前半部分去掉最后一位的反转
            String prefix = firstHalf.substring(0, firstHalf.length() - 1);
            return firstHalf + new StringBuilder(prefix).reverse().toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(countGoodNumbers(4, 2)); // 示例测试，输出应为符合条件的数量
    }

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/find-the-count-of-good-integers/solutions/3637602/tong-ji-hao-zheng-shu-de-shu-mu-by-leetc-m5l4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        /**
         * 计算满足以下条件的n位整数的数量：
         * 1. 是回文数
         * 2. 能被k整除
         * 3. 排序后字符串唯一（去除重复排列）
         *
         * @param n 回文数的位数
         * @param k 用于判断回文数是否能被其整除的数
         * @return 满足条件的不同整数数量
         */
        public long countGoodIntegers(int n, int k) {
            Set<String> dict = new HashSet<>();
            int base = (int) Math.pow(10, (n - 1) / 2);
            // 奇数为1，偶数为0
            int skip = n & 1;
            /* 枚举所有可能的n位回文数前半部分，生成完整回文数并筛选符合条件的 */
            for (int i = base; i < base * 10; i++) {
                String s = Integer.toString(i);
                s += new StringBuilder(s).reverse().substring(skip);
                long palindromicInteger = Long.parseLong(s);
                /* 筛选能被k整除的回文数，并记录其排序后的唯一表示 */
                if (palindromicInteger % k == 0) {
                    char[] chars = s.toCharArray();
                    Arrays.sort(chars);
                    dict.add(new String(chars));
                }
            }
            /* 预计算阶乘数组用于排列组合计算 */
            long[] factorial = new long[n + 1];
            factorial[0] = 1;
            for (int i = 1; i <= n; i++) {
                factorial[i] = factorial[i - 1] * i;
            }
            long ans = 0;
            /* 对每个唯一排序后的字符串计算其可能的排列数 */
            for (String s : dict) {
                int[] cnt = new int[10];
                for (char c : s.toCharArray()) {
                    cnt[c - '0']++;
                }
                /* 使用多重排列公式计算不同排列方式的数量 */
                long tot = (n - cnt[0]) * factorial[n - 1];
                for (int x : cnt) {
                    tot /= factorial[x];
                }
                ans += tot;
            }
            return ans;
        }
    }

}
package T2504;

/**
 * @Description: 组合数学
 * @Author: iniwym
 * @Date: 2025-04-10
 * @Link: https://leetcode.cn/problems/count-the-number-of-powerful-integers/
 */
public class Code2999_CountTheNumberOfPowerfulIntegers {

    /**
     * 计算在闭区间 [start, finish] 内满足特定条件的“强大整数”的数量。
     *
     * @param start  区间的起始值（包含）
     * @param finish 区间的结束值（包含）
     * @param limit  用于条件判断的限制参数
     * @param s      用于条件判断的字符串参数
     * @return 在指定区间内满足条件的强大整数的数量
     */
    public static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        String start_ = Long.toString(start - 1);
        String finish_ = Long.toString(finish);

        /* 计算从0到finish满足条件的数目 */
        long end = calculate(finish_, s, limit);
        System.out.println(end);

        /* 计算从0到start-1满足条件的数目 */
        long begin = calculate(start_, s, limit);
        System.out.println(begin);

        return end - begin;
    }

    /**
     * 计算满足特定条件的数字组合数量。
     *
     * @param x     输入的字符串，表示一个数字。
     * @param s     目标字符串，用于比较。
     * @param limit 每个数字位的最大允许值。
     * @return 满足条件的数字组合的数量。
     */
    private static long calculate(String x, String s, int limit) {
        // 如果x的长度小于s，直接返回0
        if (x.length() < s.length()) {
            return 0;
        }

        // 当x和s长度相等时，判断x是否不小于s
        if (x.length() == s.length()) {
            return x.compareTo(s) >= 0 ? 1 : 0;
        }

        // 计算x比s多出的前导位数，并提取x的后缀部分
        int preLen = x.length() - s.length();
        String suffix = x.substring(preLen);
        long count = 0;

        // 遍历前导位的每一位，计算可能的组合数
        for (int i = 0; i < preLen; i++) {
            int digit = x.charAt(i) - '0';
            if (limit < digit) {
                count += (long) Math.pow(limit + 1, preLen - i);
                return count;
            }
            count += (long) digit * (long) Math.pow(limit + 1, preLen - i - 1);
        }

        // 检查后缀是否满足条件并更新计数器
        if (suffix.compareTo(s) >= 0) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOfPowerfulInt(697662853, 11109609599885L, 6, "5"));
    }

}

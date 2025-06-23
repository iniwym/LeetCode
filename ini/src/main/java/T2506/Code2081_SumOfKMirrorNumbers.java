package T2506;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 枚举回文数
 * @Author: iniwym
 * @Date: 2025-06-23
 * @Link: https://leetcode.cn/problems/sum-of-k-mirror-numbers/
 */
public class Code2081_SumOfKMirrorNumbers {

    /**
     * 计算在给定基数k下，前n个镜像数的和
     * 镜像数是指在特定基数下，其数字序列前后对称的数
     * 例如，在基数10下，121是一个镜像数
     * 该方法通过生成不同长度的回文数，并检查它们是否在基数k下也是回文数，来找到镜像数
     *
     * @param k 基数
     * @param n 要找到的镜像数的数量
     * @return 前n个镜像数的和
     */
    public long kMirror(int k, int n) {
        // 初始化计数器，用于记录已找到的镜像数数量
        long count = 0;
        // 初始化总和，用于累加镜像数
        long sum = 0;
        // 初始化长度，用于生成回文数
        int len = 1;
        // 当找到的镜像数数量未达到n时，继续搜索
        while (count < n) {
            // 生成指定长度的所有回文数
            List<Long> palindromes = generatePalindromes(len);
            // 遍历生成的回文数，检查它们是否是镜像数
            for (long p : palindromes) {
                // 如果当前回文数在基数k下也是回文数，则计数并累加
                if (isMirrorInBaseK(p, k)) {
                    count++;
                    sum += p;
                    // 如果已找到n个镜像数，返回总和
                    if (count == n) {
                        return sum;
                    }
                }
            }
            // 增加长度，用于下一轮回文数的生成
            len++;
        }
        // 返回找到的镜像数总和
        return sum;
    }

    /**
     * 生成指定长度的回文数字列表
     * 回文数字是指正读和反读都相同的数字
     * 此方法通过数学方式生成回文数字，避免了复杂的字符串操作
     *
     * @param len 回文数字的长度
     * @return 包含所有指定长度回文数字的列表
     */
    private List<Long> generatePalindromes(int len) {
        List<Long> list = new ArrayList<>();
        // 计算回文数字的前半部分长度
        int halfLen = (len + 1) / 2;
        // 计算前半部分数字的最小值
        long start = (long) Math.pow(10, halfLen - 1);
        // 计算前半部分数字的最大值
        long end = (long) Math.pow(10, halfLen) - 1;
        // 遍历所有可能的前半部分数字
        for (long half = start; half <= end; half++) {
            String s = String.valueOf(half);
            String palindrome;
            // 根据回文数字长度的奇偶性生成完整的回文数字
            if (len % 2 == 0) {
                palindrome = s + new StringBuilder(s).reverse().toString();
            } else {
                String prefix = s.substring(0, s.length() - 1);
                palindrome = s + new StringBuilder(prefix).reverse().toString();
            }
            // 将生成的回文数字字符串转换为长整型并添加到列表中
            long num = Long.parseLong(palindrome);
            list.add(num);
        }
        return list;
    }

    /**
     * 检查一个数在k进制下是否是镜像数
     * 镜像数是指一个数在特定进制下的数字序列和其反向序列相同
     * 例如，121在十进制下是镜像数，因为它正读和反读都一样
     *
     * @param num 需要检查的数
     * @param k   进制数
     * @return 如果num在k进制下是镜像数，则返回true；否则返回false
     */
    private boolean isMirrorInBaseK(long num, int k) {
        // 特殊情况处理：num为0时，不考虑为镜像数
        if (num == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        long x = num;
        // 将num转换为k进制表示
        while (x > 0) {
            long r = x % k;
            x /= k;
            sb.append(r);
        }
        String s = sb.toString();
        int left = 0, right = s.length() - 1;
        // 双指针检查s是否为回文字符串
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public long kMirror1(int k, int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += ans[k - 2][i];
        }
        return sum;
    }

    private static final long[][] ans = {
            {1L, 3L, 5L, 7L, 9L, 33L, 99L, 313L, 585L, 717L, 7447L, 9009L, 15351L, 32223L, 39993L, 53235L, 53835L, 73737L, 585585L, 1758571L, 1934391L, 1979791L, 3129213L, 5071705L, 5259525L, 5841485L, 13500531L, 719848917L, 910373019L, 939474939L},
            {1L, 2L, 4L, 8L, 121L, 151L, 212L, 242L, 484L, 656L, 757L, 29092L, 48884L, 74647L, 75457L, 76267L, 92929L, 93739L, 848848L, 1521251L, 2985892L, 4022204L, 4219124L, 4251524L, 4287824L, 5737375L, 7875787L, 7949497L, 27711772L, 83155138L},
            {1L, 2L, 3L, 5L, 55L, 373L, 393L, 666L, 787L, 939L, 7997L, 53235L, 55255L, 55655L, 57675L, 506605L, 1801081L, 2215122L, 3826283L, 3866683L, 5051505L, 5226225L, 5259525L, 5297925L, 5614165L, 5679765L, 53822835L, 623010326L, 954656459L, 51717171715L},
            {1L, 2L, 3L, 4L, 6L, 88L, 252L, 282L, 626L, 676L, 1221L, 15751L, 18881L, 10088001L, 10400401L, 27711772L, 30322303L, 47633674L, 65977956L, 808656808L, 831333138L, 831868138L, 836131638L, 836181638L, 2512882152L, 2596886952L, 2893553982L, 6761551676L, 12114741121L, 12185058121L},
            {1L, 2L, 3L, 4L, 5L, 7L, 55L, 111L, 141L, 191L, 343L, 434L, 777L, 868L, 1441L, 7667L, 7777L, 22022L, 39893L, 74647L, 168861L, 808808L, 909909L, 1867681L, 3097903L, 4232324L, 4265624L, 4298924L, 4516154L, 4565654L},
            {1L, 2L, 3L, 4L, 5L, 6L, 8L, 121L, 171L, 242L, 292L, 16561L, 65656L, 2137312L, 4602064L, 6597956L, 6958596L, 9470749L, 61255216L, 230474032L, 466828664L, 485494584L, 638828836L, 657494756L, 858474858L, 25699499652L, 40130703104L, 45862226854L, 61454945416L, 64454545446L},
            {1L, 2L, 3L, 4L, 5L, 6L, 7L, 9L, 121L, 292L, 333L, 373L, 414L, 585L, 3663L, 8778L, 13131L, 13331L, 26462L, 26662L, 30103L, 30303L, 207702L, 628826L, 660066L, 1496941L, 1935391L, 1970791L, 4198914L, 55366355L},
            {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 191L, 282L, 373L, 464L, 555L, 646L, 656L, 6886L, 25752L, 27472L, 42324L, 50605L, 626626L, 1540451L, 1713171L, 1721271L, 1828281L, 1877781L, 1885881L, 2401042L, 2434342L, 2442442L}
    };

}

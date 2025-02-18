package T2502;

/**
 * @Description: 运算
 * @Author: iniwym
 * @Date: 2025-02-13
 * @Link: https://leetcode.cn/problems/maximum-number-of-balls-in-a-box/
 */
public class Code1742_MaximumNumberOfBallsInABox {

    /**
     * 计算在给定的数字范围内，将每个数字的各个位上的数字相加得到的和，最多出现的和的次数
     *
     * @param lowLimit  数字范围的下限
     * @param highLimit 数字范围的上限
     * @return 最多出现的和的次数
     */
    public static int countBalls(int lowLimit, int highLimit) {
        // 计算 highLimit 的位数
        int digitCount = (int) Math.log10(highLimit) + 1;
        // 计算最大可能的数字和
        int maxSum = digitCount * 9;
        // 创建一个数组来记录每个盒子中的球数
        int[] boxes = new int[maxSum + 1];

        // 计算 lowLimit 的各位数字之和
        int sum = sumOfDigits(lowLimit);
        boxes[sum]++;

        // 遍历从 lowLimit + 1 到 highLimit 的所有数字，计算每个数字的各位数字之和，并记录到相应的盒子里
        for (int num = lowLimit + 1; num <= highLimit; num++) {
            int lastDigit = num - 1;
            // 当我们从一个数字 num 过渡到下一个数字 num + 1 时，大多数情况下各位数字之和会增加 1。
            // 但存在特殊情况，当数字的某一位是 9 时，加 1 会导致进位，
            while (lastDigit % 10 == 9) {
                sum -= 9;
                lastDigit /= 10;
            }
            // 正常情况数字和加 1
            sum++;

            boxes[sum]++;
        }

        // 找出哪个盒子中的球数最多
        int ans = 0;
        for (int count : boxes) {
            ans = Math.max(ans, count);
        }

        // 返回最多球数的盒子中的球数
        return ans;
    }

    public static int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(countBalls(123, 145));
    }

}

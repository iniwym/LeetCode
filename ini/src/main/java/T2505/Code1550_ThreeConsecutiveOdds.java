package T2505;

/**
 * @Description: 数组
 * @Author: iniwym
 * @Date: 2025-05-11
 * @Link: https://leetcode.cn/problems/three-consecutive-odds/
 */
public class Code1550_ThreeConsecutiveOdds {

    /**
     * 检查数组中是否存在三个连续的奇数
     *
     * @param arr 整数数组，用于检查的输入
     * @return 如果存在三个连续的奇数，则返回true；否则返回false
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        // 初始化计数器，用于记录连续奇数的数量
        int count = 0;

        // 遍历数组中的每个元素
        for (int i = 0; i < arr.length; i++) {
            // 如果当前元素是偶数，重置计数器
            if (arr[i] % 2 == 0) {
                count = 0;
            } else {
                // 如果当前元素是奇数，增加计数器
                count++;
                // 如果连续奇数的数量达到3，返回true
                if (count == 3) {
                    return true;
                }
            }
        }
        // 如果遍历完数组后没有找到三个连续的奇数，返回false
        return false;
    }

}

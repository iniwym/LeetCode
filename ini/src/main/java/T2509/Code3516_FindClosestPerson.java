package T2509;

/**
 * @Description: 绝对值
 * @Author: iniwym
 * @Date: 2025-09-04
 * @Link: https://leetcode.cn/problems/find-closest-person/
 */
public class Code3516_FindClosestPerson {

    /**
     * 查找距离目标值最近的数字
     *
     * @param x 第一个比较数字
     * @param y 第二个比较数字
     * @param z 目标值
     * @return 当x和y到z的距离相等时返回0，当x更近时返回1，当y更近时返回2
     */
    public int findClosest(int x, int y, int z) {
        // 计算x和y到目标值z的距离
        int a = Math.abs(x - z);
        int b = Math.abs(y - z);

        // 比较距离并返回结果
        return a == b ? 0 : (a < b ? 1 : 2);

    }


}

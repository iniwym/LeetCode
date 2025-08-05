package T2508;

/**
 * @Description: 暴力
 * @Author: iniwym
 * @Date: 2025-08-05
 * @Link: https://leetcode.cn/problems/fruits-into-baskets-ii/
 */
public class Code3477_FruitsIntoBasketsIi {

    /**
     * 计算无法放入篮子中的水果数量
     *
     * @param fruits  水果数组，每个元素表示水果的大小
     * @param baskets 篮子数组，每个元素表示篮子的容量
     * @return 返回无法放入任何篮子的水果数量
     */
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {

        int ans = fruits.length;
        // 遍历每个水果，尝试将其放入合适的篮子中
        for (int i = 0; i < fruits.length; i++) {
            for (int j = 0; j < baskets.length; j++) {
                if (fruits[i] <= baskets[j]) {
                    baskets[j] = 0;
                    ans--;
                    break;
                }
            }
        }
        return ans;
    }

}

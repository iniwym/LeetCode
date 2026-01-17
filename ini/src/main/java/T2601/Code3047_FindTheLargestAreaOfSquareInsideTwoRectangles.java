package T2601;

/**
 * @Description: 枚举
 * @Author: iniwym
 * @Date: 2026-01-17
 * @Link: https://leetcode.cn/problems/find-the-largest-area-of-square-inside-two-rectangles/
 */
public class Code3047_FindTheLargestAreaOfSquareInsideTwoRectangles {

    /**
     * 计算两个矩形相交区域中能形成的最大正方形面积
     *
     * @param bottomLeft 二维数组，每个元素表示一个矩形的左下角坐标 [x, y]
     * @param topRight   二维数组，每个元素表示一个矩形的右上角坐标 [x, y]
     * @return 返回最大正方形的面积（以长整型返回）
     */
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int maxSide = 0;

        // 遍历所有矩形对，计算它们相交区域能形成的正方形最大边长
        for (int i = 0; i < bottomLeft.length; i++) {
            int[] b1 = bottomLeft[i];
            int[] t1 = topRight[i];
            for (int j = 0; j < i; j++) {
                int[] b2 = bottomLeft[j];
                int[] t2 = topRight[j];

                // 计算两个矩形相交区域的宽度和高度
                int width = Math.min(t1[0], t2[0]) - Math.max(b1[0], b2[0]);
                int height = Math.min(t1[1], t2[1]) - Math.max(b1[1], b2[1]);

                // 取宽度和高度的最小值作为正方形的边长
                int side = Math.min(width, height);
                maxSide = Math.max(maxSide, side);
            }
        }

        return (long) maxSide * maxSide;
    }


}

package T2508;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 杨辉三角
 * @Author: iniwym
 * @Date: 2025-08-01
 * @Link: https://leetcode.cn/problems/pascals-triangle/
 */
public class Code0118_PascalsTriangle {

    /**
     * 生成杨辉三角的前numRows行
     *
     * @param numRows 需要生成的行数，必须为正整数
     * @return 包含杨辉三角前numRows行的二维列表，每行包含该行的所有数字
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> c = new ArrayList<>(numRows); // 预分配空间
        c.add(new ArrayList<>(1));
        // 从第二行开始逐行生成杨辉三角
        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(i + 1); // 预分配空间
            row.add(1);
            // 计算当前行中间的数字：每个数字等于左上方和正上方两个数字之和
            for (int j = 1; j < i; j++) {
                row.add(c.get(i - 1).get(j - 1) + c.get(i - 1).get(j));
            }
            row.add(1);
            c.add(row);
        }
        return c;
    }

}

package T2509;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 哈希表
 * @Author: iniwym
 * @Date: 2025-09-19
 * @Link: https://leetcode.cn/problems/design-spreadsheet/
 */
public class Code3484_DesignSpreadsheet {

    /**
     * 电子表格类，用于存储和计算单元格数据
     */
    class Spreadsheet {
        private final Map<String, Integer> data = new HashMap<>();

        /**
         * 构造函数，创建一个指定行数的电子表格
         *
         * @param rows 电子表格的行数
         */
        public Spreadsheet(int rows) {
        }

        /**
         * 设置指定单元格的值
         *
         * @param cell  单元格名称
         * @param value 要设置的值
         */
        public void setCell(String cell, int value) {
            data.put(cell, value);
        }

        /**
         * 重置（删除）指定单元格的值
         *
         * @param cell 要重置的单元格名称
         */
        public void resetCell(String cell) {
            data.remove(cell);
        }

        /**
         * 根据公式计算并返回结果值
         * 公式格式：以等号开头，后跟加号分隔的单元格引用或数值
         * 例如：=A1+B2+5
         *
         * @param formula 计算公式字符串
         * @return 计算结果
         */
        public int getValue(String formula) {
            int ans = 0;
            // 解析公式中的各个项并累加计算
            for (String cell : formula.substring(1).split("\\+")) {
                if (Character.isUpperCase(cell.charAt(0))) {
                    // 如果是单元格引用，则从数据中获取值
                    ans += data.getOrDefault(cell, 0);
                } else {
                    // 如果是数值，则直接转换为整数
                    ans += Integer.parseInt(cell);
                }
            }
            return ans;
        }
    }

}

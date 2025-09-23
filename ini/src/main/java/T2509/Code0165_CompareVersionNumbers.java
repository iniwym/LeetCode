package T2509;

/**
 * @Description: 字符串分割
 * @Author: iniwym
 * @Date: 2025-09-23
 * @Link: https://leetcode.cn/problems/compare-version-numbers/
 */
public class Code0165_CompareVersionNumbers {

    /**
     * 比较两个版本号字符串的大小
     *
     * @param version1 第一个版本号字符串，格式如"1.2.3"
     * @param version2 第二个版本号字符串，格式如"1.2.3"
     * @return 如果version1小于version2返回-1，如果version1大于version2返回1，如果相等返回0
     */
    public int compareVersion(String version1, String version2) {
        // 将版本号按点号分割成数组
        String[] a = version1.split("\\.");
        String[] b = version2.split("\\.");
        int n = a.length;
        int m = b.length;

        // 逐个比较版本号的每一位数字
        for (int i = 0; i < n || i < m; i++) {
            // 如果某个版本号位数不够，则该位视为0
            int ver1 = i < n ? Integer.parseInt(a[i]) : 0;
            int ver2 = i < m ? Integer.parseInt(b[i]) : 0;
            // 如果当前位不相等，直接返回比较结果
            if (ver1 != ver2) {
                return ver1 < ver2 ? -1 : 1;
            }
        }
        // 所有位都相等，返回0
        return 0;
    }

}

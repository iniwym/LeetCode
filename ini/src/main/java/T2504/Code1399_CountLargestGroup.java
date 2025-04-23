package T2504;

/**
 * @Description: 枚举计数
 * @Author: iniwym
 * @Date: 2025-04-23
 * @Link: https://leetcode.cn/problems/count-largest-group/
 */
public class Code1399_CountLargestGroup {

    /** 
     * 计算在1到n之间的数字中，根据各位数字之和分组后，具有最大组大小的组的数量。
     * 
     * @param n 输入的上限数字，必须大于等于1。
     * @return 返回具有最大组大小的组的数量。
     */
    public int countLargestGroup(int n) {
        int[] cnt = new int[37]; // 数组长度37足够容纳最大可能的数字位和（如9999的和为36）
        
        // 遍历每个数字，计算其各位数字之和，并统计每个和的出现次数
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            int x = i;
            while (x > 0) {
                sum += x % 10;
                x /= 10;
            }
            cnt[sum]++;
        }
    
        // 找到出现次数最多的组的大小
        int max = 0;
        for (int i = 0; i < cnt.length; i++) {
            max = Math.max(max, cnt[i]);
        }
    
        // 统计有多少个组的大小等于最大值
        int ans = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == max) {
                ans++;
            }
        }
        return ans;
    }

}

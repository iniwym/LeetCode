package T2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 数组排序
 * @Author: iniwym
 * @Date: 2026-01-26
 * @Link: https://leetcode.cn/problems/minimum-absolute-difference/
 */
public class Code1200_MinimumAbsoluteDifference {

    /**
     * 找到数组中所有具有最小绝对差的数对
     *
     * @param arr 输入的整数数组
     * @return 包含所有具有最小绝对差的数对的列表，每个数对以升序排列
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // 对数组进行排序，使得相邻元素的差值最小
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int dif = arr[i + 1] - arr[i];
            if (dif < min) {
                min = dif;
                ans.clear();
                ans.add(Arrays.asList(arr[i], arr[i + 1]));
            } else if (dif == min) {
                ans.add(Arrays.asList(arr[i], arr[i + 1]));
            }

        }

        return ans;

    }

}

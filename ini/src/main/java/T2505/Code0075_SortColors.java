package T2505;

/**
 * @Description: 排序
 * @Author: iniwym
 * @Date: 2025-05-17
 * @Link: https://leetcode.cn/problems/sort-colors/
 */
public class Code0075_SortColors {

    /**
     * 对颜色数组进行排序
     * 该方法将数组中的0移动到数组的开头，将2移动到数组的末尾，同时保持1在中间
     * 使用双指针技术，l和r分别代表左侧和右侧的边界
     *
     * @param nums 颜色数组，其中0代表红色，1代表白色，2代表蓝色
     */
    public void sortColors(int[] nums) {

        // 数组长度
        int n = nums.length;
        // 左边界指针，初始化为-1，表示0的右边界
        int l = -1;
        // 右边界指针，初始化为数组末尾，表示2的左边界
        int r = n - 1;
        // 当前考察的索引
        int index = 0;

        // 遍历数组，直到index与右边界r相遇
        while (index <= r) {
            // 当前元素为0时，将其与l+1位置的元素交换，并同时移动l和index指针
            if (nums[index] == 0) {
                int temp = nums[l + 1];
                nums[l + 1] = nums[index];
                nums[index] = temp;
                l++;
                index++;
            }
            // 当前元素为2时，将其与r位置的元素交换，并移动r指针
            else if (nums[index] == 2) {
                int temp = nums[r];
                nums[r] = nums[index];
                nums[index] = temp;
                r--;
            }
            // 当前元素为1时，不需要交换，直接移动index指针
            else {
                index++;
            }
        }
    }

}

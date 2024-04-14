package org.example.arrays;

public class BinarySearch {

    // 从单调递增数组中查找取值为val的元素下标
    public static int binarySearch(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            // 循环不变量
            // l - 1为严格小于val的元素
            // r为严格大于val的元素
            int mid = (l + r) >> 1;
            if (nums[mid] == val) {
                return mid;
            } else if (nums[mid] < val) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return -1;
    }

    // 从单调递增数组中查找大于等于val的最小值下标，如果不存在返回-1；
    public static int lowerBound(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            // 循环不变量
            // l - 1为严格小于val的元素
            // r为大于或等于val的元素
            int mid = (l + r) >> 1;
            if (nums[mid] < val) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        //依据循环不变量r是大于或等于val的最小值
        return r != nums.length? r: -1;
    }

    // 从单调递增数组中查找小于或等于val的最大值小标，如果不存在返回-1;
    public static int upperBound(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            // 循环不变量
            // l - 1为小于或等于val的元素
            // r为严格大于val的元素
            int mid = (l + r) >> 1;
            if (nums[mid] <= val) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // 一句循环不变量l - 1是小于或等于val的最大值
        return l - 1;
    }

    public static int max(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int val: nums) {
            ans = Math.max(ans, val);
        }
        return ans;
    }

    public static int min(int[] nums) {
        int ans = Integer.MAX_VALUE;
        for (int val: nums) {
            ans = Math.min(ans, val);
        }
        return ans;
    }
}

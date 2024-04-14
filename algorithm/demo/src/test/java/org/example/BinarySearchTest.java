package org.example;

import org.example.arrays.ArraysQuickSort;
import org.example.arrays.BinarySearch;

import java.util.Arrays;

public class BinarySearchTest {
    public static final int n = 32;

    public static void main(String[] args) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (int) (1000 * Math.random());
        }
        Arrays.sort(nums);
        int minVal = nums[0];
        int maxVal = nums[n - 1];

        ArraysQuickSort.println(nums);

        for (int i = 0; i < 5; i += 2) {
            System.out.println("查找取值为" + nums[i] + "的元素下标：" + BinarySearch.binarySearch(nums, nums[i]));
        }

        System.out.println("查找不存在的元素" + (maxVal + 1) + "的下标：" + BinarySearch.binarySearch(nums, maxVal + 1));

        for (int i = 0; i < 5; i++) {
            int val = (int)(minVal + (maxVal - minVal) * Math.random());
            int idx = BinarySearch.lowerBound(nums, val);
            System.out.println("小于或等于" + val + "的最大值：" + nums[idx] + "对应的下标: " + idx);
            idx = BinarySearch.upperBound(nums, val);
            System.out.println("大于或等于" + val + "的最小值：" + nums[idx] + "对应的下标: " + idx);
        }
        System.out.println("大于或等于maxVal+1的最小值下标：" + BinarySearch.lowerBound(nums, maxVal + 1));
        System.out.println("小于或等于minVal-1的最大值下标：" + BinarySearch.upperBound(nums, minVal - 1));
    }
}

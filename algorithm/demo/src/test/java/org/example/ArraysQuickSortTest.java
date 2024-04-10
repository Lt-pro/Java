package org.example;

import org.example.arrays.ArraysQuickSort;

public class ArraysQuickSortTest {
    public static final int n = 32;

    public static void main(String[] args) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (int)(1000 * Math.random());
        }

        // 原始数组
        ArraysQuickSort.println(nums);

        // 左右指针快排
        int[] nums1 = ArraysQuickSort.arrayClone(nums);
        ArraysQuickSort.quickSort1(nums1, 0, n - 1);
        ArraysQuickSort.println(nums1);

        // 左右指针快排改进
        int[] nums2 = ArraysQuickSort.arrayClone(nums);
        ArraysQuickSort.quickSort2(nums2, 0, n - 1);
        ArraysQuickSort.println(nums2);

        // 前后指针
        int[] nums3 = ArraysQuickSort.arrayClone(nums);
        ArraysQuickSort.quickSort3(nums3, 0, n - 1);
        ArraysQuickSort.println(nums3);

        // 三向切分快排
        int[] nums4 = ArraysQuickSort.arrayClone(nums);
        ArraysQuickSort.quickSort4(nums4, 0, n - 1);
        ArraysQuickSort.println(nums4);

        // 快选
        int[] nums5 = ArraysQuickSort.arrayClone(nums);
        for (int i = 0; i < 5; i++) {
            int val = ArraysQuickSort.quickSelect(nums5, i);
            System.out.println(val);
        }
    }
}

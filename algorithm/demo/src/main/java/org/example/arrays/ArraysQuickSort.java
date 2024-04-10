package org.example.arrays;

public class ArraysQuickSort {
    static void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 左右指针
    // 左边是小于或等于pivot的元素，右边是严格大于pivot的元素
    // 数组非空时返回pivot的下标，否则为i
    static int partition1(int[] nums, int i, int j) {
        int start = i;
        int pivot = nums[i];
        while (i < j) {
            while (i < j && nums[j] > pivot) j--;
            while (i < j && nums[i] <= pivot) i++;
            swap(nums, i, j);
        }
        swap(nums, start, i);   // 归位是将pivot放在下标i的位置上。
        return i;
    }

    public static void quickSort1(int[] nums, int i, int j) {
        if (i >= j) return;     //终止条件
        int k = partition1(nums, i, j);
        quickSort1(nums, i , k - 1);
        quickSort1(nums, k + 1, j);
    }

    // 左右指针改进
    // 连续交换元素
    static int partition2(int[] nums, int i, int j) {
        int pivot = nums[i];
        while (i < j) {
            while (i < j && nums[j] > pivot) j--;
            nums[i] = nums[j];
            while (i < j && nums[i] <= pivot) i++;
            nums[j] = nums[i];
        }
        // 连续交换会导致一个元素在数组中出现两次，而pivot不在数组中，需要将pivot放到下标为i的位置上。
        nums[i] = pivot;
        return i;
    }

    public static void quickSort2(int[] nums, int i, int j) {
        if (i >= j) return;     //终止条件
        int k = partition2(nums, i, j);
        quickSort2(nums, i , k - 1);
        quickSort2(nums, k + 1, j);
    }

    // 前后指针
    static int partition3(int[] nums, int i, int j) {
        int pivot = nums[j];
        int l = i;
        // [i, l)为小于等于pivot的元素，[l, r)为大于pivot的元素，nums[r]为待访问元素。
        for (int r = i; r <= j; r++) {
            if (nums[r] <= pivot) swap(nums, l++, r);
        }
        // 选择最后一个元素作为pivot, [0, l)区间最后一个元素恰为pivot，无需归位。
        return l - 1;
    }

    public static void quickSort3(int[] nums, int i, int j) {
        if (i >= j) return;     //终止条件
        int k = partition3(nums, i, j);
        quickSort3(nums, i , k - 1);
        quickSort3(nums, k + 1, j);
    }


    // 三向切分快排
    // 适用于重复元素较多的情形
    public static void quickSort4(int[] nums, int i, int j) {
        if (i >= j) return;     //终止条件
        int pivot = nums[i];
        // [i, lt)严格小于pivot的元素，[lt, s)等于pivot的元素，(gt, j]严格大于pivot的元素
        // [s, gt]为待访问的元素
        int lt = i, s = i + 1, gt = j;
        while (s <= gt) {
            if (nums[s] < pivot) {
                swap(nums, lt, s);
                lt++;s++;
            } else if (nums[s] > pivot) {
                swap(nums, s, gt--);
            } else {
                s++;
            }
        }
        quickSort4(nums, i, lt - 1);
        quickSort4(nums, gt + 1, j);
    }

    // 快选
    // 从数组中选取第k小的元素, 最小的下标为0
    // T(n) = O(2n)
    public static int quickSelect(int[] nums, int k) throws ArrayIndexOutOfBoundsException{
        if (k < 0 || k >= nums.length) throw new ArrayIndexOutOfBoundsException();
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int idx = partition3(nums, i, j);
            if (idx == k) {
                return nums[idx];
            } else if (idx > k) {
                j = idx - 1;
            } else {
                i = idx + 1;
            }
        }
        return -1;
    }

    // 打印数组
    public static void println(int[] nums) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int val: nums) {
            sb.append(val);
            sb.append(" ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    // 拷贝数组
    public static int[] arrayClone(int[] nums) {
        int[] copy = new int[nums.length];
        System.arraycopy(nums, 0, copy, 0, nums.length);
        return copy;
    }
}

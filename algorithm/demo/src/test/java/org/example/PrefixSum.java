package org.example;

import java.util.Arrays;

public class PrefixSum {
    public static final int N = 8;

    public static void main(String[] args) {
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            //nums[i] = (int) (1000 * Math.random());
            nums[i] = i + 1;
        }

        System.out.println("Initial array: " + Arrays.toString(nums));

        // Test TreeArray (Binary Indexed Tree)
        TreeArray treeArray = new TreeArray(nums);
        System.out.println("TreeArray initial sum (0, N-1): " + treeArray.query(1, N));
        treeArray.update(3, 500); // Update index 2 to 500
        System.out.println("TreeArray sum after update (0, N-1): " + treeArray.query(1, N));
        System.out.println("TreeArray sum (2, 5): " + treeArray.query(2, 5));

        // Test SegmentTree
        SegmentTree segmentTree = new SegmentTree(nums);
        System.out.println("SegmentTree initial sum (0, N-1): " + segmentTree.query(0, N-1));
        segmentTree.update(2, 500); // Update index 2 to 500
        System.out.println("SegmentTree sum after update (0, N-1): " + segmentTree.query(0, N-1));
        System.out.println("SegmentTree sum (2, 5): " + segmentTree.query(2, 5));
    }
}


class TreeArray {
    private int[] tree;
    private int[] nums;
    private int n;

    public TreeArray(int[] nums) {
        this.tree = new int[nums.length + 1];
        this.nums = nums.clone();
        this.n = nums.length;
        this.build();
    }

    private void build() {
        for (int i = 0; i < n; i++) {
            int idx = i + 1, val = nums[i];
            while (idx <= n) {
                tree[idx] += val;
                idx += lowbit(idx);
            }
        }
    }

    // 计算区间[L, R]的数组和
    public int query(int L, int R) {
        return query(R) - query(L - 1);
    }

    // 计算区间(0, idx]的数组和
    public int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= lowbit(idx);
        }
        return sum;
    }

    // 逻辑下标idx的元素增加delta
    public void update(int idx, int val) {
        int delta = val - nums[idx - 1];
        nums[idx - 1] = val;
        while (idx <= n) {
            tree[idx] += delta;
            idx += lowbit(idx);
        }
    }

    // 获取最低位的一
    private int lowbit(int x) {
        return x & -x;
    }
}


class SegmentTree {
    private int[] tree;
    private int[] nums;
    private int n;

    public SegmentTree(int[] nums) {
        this.n = nums.length;
        this.tree = new int[n * 4];
        this.nums = nums.clone();
        this.build(0, 0, n - 1);
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = nums[l];
            return;
        }
        int mid = (l + r) >> 1;
        int left = node * 2 + 1;
        int right = node * 2 + 2;
        build(left, l, mid);
        build(right, mid + 1, r);
        // 分治求解
        tree[node] = tree[left] + tree[right];
    }

    public int query(int L, int R) {
        return query(0, 0, n - 1, L, R);
    }

    public int query(int node, int l, int r, int L, int R) {
        if (L <= l && r <= R) return tree[node];
        int mid = (l + r) >> 1;
        int left = node * 2 + 1;
        int right = node * 2 + 2;
        if (mid >= R) return query(left, l, mid, L, R);
        if (mid < L) return query(right, mid + 1, r, L, R);
        return query(left, l, mid, L, R) + query(right, mid + 1, r, L, R);
    }

    // 将物理下标idx处元素更新为val
    public void update(int idx, int val) {
        nums[idx] = val;
        update(0, 0, n - 1, idx, val);
    }

    // 遗留问题：将一条线段上的元素全部更改为指定值?
    public void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            tree[node] = val;
            return;
        }
        int mid = (l + r) >> 1;
        int left = node * 2 + 1;
        int right = node * 2 + 2;
        if (idx <= mid) {
            update(left, l, mid, idx, val);
        } else {
            update(right, mid + 1, r, idx, val);
        }
        tree[node] = tree[left] + tree[right];
    }
}
package com.example.algorithm.binary_search;

/**
 * @author W
 * @date 2022-07-12
 */
public class BinarySearch {

    /**
     * 递归实现二分查找
     */
    public static int binarySearch(int[] a, int key, int fromIndex, int toIndex) {
        //直接找不到，返回-1
        if (key < a[fromIndex] || key > a[toIndex] || fromIndex > toIndex) {
            return -1;
        }
        //计算中间位置
        int mid = (fromIndex + toIndex) / 2;
        //判断中间位置元素与key关系，更改搜索范围
        if (a[mid] < key) {
            return binarySearch(a, key, mid + 1, toIndex);
        } else if (a[mid] > key) {
            return binarySearch(a, key, fromIndex, mid - 1);
        } else {
            return mid;
        }
    }

    /**
     * 双指针
     *
     * @param a
     * @param key
     * @return
     */
    public static int binarySearch(int[] a, int key) {
        //定义初始查找范围
        int low = 0;
        int high = a.length - 1;

        if (key < a[low] || key > a[high]) {
            return -1;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < key) {
                low = mid + 1;
            } else if (a[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

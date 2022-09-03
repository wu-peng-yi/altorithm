package com.example.algorithm.top100;

/**
 * 寻找两个正序数组的中位数
 *
 * @author wupengyi
 */
public class Four {
    public static void main(String[] args) {

    }

    /**
     * 如果个数是偶数， 中位数是 (m + n) / 2 与 (m + n)/ 2 + 1 的平均值
     * 个数是奇数，中位数是(m + n) / 2
     * 其实就是找到 第 (m + n) / 2 个数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2;
            int midIndex2 = totalLength / 2 + 1;
            double median = getKthElement(nums1, nums2, midIndex1) + getKthElement(nums1, nums2, midIndex2);
            return median;
        }
    }

    private double getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int index1 = 0;
        int index2 = 0;
        while (true) {
            //边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            //正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}

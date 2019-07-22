package debug;

/**
 * Given two ordered integer arrays nums1 and nums2, with size m and n Find out
 * the median (double) of the two arrays. You may suppose nums1 and nums2 cannot
 * be null at the same time.
 *
 * Example 1: nums1 = [1, 3] nums2 = [2] The output would be 2.0
 * 
 * Example 2: nums1 = [1, 2] nums2 = [3, 4] The output would be 2.5
 * 
 * Example 3: nums1 = [1, 1, 1] nums2 = [5, 6, 7] The output would be 3.0
 * 
 * Example 4: nums1 = [1, 1] nums2 = [1, 2, 3] The output would be 1.0
 */

/**
 * Median Sorted for Two Arrays Algorithm
 * Find an i that ensures:
 * 1) i + j = m - i + n - j (or: m - i + n - j + 1)
 * 		if(n >= m) set: i = 0 ~ m, j = (m + n + 1)/2 - i
 * 2) B[j - 1] <= A[i] and A[i - 1] <= B[j]
 * @author tony
 *
 */
public class FindMedianSortedArrays {

	public double findMedianSortedArrays(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		// Swap A & B if m > n. Keep A & m as the shorter one.
		if (m > n) {
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
				//halfLen = (m + n) / 2;
		while (iMin <= iMax) {
			//i appears to be the mid when odd, mid - 1 when even
			// int i = (iMin + iMax + 1) / 2;
			int i = (iMin + iMax) / 2;
			System.out.println("First i " + i + " First iMin " + iMin + " First iMax " + iMax);
			int j = halfLen - i;
			//revised
			if (i < iMax && B[j - 1] > A[i] && j > 0) {
				iMin = i + 1;
				//revised
			} else if (i > iMin && A[i - 1] > B[j] && j < n) {
				iMax = i - 1;
			} else {
				int maxLeft = 0;
				if (i == 0) {
					//BUG here
					maxLeft = B[j - 1];
					System.out.println("B " + B[j - 1]);
				} else if (j == 0) {
					maxLeft = A[i - 1];
					System.out.println("A" + A[i - 1]);
				} else {
					maxLeft = Math.max(A[i - 1], B[j - 1]);
					// System.out.println(A[i - 1] + B[j - 1]);
				}
				// if ((m + n + 1) % 2 == 1) {
				// Choose the mid one when total array is even
				if ((m + n) % 2 == 1) {
					System.out.println("Return " + maxLeft);
					return maxLeft;
				}
				int minRight = 0;
				if (i == m) {
					minRight = B[j];
				} else if (j == n) {
					minRight = A[i];
				} else {
					minRight = Math.min(B[j], A[i]);
				}
				// Debug log
				System.out.println("maxLeft " + maxLeft + " minRight " + minRight);
				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}

}

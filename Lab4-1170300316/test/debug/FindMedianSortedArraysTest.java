package debug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FindMedianSortedArraysTest {

	@Test
	void Case1() {
		System.out.println("Case1");
		FindMedianSortedArrays median = new FindMedianSortedArrays();
		int[] a = new int[] { 1, 3 };
		int[] b = new int[] { 2 };
		assertEquals(median.findMedianSortedArrays(a, b), 2.0);
		System.out.println("Case1End");
	}

	@Test
	void Case2() {
		System.out.println("Case2");
		FindMedianSortedArrays median = new FindMedianSortedArrays();
		int[] a = new int[] { 1, 2 };
		int[] b = new int[] { 3, 4 };
		assertEquals(median.findMedianSortedArrays(a, b), 2.5);
		System.out.println("Case2End");
	}

	@Test
	void Case3() {
		System.out.println("Case3");
		FindMedianSortedArrays median = new FindMedianSortedArrays();
		int[] a = new int[] { 1, 1, 1 };
		int[] b = new int[] { 5, 6, 7 };
		assertEquals(median.findMedianSortedArrays(a, b), 3.0);
		System.out.println("Case3End");
	}

	@Test
	void Case4() {
		System.out.println("Case4");
		FindMedianSortedArrays median = new FindMedianSortedArrays();
		int[] a = new int[] { 1, 1 };
		int[] b = new int[] { 1, 2, 3 };
		assertEquals(median.findMedianSortedArrays(a, b), 1.0);
		System.out.println("Case4End");
	}
}

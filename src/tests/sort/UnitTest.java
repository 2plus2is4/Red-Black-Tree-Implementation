package tests.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import javax.management.RuntimeErrorException;
import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;
import eg.edu.alexu.csd.filestructure.sort.ISort;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {
	private final boolean debug = false;

	public UnitTest() {
	}

	@Test
	public void testRootNull() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		eg.edu.alexu.csd.filestructure.sort.INode<String> root = null;
		try {
			root = heap.getRoot();

			Assert.assertNull("Root is not null", root);

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}
	}

	@Test
	public void testGetRoot() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		eg.edu.alexu.csd.filestructure.sort.INode<String> root = null;
		try {
			heap.insert("Soso");
			root = heap.getRoot();
			Assert.assertEquals("Soso", root.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}
	}

	@Test
	public void testGetRootMulti() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		eg.edu.alexu.csd.filestructure.sort.INode<Integer> root = null;
		try {
			Integer max = Integer.valueOf(Integer.MIN_VALUE);
			for (int i = 0; i < 10000; i++) {
				Random r = new Random();
				int val = r.nextInt(Integer.MAX_VALUE);
				heap.insert(Integer.valueOf(val));
				max = Integer.valueOf(Math.max(max.intValue(), val));
			}
			root = heap.getRoot();
			Assert.assertEquals(max, root.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}
	}

	@Test
	public void testGetRootInsertingThenRemoving() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		eg.edu.alexu.csd.filestructure.sort.INode<Integer> root = null;
		try {
			for (int i = 0; i < 10000; i++) {
				heap.insert(Integer.valueOf(3));
			}
			for (int i = 0; i < 10000; i++) {
				heap.extract();
			}
			root = heap.getRoot();

			Assert.assertNull("Root is not null", root);

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}
	}

	@Test
	public void testHeapSize() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			Assert.assertEquals(0L, heap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail to get Heap size", e);
		}
	}

	@Test
	public void testHeapSizeWithInsertionAndExtraction() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			Random r2 = new Random();
			int check = r2.nextInt(10000);

			for (int i = 0; i < 10000; i++) {
				Random r = new Random();
				int val = r.nextInt(Integer.MAX_VALUE);
				heap.insert(Integer.valueOf(val));
				check--;

				if (check == 0) {
					Assert.assertEquals(i + 1, heap.size());
				}
			}
			check = r2.nextInt(1000);
			for (int i = 0; i < 10000; i++) {
				heap.extract();
				check--;

				if (check == 0) {
					Assert.assertEquals(10000 - i - 1, heap.size());
				}
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail to get heap size", e);
		}
	}

	@Test
	public void testGetChildrenAndParentPointers() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			heap.insert(Integer.valueOf(1));
			heap.insert(Integer.valueOf(2));
			heap.insert(Integer.valueOf(4));
			heap.insert(Integer.valueOf(3));
			heap.insert(Integer.valueOf(0));
			heap.insert(Integer.valueOf(5));

			eg.edu.alexu.csd.filestructure.sort.INode<Integer> root = heap.getRoot();
			Assert.assertEquals(5L, ((Integer) root.getValue()).intValue());
			Assert.assertEquals(3L, ((Integer) root.getLeftChild().getValue()).intValue());
			Assert.assertEquals(4L, ((Integer) root.getRightChild().getValue()).intValue());
			Assert.assertEquals(1L, ((Integer) root.getLeftChild().getLeftChild().getValue()).intValue());
			Assert.assertEquals(0L, ((Integer) root.getLeftChild().getRightChild().getValue()).intValue());
			Assert.assertEquals(2L, ((Integer) root.getRightChild().getLeftChild().getValue()).intValue());
			Assert.assertEquals(5L,
					((Integer) root.getLeftChild().getLeftChild().getParent().getParent().getValue()).intValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to get child/parent pointer", e);
		}
	}

	@Test
	public void testGetNullChildrenAndParentPointers() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			heap.insert(Integer.valueOf(1));
			Assert.assertNull(heap.getRoot().getLeftChild());
			Assert.assertNull(heap.getRoot().getRightChild());
			Assert.assertNull(heap.getRoot().getParent());
		} catch (Throwable e) {
			TestRunner.fail("Fail to get child/parent pointer", e);
		}
	}

	@Test
	public void testHeapifyWithNullParameter() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			heap.insert("soso");
			heap.heapify(null);

			Assert.assertEquals(1L, heap.size());

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle null in heapify method", e);
		}
	}

	@Test
	public void testInsertWithNullParameter() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			heap.insert(null);

			Assert.assertEquals(0L, heap.size());

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle null in insert method", e);
		}
	}

	@Test
	public void testInsertNormal() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			heap.insert("Soso");
			heap.insert("Toto");
			Assert.assertEquals(2L, heap.size());
			Assert.assertEquals("Toto", heap.getRoot().getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to insert to heap", e);
		}
	}

	@Test(timeout = 7000L)
	public void testInsertIsLgN() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			for (int i = 0; i < 10000000; i++)
				heap.insert("soso");
		} catch (Throwable e) {
			TestRunner.fail("Fail to insert to heap", e);
		}
	}

	@Test
	public void testExtractNormal() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			Integer max = Integer.valueOf(Integer.MIN_VALUE);
			Integer secondMax = Integer.valueOf(Integer.MIN_VALUE);

			for (int i = 0; i < 10000; i++) {
				Random r = new Random();
				int val = r.nextInt(Integer.MAX_VALUE);
				heap.insert(Integer.valueOf(val));
				if (val > max.intValue()) {
					secondMax = max;
					max = Integer.valueOf(val);
				} else if (val >= secondMax.intValue()) {
					secondMax = Integer.valueOf(val);
				}
			}

			Assert.assertEquals(max, heap.extract());
			Assert.assertEquals(secondMax, heap.extract());
		} catch (Throwable e) {
			TestRunner.fail("Fail to extract element from heap", e);
		}
	}

	@Test
	public void testExtractEmptyHeap() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			Integer i = (Integer) heap.extract();

			Assert.assertNull(i);

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle extracting from empty heap", e);
		}
	}

	@Test(timeout = 7000L)
	public void testExtractLgN() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			for (int i = 0; i < 10000000; i++)
				heap.insert("soso");
			for (int i = 0; i < 10000000; i++)
				heap.extract();
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle inserting or extracting from empty heap", e);
		}
	}

	@Test
	public void testExtractAfterInsertingAndExtractingAllElements() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			for (int i = 0; i < 1000; i++)
				heap.insert("soso");
			for (int i = 0; i < 1000; i++) {
				heap.extract();
			}
			String s = (String) heap.extract();

			Assert.assertNull(s);

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle extracting after inserting and removing all elements", e);
		}
	}

	@Test
	public void testBuildHeapWithNull() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			heap.build(null);

			Assert.assertEquals(0L, heap.size());

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle build with null paramter", e);
		}
	}

	@Test
	public void testBuildHeapWithEmptyArray() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<String> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			heap.build(new ArrayList());
			Assert.assertEquals(0L, heap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle build with empty array", e);
		}
	}

	@Test(timeout = 1000L)
	public void testBuildIsN() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			for (int i = 0; i < 1000000; i++) {
				arr.add(Integer.valueOf(i));
			}
			heap.build(arr);
		} catch (Throwable e) {
			TestRunner.fail("Fail to build heap", e);
		}
	}

	@Test
	public void testNormalBuild() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			for (int i = 0; i < 1000000; i++) {
				arr.add(Integer.valueOf(i));
			}
			heap.build(arr);
			for (int i = 999999; i >= 0; i--) {
				Assert.assertEquals(i, ((Integer) heap.extract()).intValue());
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail to build heap", e);
		}
	}

	@Test
	public void testRandomBuild() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			Random r = new Random();
			PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());

			for (int i = 0; i < 1000000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(Integer.valueOf(val));
				pq.add(Integer.valueOf(val));
			}

			heap.build(arr);
			for (int i = 0; i < 1000000; i++) {
				Assert.assertEquals(((Integer) pq.poll()).intValue(), ((Integer) heap.extract()).intValue());
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail to build heap", e);
		}
	}

	@Test
	public void testHeapSortWithNullParameter() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = sort.heapSort(null);

			Assert.assertEquals(0L, heap.size());

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	@Test
	public void testHeapSortWithEmptyArray() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = sort.heapSort(new ArrayList());

			Assert.assertEquals(0L, heap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	@Test
	public void testNormalHeapSortSmallInput() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(Integer.valueOf(val));
			}

			eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = sort.heapSort(arr);
			Collections.sort(arr);

			ArrayList<Integer> heapRes = new ArrayList();
			Queue<eg.edu.alexu.csd.filestructure.sort.INode<Integer>> q = new LinkedList();

			q.add(heap.getRoot());

			while (!q.isEmpty()) {
				eg.edu.alexu.csd.filestructure.sort.INode<Integer> curNode = (eg.edu.alexu.csd.filestructure.sort.INode) q.poll();

				if (curNode != null) {
					heapRes.add((Integer) curNode.getValue());
					q.add(curNode.getLeftChild());
					q.add(curNode.getRightChild());
				}
			}
			Assert.assertEquals(arr.size(), heapRes.size());

			for (int i = 0; i < arr.size(); i++) {
				Assert.assertEquals(arr.get(i), heapRes.get(i));
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	@Test(timeout = 5000L)
	public void testNormalHeapSortBigInput() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			Random r = new Random();

			for (int i = 0; i < 1000000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(Integer.valueOf(val));
			}

			eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = sort.heapSort(arr);
			Collections.sort(arr);

			ArrayList<Integer> heapRes = new ArrayList();
			Queue<eg.edu.alexu.csd.filestructure.sort.INode<Integer>> q = new LinkedList();

			q.add(heap.getRoot());

			while (!q.isEmpty()) {
				eg.edu.alexu.csd.filestructure.sort.INode<Integer> curNode = (INode) q.poll();

				if (curNode != null) {
					heapRes.add((Integer) curNode.getValue());
					q.add(curNode.getLeftChild());
					q.add(curNode.getRightChild());
				}
			}
			Assert.assertEquals(arr.size(), heapRes.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), heapRes.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	@Test
	public void testSlowSortWithNullParameter() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			sort.sortSlow(null);

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {

			TestRunner.fail("Fail to slow sort", e);
		}
	}

	@Test
	public void testSlowSortWithEmptyArray() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			sort.sortSlow(new ArrayList());
		} catch (Throwable e) {
			TestRunner.fail("Fail to slow sort", e);
		}
	}

	@Test
	public void testSlowSortWithSmallInput() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(Integer.valueOf(val));
			}

			ArrayList<Integer> arr2 = new ArrayList(arr);
			sort.sortSlow(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in slow sort", e);
		}
	}

	@Test(timeout = 10000L)
	public void testSlowSortWithBigInput() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			Random r = new Random();

			for (int i = 0; i < 10000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(Integer.valueOf(val));
			}

			ArrayList<Integer> arr2 = new ArrayList(arr);
			sort.sortSlow(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in slow sort", e);
		}
	}

	@Test
	public void testFastSortWithNullParameter() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			sort.sortFast(null);

		} catch (RuntimeErrorException localRuntimeErrorException) {
		} catch (Throwable e) {

			TestRunner.fail("Fail to fast sort", e);
		}
	}

	@Test
	public void testFastSortWithEmptyArray() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			sort.sortFast(new ArrayList());
		} catch (Throwable e) {
			TestRunner.fail("Fail to slow sort", e);
		}
	}

	@Test
	public void testFastSortWithSmallInput() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(Integer.valueOf(val));
			}

			ArrayList<Integer> arr2 = new ArrayList(arr);
			sort.sortFast(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}
	}

	@Test(timeout = 5000L)
	public void testFastSortWithBigInput() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			ArrayList<Integer> arr = new ArrayList();
			Random r = new Random();

			for (int i = 0; i < 1000000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(Integer.valueOf(val));
			}

			ArrayList<Integer> arr2 = new ArrayList(arr);
			sort.sortFast(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}
	}

	@Test(timeout = 5000L)
	public void testFastSortWithReverseInput() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Integer> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		try {
			ArrayList<Integer> arr = new ArrayList();

			for (int i = 0; i < 1000000; i++) {
				arr.add(Integer.valueOf(1000000 - i));
			}

			ArrayList<Integer> arr2 = new ArrayList(arr);
			sort.sortFast(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}
	}

	@Test
	public void testStressHeap() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Integer> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.IHeap.class);
		try {
			PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
			Random r = new Random();
			Random pick = new Random();

			for (int i = 0; i < 1000000; i++) {
				int numToPick = pick.nextInt(Integer.MAX_VALUE);
				int val = r.nextInt(Integer.MAX_VALUE);

				if (numToPick % 4 == 0) {
					if (!pq.isEmpty()) {
						Assert.assertEquals(pq.poll(), heap.extract());
					} else
						Assert.assertEquals(0L, heap.size());
				} else {
					pq.add(Integer.valueOf(val));
					heap.insert(Integer.valueOf(val));
				}
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap", e);
		}
	}

	@Test
	public void testStressHeapWithCustomComparable() {
		eg.edu.alexu.csd.filestructure.sort.IHeap<Pair> heap = (eg.edu.alexu.csd.filestructure.sort.IHeap) TestRunner.getImplementationInstanceForInterface(IHeap.class);
		String[] randomWords = { "bells", "remain", "crabby", "comfortable", "stamp", "quickest", "sulky", "worm",
				"vigorous", "grandfather", "crook", "show", "second", "water", "ask", "finger", "scent", "encourage",
				"harsh", "kaput", "spotted", "room", "harmony", "bear", "desk", "dramatic", "leg", "elite", "drop",
				"overjoyed", "suspend", "selection", "tow", "pancake", "doubt", "laugh", "coast", "slow", "narrow",
				"language", "hand", "preach", "shaky", "flavor", "spark", "uptight", "pail", "jog", "unadvised",
				"fortunate", "exultant", "clumsy", "rot", "train", "curtain", "spurious", "middle", "dare", "wheel",
				"snake", "jail", "crooked", "smoggy", "elfin", "abnormal", "skip", "skate", "basket", "amount",
				"invention", "vegetable", "unequaled", "part", "erratic", "branch", "car", "glib", "fish", "order",
				"deranged", "bomb", "overrated", "orange", "enjoy", "judicious", "finger", "cheap", "meek", "gruesome",
				"defective", "wicked", "bashful", "rotten", "ground", "delicious", "cellar", "chalk", "dress", "north",
				"serious" };
		try {
			PriorityQueue<Pair> pq = new PriorityQueue(Collections.reverseOrder());
			Random r = new Random();
			Random pick = new Random();

			for (int i = 0; i < 1000000; i++) {
				int numToPick = pick.nextInt(Integer.MAX_VALUE);
				Pair p = new Pair(r.nextInt(Integer.MAX_VALUE), randomWords[r.nextInt(randomWords.length)]);

				if (numToPick % 4 == 0) {
					if (!pq.isEmpty()) {
						Pair p1 = (Pair) pq.poll();
						Pair p2 = (Pair) heap.extract();

						Assert.assertEquals(p1.key, p2.key);
						Assert.assertEquals(p1.val, p2.val);
					} else {
						Assert.assertEquals(0L, heap.size());
					}
				} else {
					pq.add(p);
					heap.insert(p);
				}
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap", e);
		}
	}

	@Test
	public void testStressSlowSortWithCustomComparable() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Pair> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(eg.edu.alexu.csd.filestructure.sort.ISort.class);
		String[] randomWords = { "bells", "remain", "crabby", "comfortable", "stamp", "quickest", "sulky", "worm",
				"vigorous", "grandfather", "crook", "show", "second", "water", "ask", "finger", "scent", "encourage",
				"harsh", "kaput", "spotted", "room", "harmony", "bear", "desk", "dramatic", "leg", "elite", "drop",
				"overjoyed", "suspend", "selection", "tow", "pancake", "doubt", "laugh", "coast", "slow", "narrow",
				"language", "hand", "preach", "shaky", "flavor", "spark", "uptight", "pail", "jog", "unadvised",
				"fortunate", "exultant", "clumsy", "rot", "train", "curtain", "spurious", "middle", "dare", "wheel",
				"snake", "jail", "crooked", "smoggy", "elfin", "abnormal", "skip", "skate", "basket", "amount",
				"invention", "vegetable", "unequaled", "part", "erratic", "branch", "car", "glib", "fish", "order",
				"deranged", "bomb", "overrated", "orange", "enjoy", "judicious", "finger", "cheap", "meek", "gruesome",
				"defective", "wicked", "bashful", "rotten", "ground", "delicious", "cellar", "chalk", "dress", "north",
				"serious" };
		try {
			Random r = new Random();
			ArrayList<Pair> arr = new ArrayList();

			for (int i = 0; i < 10000; i++) {
				Pair p = new Pair(r.nextInt(Integer.MAX_VALUE), randomWords[r.nextInt(randomWords.length)]);
				arr.add(p);
			}

			ArrayList<Pair> copy = new ArrayList(arr);
			Collections.sort(arr);
			sort.sortSlow(copy);

			for (int i = 0; i < arr.size(); i++) {
				Assert.assertEquals(arr.get(i).key, copy.get(i).key);
				Assert.assertEquals(arr.get(i).val, copy.get(i).val);
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in slow sort", e);
		}
	}

	@Test
	public void testStressFastSortWithCustomComparable() {
		eg.edu.alexu.csd.filestructure.sort.ISort<Pair> sort = (eg.edu.alexu.csd.filestructure.sort.ISort) TestRunner.getImplementationInstanceForInterface(ISort.class);
		String[] randomWords = { "bells", "remain", "crabby", "comfortable", "stamp", "quickest", "sulky", "worm",
				"vigorous", "grandfather", "crook", "show", "second", "water", "ask", "finger", "scent", "encourage",
				"harsh", "kaput", "spotted", "room", "harmony", "bear", "desk", "dramatic", "leg", "elite", "drop",
				"overjoyed", "suspend", "selection", "tow", "pancake", "doubt", "laugh", "coast", "slow", "narrow",
				"language", "hand", "preach", "shaky", "flavor", "spark", "uptight", "pail", "jog", "unadvised",
				"fortunate", "exultant", "clumsy", "rot", "train", "curtain", "spurious", "middle", "dare", "wheel",
				"snake", "jail", "crooked", "smoggy", "elfin", "abnormal", "skip", "skate", "basket", "amount",
				"invention", "vegetable", "unequaled", "part", "erratic", "branch", "car", "glib", "fish", "order",
				"deranged", "bomb", "overrated", "orange", "enjoy", "judicious", "finger", "cheap", "meek", "gruesome",
				"defective", "wicked", "bashful", "rotten", "ground", "delicious", "cellar", "chalk", "dress", "north",
				"serious" };
		try {
			Random r = new Random();
			ArrayList<Pair> arr = new ArrayList();

			for (int i = 0; i < 10000; i++) {
				Pair p = new Pair(r.nextInt(Integer.MAX_VALUE), randomWords[r.nextInt(randomWords.length)]);
				arr.add(p);
			}

			ArrayList<Pair> copy = new ArrayList(arr);
			Collections.sort(arr);
			sort.sortFast(copy);

			for (int i = 0; i < arr.size(); i++) {
				Assert.assertEquals(arr.get(i).key, copy.get(i).key);
				Assert.assertEquals(arr.get(i).val, copy.get(i).val);
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}
	}

	private class Pair implements Comparable<Pair> {
		private int key;
		private String val;

		public Pair(int key, String val) {
			this.key = key;
			this.val = val;
		}

		public int compareTo(Pair o) {
			String s1 = val + Objects.hash(new Object[] { Integer.valueOf(key), val });
			String s2 = val + Objects.hash(new Object[] { Integer.valueOf(key), val });
			return s1.compareTo(s2);
		}
	}
}

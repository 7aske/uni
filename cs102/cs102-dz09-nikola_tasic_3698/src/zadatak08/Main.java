package zadatak08;


import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) {
		final int SIZE = 9000000;
		int[] list = new int[SIZE];
		for (int i = 0; i < list.length; i++)
			list[i] = (int) (Math.random() * 10000000);

		long startTime = System.currentTimeMillis();
		parallelMergeSort(list);
		long endTime = System.currentTimeMillis();
		System.out.println(Runtime.getRuntime().availableProcessors() +
				" processors - " + (endTime - startTime) + "ms");
	}

	private static void parallelMergeSort(int[] list) {
		RecursiveAction mainTask = new SortTask(list);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
	}

	private static class SortTask extends RecursiveAction {
		private int[] list;

		SortTask(int[] list) {
			this.list = list;
		}

		@Override
		protected void compute() {
			int THRESHOLD = 500;
			if (list.length < THRESHOLD)
				java.util.Arrays.sort(list);
			else {
				int[] firstHalf = new int[list.length / 2];
				System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

				int secondHalfLength = list.length - list.length / 2;
				int[] secondHalf = new int[secondHalfLength];
				System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);

				invokeAll(new SortTask(firstHalf),
						new SortTask(secondHalf));

				MergeSort.merge(firstHalf, secondHalf, list);
			}
		}
	}
}
class MergeSort {
	static void merge(int[] list1, int[] list2, int[] resultList) {
		int indexOfList1 = 0;
		int indexOfList2 = 0;
		int indexOfMergedList = 0;

		while(indexOfList1 < list1.length && indexOfList2 < list2.length) {
			if(list1[indexOfList1] < list2[indexOfList2]) {
				resultList[indexOfMergedList] = list1[indexOfList1];
				indexOfList1++;
			}else {
				resultList[indexOfMergedList] = list2[indexOfList2];
				indexOfList2++;
			}
			indexOfMergedList++;
		}
		System.arraycopy(list1, indexOfList1, resultList, indexOfMergedList, list1.length - indexOfList1);
		System.arraycopy(list2, indexOfList2, resultList, indexOfMergedList, list2.length - indexOfList2);
	}
}
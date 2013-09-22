package practice3;

public class Benchmark {

	public static void LinkedListComparison(int number) {

		System.out.println("Number of elements: " + number);
		System.out.println("LinkedList: " + LinkedListBench(number) / 1e3
				+ "s\tLinkedListFromArray: " + LinkedListFromArrayBench(number)
				/ 1e3 + "s");

	}

	public static double LinkedListBench(int number) {

		LinkedList ll = new LinkedList();

		long addLastTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.addLast("" + Math.random());
		}
		addLastTime = System.currentTimeMillis() - addLastTime;

		ll = new LinkedList();
		long addFirstTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.addFirst("" + Math.random());
		}
		addFirstTime = System.currentTimeMillis() - addFirstTime;
		
		ll = new LinkedList();
		long insertTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.insertAt(ll.getSize() / 2, "" + Math.random());
		}
		insertTime = System.currentTimeMillis() - insertTime;

		long removeTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.removeAt(ll.getSize() / 2);
		}
		removeTime = System.currentTimeMillis() - removeTime;
		
		System.out.println("LL:   " + LLBenchInfoString(addLastTime, addFirstTime,
				insertTime, removeTime));
		

		return addLastTime + addFirstTime + insertTime + removeTime;
	}

	public static double LinkedListFromArrayBench(int number) {

		LinkedListFromArray ll = new LinkedListFromArray();

		long addLastTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.addLast("" + Math.random());
		}
		addLastTime = System.currentTimeMillis() - addLastTime;

		ll = new LinkedListFromArray();
		long addFirstTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.addFirst("" + Math.random());
		}
		addFirstTime = System.currentTimeMillis() - addFirstTime;

		ll = new LinkedListFromArray();
		long insertTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.insertAt(ll.getSize() / 2, "" + Math.random());
		}
		insertTime = System.currentTimeMillis() - insertTime;

		long removeTime = System.currentTimeMillis();
		for (int i = 0; i < number; i++) {
			ll.removeAt(ll.getSize() / 2);
		}
		removeTime = System.currentTimeMillis() - removeTime;
		
		System.out.println("LLFA: " + LLBenchInfoString(addLastTime, addFirstTime,
				insertTime, removeTime));
		
		return addLastTime + addFirstTime + insertTime + removeTime;
	}

	private static String LLBenchInfoString(long addlastTime, long addfirstTime,
			long insertTime, long removeTime) {
		return "addLast: " + addlastTime / 1e3 + "s\taddFirst: " + addfirstTime
				/ 1e3 + "s\tinsert: " + insertTime / 1e3 + "s\tremove: "
				+ removeTime / 1e3 + "s";
	}
}

package practice3;

/**
 * Created with IntelliJ IDEA. User: pupoque Date: 11.09.13 Time: 13:07 To
 * change this template use File | Settings | File Templates.
 */
public class CollectionsTest {
	public static void main(String[] args) {

		Double obj0 = new Double(0.98765);
		String obj1 = new String("pew pew");
		Float obj2 = new Float(2.63234);
		Boolean obj3 = new Boolean("true");
		String obj4 = "Ololo";
		String obj5 = "The Fifth";
		String obj7 = "CREW";
		String obj8 = "x-x-x";

		Array myArray = new Array(5);
		myArray.setElement(0, obj0);
		myArray.setElement(1, obj1);
		myArray.setElement(4, obj2);

		System.out.println(myArray);
		System.out.println("Array size: " + myArray.getSize());

		System.out.println("===============================");

		Vector vec = new Vector(5);
		vec.addElement(obj0);
		vec.addElement(obj1);
		vec.addElement(obj1);
		vec.addElement(obj1);
		vec.addElement(obj1);
		vec.addElement(obj3);
		vec.addElement(obj4);

		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - set " + obj2 + " to [3]");
		vec.setElement(3, obj2);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - remove at [3]");
		vec.removeAt(3);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - remove at [2] three times");
		vec.removeAt(2);
		vec.removeAt(2);
		vec.removeAt(2);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - remove at [5] ");
		vec.removeAt(5);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - insert " + obj3 + " at [1]");
		vec.insertAt(1, obj3);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - insert " + obj2 + " at [5]");
		vec.insertAt(5, obj2);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - insert " + obj1 + " at [3]");
		vec.insertAt(3, obj1);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());
		System.out.println(" - - - remove at [6] ");
		vec.removeAt(6);
		System.out.println(vec);
		System.out.println("practice3.Vector size: " + vec.getCurrentSize());

		System.out.println("===============================");

		LinkedList llst = new LinkedList();
		// LinkedListFromArray llst = new LinkedListFromArray();
		llst.addLast(obj0);
		System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());
		System.out.println();
		llst.addLast(obj1);
		System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());
		System.out.println();
		llst.addLast(obj2);
		System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());
		System.out.println();
		llst.addLast(obj3);
		System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());
		System.out.println();
		llst.addLast(obj4);
		System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());
		System.out.println();
		llst.addLast(obj5);
		System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - the size");
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - each element");
		System.out.println(llst.getElement(0));
		System.out.println(llst.getElement(1));
		System.out.println(llst.getElement(2));
		System.out.println(llst.getElement(3));
		System.out.println(llst.getElement(4));
		System.out.println(llst.getElement(5));
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - each element with siblings");
		System.out.println(llst.toInfoString());

		System.out.println(" - - - The LinkedList");
		System.out.println(llst);
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - get first and get last");
		System.out.println(llst.getFirst());
		System.out.println(llst.getLast());
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - remove [2]");
		llst.removeAt(2);
		System.out.println(llst);
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - set " + obj1 + " to [8]");
		llst.setElement(8, obj1);
		System.out.println(llst);
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - set " + obj7 + " to [3]");
		llst.setElement(3, obj7);
		System.out.println(llst);
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - add " + obj8 + " as first");
		llst.addFirst(obj8);
		System.out.println(llst);
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - insert " + obj5 + " to [3]");
		llst.insertAt(3, obj5);
		System.out.println(llst);
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - remove " + "[" + (llst.getSize() - 2) + "]");
		llst.removeAt(llst.getSize() - 2);
		System.out.println(llst);
		System.out.println(" # # # ");
		// System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - remove " + "[" + (llst.getSize() - 1) + "]");
		llst.removeAt(llst.getSize() - 1);
		System.out.println(llst);
		System.out.println(" # # # ");
		// System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - remove " + "[" + (llst.getSize() - 1) + "]");
		llst.removeAt(llst.getSize() - 1);
		System.out.println(llst);
		System.out.println(" # # # ");
		// System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - remove " + "[" + (llst.getSize() - 1) + "]");
		llst.removeAt(llst.getSize() - 1);
		System.out.println(llst);
		System.out.println(" # # # ");
		// System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - remove " + "[" + (llst.getSize() - 1) + "]");
		llst.removeAt(llst.getSize() - 1);
		System.out.println(llst);
		System.out.println(" # # # ");
		// System.out.println(llst.toInfoString());
		System.out.println("Size: " + llst.getSize());

		System.out.println(" - - - set ordered String values");
		llst.setElement(0, "Zero");
		llst.setElement(1, "One");
		llst.setElement(2, "Two");
		llst.setElement(3, "Three");
		llst.setElement(4, "Four");
		llst.setElement(5, "Five");
		llst.setElement(6, "Six");
		System.out.println(llst);
		System.out.println("Size: " + llst.getSize());

		llst.testCoherence();

		System.out.println("===============================");
		System.out.println("LinkedList vs LinkedListfromArray");
		Benchmark.LinkedListComparison(10);
		System.out.println();
		Benchmark.LinkedListComparison(100);
		System.out.println();
		Benchmark.LinkedListComparison(1000);
		System.out.println();
		Benchmark.LinkedListComparison(10000);
		System.out.println();
		// Benchmark.LinkedListComparison(100000);
		// System.out.println();
		// Benchmark.LinkedListComparison(200000);
		// System.out.println();
		// Benchmark.LinkedListComparison(10000000);

		System.out.println("===============================");
		Stack st = new Stack();
		System.out.println(" - - - - - - Push " + obj0);
		st.push(obj0);
		System.out.println(st);
		System.out.println(" - - - - - - Push " + obj1);
		st.push(obj1);
		System.out.println(st);
		System.out.println(" - - - - - - Push " + obj2);
		st.push(obj2);
		System.out.println(st);
		System.out.println(" - - - - - - Peek");
		System.out.println(st.peek());
		System.out.println(" - - - - - - Peek");
		System.out.println(st.peek());
		System.out.println(" - - - - - - Peek");
		System.out.println(st.peek());
		System.out.println(" - - - - - - Pop: " + st.pop());
		System.out.println(st);
		System.out.println(" - - - - - - Pop: " + st.pop());
		System.out.println(st);
		System.out.println(" - - - - - - Pop: " + st.pop());
		System.out.println(st);
		System.out.println(" - - - - - - Pop: " + st.pop());
		System.out.println(st);
		System.out.println(" - - - - - - Pop: " + st.pop());
		System.out.println(st);

		System.out.println("===============================");
		Hashtable h = new Hashtable();
		h.put("yolo", "swag");
		h.put("pewpew", "zuzuz");
		h.put("foo", "bar");
		h.put("lang", "java");
		h.put("cool lang", "perl");
		System.out.println(" - - - - - - Get: foo");
		System.out.println(h.get("foo"));
		System.out.println(" - - - - - - Get: yolo");
		System.out.println(h.get("yolo"));
		System.out.println(" - - - - - - Get: pewpew");
		System.out.println(h.get("pewpew"));

		System.out.println(" - - - - - - Printing out");
		System.out.println(h);
		
		System.out.println(" - - - - - - Set: pewpew => bzzzz");
		h.put("pewpew", "bzzzz");
		System.out.println(h.get("pewpew"));

		System.out.println(" - - - - - - Printing out");
		System.out.println(h);
		
		System.out.println(" - - - - - - Remove: pewpew");
		h.remove("pewpew");
		System.out.println(h.get("pewpew"));

		System.out.println(" - - - - - - Printing out");
		System.out.println(h);

	}
}

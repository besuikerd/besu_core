package com.besuikerd.core.utils;

public class TupleUtils {

	public static final Tuple nullTuple = new Tuple(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

	/**
	 * transforms a tuple (x1, y1, x2, y2) to (x2 - x1 + 1, y2 - y1 + 1). throws
	 * a RuntimeException if y2 > y1 or x2 > x1
	 * 
	 * @param t
	 *            Tuple(x1, y1, x2, y2)
	 * @return Tuple(x2 - x1 + 1, y2 - y1 + 1)
	 */
	public static Tuple xyDiff(Tuple t) {
		return new Tuple(xDiff(t), yDiff(t));
	}

	/**
	 * transforms a tuple (x1, y1, x2, y2) to y2 - y1 + 1. throws a
	 * RuntimeException if y2 > y1
	 * 
	 * @param t
	 *            Tuple(x1, y1, x2, y2)
	 * @return y2 - y1 + 1
	 */
	public static int yDiff(Tuple t) {
		if (t.int2() > t.int4())
			throw new RuntimeException("y2 is greater than y1 in yDiff comparison");
		return t.int4() - t.int2() + 1;
	}

	/**
	 * transforms a tuple (x1, y1, x2, y2) to x2 - x1 + 1. throws a
	 * RuntimeException if x2 > x1
	 * 
	 * @param t
	 *            Tuple(x1, y1, x2, y2)
	 * @return x2 - x1 + 1
	 */
	public static int xDiff(Tuple t) {
		if (t.int2() > t.int4())
			throw new RuntimeException("x2 is greater than x1 in yDiff comparison");
		return t.int3() - t.int1() + 1;
	}
}

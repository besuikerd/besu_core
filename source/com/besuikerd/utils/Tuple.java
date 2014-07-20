package com.besuikerd.utils;

import java.util.Arrays;

/**
 * class representing a tuple. Helper functions are defined for tuples up to 10
 * elements
 * 
 * @author Besuikerd
 * 
 */
public class Tuple {
	
	
	
	private Object[] elements;

	public Tuple(Object... elements) {
		this.elements = elements;
	}

	public int n() {
		return elements.length;
	}

	/**
	 * replace the nth element with the given object
	 * @param o object to put
	 * @param n element number to replace
	 * @return this
	 */
	public Tuple putN(Object o, int n){
		elements[n] = o;
		return this;
	}

	public Object fst() {
		return elements[0];
	}

	public Object snd() {
		return elements[1];
	}

	public Object thrd() {
		return elements[2];
	}

	public Object frth() {
		return elements[3];
	}

	public Object fifth() {
		return elements[4];
	}

	public Object sixth() {
		return elements[5];
	}

	public Object svnth() {
		return elements[6];
	}

	public Object eight() {
		return elements[7];
	}

	public Object ninth() {
		return elements[8];
	}

	public Object tenth() {
		return elements[9];
	}

	public Object nth(int n) {
		return elements[n];
	}

	public int int1() {
		return intN(1);
	}

	public int int2() {
		return intN(2);
	}

	public int int3() {
		return intN(3);
	}

	public int int4() {
		return intN(4);
	}

	public int int5() {
		return intN(5);
	}

	public int int6() {
		return intN(6);
	}

	public int int7() {
		return intN(7);
	}

	public int int8() {
		return intN(8);
	}

	public int int9() {
		return intN(9);
	}

	public int int10() {
		return intN(10);
	}

	public double double1() {
		return doubleN(1);
	}

	public double double2() {
		return doubleN(2);
	}

	public double double3() {
		return doubleN(3);
	}

	public double double4() {
		return doubleN(4);
	}

	public double double5() {
		return doubleN(5);
	}

	public double double6() {
		return doubleN(6);
	}

	public double double7() {
		return doubleN(7);
	}

	public double double8() {
		return doubleN(8);
	}

	public double double9() {
		return doubleN(9);
	}

	public double double10() {
		return doubleN(10);
	}

	public float float1() {
		return floatN(1);
	}

	public float float2() {
		return floatN(2);
	}

	public float float3() {
		return floatN(3);
	}

	public float float4() {
		return floatN(4);
	}

	public float float5() {
		return floatN(5);
	}

	public float float6() {
		return floatN(6);
	}

	public float float7() {
		return floatN(7);
	}

	public float float8() {
		return floatN(8);
	}

	public float float9() {
		return floatN(9);
	}

	public float float10() {
		return floatN(10);
	}

	public long long1() {
		return longN(1);
	}

	public long long2() {
		return longN(2);
	}

	public long long3() {
		return longN(3);
	}

	public long long4() {
		return longN(4);
	}

	public long long5() {
		return longN(5);
	}

	public long long6() {
		return longN(6);
	}

	public long long7() {
		return longN(7);
	}

	public long long8() {
		return longN(8);
	}

	public long long9() {
		return longN(9);
	}

	public long long10() {
		return longN(10);
	}

	public short short1() {
		return shortN(1);
	}

	public short short2() {
		return shortN(2);
	}

	public short short3() {
		return shortN(3);
	}

	public short short4() {
		return shortN(4);
	}

	public short short5() {
		return shortN(5);
	}

	public short short6() {
		return shortN(6);
	}

	public short short7() {
		return shortN(7);
	}

	public short short8() {
		return shortN(8);
	}

	public short short9() {
		return shortN(9);
	}

	public short short10() {
		return shortN(10);
	}

	public char char1() {
		return charN(1);
	}

	public char char2() {
		return charN(2);
	}

	public char char3() {
		return charN(3);
	}

	public char char4() {
		return charN(4);
	}

	public char char5() {
		return charN(5);
	}

	public char char6() {
		return charN(6);
	}

	public char char7() {
		return charN(7);
	}

	public char char8() {
		return charN(8);
	}

	public char char9() {
		return charN(9);
	}

	public char char10() {
		return charN(10);
	}

	public int intN(int n) {
		return (Integer) elements[n - 1];
	}

	public double doubleN(int n) {
		return (Double) elements[n - 1];
	}

	public float floatN(int n) {
		return (Float) elements[n - 1];
	}

	public long longN(int n) {
		return (Long) elements[n - 1];
	}

	public short shortN(int n) {
		return (Short) elements[n - 1];
	}

	public char charN(int n) {
		return (Character) elements[n - 1];
	}

	public <E> E objN(int n, Class<E> cls) {
		return cls.cast(n);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(elements);
	}
}

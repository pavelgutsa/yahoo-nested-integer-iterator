package com.yahoo.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class NestedIntegerTest {

	@Test
	public void test1number() {

		NestedIterator iterator = new NestedIterator(Arrays.asList(new NestedIntegerImpl(1)));

		assertTrue(iterator.hasNext());
		assertEquals(1, iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void test1nestedNumber() {

		NestedIterator iterator = new NestedIterator(Arrays
				.asList(new NestedIntegerImpl(Arrays.asList(new NestedIntegerImpl(1), new NestedIntegerImpl(2)))));

		assertTrue(iterator.hasNext());
		assertEquals(1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(2, iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void test3nestedNumbers() {

		NestedIterator iterator = new NestedIterator(
				Arrays.asList(new NestedIntegerImpl(1), new NestedIntegerImpl(Arrays.asList(new NestedIntegerImpl(2),
						new NestedIntegerImpl(Arrays.asList(new NestedIntegerImpl(3), new NestedIntegerImpl(4)))))));

		assertTrue(iterator.hasNext());
		assertEquals(1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(2, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(3, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(4, iterator.next());
		assertFalse(iterator.hasNext());
	}
}

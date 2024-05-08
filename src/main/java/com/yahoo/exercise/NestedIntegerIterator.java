package com.yahoo.exercise;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

interface NestedInteger {

	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// @return the nested list that this NestedInteger holds, if it holds a nested
	// list
	// Return empty list if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}

class NestedIntegerImpl implements NestedInteger {

	public List<NestedInteger> nestedList = new ArrayList<>();
	
	int value;
	
	public NestedIntegerImpl(int value) {
		this.value = value;
	}
	
	public NestedIntegerImpl(List<NestedInteger> nestedList) {
		this.nestedList = nestedList;
	}
	
	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger() {
		return nestedList.isEmpty();
	}

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger() {
		return value;
	}

	// @return the nested list that this NestedInteger holds, if it holds a nested
	// list
	// Return empty list if this NestedInteger holds a single integer
	public List<NestedInteger> getList() {
		return nestedList;
	}
}

/**
 * Implement the NestedIterator class: NestedIterator(List<NestedInteger>
 * nestedList) Initializes the iterator with the nested list nestedList. boolean
 * hasNext() Returns true if there are still some integers in the nested list
 * and false otherwise. int next() Returns the next integer in the nested list.
 * 
 * [1] [[1,2]] [1, [2, [3, 4]]] => 1, 2, 3, 4
 */

class NestedIterator {

	private List<NestedInteger> nestedList;

	private int currentPosition;

	Deque<NestedIterator> stack = new ArrayDeque<NestedIterator>();

	public NestedIterator(List<NestedInteger> nestedList) {
		this.nestedList = nestedList;
	}

	private boolean doesHaveNext() {
		if (currentPosition >= nestedList.size()) {
			return false;
		} else {
			if (nestedList.get(currentPosition).isInteger()) {
				return true;
			} else {
				return nestedList.get(currentPosition).getList().size() > 0;
			}
		}
	}

	boolean hasNext() {
		if (stack.isEmpty()) {
			return doesHaveNext();
		} else {
			while (!stack.isEmpty() && !stack.peek().hasNext()) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				return doesHaveNext();
			} else {
				return stack.peek().hasNext();
			}
		}
	}

	int next() {
		if (stack.isEmpty()) {
			if (nestedList.get(currentPosition).isInteger()) {
				return nestedList.get(currentPosition++).getInteger();
			} else {
				NestedIterator nextIterator = new NestedIterator(nestedList.get(currentPosition++).getList());
				stack.push(nextIterator);
				if (nextIterator.hasNext()) {
					return nextIterator.next();
				} else {
					throw new RuntimeException("Called next() in iterator that doesn't have next");
				}
			}
		} else {
			return stack.peek().next();
		}
	}
}
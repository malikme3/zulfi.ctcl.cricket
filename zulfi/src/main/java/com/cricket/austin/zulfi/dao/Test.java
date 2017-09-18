package com.cricket.austin.zulfi.dao;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

public class Test {
	@Autowired
	private BattingRecordsDaoImpl bat;

	private static class Foo {
		public int id, targetCost, actualCost, runs;
		public String ref;

		public Foo(int id, String ref, int targetCost, int actualCost, int runs) {
			this.id = id;
			this.targetCost = targetCost;
			this.actualCost = actualCost;
			this.ref = ref;
			this.runs = runs;
		}

		@Override
		public String toString() {
			return String.format("Foo(%d,%s,%d,%d)", id, ref, targetCost, actualCost);
		}
	}

	public static void main(String[] args) {
		List<Foo> list = Arrays.asList(new Foo(1, "P1", 300, 400, 2), new Foo(2, "P2", 600, 400, 5),
				new Foo(3, "P3", 30, 20, 6), new Foo(3, "P3", 70, 20, 1), new Foo(1, "P1", 360, 40, 2),
				new Foo(4, "P4", 320, 200, 8), new Foo(4, "P4", 500, 900, 3));

		List<Foo> transform = list.stream().collect(Collectors.groupingBy(foo -> foo.id)).entrySet()
				.stream().map(
						e -> e.getValue().stream()
								.reduce((f1, f2) -> new Foo(f1.id, f1.ref, f1.targetCost + f2.targetCost,
										f1.runs + f2.runs, f1.runs + f2.runs)))
				.map(f -> f.get()).collect(Collectors.toList());
		System.out.println(transform);
	}
}
package com.igs.swc.hwctl.web.data;

import java.util.LinkedHashMap;

public class Result extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = -7220139121996585028L;

	public Result addVal(String key, String val) {
		put(key, val);
		return this;
	}

	public static Result of(Object... args) {
		Result r = new Result();

		for (int i = 0; i < args.length - 1; i += 2) {
			r.put(String.valueOf(args[i]), args[i + 1]);
		}
		return r;
	}

	public static void main(String... args) {
		System.out.println(Result.of("a", "b", "c", "d", "e", "f"));
	}
}

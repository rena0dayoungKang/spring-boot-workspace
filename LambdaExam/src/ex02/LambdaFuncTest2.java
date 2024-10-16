package ex02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

//함수형 인터페이스를 사용하는 컬렉션 프레임워크의 메서드
public class LambdaFuncTest2 {

	static void method(Integer n) { // [static]리턴타입이 없고 파라미터가 있는 consumer타입
		System.out.println(n);
	}

	void method2(Integer n) { // [void]리턴타입이 없고 파라미터가 있는 consumer타입
		System.out.println(n);
	}

	static Integer max(Integer x, Integer y) { //[static]파라미터가 두개, 리턴타입이 파라미터와 타입같음
		return x > y ? x : y;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.forEach(i -> System.out.print(i + ","));
		System.out.println();

		list.removeIf(i -> i % 2 == 0 || i % 3 == 0);
		list.forEach(i -> System.out.print(i + ","));
		System.out.println();

		list.replaceAll(i -> i * 10);
		list.forEach(i -> System.out.print(i + ","));
		System.out.println();

		Map<String, String> map = new HashMap<>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");

		map.forEach((k, v) -> System.out.println("{" + k + "," + v + "}"));

		// static
		Consumer<Integer> f = LambdaFuncTest2::method;
		f.accept(100);

		// void
		LambdaFuncTest2 lambdaFuncTest2 = new LambdaFuncTest2();
		Consumer<Integer> f2 = lambdaFuncTest2::method2;
		f2.accept(100);
		
		// max 1방식
		BiFunction<Integer, Integer, Integer> f3 = LambdaFuncTest2::max;
		System.out.println(f3.apply(3, 10));
		
		// max 2방식
		BinaryOperator<Integer> f4 = LambdaFuncTest2::max;
		System.out.println(f4.apply(3, 10));
		

	}

}

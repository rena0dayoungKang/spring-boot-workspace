package ex02;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//1. 매개변수 X, 반환값 X : Runnable 인터페이스 java.lang.Runnable : void run() 

//java.util.function 패키지 (일반적으로 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 미리 정의
//2. 매개변수 X, 반환값 O : Supplier<T> : T get()
//3. 매개변수 O, 반환값 X : Consumer<T> : void accept(T t)
//4. 매개변수 O, 반환값 O : Function<T,R> : R apply(T t)
//5. 매개변수 O, 반환값 (boolean) : Predicate<T> : boolean test(T t) : 조건식에 이용함

//매개변수 2개 
//6. BiConsumer<T, U> : void accept(T t, U u)
//7. BiPredicate<T, U> : boolean test(T t, U u)
//8. BiFunction<T, U, R> : R apply(T t, U u)

//매개변수 타입과 반환타입이 일치 : Function의 변형 
//9. UnaryOperator<T> : T apply(T t)
//10. BinaryOperator<T> : T apply(T t, T t)

public class LambdaFuncTest1 {

	public static void main(String[] args) {

		// 1.Runnable
		Runnable f = () -> System.out.println("Lambda Test1"); // Runnable 이미 있는 인터페이스를 사용
		f.run();

		// 2.Supplier
		Supplier<Integer> f2 = () -> (int) (Math.random() * 100) + 1;
		System.out.println(f2.get());

		// 3.Consumer
		Consumer<Integer> f3 = (i) -> System.out.println(i + ",");
		f3.accept(5);

		// 4.Function
		Function<Integer, Integer> f4 = i -> i / 10 * 10;
		System.out.println(f4.apply(15));

		// 5.Predicate
		Predicate<Integer> f5 = i -> i % 2 == 0;
		System.out.println(f5.test(13));

		// 6.BiConsumer
		BiConsumer<Integer, Integer> f6 = (x, y) -> System.out.println(x + y);
		f6.accept(5, 3);

	}

}

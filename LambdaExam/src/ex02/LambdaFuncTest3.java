package ex02;

import java.util.function.BiFunction;
import java.util.function.Supplier;

class Person {
	String name;
	Integer age;
	
	Person() {}

	Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}

public class LambdaFuncTest3 {

	public static void main(String[] args) {
		
		Supplier<Person> np1 = Person::new; // 파라미터가 없고 리턴타입이 있음 : Supplier<T> : T
		Person p = np1.get(); // () -> new Person 과 같다 Person p = np1.get();
		
		BiFunction<String, Integer, Person> np2 = Person::new;  //BiFunction<T, U, R> : R apply(T t, U u)
		//(name,age) -> new Person(name, age)
		Person p2 = np2.apply("홍길동", 20);
		System.out.println(p2);
	}

}

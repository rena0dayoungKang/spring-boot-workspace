package ex00;

import java.util.Arrays;

class Person {
	Integer age;
	String name;

	public Person(Integer age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}

}

interface Compare {
	int toCompare(Person p1, Person p2);
}

public class BubbleSortEx {

	//1.
	static void bubbleSort(Person[] pers, Compare compare) {
		for (int i = 0; i < pers.length - 1; i++) {
			for (int j = 0; j < pers.length - i - 1; j++) {
				if (compare.toCompare(pers[j], pers[j + 1]) > 0) {
					Person p = pers[j];
					pers[j] = pers[j + 1];
					pers[j + 1] = p;
				}
			}
		}
	}

	public static void main(String[] args) {
		Person[] pers = { new Person(10, "hong"), new Person(15, "song"), new Person(11, "gong") };
		// bubbleSort(pers);
		
		//나이순 sort
		bubbleSort(pers, new Compare() {
			@Override
			public int toCompare(Person p1, Person p2) {
				return p1.age - p2.age;
			}
		});
		System.out.println("나이 오름차순 :" +Arrays.asList(pers));

		//이름순 sort
		bubbleSort(pers, new Compare() {
			@Override
			public int toCompare(Person p1, Person p2) {
				return p1.name.compareTo(p2.name);
			}
		});
		System.out.println("이름 오름차순 :" +Arrays.asList(pers));
		
		
		//나이순 sort를 람다식으로 바꾼다면
		Compare compare = (p1, p2) -> p1.age - p2.age;
		bubbleSort(pers, compare);
		System.out.println("나이 오름차순 람다 :" +Arrays.asList(pers));
		
		//이름순 sort를 람다식으로 바꾼다면
		Compare compare2 = (p1, p2) -> p1.name.compareTo(p2.name);
		bubbleSort(pers, compare2);
		System.out.println("이름 오름차순 람다 :" +Arrays.asList(pers));
		
		
		Compare compare3 = (p1, p2) -> p2.age - p1.age;
		bubbleSort(pers, compare3);
		System.out.println("나이 내림차순 람다 :" +Arrays.asList(pers));
	}

}

package ex02;

import java.util.stream.Stream;

//자바의 스트림
class Student implements Comparable<Student> {
	String name;
	int ban;
	int totalScore;

	public Student(String name, int ban, int totalScore) {
		super();
		this.name = name;
		this.ban = ban;
		this.totalScore = totalScore;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", ban=" + ban + ", totalScore=" + totalScore + "]";
	}

	public String getName() {
		return name;
	}

	public int getBan() {
		return ban;
	}

	public int getTotalScore() {
		return totalScore;
	}

	@Override//기본정렬
	public int compareTo(Student o) { 
		return o.totalScore - totalScore;  //내림차순 정렬
	}

	public Student() {
		super();
	}
	

}

public class LambdaFuncTest4 {
	public static void main(String[] args) {
		
		Stream<Student> studStream = Stream.of(new Student("이자바", 3, 300),
											   new Student("김자바", 1, 200),
											   new Student("안자바", 2, 100),
											   new Student("박자바", 2, 150),
											   new Student("소자바", 1, 200),
											   new Student("나자바", 3, 290),
											   new Student("감자바", 3, 180));
		
		//기본정렬
//		studStream.sorted().forEach(System.out::println); //총점별 기본정렬
		
		//정렬
//		studStream.sorted(Comparator.comparing(Student::getBan)  //반으로 정렬
//				  .thenComparing(Comparator.naturalOrder()))	//반이 같을 경우, student가 가진 기본 정렬
//				  .forEach(System.out::println); 
//				//sorted() 괄호안의 의미 : 반으로 정렬 후 반이 같을 경우, student가 가진 기본 정렬(compareTo)을 활용하라
		
		
		//역순 기본정렬
//		studStream.sorted(Comparator.reverseOrder()).forEach(System.out::println);
		
		//역순정렬
//		studStream.sorted(Comparator.comparing(Student::getBan)  //반으로 정렬
//				  .thenComparing(Comparator.reverseOrder()))	//반이 같을 경우, 역순 기본 정렬
//				  .forEach(System.out::println); 
		
		
		//3반만 필터링 해서 이름만 출력
		studStream.filter(i -> i.getBan() == 3).map(Student::getName).forEach(System.out::println);
		
		
	}

}

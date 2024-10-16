package ex02;

import java.util.Optional;

public class StreamTest1 {

	public static void main(String[] args) throws Exception{

		Optional<Student> os = Optional.of(new Student("이자바", 3, 300));
		Student s = os.get();
		System.out.println(s);

		Optional<Student> os1 = Optional.ofNullable(null);
		if (os1.isPresent()) {
			Student s1 = os1.get();
			System.out.println(s1);
		}
		
		// orElseGet() 사용해보기 
//		Optional<Student> os2 = Optional.of(new Student("이자바", 3, 300));
		Optional<Student> os2 = Optional.ofNullable(null);
		Student s2 = os2.orElseGet(Student::new);
		System.out.println(s2);
		
		
		// orElseThrow() 사용해보기 
		Optional<Student> os3 = Optional.of(new Student("이자바", 3, 300));
//		if (os3.isPresent()) {
//			Student s3 = os3.get();
//			System.out.println(s3);
//		} else {
//			new Exception("데이터 없음");
//		}
		Student s3 = os3.orElseThrow(() -> new Exception("데이터 없음"));
		System.out.println(s3);
	}

}

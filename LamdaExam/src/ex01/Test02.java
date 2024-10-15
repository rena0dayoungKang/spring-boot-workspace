package ex01;


@FunctionalInterface
interface KFunction {
	void run();
}

public class Test02 {
	static void execute(KFunction f) {
		f.run();
	}
	
	static KFunction getKFunction() {
		return () -> System.out.println("f.run");
	}

	public static void main(String[] args) {		
		
		//방법1. 람다식을 지역변수 선언
		KFunction kf1 = () -> System.out.println("f1.run");
		kf1.run();
		
		//방법2. 파라미터로 넘기는 경우
		execute(() -> System.out.println("f2.run"));
		
		
		//방법3. 함수의 리턴
		KFunction kf3 = getKFunction();
		kf3.run();
	}

}

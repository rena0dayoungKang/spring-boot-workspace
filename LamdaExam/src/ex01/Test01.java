package ex01;

@FunctionalInterface // 오직 하나의 추상 메서드만 정의하라는 강제
interface MyFunction1 {
	int max(int x, int y);
	// int min(int x, int y);
}

interface MyFunction2 {
	void myMethod();
}

public class Test01 {

	public static void main(String[] args) {
		// 일반적 방식
		MyFunction1 mf1 = new MyFunction1() {
			@Override
			public int max(int x, int y) {
				return x > y ? x : y;
			}
		};
		System.out.println(mf1.max(3, 4));

		// lamda 방식
		MyFunction1 mf2 = (x, y) -> x > y ? x : y;
		System.out.println(mf2.max(3, 4));

		// lamda 방식2
		MyFunction2 mfm = () -> System.out.println("lamda test");
		mfm.myMethod();

	}

}

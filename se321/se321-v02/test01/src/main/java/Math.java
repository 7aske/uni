import java.util.Arrays;

public class Math {
	public static int add(int... nums) {
		return Arrays.stream(nums)
				.reduce(0, Integer::sum);
	}

	public static boolean even(int num) {
		return num % 2 == 0;
	}
}

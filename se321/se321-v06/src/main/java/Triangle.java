import java.util.Arrays;

public class Triangle {
	public static boolean isValid(int a, int b, int c) {
		int[] arr = new int[]{a, b, c};
		if (Arrays.stream(arr).anyMatch(n -> n <= 0))
			throw new IllegalArgumentException("Length cannot be <=0");
		Arrays.sort(arr);
		return (arr[0] + arr[1] > arr[2]);
	}
}

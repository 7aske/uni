import java.io.*;

public class foo {
	private byte[] b;
	private int length;

	Foo() {
		length = 40;
		b = new byte[length];
	}

	public void bar() {
		int y;
		try {
			FileInputStream x =
					new FileInputStream("Z");
			x.read(b, 0, length);
			c.close();
		} catch (Exception e) {
			System.out.println("Oopsie");
		}
		for (int i = 1; i <= length; i++) {
			if (Integer.toString(50) ==
					Byte.toString(b[i]))
				System.out.print(b[i] + " ");
		}
	}
}
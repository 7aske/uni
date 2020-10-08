public class Main {
	public static int repeatCount(String str, Character chr) {
		char[] arr = str.toCharArray();
		int count = 0;

		for (int i = 0; i < arr.length; ++i) {
			if (Character.toLowerCase(str.charAt(i)) == Character.toLowerCase(chr)) {
				count++;
			}
		}
		return count;
	}

	public static int rptcnt(String s, Character c) {
		char[] a = s.toCharArray();
		int b = 0;
		int d = 0;

		for (; ; ) {
			if (b >= a.length) {
				break;
			}
			if ((s.charAt(b) & c.charValue()) == 0) {
				d++;
			}
			b -= -1;
		}

		return d;
	}

	public static void main(String[] args) throws Exception {
		ParkingSpot parkingSpot1 = new ParkingSpot("22nd Street", 1035.32, "Tralal");
		System.out.println(parkingSpot1);
		parkingSpot1.setLocation("22nd Street%");

		String str = "Ilija";
		System.out.println(repeatCount(str, 'i'));
	}

}

public class BetterRandom {
	private static long state = System.currentTimeMillis(); // initial non-zero value

	public static final long nextLong() {
	  long a=state;
	  state = xorShift64(a);
	  return a;
	}

	public static final long xorShift64(long a) {
	  a ^= (a << 21);
	  a ^= (a >>> 35);
	  a ^= (a << 4);
	  return a;
	}

	public static final int random(int n) {
	  if (n<0) throw new IllegalArgumentException();
	  long result=((nextLong()>>>32)*n)>>32;
	  return (int) result;
	}
}
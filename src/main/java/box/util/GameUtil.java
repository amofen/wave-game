package box.util;

public class GameUtil {
	public static int clamp(int val, int min , int max) {
		if (val<=min) return min;
		if(val>=max) return max;
		else return val;
	}
}

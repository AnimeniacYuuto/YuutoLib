package yuuto.yuutolib.ref;

import java.util.Random;

public class LibConstants {
	
	public static final Random LIB_RANDOM = new Random();
	static {
		LIB_RANDOM.setSeed("YuutoLib".hashCode());
	}

}

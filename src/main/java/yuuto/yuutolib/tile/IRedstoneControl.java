package yuuto.yuutolib.tile;

public interface IRedstoneControl extends IRedstoneCache{
	public enum ControlMode{
		PULSING, LOW, HIGH, ALWAYS, NEVER;
		
		public boolean isPulsing(){
			return this == PULSING;
		}
		public boolean isLow(){
			return this == LOW;
		}
		public boolean isHigh(){
			return this == HIGH;
		}
		public boolean isAlways(){
			return this == ALWAYS;
		}
		public boolean isNever(){
			return this == NEVER;
		}
		public static ControlMode stepForward(ControlMode mode){
			int i=mode.ordinal();
			i+=1;
			if(i >= values().length)
				i = 0;
			return values()[i];
		}
		public static ControlMode stepBackward(ControlMode mode){
			int i=mode.ordinal();
			i-=1;
			if(i < 0)
				i = values().length-1;
			return values()[i];
		}
	}
	
	void setControl(ControlMode control);
	ControlMode getControl();

}

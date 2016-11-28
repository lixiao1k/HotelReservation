package vo;

import info.Room;

public class RoomInfoVO {
	private Room sourceType;
	private Room targetType;
	private int num;
	public Room getSourceType(){
		return sourceType;
	}
	public Room getTargetType(){
		return targetType;
	}
	public int getNum(){
		return num;
	}
	public void setNum(int num){
		this.num = num;
	}
	public void setTargetType(Room targetType){
		this.targetType = targetType;
	}
	public void setSourceType(Room sourceType){
		this.sourceType = sourceType;
	}
}

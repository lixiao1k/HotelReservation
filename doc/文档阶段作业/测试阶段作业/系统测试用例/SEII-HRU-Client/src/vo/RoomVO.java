package vo;

import java.util.List;

public class RoomVO {
	List<RoomInfo> info;
	public RoomVO(List<RoomInfo> info){
		this.info = info;
	}
	public List<RoomInfo> getAllRooms(){
		return this.info;
	}
	
}

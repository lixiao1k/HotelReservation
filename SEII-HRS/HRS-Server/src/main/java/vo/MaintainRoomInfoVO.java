package vo;

import java.util.Set;

public class MaintainRoomInfoVO {
	private long hotelId;
	private Set<RoomInfoVO> changeInfo;
	public long getHotelId(){
		return hotelId;
	}
	public Set<RoomInfoVO> getChangeInfo(){
		return changeInfo;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setChangeInfo(Set<RoomInfoVO> changeInfo){
		this.changeInfo = changeInfo;
	}
}

package vo;

import java.io.Serializable;
import java.util.Set;

public class MaintainRoomInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 226496798242764559L;
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

package businesslogic.HotelBL;

import java.util.List;

import vo.HotelVO;

public class HotelList {
	private List<HotelVO> list;
	public HotelList(List<HotelVO> list){
		this.list = list;
	}
	public void setHotelList(List<HotelVO> list){
		this.list = list;
	}
	public List<HotelVO> getHotelList(){
		return this.list;
	}
}

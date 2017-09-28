package businesslogic.HotelBL;

import businesslogicservice.HotelBLService.HotelBLService;
import businesslogicservice.HotelBLService.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public class HotelBLService_Stub implements HotelBLService{

	@Override
	public HotelVO GetHotelInfo(long hotelid) {
		// TODO Auto-generated method stub
		//获得某酒店的信息，这里涉及到数据库查找，用新建一个hotelvo以略过
		return new HotelVO();
	}

	@Override
	public HotelResultMessage SetHotelInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		//这里涉及到数据库存储，默认执行到就算成功
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage SetRoomInfo(long hotelid, RoomVO vo) {
		// TODO Auto-generated method stub
		//这里涉及到数据库存储，默认执行到就算成功
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage AddHotel(HotelVO vo) {
		// TODO Auto-generated method stub
		//这里涉及到数据库存储，默认执行到就算成功
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage AddRoom(RoomVO vo) {
		// TODO Auto-generated method stub
		//这里涉及到数据库存储，默认执行到就算成功
		return HotelResultMessage.SUCCESS;
	}

}

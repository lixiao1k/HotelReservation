package logic.service.impl.hotel;

import java.rmi.RemoteException;

import info.BusinessCity;
import info.ListWrapper;
import logic.service.HotelLogicService;
import resultmessage.HotelResultMessage;
import vo.AddHotelResultVO;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.RoomVO;
import vo.SearchHotelVO;
import vo.AddHotelVO;

public class HotelLogicServiceImpl implements HotelLogicService{
	private HotelDO hotelDO;
	@Override
	public ListWrapper<BusinessCity> getCity() throws RemoteException {
		return hotelDO.getCity();
	}

	@Override
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtraHotelVO getExtraHotelDetail(long hotelId, long userId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListWrapper<Long> getBookHotel(long userId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelResultMessage roomCheckIn(CheckInRoomInfoVO vo) throws RemoteException {
		
		return null;
	}

	@Override
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public RoomVO getRoomInfo(long hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelResultMessage setHotelInfo(MaintainHotelInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelResultMessage setRoomInfo(MaintainRoomInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddHotelResultVO addHotel(AddHotelVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
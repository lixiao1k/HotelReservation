package logic.service.impl.hotel;

import java.rmi.RemoteException;

import info.BusinessCity;
import info.ListWrapper;
import logic.service.HotelLogicService;
import logic.service.impl.order.OrderDO;
import resultmessage.HotelResultMessage;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.RoomVO;
import vo.RuleVO;
import vo.SearchHotelVO;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelVO getHotelInfo(long hotelId) throws RemoteException {
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
	public HotelResultMessage addHotel(HotelVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelResultMessage deleteHotel(HotelVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListWrapper<HotelVO> getHotelList(RuleVO vo, int size) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
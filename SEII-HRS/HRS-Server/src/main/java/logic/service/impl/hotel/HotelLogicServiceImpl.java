package logic.service.impl.hotel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import info.BusinessCity;
import info.ListWrapper;
import logic.service.HotelLogicService;
import resultmessage.HotelResultMessage;
import vo.AddHotelResultVO;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelItemVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.SearchHotelVO;
import vo.AddHotelVO;

public class HotelLogicServiceImpl extends UnicastRemoteObject implements HotelLogicService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3582538173761709513L;
	private HotelDO hotelDO;
	public HotelLogicServiceImpl() throws RemoteException {
		hotelDO = new HotelDO();
	}
	@Override
	public ListWrapper<BusinessCity> getCity() throws RemoteException {
		return hotelDO.getCity();
	}

	@Override
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo) throws RemoteException {
		return hotelDO.getHotels(vo);
	}

	@Override
	public ExtraHotelVO getExtraHotelDetail(long hotelId, long userId) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDO.getExtraHotelDetail(hotelId, userId);
	}

	@Override
	public ListWrapper<Long> getBookHotel(long userId) throws RemoteException {
		return hotelDO.getBookHotel(userId);
	}

	@Override
	public HotelResultMessage roomCheckIn(CheckInRoomInfoVO vo) throws RemoteException {
		
		return hotelDO.roomCheckIn(vo);
	}

	@Override
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDO.roomCheckOut(vo);
	}



	@Override
	public ListWrapper<HotelItemVO> getRoomInfo(long hotelId) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDO.getRoomInfo(hotelId);
	}

	@Override
	public HotelResultMessage setHotelInfo(MaintainHotelInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDO.setHotelInfo(vo);
	}

	@Override
	public HotelResultMessage setRoomInfo(MaintainRoomInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDO.setRoomInfo(vo);
	}

	@Override
	public AddHotelResultVO addHotel(AddHotelVO vo) throws RemoteException {
		// TODO Auto-generated method stub
		return hotelDO.addHotel(vo);
	}


}
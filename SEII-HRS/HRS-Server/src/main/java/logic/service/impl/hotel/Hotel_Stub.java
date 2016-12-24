package logic.service.impl.hotel;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.Room;
import logic.service.HotelLogicService;
import resultmessage.HotelResultMessage;
import vo.AddHotelResultVO;
import vo.AddHotelVO;
import vo.AddRoomVO;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelItemVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.SearchHotelVO;

public class Hotel_Stub implements HotelLogicService{

	@Override
	public ListWrapper<BusinessCity> getCity() throws RemoteException {
		BusinessCity bc = new BusinessCity();
		bc.setBcityId(1);
		bc.setName("±³¾°");
		BusinessCircle bcir = new BusinessCircle();
		bcir.setBcircleId(1);
		bcir.setBcity(bc);
		bcir.setName("ÐÄ½á");
		Set<BusinessCircle> bcirs = new HashSet<>();
		bcirs.add(bcir);
		bc.setCircles(bcirs);
		ListWrapper<BusinessCity> result = new ListWrapper<>();
		result.add(bc);
		System.out.println("Hotel.getCity  :  return normal result");
		return result;
	}

	@Override
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo) throws RemoteException {
		BasicHotelVO bhvo = new BasicHotelVO();
		ListWrapper<BasicHotelVO> result = new ListWrapper<>();
		result.add(bhvo);
		System.out.println("Hotel.getHotels  :  return normal result");
		return result;
	}

	@Override
	public ExtraHotelVO getExtraHotelDetail(long hotelId, long userId) throws RemoteException {
		System.out.println("Hotel.getExtraHotelDetail  :  return normal result");
		return new ExtraHotelVO();
	}

	@Override
	public ListWrapper<Long> getBookHotel(long userId) throws RemoteException {
		ListWrapper<Long> result = new ListWrapper<>();
		result.add((long)1);
		result.add((long)2);
		System.out.println("Hotel.getBookHotel  :  return normal result");
		return result;
	}

	@Override
	public HotelResultMessage roomCheckIn(CheckInRoomInfoVO vo) throws RemoteException {
		Date d = new Date();
		if(vo.getCheckInTime().getDate()>=d.getDate()){
			System.out.println("Hotel.roomCheckIn  :  vo.getCheckInTime().getDate()>=d.getDate()|return HotelResultMessage.FAIL_WRONGINFO");
			return HotelResultMessage.FAIL_WRONGINFO;
		}
		if(vo.getHotelId()==1){
			System.out.println("Hotel.roomCheckIn  :  vo.getHotelId()==1|return HotelResultMessage.FAIL_WRONGID");
			return HotelResultMessage.FAIL_WRONGID;
		}
		System.out.println("Hotel.roomCheckIn  :  normal info|return HotelResultMessage.SUCCESS");
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException {
		Date d = new Date();
		if(vo.getActualCheckOutTime().getDate()>=d.getDate()){
			System.out.println("Hotel.roomCheckOut  :  vo.getActualCheckOutTime().getDate()>=d.getDate()|return HotelResultMessage.FAIL_WRONGINFO");
			return HotelResultMessage.FAIL_WRONGINFO;
		}
		if(vo.getOrderId()==1){
			System.out.println("Hotel.roomCheckOut  :  vo.getOrderId()==1|return HotelResultMessage.FAIL_WRONGID");
			return HotelResultMessage.FAIL_WRONGID;
		}
		System.out.println("Hotel.roomCheckOut  :  normal info|return HotelResultMessage.SUCCESS");
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public ListWrapper<HotelItemVO> getRoomInfo(long hotelId) throws RemoteException {
		HotelItemVO hivo1 = new HotelItemVO();
		HotelItemVO hivo2 = new HotelItemVO();		
		ListWrapper<HotelItemVO> result = new ListWrapper<>();
		result.add(hivo1);
		result.add(hivo2);	
		System.out.println("Hotel.getRoomInfo  :  return normal result");
		return result;
	}

	@Override
	public HotelResultMessage setHotelInfo(MaintainHotelInfoVO vo) throws RemoteException {
		if(vo.getHotelId()==1){
			System.out.println("Hotel.setHotelInfo  :  vo.getHotelId()==1|return HotelResultMessage.FAIL_WRONGID");
			return HotelResultMessage.FAIL_WRONGID;
		}
		if(vo.getCircle()==null){
			System.out.println("Hotel.setHotelInfo  :  vo.getCircle()==null|return HotelResultMessage.FAIL_WRONGID");
			return HotelResultMessage.FAIL_WRONGINFO;
		}
		System.out.println("Hotel.setHotelInfo  :  normal info|return HotelResultMessage.SUCCESS");
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage setRoomInfo(MaintainRoomInfoVO vo) throws RemoteException {
		if(vo.getHotelId()==1){
			System.out.println("Hotel.setRoomInfo  :  vo.getHotelId()==1|return HotelResultMessage.FAIL_WRONGID");
			return HotelResultMessage.FAIL_WRONGID;
		}
		if(vo.getChangeInfo()==null||vo.getChangeInfo().size()==0){
			System.out.println("Hotel.setRoomInfo  :  vo.getChangeInfo()==null||vo.getChangeInfo().size()==0|return HotelResultMessage.FAIL_WRONGINFO");
			return HotelResultMessage.FAIL_WRONGINFO;
		}
		System.out.println("Hotel.setRoomInfo  :  normal info|return HotelResultMessage.SUCCESS");
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public AddHotelResultVO addHotel(AddHotelVO vo) throws RemoteException {
		AddHotelResultVO vo2 = new AddHotelResultVO();
		
		if(vo.getUsername().equals("1234")){
			System.out.println("Hotel.addHotel  :  vo.getUsername().equals(\"1234\")|return HotelResultMessage.FAIL_WRONGINFO");
			vo2.setHotelResultMessage(HotelResultMessage.FAIL_WRONGINFO);
			return vo2;
		}
		vo2.setHotelId(3);
		vo2.setHotelWorker(new HotelWorkerVO("123", "123456"));
		vo2.setHotelResultMessage(HotelResultMessage.SUCCESS);
		System.out.println("Hotel.addHotel  : normal info|return HotelResultMessage.SUCCESS");
		return vo2;
	}

	@Override
	public HotelVO getHotelInfo(long hotelId) throws RemoteException {
		if(hotelId==1){
			System.out.println("Hotel.getHotelInfo  :  hotelId=1|return null");
			return null;
		}
		System.out.println("Hotel.getHotelInfo  :  normal info|return normal result");
		return new HotelVO();
	}

	@Override
	public HotelResultMessage addNewRoom(AddRoomVO vo) throws RemoteException {
		if(vo.getHotelId()==1){
			System.out.println("Hotel.addNewRoom  :  vo.getHotelId()==1|return HotelResultMessage.FAIL_WRONGID");
			return HotelResultMessage.FAIL_WRONGID;
		}
		if(vo.getNum()<=0){
			System.out.println("Hotel.addNewRoom  :  vo.getNum()<=0|return HotelResultMessage.FAIL_WRONGINFO");
			return HotelResultMessage.FAIL_WRONGINFO;
		}
		System.out.println("Hotel.addNewRoom  :  normal info|return HotelResultMessage.SUCCESS");
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public ListWrapper<Room> getRoomTypes() throws RemoteException {
		return null;
	}

}

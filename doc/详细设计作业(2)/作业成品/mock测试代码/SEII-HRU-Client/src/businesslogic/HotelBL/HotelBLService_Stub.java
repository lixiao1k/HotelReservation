package businesslogic.HotelBL;

import businesslogicservice.HotelBLService.HotelBLService;
import businesslogicservice.HotelBLService.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public class HotelBLService_Stub implements HotelBLService{

	@Override
	public HotelVO GetHotelInfo(long hotelid) {
		// TODO Auto-generated method stub
		//���ĳ�Ƶ����Ϣ�������漰�����ݿ���ң����½�һ��hotelvo���Թ�
		return new HotelVO();
	}

	@Override
	public HotelResultMessage SetHotelInfo(HotelVO vo) {
		// TODO Auto-generated method stub
		//�����漰�����ݿ�洢��Ĭ��ִ�е�����ɹ�
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage SetRoomInfo(long hotelid, RoomVO vo) {
		// TODO Auto-generated method stub
		//�����漰�����ݿ�洢��Ĭ��ִ�е�����ɹ�
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage AddHotel(HotelVO vo) {
		// TODO Auto-generated method stub
		//�����漰�����ݿ�洢��Ĭ��ִ�е�����ɹ�
		return HotelResultMessage.SUCCESS;
	}

	@Override
	public HotelResultMessage AddRoom(RoomVO vo) {
		// TODO Auto-generated method stub
		//�����漰�����ݿ�洢��Ĭ��ִ�е�����ɹ�
		return HotelResultMessage.SUCCESS;
	}

}

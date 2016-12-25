package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import info.BusinessCity;
import info.ListWrapper;
import info.Room;
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
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.SearchHotelVO;

public interface HotelLogicService extends Remote{
	/**
	 * ��ȡ���еĳ���
	 * �÷����ڿͻ����оƵ�����ʱ����Ҫѡ����к���Ȧ���ʽ��е���
	 * @return ListWrapper<BusinessCity>
	 * ���еĳ��У�������Щ��������������Ȧ
	 * @throws RemoteException
	 */
	public ListWrapper<BusinessCity> getCity() throws RemoteException;
	/**
	 * �ͻ������Ƶ�ʱ��������Ϣ�����ô˷���
	 * @param vo
	 * �����Ƶ�ĳ�����Ȧ����סʱ����˷�ʱ��
	 * @return ListWrapper<BasicHotelVO>
	 * ��ʡ�������ػ����ľƵ���Ϣ���ľƵ��б�
	 * @throws RemoteException
	 */
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo) throws RemoteException;
	/**
	 * ��ȡ�Ƶ�Ķ�����Ϣ����ϸ��Ϣ��
	 * �÷����ڿͻ�����鿴ĳ�Ƶ����ϸ��Ϣʱ����
	 * @param hotelId
	 * ��ȡ�Ƶ��id
	 * @param userId
	 * ��ȡ��Ϣ���û�id
	 * @return
	 * �þƵ�͸��û���������ؾƵ����ϸ��Ϣ
	 * @throws RemoteException
	 */
	public ExtraHotelVO getExtraHotelDetail(long hotelId,long userId) throws RemoteException;
	/**
	 * ��ȡ�û�Ԥ�����ľƵ�id
	 * �÷������û��������Ƶ�ʱ���ã������û���һ�����оƵ궼�����Ϊ��ʡ�����ȴ�������useridԤ���ľƵ�
	 * @param userId
	 * ���û�id
	 * @return ListWrapper<Long>
	 * �û�Ԥ���ľƵ��id
	 * @throws RemoteException
	 */
	public ListWrapper<Long> getBookHotel(long userId) throws RemoteException;
	/**
	 * �ھƵ����µ�������ס�Ŀͷ��䶯ʱ���ã�Ĭ��������ס��סһ��
	 * @param vo
	 * ��ס�ľƵ�id�����䣬��������Ϣ
	 * @return HotelResultMessage
	 * ������ס����¿ͷ��Ľ��
	 * @throws RemoteException
	 */
	public HotelResultMessage roomCheckIn(CheckInRoomInfoVO vo) throws RemoteException;
	/**
	 * ���϶�����ǰ�˷�ʱ�Ƶ깤����Ա�����õķ���
	 * @param vo
	 * ��ס�ľƵ�id�����䣬��������Ϣ
	 * @return HotelResultMessage
	 * �����˷����³ɹ�����Ӧ��Ϣ
	 * @throws RemoteException
	 */
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException;
	/**
	 * ��ȡ�Ƶ�ķ�����Ϣ
	 * �÷����ھƵ깤����Ա����ͷ���ʱ�����
	 * @param hotelId
	 * ��ȡ����ľƵ�id
	 * @return ListWrapper<HotelItemVO> 
	 * ����ĸþƵ�ķ���
	 * @throws RemoteException
	 */
	public ListWrapper<HotelItemVO> getRoomInfo(long hotelId) throws RemoteException;
	/**
	 * �Ƶ깤����Աά������Ƶ���Ϣ��
	 * @param vo
	 * �µľƵ���Ϣ
	 * @return HotelResultMessage
	 * ���½��
	 * @throws RemoteException
	 */
	public HotelResultMessage setHotelInfo(MaintainHotelInfoVO vo) throws RemoteException;
	/**
	 * �Ƶ깤����Ա���·�����Ϣ�����õķ���
	 * ������·�����ϢĬ��Ϊ�����з���ת��Ϊ�·���
	 * ����ΪĬ������¾Ƶ���ܷ������ھƵ깤���乤��ȷ�������·�����߷���䶯ֻ������������໥ת��
	 * @param vo
	 * ����ת���������Ϣ
	 * @return HotelResultMessage
	 * ���·�����Ϣ����Ӧ���
	 * @throws RemoteException
	 */
	public HotelResultMessage setRoomInfo(MaintainRoomInfoVO vo) throws RemoteException;
	/**
	 * ��վ������Ա��ӾƵ�ʱ�����õķ���
	 * @param vo
	 * �����Ƶ�������Ϣ��������ʼ�ķ���
	 * @return AddHotelResultVO
	 * ������Ӻ�Ľ��
	 * �����Ƶ�id�;Ƶ깤����Ա���˺�
	 * @throws RemoteException
	 */
	public AddHotelResultVO addHotel(AddHotelVO vo) throws RemoteException;
	/**
	 * ��ȡ�Ƶ�Ļ�����Ϣ���÷���Ϊ�Ƶ깤����Ա����
	 * @param hotelId
	 * �Ƶ�id
	 * @return HotelVO
	 * �����ľƵ���Ϣ
	 */
	public HotelVO getHotelInfo(long hotelId) throws RemoteException;
	/**
	 * �Ƶ깤����Ա����ͷ���ӷ���ʱ�����õķ���
	 * @param vo
	 * ��ӷ������Ӧ��Ϣ 
	 * @return HotelResultMessage
	 * ��ӽ��
	 * @throws RemoteException
	 */
	public HotelResultMessage addNewRoom(AddRoomVO vo) throws RemoteException;
	/**
	 * ��ӾƵ���Ҫ��ӳ�ʼ�ķ�����Ϣ����վ������Ա��������ø�������
	 * @return ListWrapper<Room>
	 * ���еķ�������
	 * @throws RemoteException
	 */
	public ListWrapper<Room> getRoomTypes() throws RemoteException;
}

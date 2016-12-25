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
	 * 获取所有的城市
	 * 该方法在客户进行酒店搜索时，需要选择城市和商圈，故进行调用
	 * @return ListWrapper<BusinessCity>
	 * 所有的城市，包括这些城市所关联的商圈
	 * @throws RemoteException
	 */
	public ListWrapper<BusinessCity> getCity() throws RemoteException;
	/**
	 * 客户搜索酒店时的搜索信息，调用此方法
	 * @param vo
	 * 搜索酒店的城市商圈，入住时间和退房时间
	 * @return ListWrapper<BasicHotelVO>
	 * 节省带宽，返回基本的酒店信息，的酒店列表
	 * @throws RemoteException
	 */
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo) throws RemoteException;
	/**
	 * 获取酒店的额外信息（详细信息）
	 * 该方法在客户点击查看某酒店的详细信息时调用
	 * @param hotelId
	 * 获取酒店的id
	 * @param userId
	 * 获取信息的用户id
	 * @return
	 * 该酒店和该用户关联的相关酒店的详细信息
	 * @throws RemoteException
	 */
	public ExtraHotelVO getExtraHotelDetail(long hotelId,long userId) throws RemoteException;
	/**
	 * 获取用户预订过的酒店id
	 * 该方法在用户在搜索酒店时调用，由于用户不一定所有酒店都点击，为节省带宽，先传输所有userid预订的酒店
	 * @param userId
	 * 该用户id
	 * @return ListWrapper<Long>
	 * 用户预订的酒店的id
	 * @throws RemoteException
	 */
	public ListWrapper<Long> getBookHotel(long userId) throws RemoteException;
	/**
	 * 在酒店有新的线下入住的客房变动时调用，默认线下入住入住一天
	 * @param vo
	 * 入住的酒店id，房间，数量等信息
	 * @return HotelResultMessage
	 * 线下入住后更新客房的结果
	 * @throws RemoteException
	 */
	public HotelResultMessage roomCheckIn(CheckInRoomInfoVO vo) throws RemoteException;
	/**
	 * 线上订单提前退房时酒店工作人员所调用的方法
	 * @param vo
	 * 入住的酒店id，房间，数量等信息
	 * @return HotelResultMessage
	 * 线上退房更新成功的相应信息
	 * @throws RemoteException
	 */
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException;
	/**
	 * 获取酒店的房间信息
	 * 该方法在酒店工作人员管理客房的时候调用
	 * @param hotelId
	 * 获取房间的酒店id
	 * @return ListWrapper<HotelItemVO> 
	 * 当天的该酒店的房间
	 * @throws RemoteException
	 */
	public ListWrapper<HotelItemVO> getRoomInfo(long hotelId) throws RemoteException;
	/**
	 * 酒店工作人员维护自身酒店信息的
	 * @param vo
	 * 新的酒店信息
	 * @return HotelResultMessage
	 * 更新结果
	 * @throws RemoteException
	 */
	public HotelResultMessage setHotelInfo(MaintainHotelInfoVO vo) throws RemoteException;
	/**
	 * 酒店工作人员更新房间信息所调用的方法
	 * 这里更新房间信息默认为将现有房间转换为新房间
	 * （因为默认情况下酒店的总房间数在酒店工程落工已确定）有新房间或者房间变动只是两个房间的相互转化
	 * @param vo
	 * 房间转换的相关信息
	 * @return HotelResultMessage
	 * 更新房间信息的相应结果
	 * @throws RemoteException
	 */
	public HotelResultMessage setRoomInfo(MaintainRoomInfoVO vo) throws RemoteException;
	/**
	 * 网站管理人员添加酒店时所调用的方法
	 * @param vo
	 * 新增酒店的相关信息，包括初始的房间
	 * @return AddHotelResultVO
	 * 返回添加后的结果
	 * 包括酒店id和酒店工作人员的账号
	 * @throws RemoteException
	 */
	public AddHotelResultVO addHotel(AddHotelVO vo) throws RemoteException;
	/**
	 * 获取酒店的基本信息，该方法为酒店工作人员调用
	 * @param hotelId
	 * 酒店id
	 * @return HotelVO
	 * 基本的酒店信息
	 */
	public HotelVO getHotelInfo(long hotelId) throws RemoteException;
	/**
	 * 酒店工作人员管理客房添加房间时所调用的方法
	 * @param vo
	 * 添加房间的相应信息 
	 * @return HotelResultMessage
	 * 添加结果
	 * @throws RemoteException
	 */
	public HotelResultMessage addNewRoom(AddRoomVO vo) throws RemoteException;
	/**
	 * 添加酒店需要添加初始的房间信息，网站管理人员从这里调用更加类型
	 * @return ListWrapper<Room>
	 * 所有的房间类型
	 * @throws RemoteException
	 */
	public ListWrapper<Room> getRoomTypes() throws RemoteException;
}

package logic.service.impl.hotel;

import java.rmi.RemoteException;

import javax.management.RuntimeErrorException;

import org.hibernate.engine.HibernateIterator;

import data.dao.HotelDao;
import info.Cache;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import po.HotelPO;
import resultmessage.HotelResultMessage;
import util.HibernateUtil;
import vo.HotelVO;
import vo.RoomVO;
import vo.RuleVO;

public class HotelDO {
	private HotelDao hotelDao;
	private Cache<HotelPO> hotels;
	public HotelVO getHotelInfo(long hotelId) throws RemoteException{
		HotelPO po = null;
		HotelResultMessage result = null;
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			po = hotelDao.getInfo(hotelId);
			if(po==null) 
				result = HotelResultMessage.FAIL;
			else
				result = HotelResultMessage.SUCCESS;
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
		return null;
	}
	public RoomVO getRoomInfo(long hotelId) throws RemoteException{
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
		return null;
	}
	public HotelResultMessage setHotelInfo(HotelVO vo) throws RemoteException{
		return null;
	}
	public HotelResultMessage setRoomInfo(long hotelId,RoomVO vo) throws RemoteException{
		return null;
	}
	public HotelResultMessage addHotel(HotelVO vo) throws RemoteException{
		return null;
	}
	public HotelResultMessage deleteHotel(HotelVO vo) throws RemoteException{
		return null;
	}
	public ListWrapper<HotelVO> getHotelList(RuleVO vo,int size) throws RemoteException{
		return null;
	}

}

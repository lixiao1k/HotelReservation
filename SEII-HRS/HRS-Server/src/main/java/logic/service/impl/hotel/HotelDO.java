package logic.service.impl.hotel;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.hibernate.engine.HibernateIterator;

import data.dao.HotelDao;
import data.dao.OrderDao;
import info.BusinessCity;
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
	public ListWrapper<BusinessCity> getCity() throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			
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
	public HotelVO getHotelInfo(long hotelId) throws RemoteException{
		HotelPO po = null;
		HotelResultMessage result = null;
		//ËÑË÷cache
		HotelPO cachePO = null;
		cachePO = hotels.get(hotelId);
		boolean flag = false;
		if (cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				po = hotelDao.getInfo(hotelId);
				if (po!=null){
					hotels.put(hotelId, po);
					result = HotelResultMessage.SUCCESS;
				}else{
					result = HotelResultMessage.FAIL;
				}
			}catch(RuntimeException e){
				hotels.remove(hotelId);
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}else{
			result = HotelResultMessage.SUCCESS;
		}
		return null;
	}
	public RoomVO getRoomInfo(long hotelId) throws RemoteException{
		HotelPO po = null;
		Iterator<HotelItem> rit;
		Set<HotelItem> rooms = new HashSet<HotelItem>();
		HotelResultMessage result = null;
		//ËÑË÷cache
		HotelPO cachePO = null;
		cachePO = hotels.get(hotelId);
		boolean flag = false;
		if (cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				po = hotelDao.getInfo(hotelId);
				if (po!=null){
					rit = po.getRoom();
					while(rit.hasNext()){
						HotelItem hi = rit.next();
						rooms.add(hi);
					}
					hotels.put(hotelId, po);
					result = HotelResultMessage.SUCCESS;
					
				}else{
					result = HotelResultMessage.FAIL;
				}
			}catch(RuntimeException e){
				hotels.remove(hotelId);
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}else{
			result = HotelResultMessage.SUCCESS;
			rit = cachePO.getRoom();
			while(rit.hasNext()){
				HotelItem hi = rit.next();
				rooms.add(hi);
			}
		}
		return null;
	}
	public HotelResultMessage setHotelInfo(HotelVO vo) throws RemoteException{
		
		return null;
	}
	public HotelResultMessage setRoomInfo(long hotelId,RoomVO vo) throws RemoteException{
		HotelPO po = null;
		HotelResultMessage result = null;
		//ËÑË÷cache
		HotelPO cachePO = null;
		cachePO = hotels.get(hotelId);
		boolean flag = false;
		if (cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				po = hotelDao.getInfo(hotelId);
				if (po!=null){
					hotels.put(hotelId, po);
					result = HotelResultMessage.SUCCESS;
				}else{
					result = HotelResultMessage.FAIL;
				}
			}catch(RuntimeException e){
				hotels.remove(hotelId);
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}else{
			result = HotelResultMessage.SUCCESS;
		}
		return result;
	}
	public HotelResultMessage addHotel(HotelVO vo) throws RemoteException{
		return null;
	}
	public HotelResultMessage deleteHotel(long hotelId) throws RemoteException{
		HotelResultMessage result;
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			//hotelDao.delete(hotelId);
			result = HotelResultMessage.SUCCESS;
		}catch(RuntimeException e){
			result = HotelResultMessage.FAIL;
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
	//private void transform(){}
	public ListWrapper<HotelVO> getHotelList(RuleVO vo,int size) throws RemoteException{
		
		return null;
	}

}

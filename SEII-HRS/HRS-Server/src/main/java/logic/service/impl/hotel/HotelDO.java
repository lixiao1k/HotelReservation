package logic.service.impl.hotel;

import java.lang.reflect.Member;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.hibernate.engine.HibernateIterator;

import data.dao.HotelDao;
import data.dao.MemberDao;
import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import info.BusinessCity;
import info.Cache;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import po.CommentPO;
import po.HotelPO;
import po.MemberPO;
import po.OrderPO;
import resultmessage.HotelResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelCommentVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;
import vo.RuleVO;
import vo.SearchHotelVO;

public class HotelDO {
	private HotelDao hotelDao;
	private Cache<HotelPO> hotels;
	public ListWrapper<BusinessCity> getCity() throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			ListWrapper<BusinessCity> result =  hotelDao.getAllCity();
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return result;
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
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
			}catch(RuntimeException e){
				hotels.remove(hotelId);
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					result = HotelResultMessage.FAIL;
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

	public ExtraHotelVO getExtraHotelDetail(long hotelId, long userId) throws RemoteException {
		HotelPO cachePO = null;
		cachePO = hotels.get(hotelId);
		ExtraHotelVO result = null;
		boolean flag = false;
		if (cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				HotelPO po = hotelDao.getInfo(hotelId);
				if (po==null)
					return null;
				else{
					hotels.put(hotelId, po);
					Iterator<CommentPO> coit = po.getComment();
					Set<HotelCommentVO> coList = new HashSet<>();
					while(coit.hasNext()){
						CommentPO cpo = coit.next();
						HotelCommentVO hcvo = DozerMappingUtil.getInstance().map(cpo, HotelCommentVO.class);
						coList.add(hcvo);
					}
					result.setComments(coList);
					ListWrapper<OrderPO> olist = DaoManager.getInstance().getOrderDao().getHotelUserOrders(hotelId, userId);
					Iterator<OrderPO> oit = olist.iterator();
					Set<OrderVO> oList = new HashSet<>();
					while(oit.hasNext()){
						OrderPO opo = oit.next();
						OrderVO ovo = DozerMappingUtil.getInstance().map(opo, OrderVO.class);
						oList.add(ovo);
					}
					result.setBookedOrders(oList);
				}
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return result;
			}catch(RuntimeException e){
				hotels.remove(hotelId);
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return null;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}else{
			HotelPO po = cachePO;
			Iterator<CommentPO> coit = po.getComment();
			Set<HotelCommentVO> coList = new HashSet<>();
			while(coit.hasNext()){
				CommentPO cpo = coit.next();
				HotelCommentVO hcvo = DozerMappingUtil.getInstance().map(cpo, HotelCommentVO.class);
				coList.add(hcvo);
			}
			result.setComments(coList);
			Iterator<OrderPO> oit = po.getOrder();
			Set<OrderVO> oList = new HashSet<>();
			while(oit.hasNext()){
				OrderPO opo = oit.next();
				if (opo.getMember().getMid()==userId){
					OrderVO ovo = DozerMappingUtil.getInstance().map(opo, OrderVO.class);
					oList.add(ovo);
				}
			}
			result.setBookedOrders(oList);
			return result;
		}
	}

	public ListWrapper<Long> getBookHotel(long userId) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			MemberPO member = DaoManager.getInstance().getMemberDao().getInfo(userId);
			Set<Long> result = new HashSet<>();
			if (member!=null){
				Iterator<OrderPO> oit = member.getOrder();
				while(oit.hasNext()){
					OrderPO opo = oit.next();
					result.add(opo.getHotel().getHid());
				}
			}
			else
				return null;
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return new ListWrapper<Long>(result);
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return null;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}

	public HotelResultMessage roomCheckIn(CheckInRoomInfoVO vo) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			ListWrapper<HotelItem> rooms = hotelDao.getHotelItemByRoom(vo.getHotelId(), vo.getRoom());
			Iterator<HotelItem> it = rooms.iterator();
			while(it.hasNext()){
				HotelItem hi = it.next();
				if(vo.getCheckInTime().before(hi.getDate())&&vo.getCheckInTime().after(hi.getDate())){
					int num = hi.getNum();
					num-=vo.getRoomNum();
					hi.setNum(num);
					//hotelDao.
				}
			}
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
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

	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException {
		// TODO Auto-generated method stub
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
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo){
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			return null;
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
	}
}

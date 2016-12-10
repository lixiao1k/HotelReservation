package logic.service.impl.hotel;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.management.RuntimeErrorException;

import org.hibernate.Hibernate;

import data.dao.HotelDao;
import data.dao.Impl.DaoManager;
import info.BusinessCity;
import info.Cache;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import info.UserStatus;
import info.UserType;
import po.ClientMemberPO;
import po.CommentPO;
import po.HotelPO;
import po.HotelWorkerPO;
import po.MemberPO;
import po.OrderPO;
import po.UserPO;
import resultmessage.HotelResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import util.SerializeUtil;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelCommentVO;
import vo.HotelItemVO;
import vo.HotelVO;
import vo.HotelWorkerVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.OrderVO;
import vo.RoomInfoVO;
import vo.SearchHotelVO;
import vo.AddHotelResultVO;
import vo.AddHotelVO;

public class HotelDO {
	private HotelDao hotelDao;
	private Cache<HotelPO> hotels;
	public HotelDO(){
		hotelDao = DaoManager.getInstance().getHotelDao();
		hotels = new Cache<HotelPO>(10);
	}
	public HotelDO(int size){
		hotelDao = DaoManager.getInstance().getHotelDao();
		hotels = new Cache<HotelPO>(size);
		
	}
	public HotelVO getHotelInfo(long hotelId){
		HotelPO cachePO = null;
		cachePO = hotels.get(hotelId);
		if(cachePO==null){
			try{
				HibernateUtil.getCurrentSession().beginTransaction();
				HotelPO po = hotelDao.getInfo(hotelId);
				if(po==null)
					return null;
				Hibernate.initialize(po.getBusinessCity().getCircles());
				HotelVO result = DozerMappingUtil.getInstance().map(po, HotelVO.class);
				if(po.getImageData()!=null)
					result.setImage(SerializeUtil.blobToImage(po.getImageData()));
				hotels.put(hotelId, po);
				HibernateUtil.getCurrentSession().getTransaction().commit();
				return result;
			}catch(RuntimeException e){
				e.printStackTrace();
				hotels.remove(hotelId);
				try{
					HibernateUtil.getCurrentSession().getTransaction().rollback();
					return null;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
		else{
			HotelVO result = DozerMappingUtil.getInstance().map(cachePO, HotelVO.class);
			result.setImage(SerializeUtil.blobToImage(cachePO.getImageData()));
			return result;
		}
	}
	public ListWrapper<BusinessCity> getCity() throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			ListWrapper<BusinessCity> result =  hotelDao.getAllCity();
			if (result==null)
				return null;
			else{
				Iterator<BusinessCity> it = result.iterator();
				while(it.hasNext()){
					BusinessCity bc = it.next();
					bc.getBcityId();
					bc.getCircleIterator().next().getName();
				}
			}
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
	public ListWrapper<HotelItemVO> getRoomInfo(long hotelId) throws RemoteException{
		HotelPO po = null;
		Iterator<HotelItem> rit;
		Set<HotelItemVO> rooms = new HashSet<HotelItemVO>();
		//����cache
		HotelPO cachePO = null;
		cachePO = hotels.get(hotelId);
		if (cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				po = hotelDao.getInfo(hotelId);
				
				if (po!=null){
					rit = po.getRoom();
					Date now = new Date();
					while(rit.hasNext()){
						HotelItem hi = rit.next();
						if(hi.getDate().getDate()==now.getDate())
							rooms.add(DozerMappingUtil.getInstance().map(hi, HotelItemVO.class));
					}
					hotels.put(hotelId, po);

				}else
					return null;
				return new ListWrapper<>(rooms);
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
			rit = cachePO.getRoom();
			while(rit.hasNext()){
				HotelItem hi = rit.next();
				if(hi.getDate().toString().equals(new Date().toString()))
					rooms.add(DozerMappingUtil.getInstance().map(hi, HotelItemVO.class));
			}
			return new ListWrapper<>(rooms);
		}
		
	}
	public ExtraHotelVO getExtraHotelDetail(long hotelId, long userId) throws RemoteException {
		HotelPO cachePO = null;
		cachePO = hotels.get(hotelId);
		ExtraHotelVO result = new ExtraHotelVO();
		if (cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
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
					result.setImage(SerializeUtil.blobToImage(po.getImageData()));
				}
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return result;
			}catch(RuntimeException e){
				e.printStackTrace();
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
			result.setImage(SerializeUtil.blobToImage(po.getImageData()));
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
				Iterator<OrderPO> oit = ((ClientMemberPO)member).getOrder();
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
			e.printStackTrace();
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
				if(vo.getCheckInTime().getDate()==hi.getDate().getDate()){
					int num = hi.getNum();
					num-=vo.getRoomNum();
					hi.setNum(num);
					hotelDao.updateRoom(vo.getHotelId(), hi);
				}
			}
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return HotelResultMessage.SUCCESS;
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return HotelResultMessage.FAIL;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			OrderPO po = DaoManager.getInstance().getOrderDao().getInfo(vo.getOrderId());
			Date checkOutTime = po.getCheckOutTime();
			ListWrapper<HotelItem> rooms = hotelDao.getHotelItemByRoom(po.getHotel().getHid(), po.getRoom());
			Iterator<HotelItem> it = rooms.iterator();
			while(it.hasNext()){
				HotelItem hi = it.next();
				if (hi.getDate().after(vo.getActualCheckOutTime())&&hi.getDate().before(checkOutTime)){
					int num = hi.getNum();
					num+=po.getRoomNum();
					hi.setNum(num);
					hotelDao.updateRoom(po.getHotel().getHid(), hi);
				}
			}
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return HotelResultMessage.SUCCESS;
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return HotelResultMessage.FAIL;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	public HotelResultMessage setHotelInfo(MaintainHotelInfoVO vo) throws RemoteException{
		HotelPO cachePO = null;
		cachePO = hotels.get(vo.getHotelId());
		HotelPO po = null;
		boolean flag = false;
		if (cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				po = hotelDao.getInfo(vo.getHotelId());
				if (po==null)
					return HotelResultMessage.FAIL_WRONGID;
				else{
					if(vo.getAddress()!=null)
						po.setAddress(vo.getAddress());
					if(vo.getCircle()!=null)
						po.setBusinessCircle(vo.getCircle());
					if(vo.getDescription()!=null)
						po.setDescription(vo.getDescription());
					if(vo.getFacility()!=null)
						po.setFacility(vo.getFacility());
					if(vo.getService()!=null)
						po.setService(vo.getService());
					if(vo.getImage()!=null)
						po.setImageData(SerializeUtil.objectToBlob(vo.getImage()));
					hotelDao.update(po);
					hotels.put(vo.getHotelId(), po);
				}
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return HotelResultMessage.SUCCESS;
			}catch(RuntimeException e){
				hotels.remove(vo.getHotelId());
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return HotelResultMessage.FAIL;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}else{
			try{
				if(!flag)
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				po = cachePO;
				if(vo.getAddress()!=null)
					po.setAddress(vo.getAddress());
				if(vo.getCircle()!=null)
					po.setBusinessCircle(vo.getCircle());
				if(vo.getDescription()!=null)
					po.setDescription(vo.getDescription());
				if(vo.getFacility()!=null)
					po.setFacility(vo.getFacility());
				if(vo.getService()!=null)
					po.setService(vo.getService());
				hotelDao.update(po);
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				hotels.remove(vo.getHotelId());
				hotels.put(vo.getHotelId(), po);
				return HotelResultMessage.SUCCESS;
			}catch(RuntimeException e){
				hotels.remove(vo.getHotelId());
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return HotelResultMessage.FAIL;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		
		}
	}
	public HotelResultMessage setRoomInfo(MaintainRoomInfoVO vo) throws RemoteException{
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			Set<RoomInfoVO> changeInfo = vo.getChangeInfo();
			Iterator<RoomInfoVO> it = changeInfo.iterator();
			while(it.hasNext()){
				RoomInfoVO rivo = it.next();
				int num = rivo.getNum();
				ListWrapper<HotelItem> before = hotelDao.getHotelItemByRoom(vo.getHotelId(), rivo.getSourceType());
				ListWrapper<HotelItem> after = hotelDao.getHotelItemByRoom(vo.getHotelId(), rivo.getTargetType());
				Iterator<HotelItem> beforeIt = before.iterator();
				Iterator<HotelItem> afterIt = after.iterator();
				while(beforeIt.hasNext()){
					HotelItem hi = beforeIt.next();
					hi.setNum(hi.getNum()-num);
					hi.setTotal(hi.getTotal()-num);
					hotelDao.updateRoom(vo.getHotelId(), hi);
				}
				while(afterIt.hasNext()){
					HotelItem hi = afterIt.next();
					hi.setNum(hi.getNum()+num);
					hi.setTotal(hi.getTotal()+num);
					hotelDao.updateRoom(vo.getHotelId(), hi);
				}
			}
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return HotelResultMessage.SUCCESS;
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return HotelResultMessage.FAIL;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	public AddHotelResultVO addHotel(AddHotelVO vo) throws RemoteException{
		HotelResultMessage resultMessage = null;
		AddHotelResultVO result = new AddHotelResultVO();
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			HotelPO po = DozerMappingUtil.getInstance().map(vo, HotelPO.class);
			Set<HotelItemVO> newItems = vo.getItems();
			Iterator<HotelItemVO> it = newItems.iterator();
			Set<HotelItem> hiList = new HashSet<HotelItem>();
		    Calendar calendar = new GregorianCalendar(); 
		    Date now = new Date();
			while(it.hasNext()){
				calendar.setTime(now);
				Date temp = calendar.getTime();
				HotelItemVO hivo = it.next();
				for(int i=0;i<15;i++){
					HotelItem hi = new HotelItem();
					hi.setHotel(po);
					hi.setNum(hivo.getNum());
					hi.setRoom(hivo.getRoom());
					hi.setPrice(hivo.getPrice());
					hi.setTotal(hivo.getTotal());
					hi.setDate(temp);
					hiList.add(hi);
					calendar.add(calendar.DATE, 1);
					temp = calendar.getTime();
				}
			}
			po.setRooms(hiList);
			//add new �˺�
			UserPO newPo = DaoManager.getInstance().getUserDao().getInfo(vo.getUsername());
			if(newPo!=null){
				result.setHotelResultMessage(HotelResultMessage.FAIL);
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return result;
			}
			UserPO upo = new UserPO();
			HotelWorkerPO hwpo = new HotelWorkerPO();
			upo.setType(UserType.HOTEL_WORKER);
			upo.setStatus(UserStatus.OFFLINE);
			upo.setPassword(vo.getPassword());
			upo.setUsername(vo.getUsername());
			upo.setMember(hwpo);
			hwpo.setHotel(po);
			hwpo.setUser(upo);
			hwpo.setName(vo.getMemberName());
			hwpo.setType(UserType.HOTEL_WORKER);
			DaoManager.getInstance().getUserDao().insert(upo);
			DaoManager.getInstance().getMemberDao().add(hwpo);
			po.setHotelworker(hwpo);
			hotelDao.insert(po);
			resultMessage = HotelResultMessage.SUCCESS;
			long hotelId = po.getHid();
			
		
			result.setHotelId(hotelId);
			result.setHotelResultMessage(resultMessage);
			HotelWorkerVO hwvo = new HotelWorkerVO("", "");
			result.setHotelWorker(hwvo);
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return result;
		}catch(RuntimeException e){
			e.printStackTrace();
			resultMessage = HotelResultMessage.FAIL;
			result.setHotelResultMessage(resultMessage);
			result.setHotelWorker(null);
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return result;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo)throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			Rule rule = DozerMappingUtil.getInstance().map(vo, Rule.class);
			ListWrapper<HotelPO> hotels = hotelDao.getHotelListByRule(rule);
			
			Iterator<HotelPO> it = hotels.iterator();
			Set<BasicHotelVO> result = new HashSet<>();
			while(it.hasNext()){
				HotelPO po = it.next();
				BasicHotelVO bhvo = DozerMappingUtil.getInstance().map(po, BasicHotelVO.class);
				Set<HotelItemVO> items = new HashSet<>();
				Iterator<HotelItem> hiit = po.getRoom();
				while(hiit.hasNext()){
					HotelItem hi = hiit.next();
					HotelItemVO hivo = DozerMappingUtil.getInstance().map(hi, HotelItemVO.class);
					items.add(hivo);
				}
				bhvo.setRooms(items);
				result.add(bhvo);
			}
			HibernateUtil.getCurrentSession().getTransaction().commit();
			return new ListWrapper<>(result);
		}catch(RuntimeException e){
			e.printStackTrace();
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
}

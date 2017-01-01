package logic.service.impl.strategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;

import data.dao.StrategyDao;
import data.dao.impl.DaoManager;
import info.Cache;
import info.HotelItem;
import info.ListWrapper;
import info.OrderStrategy;
import info.StrategyItem;
import info.StrategyType;
import po.HotelPO;
import po.StrategyPO;
import po.strategies.StrategyRule;
import resultmessage.StrategyResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import util.SerializeUtil;
import util.StrategyRuleUtil;
import vo.CreateStrategyVO;
import vo.HotelStrategyVO;
import vo.StrategyItemVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public class StrategyDO {
	private StrategyDao strategyDao;
	private Cache<StrategyPO> strategies;
	public StrategyDO(){
		strategyDao = DaoManager.getInstance().getStrategyDao();
		strategies = new Cache<StrategyPO>(20);
	}
	/*
	 * Constructor
	 * @param cacheSize
	 * cahce大小
	 */
	public StrategyDO(int cacheSize){
		strategyDao = DaoManager.getInstance().getStrategyDao();
		strategies = new Cache<StrategyPO>(cacheSize);
	}
	/**
	 * @param strategyId
	 * 待删除的策略id
	 * @return StrategyResultMessage
	 * 删除结果
	 * @throws RemoteException
	 */
	public StrategyResultMessage delete(long strategyId) throws RemoteException {
		//先从cache中搜素待删除的策略
		StrategyPO cachePO = null;
		//标记hibernate 是否开启事务
		boolean flag = false;
		cachePO = strategies.get(strategyId);
		if (cachePO!=null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				cachePO.setStatus(true);
				strategyDao.update(cachePO);
				//同时锟接伙拷锟斤拷锟斤拷锟狡筹拷
				strategies.remove(strategyId);
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return StrategyResultMessage.SUCCESS;
			}catch(RuntimeException e){
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return StrategyResultMessage.FAIL_WRONG;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
		//没有从cache中找到则从数据库中获取，处理后把相应结果存入cache
		else{
			try{
				if (!flag)
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				StrategyPO po = strategyDao.getInfo(strategyId);
				if (po==null){
					return StrategyResultMessage.FAIL_WRONGID;
				}
				else{
					po.setStatus(true);
					strategyDao.update(po);
					HibernateUtil.getCurrentSession().getTransaction().commit();
					return StrategyResultMessage.SUCCESS;
				}
			}catch(RuntimeException e){
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return StrategyResultMessage.FAIL_WRONG;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
	}
	/**
	 * @param StrategyVO vo
	 * 传入新增策略所必须的数据
	 * @return StrategyResultVO
	 * 创建结果 成功返回SUCCESS和相应额外的信息，失败返回FAIL
	 * @throws RemoteException
	 */
	public StrategyResultVO create(StrategyVO vo) throws RemoteException {
		StrategyResultVO srvo = new StrategyResultVO();
		StrategyResultMessage result = null;
		StrategyPO po = null;
		try{
			
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			HotelPO hpo = DaoManager.getInstance().getHotelDao().getInfo(vo.getHotelId());
			if (hpo==null&&vo.getHotelId()!=-1){
				result = StrategyResultMessage.FAIL_WRONGINFO;
				srvo.setResultMessage(result);
				return srvo;
			}
			po = DozerMappingUtil.getInstance().map(vo, StrategyPO.class);
			po.setHotel(hpo);
			po.setStatus(false);
			//使用反射机制动态加载策略规则
			Class<?> clazz = Class.forName(StrategyRuleUtil.getInstance().getClassName(vo.getType().getName()));
			Constructor constructor = clazz.getConstructor(String.class);
			StrategyRule rule = (StrategyRule) constructor.newInstance(vo.getExtraInfo());
			po.setRule(SerializeUtil.objectToBlob(rule));
			//如果hotelid!=-1则表明是网站人员，故此时不用存入房间数据
			if(vo.getHotelId()!=-1){
			Iterator<StrategyItemVO> sivoIt = vo.getItems().iterator();
			Set<StrategyItem> items = new HashSet<>();
			while(sivoIt.hasNext()){
				StrategyItemVO sivo = sivoIt.next();
				StrategyItem si = new StrategyItem();
				si.setOff(sivo.getOff());
				si.setRoom(sivo.getRoom());
				si.setStrategy(po);
				items.add(si);
			}
			po.setItems(items);
			}
			strategyDao.insert(po);
			//锟斤拷锟斤拷cache
			strategies.put(po.getId(), po);
			//锟斤拷锟届返锟斤拷锟斤拷息
			Set<StrategyItemVO> set = null;
			if(vo.getHotelId()!=-1){
			set = new HashSet<>();
			Iterator<StrategyItemVO> siit = vo.getItems().iterator();
			while(siit.hasNext()){
				StrategyItemVO si = siit.next();
				HotelItem hi = DaoManager.getInstance()
											.getHotelDao()
											.getRoomByRid(po.getHotel().getHid(),si.getRoom());
				double before = hi.getPrice();
				StrategyItemVO sivo = new StrategyItemVO();
				sivo.setPriceBefore(before);
				sivo.setOff(si.getOff());
				sivo.setRoom(si.getRoom());
				sivo.setPriceAfter(before*si.getOff());
				set.add(sivo);
			}
			}
			CreateStrategyVO csvo = new CreateStrategyVO();
			csvo.setItems(set);
			csvo.setStrategyId(po.getId());
			srvo.setCreateStrategyVO(csvo);
			result = StrategyResultMessage.SUCCESS;
			srvo.setResultMessage(result);
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return srvo;
		}catch(RuntimeException | ClassNotFoundException 
				| NoSuchMethodException | InstantiationException 
				| IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
			strategies.remove(po.getId());
			result = StrategyResultMessage.FAIL_WRONG;
			srvo.setResultMessage(result);
			srvo.setCreateStrategyVO(null);
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return srvo;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 内部将持久化对象转化为VO类的方法，抽离开以重用
	 * @param list 
	 * 策略列表
	 * @return ListWrapper<HotelStrategyVO>
	 * 转化后的策略列表
	 * @throws RemoteException
	 */
	private ListWrapper<HotelStrategyVO> transform(ListWrapper<StrategyPO> list)throws RemoteException {
		Set<HotelStrategyVO> result = new HashSet<HotelStrategyVO>();
		Iterator<StrategyPO> it = list.iterator();
		while (it.hasNext()){
			StrategyPO po = it.next();
			HotelStrategyVO vo = DozerMappingUtil.getInstance().map(po, HotelStrategyVO.class);
			vo.setExtraInfo(SerializeUtil.blobToStrategyRule(po.getRule()).getInfo());
			Set<StrategyItemVO> set = new HashSet<>();
			if(po.getHotel()==null){
				result.add(vo);
				continue;
			}
			Iterator<StrategyItem> siit = po.getStrategyRoom();
			while(siit.hasNext()){
				StrategyItem si = siit.next();
				HotelItem hi = DaoManager.getInstance()
											.getHotelDao()
											.getRoomByRid(po.getHotel().getHid(), si.getRoom());
				if(hi!=null){
					double before = hi.getPrice();
					StrategyItemVO sivo = new StrategyItemVO();
					sivo.setPriceBefore(before);
					sivo.setOff(si.getOff());
					sivo.setRoom(si.getRoom());
					sivo.setPriceAfter(before*si.getOff());
					set.add(sivo);
				}
			}
			vo.setItems(set);
			result.add(vo);
		}
		return new ListWrapper<>(result);
	}
	/**
	 * 酒店工作人员获取自身酒店订单所调用的方法
	 * @param hotelId
	 * 获取策略列表的酒店ID
	 * @return ListWrapper<HotelStrategyVO>
	 * 酒店策略列表
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getStrategyList(long hotelId) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			ListWrapper<StrategyPO> list = strategyDao.getHotelStrategyList(hotelId);
			ListWrapper<HotelStrategyVO> result = transform(list);
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return result;
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
	/**
	 * 获取网站营销人员的策略列表
	 * @return ListWrapper<HotelStrategyVO>
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getWEBStrategyList() throws RemoteException{
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			ListWrapper<StrategyPO> webList = strategyDao.getWEBStrategyList();
			ListWrapper<HotelStrategyVO> result = transform(webList);
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return result;
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
	/**
	 * 给订单获取能采用的策略列表
	 * 此方法在下订单时选择相应酒店，房间及数量时调用
	 * @param OrderStrategy 
	 * 基本的订单数据，将会根据这些数据获取能采用的策略
	 * @return ListWrapper<HotelStrategyVO>
	 * 所有能采用的策略列表
	 * 包括酒店策略和网站营销策略
	 * @throws RemoteException
	 */
	public ListWrapper<HotelStrategyVO> getStrategyForOrder(OrderStrategy vo) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			ListWrapper<StrategyPO> list = strategyDao.getHotelStrategyList(vo.getHotelId());
			ListWrapper<StrategyPO> webList = strategyDao.getWEBStrategyList();
			ListWrapper<HotelStrategyVO> result = transform(list);
			Set<StrategyPO> tempList = new HashSet<StrategyPO>();
			Iterator<StrategyPO> it = webList.iterator();
			while(it.hasNext()){
				StrategyPO po = it.next();
				if (SerializeUtil.blobToStrategyRule(po.getRule()).canBeApplied(vo)){
					tempList.add(po);
				}
			}
			result.addAll(transform(new ListWrapper<>(tempList)));
			HibernateUtil.getCurrentSession()
							.getTransaction()
							.commit();
			return result;
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
	/**
	 * 创建策略时需要知道所有可创建的策略类型
	 * 此方法返回所有策略类型
	 * @return ListWrapper<StrategyType>
	 * 所有的策略类型
	 * @throws RemoteException
	 */
	public ListWrapper<StrategyType> getTypes()throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			return strategyDao.getTypes();
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
				return null;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
}

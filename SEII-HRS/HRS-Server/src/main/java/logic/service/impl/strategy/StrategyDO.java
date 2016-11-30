package logic.service.impl.strategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;

import data.dao.StrategyDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.HotelItem;
import info.ListWrapper;
import info.OrderStrategy;
import info.StrategyItem;
import po.HotelPO;
import po.StrategyPO;
import po.strategies.StrategyRule;
import resultmessage.StrategyResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
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
		//Ĭ�ϻ���20��StrategyPO
		strategies = new Cache<StrategyPO>(20);
	}
	/*
	 * Constructor
	 * @param cacheSize-ָ���ض���cacheSize
	 */
	public StrategyDO(int cacheSize){
		strategyDao = DaoManager.getInstance().getStrategyDao();
		strategies = new Cache<StrategyPO>(cacheSize);
	}
	public StrategyResultMessage delete(long strategyId) throws RemoteException {
		//�ȴ�cache������Ӧ��strategy
		StrategyPO cachePO = null;
		//����Ƿ�������
		boolean flag = false;
		cachePO = strategies.get(strategyId);
		if (cachePO!=null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				strategyDao.delete(cachePO);
				//ͬʱ�ӻ������Ƴ�
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
		//û�ҵ���ȥ���ݿ��в�ѯ��ɾ��
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
					strategyDao.delete(po);
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
	public StrategyResultVO create(StrategyVO vo) throws RemoteException {
		StrategyResultVO srvo = new StrategyResultVO();
		StrategyResultMessage result = null;
		StrategyPO po = null;
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			HotelPO hpo = DaoManager.getInstance().getHotelDao().getInfo(vo.getHotelId());
			if (hpo==null){
				result = StrategyResultMessage.FAIL_WRONGINFO;
				srvo.setResultMessage(result);
				return srvo;
			}
			po = DozerMappingUtil.getInstance().map(vo, StrategyPO.class);
			po.setHotel(hpo);
			//ʹ�÷�����Ƽ��ؾ����StrategyRule
			Class<?> clazz = Class.forName(StrategyRuleUtil.getInstance().getClassName(vo.getType().getName()));
			Constructor constructor = clazz.getConstructor(String.class);
			StrategyRule rule = (StrategyRule) constructor.newInstance(vo.getExtraInfo());
			po.setStrategyRule(rule);
			strategyDao.insert(po);
			//����cache
			strategies.put(po.getId(), po);
			//���췵����Ϣ
			Set<StrategyItemVO> set = new HashSet<>();
			Iterator<StrategyItem> siit = vo.getItems().iterator();
			while(siit.hasNext()){
				StrategyItem si = siit.next();
				HotelItem hi = DaoManager.getInstance()
											.getHotelDao()
											.getRoomByRid(po.getHotel().getHid(), si.getId());
				double before = hi.getPrice();
				StrategyItemVO sivo = new StrategyItemVO();
				sivo.setPriceBefore(before);
				sivo.setOff(si.getOff());
				sivo.setRoom(si.getRoom());
				sivo.setPriceAfter(before*si.getOff());
				set.add(sivo);
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
		}catch(RuntimeException | ClassNotFoundException 
				| NoSuchMethodException | InstantiationException 
				| IllegalAccessException | InvocationTargetException e){
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
	private ListWrapper<HotelStrategyVO> transform(ListWrapper<StrategyPO> list){
		Set<HotelStrategyVO> result = new HashSet<HotelStrategyVO>();
		Iterator<StrategyPO> it = list.iterator();
		while (it.hasNext()){
			StrategyPO po = it.next();
			HotelStrategyVO vo = DozerMappingUtil.getInstance().map(po, HotelStrategyVO.class);
			vo.setExtraInfo(po.getRule().getInfo());
			Set<StrategyItemVO> set = new HashSet<>();
			Iterator<StrategyItem> siit = po.getStrategyRoom();
			while(siit.hasNext()){
				StrategyItem si = siit.next();
				HotelItem hi = DaoManager.getInstance()
											.getHotelDao()
											.getRoomByRid(po.getHotel().getHid(), si.getId());
				double before = hi.getPrice();
				StrategyItemVO sivo = new StrategyItemVO();
				sivo.setPriceBefore(before);
				sivo.setOff(si.getOff());
				sivo.setRoom(si.getRoom());
				sivo.setPriceAfter(before*si.getOff());
				set.add(sivo);
			}
			vo.setItems(set);
			result.add(vo);
		}
		return new ListWrapper<>(result);
	}
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
				if (po.getRule().canBeApplied(vo)){
					tempList.add(po);
				}
			}
			result.addAll(transform(new ListWrapper<>(tempList)));
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

}
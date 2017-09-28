package businesslogic.BrowseBL;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Or;

import businesslogic.OrderBL.OrderBLService_Stub;
import businesslogicservice.BrowseBLService.BrowseBLService;
import vo.BrowseVO;
import vo.CreditVO;
import vo.HotelVO;
import vo.MemberVO;
import vo.OrderType;
import vo.OrderVO;
import vo.RuleVO;
import vo.StrategyVO;

public class BrowseBLService_Stub implements BrowseBLService{

	@Override
	public List<OrderVO> getOrdersInfo(long userid, OrderType type) {
		// TODO Auto-generated method stub
		// search the database and find 
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<OrderVO> getHotelOrdersInfo(long hotelid, OrderType type) {
		// TODO Auto-generated method stub
		//�����漰������bl�����ã��ʴ���һ����list���Թ������������
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<OrderVO> getWEBOrdersInfo() {
		// TODO Auto-generated method stub
		//�����漰������bl�����ã��ʴ���һ����list���Թ������������
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<CreditVO> getCreditInfo(long userid) {
		// TODO Auto-generated method stub
		//�����漰������bl�����ã��ʴ���һ����list���Թ������������
		CreditVO vo = new CreditVO();
		List<CreditVO> list = new ArrayList<CreditVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<HotelVO> getHotels(RuleVO vo) {
		// TODO Auto-generated method stub
		//�����漰������bl�����ã��ʴ���һ����list���Թ������������
		HotelVO voi = new HotelVO();
		List<HotelVO> list = new ArrayList<HotelVO>();
		list.add(voi);
		return list;
	}

	@Override
	public HotelVO getHotel(long hotelid) {
		// TODO Auto-generated method stub
		//search the database and return;
		return new HotelVO();
	}

	@Override
	public StrategyVO getStrategyInfo(int orderid) {
		// TODO Auto-generated method stub
		//�����漰������bl�����ã��ʴ���һ����list���Թ������������
		return new StrategyVO();
	}

	@Override
	public MemberVO getMemberInfo(long memberid) {
		// TODO Auto-generated method stub
		//�����漰������bl�����ã��ʴ���һ����list���Թ������������
		return new MemberVO();
	}

	@Override
	public List<BrowseVO> getBrowseInfo(long userid) {
		// TODO Auto-generated method stub
		//�����漰������bl�����ã��ʴ���һ����list���Թ������������
		BrowseVO voi = new BrowseVO();
		List<BrowseVO> list = new ArrayList<BrowseVO>();
		list.add(voi);
		return list;
	}

	@Override
	public void clear(long userid) {
		// TODO Auto-generated method stub
		//��������¼
	}
	
}

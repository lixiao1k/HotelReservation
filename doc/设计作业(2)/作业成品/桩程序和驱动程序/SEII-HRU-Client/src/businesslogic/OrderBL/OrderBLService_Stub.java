package businesslogic.OrderBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.OrderBLService.OrderBLService;
import businesslogicservice.OrderBLService.OrderResultMessage;
import vo.OrderType;
import vo.OrderVO;

public class OrderBLService_Stub implements OrderBLService{

	@Override
	public List<OrderVO> getuserInfo(long userid, OrderType type) {
		// TODO Auto-generated method stub
		//这里涉及到数据库操作，故创建一个新list以略过操作方便测试
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<OrderVO> getHotelOrdersInfo(long hotelid, OrderType type) {
		// TODO Auto-generated method stub
		//这里涉及到数据库操作，故创建一个新list以略过操作方便测试
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<OrderVO> getWEBOrdersInfo() {
		// TODO Auto-generated method stub
		//这里涉及到数据库操作，故创建一个新list以略过操作方便测试
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public OrderResultMessage create(OrderVO vo) {
		// TODO Auto-generated method stub
		//默认创建成功
		if (vo!=null) return OrderResultMessage.SUCCESS;
		else return OrderResultMessage.FAIL;

	}

	@Override
	public void abnormal(OrderVO vo) {
		// TODO Auto-generated method stub
		//set database this po abnormal
	}

	@Override
	public OrderResultMessage userCancel(OrderVO vo) {
		// TODO Auto-generated method stub
		//用户取消即调用此函数
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage execute(OrderVO vo) {
		// TODO Auto-generated method stub
		//执行订单
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage WEBCancel(OrderVO vo) {
		// TODO Auto-generated method stub
		//网站营销人员撤销异常订单
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public double getTotal(OrderVO vo) {
		// TODO Auto-generated method stub
		//获取订单总价
		return 1000;
	}

}

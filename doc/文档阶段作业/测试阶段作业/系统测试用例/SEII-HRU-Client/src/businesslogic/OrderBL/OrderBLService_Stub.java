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
		//�����漰�����ݿ�������ʴ���һ����list���Թ������������
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<OrderVO> getHotelOrdersInfo(long hotelid, OrderType type) {
		// TODO Auto-generated method stub
		//�����漰�����ݿ�������ʴ���һ����list���Թ������������
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public List<OrderVO> getWEBOrdersInfo() {
		// TODO Auto-generated method stub
		//�����漰�����ݿ�������ʴ���һ����list���Թ������������
		OrderVO vo = new OrderVO();
		List<OrderVO> list = new ArrayList<OrderVO>();
		list.add(vo);
		return list;
	}

	@Override
	public OrderResultMessage create(OrderVO vo) {
		// TODO Auto-generated method stub
		//Ĭ�ϴ����ɹ�
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
		//�û�ȡ�������ô˺���
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage execute(OrderVO vo) {
		// TODO Auto-generated method stub
		//ִ�ж���
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage WEBCancel(OrderVO vo) {
		// TODO Auto-generated method stub
		//��վӪ����Ա�����쳣����
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public double getTotal(OrderVO vo) {
		// TODO Auto-generated method stub
		//��ȡ�����ܼ�
		return 1000;
	}

}

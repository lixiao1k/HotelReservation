package businesslogic.MemberBL;

import businesslogicservice.MemberBLService.MemberBLService;
import businesslogicservice.MemberBLService.MemberResultMessage;
import vo.UserVO;

public class MemberBLService_Stub implements MemberBLService{

	@Override
	public MemberResultMessage registerVIP(UserVO vo) {
		// TODO Auto-generated method stub
		if (vo.getUsername().equals("1234")) return MemberResultMessage.SUCCESS;
		return MemberResultMessage.FAIL;
	}

	@Override
	public MemberResultMessage cancel(UserVO vo) {
		// TODO Auto-generated method stub
		long id = vo.getID();
		// ���Ҷ�Ӧ�û�������ֵ,�漰�����ݿ�������ʼ���һ��int credit �Բ���
		int credit = 123;
		if (credit<=100) return MemberResultMessage.SUCCESS;
		return MemberResultMessage.FAIL;
	}

	@Override
	public MemberResultMessage getInfo(long memberID) {
		// TODO Auto-generated method stub
		// ��ѯmembr��Ϣ��Ĭ�ϳɹ�
		return MemberResultMessage.SUCCESS;
	}
	

}

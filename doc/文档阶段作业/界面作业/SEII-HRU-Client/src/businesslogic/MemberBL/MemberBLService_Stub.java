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
		// 查找对应用户的信用值,涉及到数据库操作，故假设一个int credit 以测试
		int credit = 123;
		if (credit<=100) return MemberResultMessage.SUCCESS;
		return MemberResultMessage.FAIL;
	}

	@Override
	public MemberResultMessage getInfo(long memberID) {
		// TODO Auto-generated method stub
		// 查询membr信息，默认成功
		return MemberResultMessage.SUCCESS;
	}
	

}

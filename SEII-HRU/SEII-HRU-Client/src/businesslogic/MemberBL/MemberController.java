package businesslogic.MemberBL;

import java.util.List;

import businesslogicservice.MemberBLService.MemberBLService;
import businesslogicservice.MemberBLService.MemberBLService_Update;
import businesslogicservice.MemberBLService.MemberResultMessage;
import vo.MemberVO;
import vo.UserVO;
import vo.VIPVO;

public class MemberController implements MemberBLService_Update{
	Member memberObject = new Member();
	public void setMembers(List<MemberInfo> members){ 
		memberObject.serMembers(members);
	}
	@Override
	public MemberResultMessage registerVIP(VIPVO vo) {
		return memberObject.registerVIP(vo.getType(), vo.getInfo(), vo.getId());
	}

	@Override
	public MemberResultMessage cancel(long id) {
		return memberObject.cancel(id);
	}

	@Override
	public MemberVO getInfo(long id) {
		// TODO Auto-generated method stub
		return memberObject.getInfo(id);
	}

	@Override
	public MemberResultMessage changeInfo(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberObject.changeInfo(vo);
	}

	@Override 
	public MemberInfoList manageInfo() {
		// TODO Auto-generated method stub
		return memberObject.manageInfo();
	}
	
}

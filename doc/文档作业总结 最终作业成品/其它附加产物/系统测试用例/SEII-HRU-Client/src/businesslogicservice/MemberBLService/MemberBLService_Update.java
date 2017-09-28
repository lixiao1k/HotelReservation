package businesslogicservice.MemberBLService;

import businesslogic.MemberBL.MemberInfoList;
import vo.MemberVO;
import vo.UserVO;
import vo.VIPVO;

public interface MemberBLService_Update {
	public MemberResultMessage registerVIP(VIPVO vo);
	public MemberResultMessage cancel(long id);
	public MemberVO getInfo(long id);
	public MemberResultMessage changeInfo(MemberVO vo);
	public MemberInfoList manageInfo();
	
}

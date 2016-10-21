package businesslogicservice.MemberBLService;

import vo.UserVO;

public interface MemberBLService {
	public MemberResultMessage  registerVIP(UserVO vo);
	public MemberResultMessage cancel(UserVO vo);
	public MemberResultMessage getInfo(long memberID);
}

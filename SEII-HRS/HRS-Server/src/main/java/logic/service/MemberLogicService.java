package logic.service;

import list.MemberInfoList;
import resultmessage.MemberResultMessage;
import vo.MemberVO;
import vo.VIPVO;

public interface MemberLogicService {
	public MemberResultMessage registerVIP(VIPVO vo);
	public MemberResultMessage cancel(long id);
	public MemberVO getInfo(long id);
	public MemberResultMessage changeInfo(MemberVO vo);
	public MemberInfoList manageInfo();
}

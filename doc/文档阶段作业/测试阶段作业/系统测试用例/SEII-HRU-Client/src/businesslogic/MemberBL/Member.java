package businesslogic.MemberBL;

import java.util.List;

import businesslogicservice.MemberBLService.MemberResultMessage;
import vo.MemberVO;
import vo.UserVO;

public class Member {
	private List<MemberInfo> members;
	public void serMembers(List<MemberInfo> members){ this.members = members; }
	public MemberResultMessage registerVIP(int type,String info,long memberId) {
		for (MemberInfo member:members){
			if (member.getId()==memberId){
				if (!member.isVIP()){
					if (member.getCredit()>=100){
						member.setVIP(new VIPInfo(type, info));
						return MemberResultMessage.REGISTER_VIP_SUCCESS;
					}else return MemberResultMessage.REGISTER_VIP_FAIL_CREDITNOTENOUGH;
				}else return MemberResultMessage.REGISTER_VIP_FAIL_ALREADYVIP;
			}
		}
		return MemberResultMessage.REGISTER_VIP_FAIL_WRONGID;
	}


	public MemberResultMessage cancel(long id) {
		for (MemberInfo member:members){
			if (member.getId()==id){
				if(!member.isVIP()) return MemberResultMessage.CANCEL_VIP_FAIL_NOTVIP;
				else{
					if (member.getCredit()>=100) return MemberResultMessage.CANCELVIP_FAIL_CREDITENOUGH;
					else{
						member.setVIP(null);
						member.setFlag(false);
						return MemberResultMessage.CANCELVIP_SUCCESS;
					}
				}
			}
		}
		return MemberResultMessage.CHANGEINFO_FAIL_WORNDID;
	}

	public MemberVO getInfo(long id) {
		for (MemberInfo member:members){
			if (member.getId()==id){
				return new MemberVO(member);
			}
		}
		return null;
	}
	public MemberResultMessage changeInfo(MemberVO vo) {
		for (MemberInfo member:members){
			if (member.getId()==vo.getId()){
				member.setAllInfo(vo);
				return MemberResultMessage.CHANGEINFO_SUCCESS;
			}
		}
		return MemberResultMessage.CHANGEINFO_FAIL_WORNDID;
	}
	public MemberInfoList manageInfo() {
		return new MemberInfoList(members);
	}
}

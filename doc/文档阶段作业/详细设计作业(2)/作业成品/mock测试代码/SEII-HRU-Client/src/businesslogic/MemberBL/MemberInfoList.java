package businesslogic.MemberBL;

import java.util.ArrayList;
import java.util.List;

import vo.MemberVO;

public class MemberInfoList {
	private List<MemberInfo> members;
	public MemberInfoList(List<MemberInfo> members){ this.members = members; }
	public List<MemberVO> getList(){
		List<MemberVO> result = new ArrayList<MemberVO>();
		for (MemberInfo info:members){
			MemberVO vo = new MemberVO(info);
			result.add(vo);
		}
		return result;
	}
	public void setList(List<MemberInfo> members){ this.members = members; }
}

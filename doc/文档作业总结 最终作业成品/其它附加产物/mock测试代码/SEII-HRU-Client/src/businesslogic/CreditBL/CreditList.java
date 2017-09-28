package businesslogic.CreditBL;

import java.util.List;

import vo.CreditVO;

public class CreditList {
	private List<CreditInfo> list;
	public CreditList(List<CreditInfo> list){
		this.list = list;
	}
	public void setCreditList(List<CreditInfo> list){
		this.list = list;
	}
	public List<CreditInfo> getList(){
		return this.list;
	}
	
}

package businesslogic.MemberBL;

public class VIPInfo {
	private int type;
	private String birthday;
	private String companyName;
	public VIPInfo(int type,String info){
		if (type==0) birthday = info;
		else companyName = info;
	}
	public String getInfo(){
		if (type==0){
			return "Type:个人会员; 信息:"+birthday;
		}else{
			return "Type:企业会员; 信息:"+companyName;
		}
	}
}

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
			return "Type:���˻�Ա; ��Ϣ:"+birthday;
		}else{
			return "Type:��ҵ��Ա; ��Ϣ:"+companyName;
		}
	}
}

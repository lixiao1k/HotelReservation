package po;

public class CreditPO {
	private int credit;
	public CreditPO(){
		credit=0;
	}
	public void addCredit(int value){
		credit+=value;
	}
	public void decreaseCredit(int value){
		credit-=value;
	}
}

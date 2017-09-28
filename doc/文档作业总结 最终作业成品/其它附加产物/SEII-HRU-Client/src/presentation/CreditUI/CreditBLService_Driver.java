package presentation.CreditUI;

import java.util.List;


import businesslogicservice.CreditBLService.CreditBLService;
import businesslogicservice.CreditBLService.CreditResultMessage;


public class CreditBLService_Driver {
	public void drive(CreditBLService service){
		List list = service.getInfo(1234);
		if (list!=null) System.out.println("CreditBLService.getInfo SUCCESS");
		CreditResultMessage result = service.recharge(1);
		if (result==CreditResultMessage.SUCCESS) System.out.println("CreditBLService.recharge SUCCESS");
		result = service.recharge(-1);
		
		if (result==CreditResultMessage.FAIL_LESSTHANZERO) System.out.println("CreditBLService.recharge FAIL_LESSTHANZERO");
	}
}

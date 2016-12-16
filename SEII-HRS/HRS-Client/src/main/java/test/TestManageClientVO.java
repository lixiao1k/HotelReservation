package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.util.converter.LocalDateStringConverter;
import vo.ManageClientVO;

public class TestManageClientVO {
    public ManageClientVO returnManageClientVO(){
    	LocalDate birthday = LocalDate.of(1996,11,19);
    	long userid =151250084;
    	String username="Shelton Lee";
    	String companyname="Nanjing University";
    	String phonenumber="15951921161";
    	
    	return new ManageClientVO(userid, username, birthday, companyname, phonenumber);
    }
}

package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.chart.PieChart.Data;
import javafx.util.converter.LocalDateStringConverter;
import vo.ManageClientVO;

public class TestManageClientVO {
    public ManageClientVO returnManageClientVO(){
    	Date birthday = new Date(1996,11,19);
    	long userid =151250084;
    	String companyname="Nanjing University";

    	String phonenumber="15951921161";

    	return new ManageClientVO(userid, birthday, companyname, phonenumber);
    }
}

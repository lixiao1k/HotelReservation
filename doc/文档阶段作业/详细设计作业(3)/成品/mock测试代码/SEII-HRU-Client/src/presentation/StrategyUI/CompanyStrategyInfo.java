package presentation.StrategyUI;

import java.util.List;

import vo.StrategyRoomInfo;

public class CompanyStrategyInfo extends StrategyInfo{
	String companyName;
	public CompanyStrategyInfo(String type, String name,String companyName, List<StrategyRoomInfo> strategyRoom) {
		super(type, name, strategyRoom);
		this.companyName = companyName;
	}

}

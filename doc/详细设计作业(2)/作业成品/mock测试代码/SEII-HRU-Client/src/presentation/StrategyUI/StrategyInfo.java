package presentation.StrategyUI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import vo.RoomInfo;
import vo.StrategyRoomInfo;

public class StrategyInfo {

	List<StrategyRoomInfo> strategyRoom;
	String name,type;
	long id;
	public StrategyInfo(String type,String name,List<StrategyRoomInfo> strategyRoom){
		this.type = type;
		this.strategyRoom = strategyRoom;
		this.name = name;
	}
	public String getName(){
		return this.name;
	}

	public List<StrategyRoomInfo> getStrategyRoomList(){
		return this.strategyRoom;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getType(){
		return this.type;
	}
	
}

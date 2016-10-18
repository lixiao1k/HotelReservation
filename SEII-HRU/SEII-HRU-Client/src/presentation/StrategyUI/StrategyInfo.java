package presentation.StrategyUI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import vo.RoomInfo;
import vo.StrategyRoomInfo;

public class StrategyInfo {
	LocalDate beginTime;
	LocalDate endTime;
	List<StrategyRoomInfo> strategyRoom;
	String name,type;
	long id;
	public StrategyInfo(String type,String name,LocalDate beginTime,LocalDate endTime,List<StrategyRoomInfo> strategyRoom){
		this.type = type;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.strategyRoom = strategyRoom;
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public LocalDate getBeginTime(){
		return this.beginTime;
	}
	public LocalDate getEndTime(){
		return this.endTime;
	}
	public List<StrategyRoomInfo> getStrategyRoomList(){
		return this.strategyRoom;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setBeginTime(LocalDate time){
		this.beginTime = time;
	}
	public void setEndTime(LocalDate time){
		this.endTime = time;
	}
	public String getType(){
		return this.type;
	}
	
}

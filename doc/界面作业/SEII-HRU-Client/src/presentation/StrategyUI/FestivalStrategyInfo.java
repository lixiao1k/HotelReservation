package presentation.StrategyUI;

import java.time.LocalDate;
import java.util.List;

import vo.StrategyRoomInfo;

public class FestivalStrategyInfo extends StrategyInfo{
	LocalDate beginTime;
	LocalDate endTime;
	public FestivalStrategyInfo(String type,String name,LocalDate beginTime,LocalDate endTime,List<StrategyRoomInfo> strategyRoom){
		super(type,name,strategyRoom);
		this.beginTime = beginTime;
		this.endTime = endTime;
	}
	public LocalDate getBeginTime(){
		return this.beginTime;
	}
	public LocalDate getEndTime(){
		return this.endTime;
	}
	public void setBeginTime(LocalDate time){
		this.beginTime = time;
	}
	public void setEndTime(LocalDate time){
		this.endTime = time;
	}
}

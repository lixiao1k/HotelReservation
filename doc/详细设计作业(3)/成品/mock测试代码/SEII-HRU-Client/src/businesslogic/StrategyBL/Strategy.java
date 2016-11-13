package businesslogic.StrategyBL;

import businesslogic.HotelBL.HotelController;
import businesslogicservice.StrategyBLService.StrategyResultMessage;
import vo.RoomVO;
import vo.StrategyVO;

public class Strategy {
	StrategyList strategyList;
	HotelController hotelController;
	public void setHotelController(HotelController hotelController){
		this.hotelController = hotelController;
	}
	public StrategyList GetStrateInfo(long Hotelid){
		return hotelController.GetHotelInfo(Hotelid).getStrategyList();
	}
	public StrategyResultMessage DeleteStrategyInfo(long Hotelid,long Strategyid){
		if(hotelController.GetHotelInfo(Hotelid).getStrategyList().delete(Strategyid)){
			return StrategyResultMessage.SUCCESS;
		}else{
			return StrategyResultMessage.FAIL;
		}
	}
	public StrategyResultMessage AddStrategyInfo(StrategyVO vo){
		if(strategyList.getStrategyList().add(vo)){
			return StrategyResultMessage.SUCCESS;
		}else{
			return StrategyResultMessage.FAIL;
		}
	}
	public StrategyResultMessage ChangeStrategyInfo(StrategyVO vo){
		for(StrategyVO strategy:strategyList.getStrategyList()){
			if(strategy.getID()==vo.getID()){
				strategy=vo;
				return StrategyResultMessage.SUCCESS;
			}
		}
		return StrategyResultMessage.FAIL;
	}
	public RoomVO getRoomInfo(long Hotelid){
		return hotelController.GetRoomInfo(Hotelid);
	}
}

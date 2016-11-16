package logic.service;

import list.StrategyList;
import resultmessage.StrategyResultMessage;
import vo.RoomVO;
import vo.StrategyVO;

public interface StrategyLogicService {
	public StrategyList GetStrateInfo(long Hotelid);
	public StrategyResultMessage DeleteStrategyInfo(long Hotelid,long Strategyid);
	public StrategyResultMessage AddStrategyInfo(StrategyVO vo);
	public StrategyResultMessage ChangeStrategyInfo(StrategyVO vo);
	public RoomVO getRoomInfo(long Hotelid);
}

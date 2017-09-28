package businesslogicservice.StrategyBLService;

import businesslogic.StrategyBL.StrategyList;
import vo.RoomVO;
import vo.StrategyVO;

public interface StrategyBLService_Update {
	public StrategyList GetStrateInfo(long Hotelid);
	public StrategyResultMessage DeleteStrategyInfo(long Hotelid,long Strategyid);
	public StrategyResultMessage AddStrategyInfo(StrategyVO vo);
	public StrategyResultMessage ChangeStrategyInfo(StrategyVO vo);
	public RoomVO getRoomInfo(long Hotelid);
}

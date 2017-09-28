package businesslogic.StrategyBL;

import businesslogicservice.StrategyBLService.StrategyBLService_Update;
import businesslogicservice.StrategyBLService.StrategyResultMessage;
import vo.RoomVO;
import vo.StrategyVO;

public class StrategyController implements StrategyBLService_Update{
	Strategy strategyobject=new Strategy();

	@Override
	public StrategyList GetStrateInfo(long Hotelid) {
		return strategyobject.GetStrateInfo(Hotelid);
	}

	@Override
	public StrategyResultMessage DeleteStrategyInfo(long Hotelid, long Strategyid) {
		return strategyobject.DeleteStrategyInfo(Hotelid, Strategyid);
	}

	@Override
	public StrategyResultMessage AddStrategyInfo(StrategyVO vo) {
		return strategyobject.AddStrategyInfo(vo);
	}

	@Override
	public StrategyResultMessage ChangeStrategyInfo(StrategyVO vo) {
		return strategyobject.ChangeStrategyInfo(vo);
	}

	@Override
	public RoomVO getRoomInfo(long Hotelid) {
		return strategyobject.getRoomInfo(Hotelid);
	}

}

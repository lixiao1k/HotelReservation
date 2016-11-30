package vo;

import resultmessage.StrategyResultMessage;

public class StrategyResultVO {
	private StrategyResultMessage resultMessage;
	private CreateStrategyVO createStrategyVO;
	public void setCreateStrategyVO(CreateStrategyVO createStrategyVO){
		this.createStrategyVO = createStrategyVO;
	}
	public void setResultMessage(StrategyResultMessage resultMessage){
		this.resultMessage = resultMessage;
	}
	public StrategyResultMessage getResultMessage(){
		return resultMessage;
	}
	public CreateStrategyVO getCreateStrategyVO(){
		return createStrategyVO;
	}
}

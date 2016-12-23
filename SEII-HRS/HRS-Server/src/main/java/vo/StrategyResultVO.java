package vo;

import java.io.Serializable;

import resultmessage.StrategyResultMessage;

public class StrategyResultVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4651156633990154811L;
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

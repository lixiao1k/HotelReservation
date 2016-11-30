import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import info.ListWrapper;
import logic.service.CommentLogicService;
import logic.service.impl.comment.CommentLogicServiceImpl;
import resultmessage.CommentResultMessage;
import vo.CommentVO;

public class TestCommentBL {
	@Test
	public void testgetHotelInfo() throws RemoteException {
		CommentLogicService commentLogic=new CommentLogicServiceImpl();
		ListWrapper<CommentVO> resultlist=commentLogic.getHotelInfo(1);
		CommentVO result=resultlist.iterator().next();
		Assert.assertEquals("Right message.", "1970-01-01 ∆¿¬€»À:1 ∆¿¬€æ∆µÍ:1 comment:hao grade:99", result);
	}
	
	@Test
	public void testreview() throws RemoteException {
		CommentLogicService commentLogic=new CommentLogicServiceImpl();
		CommentVO testvo=new CommentVO(99, "hao", 1, 1);
		CommentResultMessage result=null;
		result=commentLogic.review(testvo);
		Assert.assertEquals("Review success.", CommentResultMessage.SUCCESS, result);
	}
}

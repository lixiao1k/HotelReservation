import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import info.ListWrapper;
import logic.service.CommentLogicService;
import logic.service.impl.comment.CommentLogicServiceImpl;
import resultmessage.CommentResultMessage;
import vo.CommentVO;
import vo.HotelCommentVO;

public class TestCommentBL {
	@Test
	public void testgetHotelInfo() throws RemoteException {
		CommentLogicService commentLogic=new CommentLogicServiceImpl();
		ListWrapper<HotelCommentVO> resultlist= commentLogic.getHotelInfo(2);
		Assert.assertNotEquals("wrong", null,resultlist);
		System.out.println(resultlist.size());
	}
	@Test
	public void testreview() throws RemoteException {
		CommentLogicService commentLogic=new CommentLogicServiceImpl();
		CommentVO testvo=new CommentVO(99, "hao",1,1,3);
		CommentResultMessage result=null;
		result=commentLogic.review(testvo);
		Assert.assertEquals("Review success.", CommentResultMessage.SUCCESS, result);
	}
}

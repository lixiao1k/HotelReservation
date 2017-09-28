package businesslogic.CommentBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.CommentBLService.CommentResultMessage;
import vo.CommentVO;

public class TestCommentBL {
	CommentController controller;
	public TestCommentBL(){
		controller = new CommentController();
	}
	public void test(){
		CommentInfo info1 = new CommentInfo(4,"这家酒店非常好",12344321,12);
		CommentInfo info2 = new CommentInfo(2,"这家酒店好烂",12344322,11);
		List<CommentInfo> list = new ArrayList<>();
		list.add(info1);
		list.add(info2);
		controller.setComments(list);
		CommentList clist;
		List<CommentInfo> infolist;
		clist = controller.getHotelInfo(11);
		infolist = clist.getList();
		for (int i=0;i<infolist.size();i++)
			System.out.println(infolist.get(i));
		CommentResultMessage result = controller.review(new CommentVO(2,"烂死了",12344321,11));
		System.out.println(result);
		clist = controller.getHotelInfo(11);
		infolist = clist.getList();
		for (int i=0;i<infolist.size();i++)
			System.out.println(infolist.get(i));
	}
	public static void main(String[] args) {
		TestCommentBL test = new TestCommentBL();
		test.test();
	}
}

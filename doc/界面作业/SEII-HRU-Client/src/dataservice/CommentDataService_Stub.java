package dataservice;

import po.CommentPO;

public class CommentDataService_Stub implements CommentDataService{

	@Override
	public void insert(CommentPO po) {
		// TODO Auto-generated method stub
		System.out.println("CommentDataService.insert SUCCESS");
	}

	@Override
	public CommentPO getInfo(long id) {
		// TODO Auto-generated method stub
		return new CommentPO();
	}

}

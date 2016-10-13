package businesslogic.CommentBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.CommentBLService.CommentBLService;
import businesslogicservice.CommentBLService.CommentResultMessage;
import vo.CommentVO;
import vo.OrderVO;

public class CommentBLService_Stub implements CommentBLService{

	@Override
	public List<CommentVO> getInfo(long hotelid) {
		// TODO Auto-generated method stub
		CommentVO vo = new CommentVO();
		List<CommentVO> list = new ArrayList<CommentVO>();
		list.add(vo);
		return list;
	}

	@Override
	public CommentResultMessage review(CommentVO vo) {
		// TODO Auto-generated method stub

		return CommentResultMessage.SUCCESS;
	}

}

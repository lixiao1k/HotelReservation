package businesslogicservice.CommentBLService;

import java.util.List;

import vo.CommentVO;

public interface CommentBLService {
	public List<CommentVO> getInfo(long hotelid);
	public CommentResultMessage review(CommentVO vo);
}

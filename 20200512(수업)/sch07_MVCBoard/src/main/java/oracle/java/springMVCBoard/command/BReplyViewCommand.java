package oracle.java.springMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import oracle.java.springMVCBoard.dao.BDao;
import oracle.java.springMVCBoard.dto.BDto;
public class BReplyViewCommand implements BCommand {
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		
		BDao dao = new BDao();
		BDto board = dao.reply_view(bId);
		
		model.addAttribute("reply_view", board);
	}

}

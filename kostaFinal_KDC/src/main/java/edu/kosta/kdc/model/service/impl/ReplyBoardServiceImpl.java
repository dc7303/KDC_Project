package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.ReplyBoardService;

@Service
public class ReplyBoardServiceImpl implements ReplyBoardService {

    @Autowired
    private ReplyBoardDAO replyBoardDAO;
    
    @Override
    public List<ReplyBoardDTO> selectAll(String title) {
        return replyBoardDAO.selectAll(title);
    }

    @Override
    public int insert(ReplyBoardDTO replyBoardDTO) {
        return replyBoardDAO.insert(replyBoardDTO);
    }

    @Override
    public ReplyBoardDTO selectByReplyBoardTitle(String replyBoardTitle, boolean state) {
        if(state) {
            int result = replyBoardDAO.readnumUpdate(replyBoardTitle);
            if(result==0) throw new RuntimeException("조회수 증가 오류 입니다.");
        }
        return replyBoardDAO.selectByReplyBoardTitle(replyBoardTitle);
    }

}

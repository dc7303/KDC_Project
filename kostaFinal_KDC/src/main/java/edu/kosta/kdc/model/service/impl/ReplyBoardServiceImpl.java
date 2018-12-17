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
    public int insertReply(ReplyBoardDTO replyBoardDTO) {
        return replyBoardDAO.insertReply(replyBoardDTO);
    }

    @Override
    public int insertHashTag(String hashTagName) {
        int result=0;
        String [] hashTags = hashTagName.replaceAll(" ", "").split(",");
        for(String s: hashTags) {
            result += replyBoardDAO.insertHashTag(s);
        }
        
        return result;
    }   

    @Override
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB, boolean state) {
        if(state) {
            int result = replyBoardDAO.readnumUpdate(replyBoardDTODB.getReplyBoardPk());
            if(result==0) throw new RuntimeException("��ȸ�� ���� ���� �Դϴ�.");
        }
        return replyBoardDAO.selectByReplyBoardPK(replyBoardDTODB);
    }


}

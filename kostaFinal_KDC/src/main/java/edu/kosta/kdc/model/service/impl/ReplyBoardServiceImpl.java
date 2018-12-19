package edu.kosta.kdc.model.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
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
    @Transactional
    public int insertReply(ReplyBoardDTO replyBoardDTO, String hashTagName) {
        String tagName = hashTagName.replaceAll(" ","");
        int rs=0;
        rs = replyBoardDAO.insertReply(replyBoardDTO);
        int result=0;
        if(rs!=0 && tagName.length()!=0) {
            String [] hashTags = tagName.split(",");

            for(String s: hashTags) {
                result += replyBoardDAO.insertHashTag(s);
            }
        }
        
        //if(rs==0) throw new KdcException(replyBoardDTO.getReplyBoardClassification() + "게시판 글 쓰기 오류");
        
        return 1;
    }

    @Override
    public int replyInsert(ReplyBoardDTO replyBoardDTO) {
        return replyBoardDAO.replyInsert(replyBoardDTO);
    }
    
    @Override
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB, boolean state) {
        if(state) {
            int result = replyBoardDAO.readnumUpdate(replyBoardDTODB.getReplyBoardPk());
            if(result==0) throw new KdcException("조회수 증가 오류 입니다.");
        }
        return replyBoardDAO.selectByReplyBoardPK(replyBoardDTODB);
    }

    @Override
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO, String hashTagName) {
        String tagName = hashTagName.replaceAll(" ","");
        int result=0;
        int rs=0;
        rs=replyBoardDAO.hashTagUpdateDelete(replyBoardDTO);
        replyBoardDAO.replyBoardUpdate(replyBoardDTO);
        if(rs!=0 && tagName.length()!=0) {
            String [] hashTags = tagName.split(",");

            for(String s: hashTags) {
                result += replyBoardDAO.hashTagUpdateInsert(replyBoardDTO, s);
            }
        }
        
        return 1;
    }

    @Override
    public int replyBoardDelete(int replyBoardPk) {
        replyBoardDAO.replyBoardDelete(replyBoardPk);
        replyBoardDAO.replyBoardReplyDelete(replyBoardPk);
        replyBoardDAO.replyBoardHashTagDelete(replyBoardPk);
        replyBoardDAO.replyBoardUpDownDelete(replyBoardPk);
        return 1;
    }

    @Override
    public int replyDelete(int replyBoardReplyPk) {
        return replyBoardDAO.replyDelete(replyBoardReplyPk);
    }

    @Override
    public int replyUpdate(ReplyBoardDTO replyBoardDTO) {
        return replyBoardDAO.replyUpdate(replyBoardDTO);
    }

    @Override
    public List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort) {
        return replyBoardDAO.replyBoardSelectAllOrderBy(classification, sort);
    }

    @Override
    public List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch,String classification) {
        List<ReplyBoardDTO> list = replyBoardDAO.replyBoardListSearch(department, boardSearch,classification);
        return list;
    }

}

package edu.kosta.kdc.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.dto.HashTagDTO;
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
    public int insertReply(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        int rs=0;
        rs = replyBoardDAO.insertReply(replyBoardDTO);
        int result=0;
        if(rs!=0 && hashTagName.length!=0) {

            for(String s: hashTagName) {
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
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        int result=0;
        int rs=0;
        replyBoardDAO.hashTagUpdateDelete(replyBoardDTO);
        rs = replyBoardDAO.replyBoardUpdate(replyBoardDTO);
        if(rs!=0 && hashTagName.length!=0) {
            for(String s: hashTagName) {
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

    @Override
    public int replyBoardLike(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardLike(replyBoardPk);
        return result;
    }
    
    @Override
    public int replyBoardDisLike(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardDisLike(replyBoardPk);
        return result;
    }
    
    @Override
    public List<String> hashtagSuggest(String keyword) {
        List<HashTagDTO> resultList = replyBoardDAO.hashtagSuggest(keyword);
        List<String> list = new ArrayList<String>();
        
        for(HashTagDTO dto:resultList) {
            list.add(dto.getHashTagName());
        }
        
        return list;
    }
    
    @Override
    public int replyBoardLikeCancle(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardLikeCancle(replyBoardPk);
        return result;
    }

    @Override
    public int reportPopInsert(String reportContents, int replyBoardPk, String otherWords) {
        
        int result=0;
        if(reportContents.length()!=0) {
            result = replyBoardDAO.reportPopInsert(reportContents, replyBoardPk);
        }else {
            result = replyBoardDAO.reportPopInsert(otherWords, replyBoardPk);
        }
        
        return result;
    }


}

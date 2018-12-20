package edu.kosta.kdc.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.dto.HashTagDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.ReplyBoardService;

@Service
public class ReplyBoardServiceImpl implements ReplyBoardService {

    @Autowired
    private ReplyBoardDAO replyBoardDAO;
    
    /**
     * selectAll(전체 정렬)
     * */
    @Override
    public List<ReplyBoardDTO> selectAll(String title) {
        return replyBoardDAO.selectAll(title);
    }

    /**
     * 정렬하여 select
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort) {
        return replyBoardDAO.replyBoardSelectAllOrderBy(classification, sort);
    }
    
    /**
     *  레코드 삽입
     */
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
        return rs;
    }

    /**
     * 레코드 삽입(댓글)
     * */
    @Override
    public int replyInsert(ReplyBoardDTO replyBoardDTO) {
        String revised = replyBoardDTO.getMentionNickName().replaceAll("@", "");
        replyBoardDTO.setMentionNickName(revised);
        return replyBoardDAO.replyInsert(replyBoardDTO);
    }
    
    /**
     * 게시글 제목에 해당하는 상세보기
     * @param: state true이면 조회수증가, false이면 조회증가안함.
     *                transaction으로 묶여야한다. 
     * */
    @Override
    @Transactional
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB, boolean state) {
        if(state) {
            int result = replyBoardDAO.readnumUpdate(replyBoardDTODB.getReplyBoardPk());
            if(result==0) throw new KdcException("조회수 증가 오류 입니다.");
        }
        return replyBoardDAO.selectByReplyBoardPK(replyBoardDTODB);
    }

    /**
     * 게시글 수정하기
     * */
    @Override
    @Transactional
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
        return rs;
    }
    
    /**
     * 댓글 수정하기
     * */
    @Override
    public int replyUpdate(ReplyBoardDTO replyBoardDTO) {
        return replyBoardDAO.replyUpdate(replyBoardDTO);
    }

    /**
     * 게시글 삭제하기
     * */
    @Override
    @Transactional
    public int replyBoardDelete(int replyBoardPk) {
        int result =0;
        result=replyBoardDAO.replyBoardDelete(replyBoardPk);
        if(result==0) throw new KdcException("삭제 실패했습니다.");
        
        result=replyBoardDAO.replyBoardReplyDelete(replyBoardPk);
        if(result==0) throw new KdcException("삭제 실패했습니다.");

        result=replyBoardDAO.replyBoardHashTagDelete(replyBoardPk);
        if(result==0) throw new KdcException("삭제 실패했습니다.");

        result=replyBoardDAO.replyBoardUpDownDelete(replyBoardPk);
        if(result==0) throw new KdcException("삭제 실패했습니다.");
        
        return result;
    }

    /**
     * 댓글 삭제 하기
     * */
    @Override
    public int replyDelete(int replyBoardReplyPk) {
        int result = replyBoardDAO.replyDelete(replyBoardReplyPk);
        return result;
    }

    /**
     * replyBoard게시판에서 조건별 검색하기
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch,String classification) {
        List<ReplyBoardDTO> list = replyBoardDAO.replyBoardListSearch(department, boardSearch,classification);
        return list;
    }

    /**
     * replyBoard 좋아요
     * */
    @Override
    public int replyBoardLike(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardLike(replyBoardPk);
        return result;
    }
    
    /**
     * replyBoard 싫어요
     * */
    @Override
    public int replyBoardDisLike(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardDisLike(replyBoardPk);
        return result;
    }
    
    /**
     * replyBoard 좋아요,싫어요 취소 기능
     * */
    @Override
    public int replyBoardLikeCancle(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardLikeCancle(replyBoardPk);
        return result;
    }
    
    /**
     * 해시태그 제안하기
     * */
    @Override
    public List<String> hashtagSuggest(String keyword) {
        List<HashTagDTO> resultList = replyBoardDAO.hashtagSuggest(keyword);
        List<String> list = new ArrayList<String>();
        
        for(HashTagDTO dto:resultList) {
            list.add(dto.getHashTagName());
        }
        
        return list;
    }
    
    /**
     * 신고 insert하기
     * */
    @Override
    @Transactional
    public int reportPopInsert(String reportContents, int replyBoardPkReport, String otherWords) {
        
        int result=0;
        if(reportContents.length()!=0) {
            result = replyBoardDAO.reportPopInsert(reportContents, replyBoardPkReport);
        }else {
            result = replyBoardDAO.reportPopInsert(otherWords, replyBoardPkReport);
        }
        
        return result;
    }

    /**
     * 멘션태그 제안하기
     * */
    @Override
    public List<String> mentionSuggest(String keyword) {
        
        String revised = keyword.replace("@", "");
        
        List<MemberDTO> resultList = replyBoardDAO.mentionSuggest(revised);
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@"+dto.getMemberNickName());
        }

        return list;
    }

    /**
     * 멘션태그 제안하기(맨션이 있어야 insert가 되야되서 모든 nickname가져옴)
     * */
    @Override
    public List<String> allNicknames() {
        
        List<MemberDTO> resultList = replyBoardDAO.allNicknames();
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@"+dto.getMemberNickName());
        }

        return list;
    }

}

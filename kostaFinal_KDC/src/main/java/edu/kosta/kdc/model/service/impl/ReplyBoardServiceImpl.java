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
        
        List<ReplyBoardDTO> list = replyBoardDAO.selectAll(title);
        if(list == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        return list;
    }

    /**
     * 정렬하여 select
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort) {
        
        List<ReplyBoardDTO> list = replyBoardDAO.replyBoardSelectAllOrderBy(classification, sort);
        if(list == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        return replyBoardDAO.replyBoardSelectAllOrderBy(classification, sort);
    }
    
    /**
     *  레코드 삽입
     */
    @Override
    @Transactional
    public int insertReply(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        
        int result = 0;
        //게시글 등록
        result = replyBoardDAO.insertReply(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("등록 실패입니다.");
        }
        
        //해시태그 등록
        if(hashTagName.length > 0) {
            for(String s: hashTagName) {
                result = replyBoardDAO.insertHashTag(s);
                if(result == 0) {
                    throw new KdcException("해시태그 등록 실패입니다.");
                }
            }
        }
        
        return result;
    }

    /**
     * 레코드 삽입(댓글)
     * */
    @Override
    public int replyInsert(ReplyBoardDTO replyBoardDTO) {
        
        //@ 공백으로 변환
        String revised = replyBoardDTO.getMentionNickName().replaceAll("@", "");
        //replace 값 set
        replyBoardDTO.setMentionNickName(revised);
        
        int result = 0;
        
        result = replyBoardDAO.replyInsert(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("댓글 입력 실패입니다.");
        }
        
        return result;
    }
    
    /**
     * 게시글 제목에 해당하는 상세보기
     * @param: state true이면 조회수증가, false이면 조회증가안함.
     *                transaction으로 묶여야한다. 
     * */
    @Override
    @Transactional
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTO, boolean state) {
        
        //게시글 조회
        List<ReplyBoardDTO> list = replyBoardDAO.selectByReplyBoardPK(replyBoardDTO);
        if(list == null) {
            throw new KdcException("게시글을 불러오는데 실패했습니다.");
        }
        
        //조회수 증가
        if(state) {
            int result = replyBoardDAO.readnumUpdate(replyBoardDTO.getReplyBoardPk());
            if(result == 0) {
                throw new KdcException("조회수 증가 오류 입니다.");
            }
        }
        
        return list;
    }

    /**
     * 게시글 수정하기
     * */
    @Override
    @Transactional
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        
        int result = 0;
        //해시태그 업데이트를 위한 삭제
        result = replyBoardDAO.hashTagUpdateDelete(replyBoardDTO);
        /*if(result == 0) {
            throw new KdcException("해시태그 삭제 실패로 오류발생");
        }*/
        
        //게시글 업데이트
        result = replyBoardDAO.replyBoardUpdate(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("게시글 수정 삭제입니다.");
        }
        
        //해시태그 재입력 
        if(hashTagName.length!=0) {
            for(String s: hashTagName) {
                result = replyBoardDAO.hashTagUpdateInsert(replyBoardDTO, s);
                if(result == 0) {
                    throw new KdcException("해시태그 재입력 실패로 오류발생");
                }
            }
        }
        
        return result;
    }
    
    /**
     * 댓글 수정하기
     * */
    @Override
    public int replyUpdate(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyUpdate(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("댓글 수정 실패");
        }
        
        return replyBoardDAO.replyUpdate(replyBoardDTO);
    }

    /**
     * 게시글 삭제하기
     * */
    @Override
    @Transactional
    public int replyBoardDelete(int replyBoardPk) {
        ReplyBoardDTO replyBoardDTO = new ReplyBoardDTO();
        int result =0;
        
        result=replyBoardDAO.replyBoardDelete(replyBoardPk);
        if(result==0) throw new KdcException("삭제 실패했습니다.");

        if(replyBoardDTO.getReplyBoardReplyNo()!=0) {
            result=replyBoardDAO.replyBoardReplyDelete(replyBoardPk);
            if(result==0) throw new KdcException("삭제 실패했습니다.");
        }
        
        //예외 처리하면 해시태그 없을때 삭제가 안됨(고쳐야함)
        result=replyBoardDAO.replyBoardHashTagDelete(replyBoardPk);
        
        //예외 처리하면 좋아요 없을때 삭제가 안됨(고쳐야함)    
        result=replyBoardDAO.replyBoardUpDownDelete(replyBoardPk);
        
        return result;
    }

    /**
     * 댓글 삭제 하기
     * */
    @Override
    public int replyDelete(int replyBoardReplyPk) {
        
        int result = 0;
        
        result = replyBoardDAO.replyDelete(replyBoardReplyPk);
        if(result == 0) {
            throw new KdcException("댓글 삭제 실패.");
        }
        
        return result;
    }

    /**
     * replyBoard게시판에서 조건별 검색하기
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch,String classification) {
        
        List<ReplyBoardDTO> list = replyBoardDAO.replyBoardListSearch(department, boardSearch,classification);
        
        if(list == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        return list;
    }

    /**
     * replyBoard 좋아요
     * */
    @Override
    public int replyBoardLike(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyBoardLike(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("좋아요 등록 실패했습니다.");
        }
        
        return result;
    }
    
    /**
     * replyBoard 싫어요
     * */
    @Override
    public int replyBoardDisLike(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyBoardDisLike(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("싫어요 등록 실패했습니다.");
        }
        return result;
    }
    
    /**
     * replyBoard 좋아요,싫어요 취소 기능
     * */
    @Override
    public int replyBoardLikeCancle(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyBoardLikeCancle(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("취소 실패입니다.");
        }
        
        return result;
    }
    
    /**
     * 해시태그 제안하기
     * */
    @Override
    public List<String> hashtagSuggest(String keyword) {
        
        //해시태그 DTO
        List<HashTagDTO> resultList = replyBoardDAO.hashtagSuggest(keyword);
        if(resultList == null) {
            throw new KdcException("해시태그가 존재하지 않습니다.");
        }
        
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
    public int reportPopInsert(String reportContents, int replyBoardPkReport, String otherWords, String memberId) {
        
        int result = 0;
        
        if(reportContents.length()!=0) {
            result = replyBoardDAO.reportPopInsert(reportContents, replyBoardPkReport,memberId);
            if(result == 0) {
                throw new KdcException("등록 실패입니다.");
            }
        }else {
            result = replyBoardDAO.reportPopInsert(otherWords, replyBoardPkReport,memberId);
            if(result == 0) {
                throw new KdcException("등록 실패입니다.");
            }
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
        if(resultList == null) {
            throw new KdcException("리스트를 불러오는데 실패했습니다.");
        }
        
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@" + dto.getMemberNickName());
        }

        return list;
    }

    /**
     * 멘션태그 제안하기(맨션이 있어야 insert가 되야되서 모든 nickname가져옴)
     * */
    @Override
    public List<String> allNicknames() {
        
        List<MemberDTO> resultList = replyBoardDAO.allNicknames();
        if(resultList == null) {
            throw new KdcException();
        }
        
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@"+dto.getMemberNickName());
        }

        return list;
    }

    /**
     * 메인화면에 띄울 tech 게시판 게시글 5개 가져오기
     * */
    @Override
    public List<ReplyBoardDTO> selectFiveByTitle(String title) {
        
        List<ReplyBoardDTO> fiveSelectList = replyBoardDAO.selectFiveByTitle(title);
        if(fiveSelectList == null) {
            throw new KdcException("게시글이 존재하지 않습니다.");
        }
        
        return fiveSelectList;
    }

}

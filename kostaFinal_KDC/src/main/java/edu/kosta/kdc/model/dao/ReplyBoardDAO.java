package edu.kosta.kdc.model.dao;

import java.util.List;
import java.util.Map;

import edu.kosta.kdc.model.dto.HashTagDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;

public interface ReplyBoardDAO {
    
    /**
     * selectAll(전체 정렬)
     * */
    List<ReplyBoardDTO> selectAll(Map<String, Object> map);

    /**
     * 정렬하여 select
     * */
    List<ReplyBoardDTO> replyBoardSelectAllOrderBy(Map<String, Object> map);
    
    /**
     * 레코드 삽입
     * 1)게시글 insert
     * 2)해시태그 insert
     * 3)게시글의 댓글 insert
     */
    int insertReply(ReplyBoardDTO replyBoardDTO);

    int insertHashTag(String hashTags);
    
    int replyInsert(ReplyBoardDTO replyBoardDTO);
    
    /**
     * 게시글 눌럿을때 조회수 올리기
     * */
    int readnumUpdate(int replyBoardPk);

    /**
     * 게시글 제목에 해당하는 상세보기
     * */
    List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTO);

    /**
     * 게시글 수정하기
     * */
    int replyBoardUpdate(ReplyBoardDTO replyBoardDTO);

    /**
     * 댓글 수정하기
     * */
    int replyUpdate(ReplyBoardDTO replyBoardDTO);
    
   /**
    * 게시글 수정시 해시태그 삭제
    * */
    int hashTagUpdateDelete(ReplyBoardDTO replyBoardDTO);
    
    /**
     * 게시글 수정후 해시태그 다시 insert
     * */
    int hashTagUpdateInsert(ReplyBoardDTO replyBoardDTO, String hashTagName);

    /**
     * 게시글 삭제하기
     * */
    int replyBoardDelete(int replyBoardPk);
    
    /**
     * 게시글의 댓글 삭제하기
     * */
    int replyBoardReplyDelete(int replyBoardPk);
    
    /**
     * 게시글의 해시태그 삭제하기
     * */
    int replyBoardHashTagDelete(int replyBoardPk);
    
    /**
     * 게시글의 좋아요수 삭제하기
     * */
    int replyBoardUpDownDelete(int replyBoardPk);
    
    /**
     * 댓글 삭제 하기
     * */
    int replyDelete(int replyBoardReplyPk);

    /**
     * replyBoard게시판에서 조건별 검색
     * */
    List<ReplyBoardDTO> replyBoardListSearch(Map<String, Object> map);

    /**
     * replyBoard좋아요 기능
     * */
    int replyBoardLike(ReplyBoardDTO replyBoardDTO);

    /**
     * replyBoard싫어요 기능
     * */
    int replyBoardDisLike(ReplyBoardDTO replyBoardDTO);
    
    /**
     * replyBoard 좋아요, 싫어요 취소 기능
     * */
    int replyBoardLikeCancle(ReplyBoardDTO replyBoardDTO);
    
    /**
     * 해시태그 제안하기
     * */
    List<HashTagDTO> hashtagSuggest(String keyword);
    
    /**
     * 신고하기 insert(radio박스에 있는거 체크할시)
     * */
    int reportPopInsert(String reportContents, int replyBoardPk, String memberId);
    
    /**
     * 신고하기 insert(radio박스에서 기타를 선택했을 경우)
     * */
    int reportPopOtherInsert(String otherWords, int replyBoardPkReport, String memberId);
    
    /**
     * 멘션태그 제안하기
     * */
    List<MemberDTO> mentionSuggest(String keyword);
    
    /**
     * 모든 닉네임 가오기
     * */
    List<MemberDTO> allNicknames();

    /**
     * classification 기준으로 컬럼 수량 가져오기.
     *
     * @param classification
     * @return
     */
    public int boardQuantityByClassification(String classification);

    /**
     * 메인화면에 띄울 댓글있는 게시판 게시글 5개 가져오기
     * @param String 
     * */
    List<ReplyBoardDTO> selectFiveByTitle(String title);

    /**
     * 게시판 종류별 검색된 전체 게시물 수 가져오기
     */
    int boardQuantityByClassificationwithSearch(Map<String, Object> map);

}

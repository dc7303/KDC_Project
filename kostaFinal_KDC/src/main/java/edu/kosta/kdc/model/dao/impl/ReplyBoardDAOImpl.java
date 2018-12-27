package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.dto.HashTagDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;

@Repository
public class ReplyBoardDAOImpl implements ReplyBoardDAO {

    @Autowired
    private SqlSession session;

    /**
     * selectAll(전체 정렬)
     */
    @Override
    public List<ReplyBoardDTO> selectAll(String title) {

        return session.selectList("replyBoardMapper.replyBoardSelectAll", title);
    }

    /**
     * 정렬하여 select
     */
    @Override
    public List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort) {

        Map<String, Object> map = new HashMap<>();
        map.put("classification", classification);
        map.put("sort", sort);

        return session.selectList("replyBoardMapper.replyBoardSelectAllOrderBy", map);
    }

    /**
     * 레코드 삽입 1)게시글 insert 2)해시태그 insert 3)게시글의 댓글 insert
     */
    @Override
    public int insertReply(ReplyBoardDTO replyBoardDTO) {

        return session.insert("replyBoardMapper.replyBoardInsert", replyBoardDTO);
    }

    @Override
    public int insertHashTag(String hashTagName) {

        return session.insert("replyBoardMapper.hashTagInsert", hashTagName);
    }

    @Override
    public int replyInsert(ReplyBoardDTO replyBoardDTO) {
        System.out.println(replyBoardDTO.getMentionNickName());
        System.out.println(replyBoardDTO.getReplyBoardContents());
        return session.insert("replyBoardMapper.replyInsert", replyBoardDTO);
    }

    /**
     * 게시글 눌럿을때 조회수 올리기
     */
    @Override
    public int readnumUpdate(int replyBoardPk) {

        return session.update("replyBoardMapper.readnumUpdate", replyBoardPk);
    }

    /**
     * 게시글 제목에 해당하는 상세보기
     */
    @Override
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTO) {

        return session.selectList("replyBoardMapper.boardByModelNum", replyBoardDTO);
    }

    /**
     * 게시글 수정하기
     */
    @Override
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO) {

        return session.update("replyBoardMapper.replyBoardUpdate", replyBoardDTO);
    }

    /**
     * 댓글 수정하기
     */
    @Override
    public int replyUpdate(ReplyBoardDTO replyBoardDTO) {

        return session.update("replyBoardMapper.replyUpdate", replyBoardDTO);
    }

    /**
     * 게시글 수정시 해시태그 삭제
     */
    @Override
    public int hashTagUpdateDelete(ReplyBoardDTO replyBoardDTO) {

        return session.update("replyBoardMapper.hashTagUpdateDelete", replyBoardDTO);
    }

    /**
     * 게시글 수정후 해시태그 다시 insert
     */
    @Override
    public int hashTagUpdateInsert(ReplyBoardDTO replyBoardDTO, String hashTagName) {

        Map<String, Object> map = new HashMap<>();
        map.put("replyBoardPk", replyBoardDTO.getReplyBoardPk());
        map.put("hashTags", hashTagName);

        return session.insert("replyBoardMapper.hashTagUpdateInsert", map);
    }

    /**
     * 게시글 삭제하기
     */
    @Override
    public int replyBoardDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardDelete", replyBoardPk);
    }

    /**
     * 게시글의 댓글 삭제하기
     */
    @Override
    public int replyBoardReplyDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardReplyDelete", replyBoardPk);
    }

    /**
     * 게시글의 해시태그 삭제하기
     */
    @Override
    public int replyBoardHashTagDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardHashTagDelete", replyBoardPk);
    }

    /**
     * 게시글의 좋아요수 삭제하기
     */
    @Override
    public int replyBoardUpDownDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardUpDownDelete", replyBoardPk);
    }

    /**
     * 댓글 삭제 하기
     */
    @Override
    public int replyDelete(int replyBoardReplyPk) {

        return session.update("replyBoardMapper.replyDelete", replyBoardReplyPk);
    }

    /**
     * replyBoard게시판에서 조건별 검색
     */
    @Override
    public List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch, String classification) {

        Map<String, Object> map = new HashMap<>();

        map.put("department", department);
        map.put("boardSearch", boardSearch);
        map.put("classification", classification);

        return session.selectList("replyBoardMapper.replyBoardListSearch", map);
    }

    /**
     * replyBoard좋아요 기능
     */
    @Override
    public int replyBoardLike(ReplyBoardDTO replyBoardDTO) {

        return session.insert("replyBoardMapper.replyBoardLike", replyBoardDTO);
    }

    /**
     * replyBoard싫어요 기능
     */
    @Override
    public int replyBoardDisLike(ReplyBoardDTO replyBoardDTO) {

        return session.insert("replyBoardMapper.replyBoardDisLike", replyBoardDTO);
    }

    /**
     * replyBoard 좋아요, 싫어요 취소 기능
     */
    @Override
    public int replyBoardLikeCancle(ReplyBoardDTO replyBoardDTO) {

        return session.delete("replyBoardMapper.replyBoardLikeCancle", replyBoardDTO);
    }

    /**
     * 해시태그 제안하기
     */
    @Override
    public List<HashTagDTO> hashtagSuggest(String keyword) {

        return session.selectList("replyBoardMapper.hashtagSuggest", keyword);
    }

    /**
     * 신고하기 insert(radio박스에 있는거 체크할시)
     */
    @Override
    public int reportPopInsert(String reportContents, int replyBoardPkReport, String memberId) {
        Map<String, Object> map = new HashMap<>();
        map.put("reportContents", reportContents);
        map.put("replyBoardPk", replyBoardPkReport);
        map.put("memberId", memberId);
        return session.insert("replyBoardMapper.reportPopInsert", map);
    }

    /**
     * 신고하기 insert(radio박스에서 기타를 선택했을 경우)
     */
    @Override
    public int reportPopOtherInsert(String otherWords, int replyBoardPkReport, String memberId) {

        Map<String, Object> map = new HashMap<>();
        map.put("replyBoardPk", replyBoardPkReport);
        map.put("reportContents", otherWords);
        map.put("memberId", memberId);
        return session.insert("replyBoardMapper.reportPopInsert", map);
    }

    /**
     * 멘션태그 제안하기
     */
    @Override
    public List<MemberDTO> mentionSuggest(String keyword) {

        List<MemberDTO> list = session.selectList("replyBoardMapper.nicknameSuggest", keyword);

        return list;
    }

    /**
     * 모든 닉네임 가오기
     */
    @Override
    public List<MemberDTO> allNicknames() {
        
        List<MemberDTO> list = session.selectList("replyBoardMapper.allnicknames");
        
        return list;
    }

    /**
     * 메인화면에 띄울 댓글있는 게시판 게시글 5개 가져오기
     * */
    @Override
    public List<ReplyBoardDTO> selectFiveByTitle(String title) {
        
        return session.selectList("replyBoardMapper.selectFive", title);
    }

}

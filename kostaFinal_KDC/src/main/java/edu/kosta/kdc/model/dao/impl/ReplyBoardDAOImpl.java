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
     * selectAll(��ü ����)
     */
    @Override
    public List<ReplyBoardDTO> selectAll(String title) {

        return session.selectList("replyBoardMapper.replyBoardSelectAll", title);
    }

    /**
     * �����Ͽ� select
     */
    @Override
    public List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort) {

        Map<String, Object> map = new HashMap<>();
        map.put("classification", classification);
        map.put("sort", sort);

        return session.selectList("replyBoardMapper.replyBoardSelectAllOrderBy", map);
    }

    /**
     * ���ڵ� ���� 1)�Խñ� insert 2)�ؽ��±� insert 3)�Խñ��� ��� insert
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
     * �Խñ� �������� ��ȸ�� �ø���
     */
    @Override
    public int readnumUpdate(int replyBoardPk) {

        return session.update("replyBoardMapper.readnumUpdate", replyBoardPk);
    }

    /**
     * �Խñ� ���� �ش��ϴ� �󼼺���
     */
    @Override
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTO) {

        return session.selectList("replyBoardMapper.boardByModelNum", replyBoardDTO);
    }

    /**
     * �Խñ� �����ϱ�
     */
    @Override
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO) {

        return session.update("replyBoardMapper.replyBoardUpdate", replyBoardDTO);
    }

    /**
     * ��� �����ϱ�
     */
    @Override
    public int replyUpdate(ReplyBoardDTO replyBoardDTO) {

        return session.update("replyBoardMapper.replyUpdate", replyBoardDTO);
    }

    /**
     * �Խñ� ������ �ؽ��±� ����
     */
    @Override
    public int hashTagUpdateDelete(ReplyBoardDTO replyBoardDTO) {

        return session.update("replyBoardMapper.hashTagUpdateDelete", replyBoardDTO);
    }

    /**
     * �Խñ� ������ �ؽ��±� �ٽ� insert
     */
    @Override
    public int hashTagUpdateInsert(ReplyBoardDTO replyBoardDTO, String hashTagName) {

        Map<String, Object> map = new HashMap<>();
        map.put("replyBoardPk", replyBoardDTO.getReplyBoardPk());
        map.put("hashTags", hashTagName);

        return session.insert("replyBoardMapper.hashTagUpdateInsert", map);
    }

    /**
     * �Խñ� �����ϱ�
     */
    @Override
    public int replyBoardDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardDelete", replyBoardPk);
    }

    /**
     * �Խñ��� ��� �����ϱ�
     */
    @Override
    public int replyBoardReplyDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardReplyDelete", replyBoardPk);
    }

    /**
     * �Խñ��� �ؽ��±� �����ϱ�
     */
    @Override
    public int replyBoardHashTagDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardHashTagDelete", replyBoardPk);
    }

    /**
     * �Խñ��� ���ƿ�� �����ϱ�
     */
    @Override
    public int replyBoardUpDownDelete(int replyBoardPk) {

        return session.update("replyBoardMapper.replyBoardUpDownDelete", replyBoardPk);
    }

    /**
     * ��� ���� �ϱ�
     */
    @Override
    public int replyDelete(int replyBoardReplyPk) {

        return session.update("replyBoardMapper.replyDelete", replyBoardReplyPk);
    }

    /**
     * replyBoard�Խ��ǿ��� ���Ǻ� �˻�
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
     * replyBoard���ƿ� ���
     */
    @Override
    public int replyBoardLike(ReplyBoardDTO replyBoardDTO) {

        return session.insert("replyBoardMapper.replyBoardLike", replyBoardDTO);
    }

    /**
     * replyBoard�Ⱦ�� ���
     */
    @Override
    public int replyBoardDisLike(ReplyBoardDTO replyBoardDTO) {

        return session.insert("replyBoardMapper.replyBoardDisLike", replyBoardDTO);
    }

    /**
     * replyBoard ���ƿ�, �Ⱦ�� ��� ���
     */
    @Override
    public int replyBoardLikeCancle(ReplyBoardDTO replyBoardDTO) {

        return session.delete("replyBoardMapper.replyBoardLikeCancle", replyBoardDTO);
    }

    /**
     * �ؽ��±� �����ϱ�
     */
    @Override
    public List<HashTagDTO> hashtagSuggest(String keyword) {

        return session.selectList("replyBoardMapper.hashtagSuggest", keyword);
    }

    /**
     * �Ű��ϱ� insert(radio�ڽ��� �ִ°� üũ�ҽ�)
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
     * �Ű��ϱ� insert(radio�ڽ����� ��Ÿ�� �������� ���)
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
     * ����±� �����ϱ�
     */
    @Override
    public List<MemberDTO> mentionSuggest(String keyword) {

        List<MemberDTO> list = session.selectList("replyBoardMapper.nicknameSuggest", keyword);

        return list;
    }

    /**
     * ��� �г��� ������
     */
    @Override
    public List<MemberDTO> allNicknames() {
        
        List<MemberDTO> list = session.selectList("replyBoardMapper.allnicknames");
        
        return list;
    }

    /**
     * ����ȭ�鿡 ��� ����ִ� �Խ��� �Խñ� 5�� ��������
     * */
    @Override
    public List<ReplyBoardDTO> selectFiveByTitle(String title) {
        
        return session.selectList("replyBoardMapper.selectFive", title);
    }

}

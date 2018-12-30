package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.emailSend.EmailForm;
import edu.kosta.kdc.model.dao.MemberDAO;
import edu.kosta.kdc.model.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

    /**
     * ��� ���̵� üũ
     */
    @Override
    public MemberDTO memberSelectByMemberId(String memberId) {

        return sqlSession.selectOne("memberMapper.selectByMemberId", memberId);
    }

    /**
     * ��� �г��� üũ
     */
    @Override
    public MemberDTO memberSelectByMemberNickName(String memberNickName) {
        
        return sqlSession.selectOne("memberMapper.selectByMemberNickName", memberNickName);
    }
    
    @Autowired
    private SqlSession sqlSession;
    
    /**
     * ȸ������
     */
    @Override
    public int memberInsert(MemberDTO memberDTO) {
        
        return sqlSession.insert("memberMapper.insert", memberDTO);
    }

    /**
     * ��� ���� ����
     */
    @Override
    public int updateByMemberInfo(MemberDTO memberDTO) {
        
        return sqlSession.update("memberMapper.updateByMemberInfo", memberDTO);
    }

    /**
     * ȸ��Ż��
     */
    @Override
    public int updateByIsWithDrawal(String memberId) {
        
        return sqlSession.update("memberMapper.updateByIsWithDrawal", memberId);
    }
    
    /**
     * ��� ȸ�� ���� �������� ( ����¡ ó�� )
     * */
    @Override
    public List<MemberDTO> memberSelectAll(int firstColumnRange, int lastColumnRange) {
        
        Map<String, Integer> map = new HashMap<>();
        
        map.put("firstColumn", firstColumnRange);
        map.put("lastColumn", lastColumnRange);
        
        return sqlSession.selectList("memberMapper.memberPagingSelect", map);
        
    }

    /**
     * ��� ��ü �� ��������
     */
    @Override
    public int memberTotalCount() {
        
        return sqlSession.selectOne("memberMapper.memberTotalCount");
    }
    
    

    /**
     * �ӽú�й�ȣ db�� update���ֱ�
     * */
    @Override
    public int updatePwdByEmail(String encodePwd, String email) {
        Map<String, String> map = new HashMap<>();
        map.put("memberPwd", encodePwd);
        map.put("memberEmail", email);
        
        return sqlSession.update("memberMapper.updatePwdByEmail",map);
    }

    /**
     * ��� �̸��� üũ
     */
    @Override
    public MemberDTO memberByEmailCheck(String emailCheck) {
        
        return sqlSession.selectOne("memberMapper.memberByEmailCheck", emailCheck);
    }
    
    /**
     * ��� Ű���� �˻� ���� ��������
     */
    @Override
    public int memberSelectByKewordQuntity(String keyword, String word) {
        
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("word", word);

        return sqlSession.selectOne("memberMapper.memberSelectByKeywordQuantity", map);
    }

    /**
     * ��� Ű����� �˻�
     */
    @Override
    public List<MemberDTO> memberSelectByKeyword(String keyword, String word, int firstColumnRange,
            int lastColumnRange) {
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("keyword", keyword);
        map.put("word", word);
        map.put("firstColumn", firstColumnRange);
        map.put("lastColumn", lastColumnRange);
        
        return sqlSession.selectList("memberMapper.memberPagingSelectByKeyword", map);
    }


}

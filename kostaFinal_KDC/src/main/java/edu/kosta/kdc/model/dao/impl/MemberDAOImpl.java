package edu.kosta.kdc.model.dao.impl;

import java.util.List;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
     * ��� ȸ�� ���� ��������
     * */
    @Override
    public List<MemberDTO> memberSelectAll() {
        
        return sqlSession.selectList("memberMapper.memberSelectAll");
        
    }

}

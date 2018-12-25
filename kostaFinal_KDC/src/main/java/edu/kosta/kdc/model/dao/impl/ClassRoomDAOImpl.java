package edu.kosta.kdc.model.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ClassRoomDAO;
import edu.kosta.kdc.model.dto.ClassRoomDTO;

@Repository
public class ClassRoomDAOImpl implements ClassRoomDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * ���� ����Ʈ�� ������ Ŭ������ ��������
     */
    @Override
    public ClassRoomDTO currentClassSelectByMemberId(String memberId) {
        
        return sqlSession.selectOne("classRoomMapper.selectCurrentCode", memberId);
    }

}

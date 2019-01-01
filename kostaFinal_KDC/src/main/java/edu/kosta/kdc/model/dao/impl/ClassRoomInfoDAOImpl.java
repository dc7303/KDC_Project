package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ClassRoomInfoDAO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.dto.MemberDTO;


@Repository
public class ClassRoomInfoDAOImpl implements ClassRoomInfoDAO {

    @Autowired
    private SqlSession sqlSession;
    
    /**
     * Ŭ���� ��ȸ
     */
    @Override
    public List<ClassRoomInfoDTO> classList(String id) {
        
        List<ClassRoomInfoDTO> list = sqlSession.selectList("classRoomInfoMapper.classList", id);
        
        return list;
        
    }
    
    /**
     * ���� - Ŭ���� �� ����
     * */
    @Override
    public int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        return sqlSession.insert("classRoomInfoMapper.createClassRoom", classRoomInfoDTO);
    }

    /**
     * ���� - ���� ���̵� üũ(ajax)
     * */
    @Override
    public String teacherCheck(String teacherId) {

        MemberDTO dto = sqlSession.selectOne("memberMapper.selectByMemberId", teacherId);
        
        if(dto == null) {
            return "�������� �ʴ� ���̵��Դϴ�.";
        }else {
            return "�����ϴ� �����Դϴ�.";
        }
    }

    /**
     * Ŭ������ ������ �������� �� ä�� ���� ������ ���� ���ϸ��� �������� �޼ҵ�
     * */
    @Override
    public String selectChatFileName(ClassRoomInfoDTO classRoomInfoDTO) {
        
        return sqlSession.selectOne("classRoomInfoMapper.selectChatFileName", classRoomInfoDTO);
    }

    /**
     * Ŭ������ �ڵ尡 �ִ��� üũ�ϴ� �޼ҵ�
     * */
    @Override
    public int codeCheck(String classRoomCode) {
        
        ClassRoomInfoDTO classRoomInfoDTO = sqlSession.selectOne("classRoomInfoMapper.codeCheck", classRoomCode);
        if(classRoomInfoDTO == null) {
            return 0;
        }else {
            return 1;
        }
        
    }

}

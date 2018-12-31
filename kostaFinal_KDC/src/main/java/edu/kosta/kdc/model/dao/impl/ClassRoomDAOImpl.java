package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ClassRoomDAO;
import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

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

    /**
     * ���������� - member�� Ŭ���� �ڵ� �Է��ϸ� DB�� �ش� ������ �ڵ带 �Է½����ִ� �޼ҵ�
     * */
    @Override
    public int insertMyClassRoom(ClassRoomDTO classRoomDTO) {
        
        return sqlSession.insert("classRoomMapper.insertMyClassRoom", classRoomDTO);
    }

    /**
     * ���������� - �̹� ���̵� ��ϵ� �ڵ����� �˻����ִ� �޼ҵ�
     * */
    @Override
    public int selectMyClassRoomCodeByClassRoomDTO(ClassRoomDTO classRoomDTO) {
        
        ClassRoomDTO dto = sqlSession.selectOne("classRoomMapper.selectMyClassRoomCodeByClassRoomDTO", classRoomDTO);
        
        if(dto == null) {
            return 1;
        }else {
            return 0;
        }
        
    }

    /**
     *  myClassRoom ���������� radio ��ư ���� ���� �� �ش� �ڵ�� CurrentClass = True �� �ٲٴ� �޼ҵ�
     * */
    @Override
    public int defaultClassSet(ClassRoomDTO classRoomDTO) {
        
        return sqlSession.update("classRoomMapper.defaultClassSet", classRoomDTO);
    }

    /**
     * classRoomIsCurrent = 'TRUE' �� Ŭ������DTO ��������
     * */
    @Override
    public List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId) {
        
        return sqlSession.selectList("classRoomInfoMapper.selectCurrentClassRoom", memberId);
    }

    /**
     * ������Ʈ�� defaultClass�� �ٲٱ� ���� �ٸ� Class�� CurrentClass=False�� �ٲٴ� �޼ҵ�.
     * */
    @Override
    public int updateOtherCurrentClass(ClassRoomDTO classRoomDTO) {
       
        return sqlSession.update("classRoomMapper.updateOtherCurrentClass", classRoomDTO);
    }

}

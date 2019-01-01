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
     * 클래스 조회
     */
    @Override
    public List<ClassRoomInfoDTO> classList(String id) {
        
        List<ClassRoomInfoDTO> list = sqlSession.selectList("classRoomInfoMapper.classList", id);
        
        return list;
        
    }
    
    /**
     * 강사 - 클래스 룸 생성
     * */
    @Override
    public int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        return sqlSession.insert("classRoomInfoMapper.createClassRoom", classRoomInfoDTO);
    }

    /**
     * 강사 - 강사 아이디 체크(ajax)
     * */
    @Override
    public String teacherCheck(String teacherId) {

        MemberDTO dto = sqlSession.selectOne("memberMapper.selectByMemberId", teacherId);
        
        if(dto == null) {
            return "존재하지 않는 아이디입니다.";
        }else {
            return "존재하는 강사입니다.";
        }
    }

    /**
     * 클래스룸 생성이 성공했을 때 채팅 파일 생성을 위한 파일명을 가져오는 메소드
     * */
    @Override
    public String selectChatFileName(ClassRoomInfoDTO classRoomInfoDTO) {
        
        return sqlSession.selectOne("classRoomInfoMapper.selectChatFileName", classRoomInfoDTO);
    }

    /**
     * 클래스룸 코드가 있는지 체크하는 메소드
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

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
     * 관리자 - 클래스 룸 생성
     * */
    @Override
    public int createClassRoom(ClassRoomInfoDTO classRoomInfoDTO) {
        
        return sqlSession.insert("classRoomInfoMapper.createClassRoom", classRoomInfoDTO);
    }

    /**
     * 관리자 - 클래스 코드 중복 체크(ajax)
     * */
    @Override
    public String codeCheck(String classRoomCode) {
        
        ClassRoomInfoDTO dto = sqlSession.selectOne("classRoomInfoMapper.codeCheck", classRoomCode);
        if(dto == null) {
            return "사용 가능합니다";
        }else {
            return "중복된 코드입니다";
        }
    }

    /**
     * 관리자 - 강사 아이디 체크(ajax)
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
     * 관리자 - 풀캘린더에 들어갈 클래스 일정 모두 가져오기
     * */
    @Override
    public List<ClassRoomInfoDTO> getClassInfo() {
        
        return sqlSession.selectList("classRoomInfoMapper.getClassInfo");
    }

}

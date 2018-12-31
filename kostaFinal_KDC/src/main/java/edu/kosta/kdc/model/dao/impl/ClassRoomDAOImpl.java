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
     * 현재 디폴트로 설정된 클래스룸 가져오기
     */
    @Override
    public ClassRoomDTO currentClassSelectByMemberId(String memberId) {
        
        return sqlSession.selectOne("classRoomMapper.selectCurrentCode", memberId);
    }

    /**
     * 마이페이지 - member가 클래스 코드 입력하면 DB에 해당 유저와 코드를 입력시켜주는 메소드
     * */
    @Override
    public int insertMyClassRoom(ClassRoomDTO classRoomDTO) {
        
        return sqlSession.insert("classRoomMapper.insertMyClassRoom", classRoomDTO);
    }

    /**
     * 마이페이지 - 이미 아이디에 등록된 코드인지 검사해주는 메소드
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
     *  myClassRoom 페이지에서 radio 버튼 선택 했을 때 해당 코드로 CurrentClass = True 로 바꾸는 메소드
     * */
    @Override
    public int defaultClassSet(ClassRoomDTO classRoomDTO) {
        
        return sqlSession.update("classRoomMapper.defaultClassSet", classRoomDTO);
    }

    /**
     * classRoomIsCurrent = 'TRUE' 인 클래스룸DTO 가져오기
     * */
    @Override
    public List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId) {
        
        return sqlSession.selectList("classRoomInfoMapper.selectCurrentClassRoom", memberId);
    }

    /**
     * 업데이트로 defaultClass를 바꾸기 전에 다른 Class의 CurrentClass=False로 바꾸는 메소드.
     * */
    @Override
    public int updateOtherCurrentClass(ClassRoomDTO classRoomDTO) {
       
        return sqlSession.update("classRoomMapper.updateOtherCurrentClass", classRoomDTO);
    }

}

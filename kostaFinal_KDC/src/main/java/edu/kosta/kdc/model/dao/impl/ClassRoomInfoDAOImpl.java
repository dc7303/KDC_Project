package edu.kosta.kdc.model.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.ClassRoomInfoDAO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

@Repository
public class ClassRoomInfoDAOImpl implements ClassRoomInfoDAO {

    @Autowired
    private SqlSession session;
    
    @Override
    public List<ClassRoomInfoDTO> classList(String id) {
        
        List<ClassRoomInfoDTO> list = session.selectList("classRoomInfoMapper.classList", id);
        
        return list;
        
    }
    
    @Override
    public void classCreate(ClassRoomInfoDTO dto) {
        
        System.out.println(dto.getClassRoomCode());
        
        session.insert("classRoomInfoMapper.classCreate", dto);
        
    }

}

package edu.kosta.kdc.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.WrittenBoardListDAO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.WrittenBoardListService;

@Service
public class WrittenBoardListServiceImpl implements WrittenBoardListService {

    @Autowired
    private WrittenBoardListDAO writtenBoardListDAO;
    
    @Override
    public List<ReplyBoardDTO> writtenBoardList(String id) {

        return writtenBoardListDAO.writtenBoardList(id);
        
    }
    
    @Override
    public void delelteWrittenBoard(int replyBoardPk) {
        
        writtenBoardListDAO.delelteWrittenBoard(replyBoardPk);
        
    }

}

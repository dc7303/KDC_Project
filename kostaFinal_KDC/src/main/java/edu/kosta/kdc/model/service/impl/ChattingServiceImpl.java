package edu.kosta.kdc.model.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dao.ChattingDAO;
import edu.kosta.kdc.model.dao.impl.ChattingDAOImpl;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;
import edu.kosta.kdc.model.service.ChattingService;
import edu.kosta.kdc.util.KdcException;

@Service
public class ChattingServiceImpl implements ChattingService {

    @Autowired
    private HttpSession session;
    
    @Autowired
    private ChattingDAO dao;
    
    //로그인한 사용자에 해당하는 현재클래스룸 정보 조회
    @Override
    public ClassRoomInfoDTO infoSelectByMemberId(String memberId) {
        ClassRoomInfoDTO result = dao.infoSelectByMemberId(memberId);
        if(result==null) throw new KdcException("클래스 코드가 등록되어있지 않습니다.");
        return result;
    }

    //스케쥴러를 통한 채팅로그 삭제
    @Scheduled(cron="0 59 23 * * ?")
    @Override
    public void initChatLog() {
        List<ClassRoomInfoDTO> infoList = dao.infoListSelect();
        for(ClassRoomInfoDTO info:infoList) {
            System.out.println(info);
            String filePath = info.getClassRoomInfoChatFile();
            
            //fileName에 해당하는 파일내용 초기화
            BufferedWriter file;
            try {
                file = new BufferedWriter(new FileWriter(filePath));
                file.close();
            } catch (IOException e) {
                System.out.println("채팅로그 삭제 실패");
                e.printStackTrace();
            }
        }
        System.out.println("채팅로그 초기화 완료");
    }

}

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
    
    //�α����� ����ڿ� �ش��ϴ� ����Ŭ������ ���� ��ȸ
    @Override
    public ClassRoomInfoDTO infoSelectByMemberId(String memberId) {
        ClassRoomInfoDTO result = dao.infoSelectByMemberId(memberId);
        if(result==null) throw new KdcException("Ŭ���� �ڵ尡 ��ϵǾ����� �ʽ��ϴ�.");
        return result;
    }

    //�����췯�� ���� ä�÷α� ����
    @Scheduled(cron="0 59 23 * * ?")
    @Override
    public void initChatLog() {
        List<ClassRoomInfoDTO> infoList = dao.infoListSelect();
        for(ClassRoomInfoDTO info:infoList) {
            System.out.println(info);
            String filePath = info.getClassRoomInfoChatFile();
            
            //fileName�� �ش��ϴ� ���ϳ��� �ʱ�ȭ
            BufferedWriter file;
            try {
                file = new BufferedWriter(new FileWriter(filePath));
                file.close();
            } catch (IOException e) {
                System.out.println("ä�÷α� ���� ����");
                e.printStackTrace();
            }
        }
        System.out.println("ä�÷α� �ʱ�ȭ �Ϸ�");
    }

}

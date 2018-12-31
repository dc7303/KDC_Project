package edu.kosta.kdc.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dto.VisitDTO;

@Service
public interface AdminService {
    
    /**
     * classification �������� �÷� �� ��������.
     * admin���� ���Ȳ ��Ʈ���� ����
     * 
     * @return
     */
    public Map<String, Integer> boardQuantityByClassification();

    /**
     * �湮�� �� �����ϴ� �޼ҵ�
     * */
    int userCountIntoDB(int todayUserCount);

    /**
     * �湮�� �� �������� (�ֱ� 5��)
     * */
    public List<VisitDTO> visitNumListSelect();

    
}

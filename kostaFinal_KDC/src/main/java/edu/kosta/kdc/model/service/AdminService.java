package edu.kosta.kdc.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    
    /**
     * classification �������� �÷� �� ��������.
     * admin���� ���Ȳ ��Ʈ���� ����
     * 
     * @return
     */
    public Map<String, Integer> boardQuantityByClassification();
    
}

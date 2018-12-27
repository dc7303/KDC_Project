package edu.kosta.kdc.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    
    /**
     * classification 기준으로 컬럼 수 가져오기.
     * admin에서 운영현황 차트에서 사용됨
     * 
     * @return
     */
    public Map<String, Integer> boardQuantityByClassification();
    
}

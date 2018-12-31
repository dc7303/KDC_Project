package edu.kosta.kdc.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.kosta.kdc.model.dto.VisitDTO;

@Service
public interface AdminService {
    
    /**
     * classification 기준으로 컬럼 수 가져오기.
     * admin에서 운영현황 차트에서 사용됨
     * 
     * @return
     */
    public Map<String, Integer> boardQuantityByClassification();

    /**
     * 방문자 수 저장하는 메소드
     * */
    int userCountIntoDB(int todayUserCount);

    /**
     * 방문자 수 가져오기 (최근 5일)
     * */
    public List<VisitDTO> visitNumListSelect();

    
}

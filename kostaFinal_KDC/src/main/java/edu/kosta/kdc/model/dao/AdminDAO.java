package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.VisitDTO;

public interface AdminDAO {

    /**
     * �湮�� �� �����ϴ� �޼ҵ�
     * */
    int userCountIntoDB(int todayUserCount);

    /**
     * �湮�� �� �������� �޼ҵ� (�ֱ� 5��) 
     * */
    List<VisitDTO> visitNumListSelect();
    
}

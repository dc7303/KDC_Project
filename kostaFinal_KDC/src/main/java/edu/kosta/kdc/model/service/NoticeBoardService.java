package edu.kosta.kdc.model.service;

import java.util.List;
import java.util.Map;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardService {
    /**
     * ��ü�˻�
     */
    Map<String, Object> selectAll(String classification, int pageNum);

    /**
     * ���ڵ� ����
     */
    int noticeInsert(NoticeBoardDTO noticeBoard, String classification);

    /**
     * ���� �����ؼ� �󼼺���
     */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK, boolean state);

    /**
     * �����ϱ�
     */
    int update(NoticeBoardDTO noticeBoard);

    /**
     * �����ϱ�
     */
    int delete(int noticeBoardPk);

    /**
     * ���ǰ˻�
     */
    List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch, String classification);

    /**
     * ���� �������� ��� �������� �� 5�� �������� �޼ҵ�
     * */
    List<NoticeBoardDTO> selectFive();
    
    /**
     * ����¡ count ����
     * 
     * @param department
     * @param noticeBoardSearch
     * @param classification
     * @return
     */
    int selectNoticePagingCount(String department, String noticeBoardSearch, String classification);

    /**
     * notice ����¡ select
     * 
     * @param map
     * @return
     */
    Map<String, Object> selectNoticePaging(String department, String noticeBoardSearch, String classification, int pageNum);
    
}

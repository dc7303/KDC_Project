package edu.kosta.kdc.model.dao;

import java.util.List;
import java.util.Map;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
    /**
     *  ��ü�˻�
     */
    List<NoticeBoardDTO> selectAll (String classification, String classRoomCode);
    
    /**
     * ���ڵ� ����
     */
    int noticeInsert(NoticeBoardDTO noticeBoard);
    
    /**
     * ���� �˻��ؼ� �󼼺���
     */
    NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPK);
    
    /**
     *  �����ϱ�
     */
    int update(NoticeBoardDTO noticeBoard);
    
    /**
     * �����ϱ�
     */
    int delete(int noticeBoardPk);
    
    /**
     *  ��ȸ�� ����
     */
    int noticeViewsUpdate(int noticeBoardPk);

    /**
     *  ���ǰ˻�
     */
    List<NoticeBoardDTO> SelechSerch(String department, String noticeBoardSearch, String classification);

    /**
     * classification �������� �÷� ���� ��������.
     *
     * @param classification
     * @return
     */
    public int boardQuantityByClassification(String classification);
     
     /**
     * ���� �������� ��� �������� �� 5�� �������� �޼ҵ�
     * 
     * @param
     * @return List<NoticeBoardDTO>
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
    List<NoticeBoardDTO> selectNoticePaging(Map<String, Object> map);
    
    /**
     * ��ü�˻� ����¡ ó��
     * 
     * @param map
     * @return
     */
    public List<NoticeBoardDTO> selectAllForPaging(Map<String, Object> map);
}

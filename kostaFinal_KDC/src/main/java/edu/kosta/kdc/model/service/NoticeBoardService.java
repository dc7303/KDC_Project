package edu.kosta.kdc.model.service;

import java.util.List;

import edu.kosta.kdc.model.dto.NoticeBoardDTO;

public interface NoticeBoardService {
    /**
     * ��ü�˻�
     */
    List<NoticeBoardDTO> selectAll(String classification);

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
    List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch);

    /**
     * ���� �������� ��� �������� �� 5�� �������� �޼ҵ�
     * */
    List<NoticeBoardDTO> selectFive();
    
}

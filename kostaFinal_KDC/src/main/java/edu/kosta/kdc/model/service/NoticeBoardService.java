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
    int insert(NoticeBoardDTO noticeBoard);

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

}

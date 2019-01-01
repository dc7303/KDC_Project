package edu.kosta.kdc.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ClassRoomDAO;
import edu.kosta.kdc.model.dao.NoticeBoardDAO;
import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.NoticeBoardDTO;
import edu.kosta.kdc.model.dto.PageDTO;
import edu.kosta.kdc.model.service.NoticeBoardService;
import edu.kosta.kdc.util.interfaces.PageHandler;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    @Autowired
    private NoticeBoardDAO noticeBoardDAO;
    
    @Autowired
    private ClassRoomDAO classRoomDAO;
    
    @Autowired
    private PageHandler pageHandler;
    
    /**
     *  ��ü�˻�
     */
    @Override
    public Map<String, Object> selectAll(String classification, int pageNum) {
        
        //�ݺ� ���������� ��� ���� Ŭ������ �ڵ�
        String classRoomCode = null;
        
        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //�ݺ� ���������� ��� ����Ʈ�� ������ Ŭ�������� �����´�.
        if(classification != null && classification.equals("classNotice")) {
            
            //�α��� �ȵȻ����� anonymousUser�� �ƴ� ��� ���� ����
            if(!contextHolder.toString().equals("anonymousUser")) {
                MemberDTO memberDTO = (MemberDTO)contextHolder;
                
                //���� ����Ʈ�� ������ Ŭ�����ڵ� ��������
                ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
                if(classRoomDTO == null) {
                    throw new KdcException("����Ʈ�� ������ Ŭ�������� �������� �ʽ��ϴ�. �������������� �������ּ���.");
                }
                
                classRoomCode = classRoomDTO.getClassRoomCode();
            }else {
                throw new KdcException("Kosta ������ �Ǵ� ���縸 ���ٰ����մϴ�.");
            }
        }
        
        int totalCount = noticeBoardDAO.boardQuantityByClassification(classification);
        PageDTO pageDTO = pageHandler.pageInfoSet(pageNum, 10, 10, totalCount);
        
        int firstColumnPage = pageDTO.getFirstColumnRange();
        int lastColumnPage = pageDTO.getLastColumnRange();
        
        Map<String, Object> map = new HashMap<>();
        
        if(classRoomCode != null) {
            map.put("classRoomCode", classRoomCode);
        }
        
        map.put("classification", classification);
        map.put("firstColumnPage", firstColumnPage);
        map.put("lastColumnPage", lastColumnPage);
        
        List<NoticeBoardDTO> list = noticeBoardDAO.selectAllForPaging(map);
        
        if(list == null) {
            throw new KdcException("�Խñ��� ���������ʽ��ϴ�.");
        }
        
        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("noticeList", list);
        resultMap.put("pageDTO", pageDTO);
        
        return resultMap;
    }
 
    /**
     *  ���ڵ� ����
     */
    @Override
    public int noticeInsert(NoticeBoardDTO noticeBoard, String classification) {
        
        //�ݺ� ���������� ��� ���� Ŭ������ �ڵ�
        String classRoomCode = null;
        
        Object contextHolder = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        
        //�ݺ� ���������� ��� ����Ʈ�� ������ Ŭ�������� �����´�.
        if(classification != null && classification.equals("classNotice")) {
            
            //�α��� �ȵȻ����� anonymousUser�� �ƴ� ��� ���� ����
            if(!contextHolder.toString().equals("anonymousUser")) {
                MemberDTO memberDTO = (MemberDTO)contextHolder;
                
                //���� ����Ʈ�� ������ Ŭ�����ڵ� ��������
                ClassRoomDTO classRoomDTO = classRoomDAO.currentClassSelectByMemberId(memberDTO.getMemberId());
                if(classRoomDTO == null) {
                    throw new KdcException("����Ʈ�� ������ Ŭ�������� �������� �ʽ��ϴ�. �������������� �������ּ���.");
                }
                
                noticeBoard.setNoticeBoardWriterId(memberDTO.getMemberId());
                classRoomCode = classRoomDTO.getClassRoomCode();
            }else {
                throw new KdcException("Kosta ������ �Ǵ� ���縸 ���ٰ����մϴ�.");
            }
        }
        
        //DTO ����
        noticeBoard.setNoticeBoardClassification(classification);
        noticeBoard.setNoticeBoardClassRoomCode(classRoomCode);
        System.out.println(classRoomCode);
        
        int result = 0;
        //�Խñ� Insert
        result = noticeBoardDAO.noticeInsert(noticeBoard);
        if(result == 0) {
            throw new KdcException("�ۼ� �����Դϴ�.");
        }
        
        return result;
        
    }
    
    /**
     *  ���� �����ؼ� �󼼺���
     */
    @Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public NoticeBoardDTO selectByNoticeBoardTitle(int noticeBoardPk, boolean state) {
        
        //�Խñ� ��ȸ 
        NoticeBoardDTO noticeDTO = noticeBoardDAO.selectByNoticeBoardTitle(noticeBoardPk);
        if(noticeDTO == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        //��ȸ�� ����
        if(state){
            int views = noticeBoardDAO.noticeViewsUpdate(noticeBoardPk);//��ȸ������
            if(views == 0) throw new KdcException("��ȸ�� ���� �����Դϴ�.");
        }
       
        return noticeDTO;
    }
    
    /**
     * �����ϱ�
     */
    @Override
    public int update(NoticeBoardDTO noticeBoard) {
        
        int result = 0;
        //�Խñ� update
        result = noticeBoardDAO.update(noticeBoard);
        if(result == 0) {
            throw new KdcException("������Ʈ �����Դϴ�.");
        }
        
        return result;
    }
    
    /**
     *  �����ϱ�
     */
    @Override
    public int delete(int noticeBoardPk) {
        
        int result = 0;
        //�Խñ� delte
        result = noticeBoardDAO.delete(noticeBoardPk);
        if(result == 0) {
            throw new KdcException("���� �����Դϴ�.");
        }
        
        return result;
    }

    /**
     *   �˻� ����Ʈ
     */
    @Override
    public List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch, String classification) {
     
        //�Խñ� ��ȸ
        List<NoticeBoardDTO> list = noticeBoardDAO.SelechSerch(department, noticeBoardSearch, classification);
        if(list == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        return list;
    }
    
    /**
     * ���� �������� ��� �������� �� 5�� �������� �޼ҵ�
     * 
     * @param
     * @return List<NoticeBoardDTO>
     * */
    @Override
    public List<NoticeBoardDTO> selectFive() {
        
        return noticeBoardDAO.selectFive();
    }

    /**
     * ����¡ó�� ��ȸ
     */
    @Override
    public int selectNoticePagingCount(String department, String noticeBoardSearch, String classification) {

        return noticeBoardDAO.selectNoticePagingCount(department, noticeBoardSearch, classification);
    }

    /**
     * ����¡ ó���� ��ȸ�� �÷� counter
     */
    @Override
    public Map<String, Object> selectNoticePaging(String department, String noticeBoardSearch, String classification, int pageNum) {
        
        //�Խñ� count
        int totalCount = noticeBoardDAO.selectNoticePagingCount(department, noticeBoardSearch, classification);
        PageDTO pageDTO = pageHandler.pageInfoSet(pageNum, 10, 10, totalCount);
        
        int firstColumnPage = pageDTO.getFirstColumnRange();
        int lastColumnPage = pageDTO.getLastColumnRange();
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("classification", classification);
        map.put("firstColumnPage", firstColumnPage);
        map.put("lastColumnPage", lastColumnPage);
        map.put("department", department);
        map.put("noticeBoardSearch", noticeBoardSearch);
        
        List<NoticeBoardDTO> list = noticeBoardDAO.selectNoticePaging(map);
        if(list == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("noticeList", list);
        resultMap.put("pageDTO", pageDTO);
        
        
        return resultMap;
    }
    
 }

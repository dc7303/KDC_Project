package edu.kosta.kdc.model.service.impl;

import java.util.List;

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
import edu.kosta.kdc.model.service.NoticeBoardService;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

    @Autowired
    private NoticeBoardDAO noticeBoardDAO;
    
    @Autowired
    private ClassRoomDAO classRoomDAO;
    
    /**
     *  ��ü�˻�
     */
    @Override
    public List<NoticeBoardDTO> selectAll(String classification) {
        
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
        
        List<NoticeBoardDTO> list = noticeBoardDAO.selectAll(classification, classRoomCode);
        if(list == null) {
            throw new KdcException("�Խñ��� ���������ʽ��ϴ�.");
        }
        
        return list;
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
    public List<NoticeBoardDTO> SelectSerch(String department, String noticeBoardSearch) {
     
        //�Խñ� ��ȸ
        List<NoticeBoardDTO> list = noticeBoardDAO.SelechSerch(department, noticeBoardSearch);
        if(list == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        return list;
    }

    
    
 }

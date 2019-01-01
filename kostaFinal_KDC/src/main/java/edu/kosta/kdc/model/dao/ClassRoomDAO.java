package edu.kosta.kdc.model.dao;

import java.util.List;

import edu.kosta.kdc.model.dto.ClassRoomDTO;
import edu.kosta.kdc.model.dto.ClassRoomInfoDTO;

public interface ClassRoomDAO {
    
    /**
     * memberId�� ����Ʈ�� ��ϵ� Ŭ������ ��ȸ�ϱ�
     * 
     * @param memberId
     * @return
     */
    ClassRoomDTO currentClassSelectByMemberId(String memberId);
    
    /**
     * ���������� - member�� Ŭ���� �ڵ� �Է��ϸ� DB�� �ش� ������ �ڵ带 �Է½����ִ� �޼ҵ�
     * */
    int insertMyClassRoom(ClassRoomDTO classRoomDTO);

    /**
     * ���������� - �̹� ���̵� ��ϵ� �ڵ����� �˻����ִ� �޼ҵ�
     * */
    int selectMyClassRoomCodeByClassRoomDTO(ClassRoomDTO classRoomDTO);

    /**
     *  myClassRoom ���������� radio ��ư ���� ���� �� �ش� �ڵ�� CurrentClass = True �� �ٲٴ� �޼ҵ�
     * */
    int defaultClassSet(ClassRoomDTO classRoomDTO);

    /**
     * classRoomIsCurrent = 'TRUE' �� Ŭ������DTO ��������
     * */
    List<ClassRoomInfoDTO> selectCurrentClassRoom(String memberId);

    /**
     * //������Ʈ�� defaultClass�� �ٲٱ� ���� �ٸ� Class�� CurrentClass=False�� �ٲٴ� �޼ҵ�
     * */
    int updateOtherCurrentClass(ClassRoomDTO classRoomDTO);
    
}

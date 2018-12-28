package edu.kosta.kdc.util;

import org.springframework.stereotype.Component;

import edu.kosta.kdc.model.dto.PageDTO;
import edu.kosta.kdc.util.interfaces.PageHandler;

@Component
public class PageHandlerImpl implements PageHandler {

    /**
     * ���� ������, ���������� ��µ� �Խù� ��, �������� ��µ� ������ ��, �� ���ڵ� ���� �����Ͽ�
     * �信 �ѷ��� ����¡�� �������ִ� ���
     * 
     * 
     * @return PageDTO
     */
    @Override
    public PageDTO pageInfoSet(int setPage, int setCountList, int setCountPage, int setTotalCount) {
        
        //���� ������
        //setPage�� 0�� ��� ����Ʈ 1
        int page = 1;
        if(setPage == 0) {
            page = setPage;
        }
        
        //���������� ��µ� �Խù� ��
        //5������ ��� ����Ʈ 5�� ����
        int countList = 5;
        if(setCountList < 5) {
            countList = setCountList;
        }

        //�� ȭ�鿡 ��µ� ������ ��
        int countPage = 5;
        if(setCountPage < 5) {
            countPage = setCountPage;
        }
        
        //�� �÷� ��
        int totalCount = 0;
        if(setTotalCount > 0) {
            totalCount = setTotalCount;
        }
        
        int totalPage = totalCount / countList;     //��ü ������
        
        boolean firstMove = false;      //ù �������� �̵��ϴ� at ���� ����
        boolean backPage = false;       //���� ��������
        boolean nextPage = false;       //���� ��������
        boolean lastMove = false;       //������ ��������
        
        //�÷��� 255���� ��� ��µ� �Խù� �� �� % ������ ���� 5���� ���� �÷��� �߻���.
        //�̸� �ذ��ϱ����� % �����Ͽ� 0���� Ŭ ��� ������ ���� +1 ���ش�.
        if(totalCount % countList > 0) {
            totalPage++;
        }
        
        //���� �������� �� ���������� ũ�ٸ� ���� �������� ������ ���������� ������.
        if(totalPage < page) {
            page = totalPage;
        }
        
        //������ ���� 
        int startPage = ((page - 1) / 10) * 10 + 1;
        
        //������ ������
        int endPage = startPage + countPage -1;
        
        //������ �������� totalPage���� ũ�� �ȵǱ� ������ end�������� Ŭ ��� �����.
        if(endPage > totalPage) {
            endPage = totalPage;
        }
        
        //startPage�� 1�� �ƴ� ��� ó������ �̵��ϴ� at�� �߰��� �� �ְ� ���ش�.
        if(startPage > 1 || page > 1) {
            firstMove = true;
        }
        
        //�������� 1�� �ƴ� ��� ������������ �̵����ִ� at�� �߰��ϰ� ���ش�.
        if(page > 1) {
            backPage = true;
        }

        //<����> ����
        if(page < totalPage) {
            nextPage = true;
        }
        
        //������ ������ ����
        if(endPage < totalPage) {
            lastMove = true;
        }
        
        
        return new PageDTO(page, countList, countPage, totalCount, totalPage, firstMove, backPage, nextPage, lastMove, startPage, endPage);
    }
}

package edu.kosta.kdc.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import edu.kosta.kdc.model.dao.MessageDAO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.MessageDTO;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private SqlSession session;
    
    /**
     * ��ü �޼��� ����Ʈ(no Paging)
     * */
    @Override
    public List<MessageDTO> messageLIstAllNoPaging() {
        
      //���� �α��ε� ������� ������ �������� �ڵ�
        MemberDTO member = (MemberDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String id = member.getMemberId();
        
        return session.selectList("messageMapper.selectAll", id);
        
    }
    
    /**
     * RowBounds�� �̿��� header�޼��� ���� ����
     * */
    private RowBounds RowBounds(int i, int j) {
        
        return new RowBounds(i, j);
        
    }

    /**
     * ��ȸ�� �޼��� ����Ʈ �� ��������
     * @return
     */
    @Override
    public int messageSelectQuntity() {

        return session.selectOne("messageMapper.messageTotalCount");
    }
    
    /**
     * ��ü �޼��� ����Ʈ
     */
    @Override
    public List<MessageDTO> messageAll(String id, int firstColumnRange, int lastColumnRange) {

        Map<String, Object> map = new HashMap<>();
        
        map.put("receiverId", id);
        map.put("firstColumn", firstColumnRange);
        map.put("lastColumn", lastColumnRange);
        
        List<MessageDTO> list = session.selectList("messageMapper.messagePagingSelect", map);

        return list;

    }
    
    /**
     * �������� ��ü �޼��� ����Ʈ
     * */
    @Override
    public List<MessageDTO> unReadMessageList(String id) {
        
        List<MessageDTO> list = session.selectList("messageMapper.unReadMessageList", id, RowBounds(0, 3));
        
        return list;
    }

    /**
     * �޼��� ����
     */
    @Override
    public int messageInsert(MessageDTO messageDTO) {

        return session.insert("messageMapper.messageInsert", messageDTO);

    }

    /**
     * �޼��� ��ȣ�� �ش��ϴ� �޼��� ����
     */
    @Override
    public int messageDelete(int messageNum) {

            return session.update("messageMapper.messageDelete", messageNum);        

    }

    /**
     * �޼��� �󼼺���
     */
    @Override
    public MessageDTO selectByMesssage(int messageNum) {

        return session.selectOne("messageMapper.selectByMessage", messageNum);

    }

    /**
     * �޼��� Ȯ�� ����
     */
    @Override
    public void messageIsRead(int messageNum) {

        session.update("messageMapper.isReadMessage", messageNum);

    }

    /**
     * ����ID(serderId) üũ : �����ư�� Ŭ���ϸ� senderId�� ��ȿ���� üũ
     */
    @Override
    public String messageCheckById(String senderId) {

        String checkId = session.selectOne("messageMapper.checkById", senderId);

        return checkId;

    }

    /**
     * ���� ���� �޼��� ī��Ʈ
     */
    @Override
    public int messageUnReadCount(String id) {

        int count = session.selectOne("messageMapper.unReadCount", id);

        return count;

    }
    
}

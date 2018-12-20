package edu.kosta.kdc.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.exception.KdcException;
import edu.kosta.kdc.model.dao.ReplyBoardDAO;
import edu.kosta.kdc.model.dto.HashTagDTO;
import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.dto.ReplyBoardDTO;
import edu.kosta.kdc.model.service.ReplyBoardService;

@Service
public class ReplyBoardServiceImpl implements ReplyBoardService {

    @Autowired
    private ReplyBoardDAO replyBoardDAO;
    
    /**
     * selectAll(��ü ����)
     * */
    @Override
    public List<ReplyBoardDTO> selectAll(String title) {
        return replyBoardDAO.selectAll(title);
    }

    /**
     * �����Ͽ� select
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort) {
        return replyBoardDAO.replyBoardSelectAllOrderBy(classification, sort);
    }
    
    /**
     *  ���ڵ� ����
     */
    @Override
    @Transactional
    public int insertReply(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        int rs=0;
        rs = replyBoardDAO.insertReply(replyBoardDTO);
        int result=0;
        if(rs!=0 && hashTagName.length!=0) {

            for(String s: hashTagName) {
                result += replyBoardDAO.insertHashTag(s);
            }
        }
        
        //if(rs==0) throw new KdcException(replyBoardDTO.getReplyBoardClassification() + "�Խ��� �� ���� ����");
        return rs;
    }

    /**
     * ���ڵ� ����(���)
     * */
    @Override
    public int replyInsert(ReplyBoardDTO replyBoardDTO) {
        String revised = replyBoardDTO.getMentionNickName().replaceAll("@", "");
        replyBoardDTO.setMentionNickName(revised);
        return replyBoardDAO.replyInsert(replyBoardDTO);
    }
    
    /**
     * �Խñ� ���� �ش��ϴ� �󼼺���
     * @param: state true�̸� ��ȸ������, false�̸� ��ȸ��������.
     *                transaction���� �������Ѵ�. 
     * */
    @Override
    @Transactional
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTODB, boolean state) {
        if(state) {
            int result = replyBoardDAO.readnumUpdate(replyBoardDTODB.getReplyBoardPk());
            if(result==0) throw new KdcException("��ȸ�� ���� ���� �Դϴ�.");
        }
        return replyBoardDAO.selectByReplyBoardPK(replyBoardDTODB);
    }

    /**
     * �Խñ� �����ϱ�
     * */
    @Override
    @Transactional
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        int result=0;
        int rs=0;
        replyBoardDAO.hashTagUpdateDelete(replyBoardDTO);
        rs = replyBoardDAO.replyBoardUpdate(replyBoardDTO);
        if(rs!=0 && hashTagName.length!=0) {
            for(String s: hashTagName) {
                result += replyBoardDAO.hashTagUpdateInsert(replyBoardDTO, s);
            }
        }
        return rs;
    }
    
    /**
     * ��� �����ϱ�
     * */
    @Override
    public int replyUpdate(ReplyBoardDTO replyBoardDTO) {
        return replyBoardDAO.replyUpdate(replyBoardDTO);
    }

    /**
     * �Խñ� �����ϱ�
     * */
    @Override
    @Transactional
    public int replyBoardDelete(int replyBoardPk) {
        int result =0;
        result=replyBoardDAO.replyBoardDelete(replyBoardPk);
        if(result==0) throw new KdcException("���� �����߽��ϴ�.");
        
        result=replyBoardDAO.replyBoardReplyDelete(replyBoardPk);
        if(result==0) throw new KdcException("���� �����߽��ϴ�.");

        result=replyBoardDAO.replyBoardHashTagDelete(replyBoardPk);
        if(result==0) throw new KdcException("���� �����߽��ϴ�.");

        result=replyBoardDAO.replyBoardUpDownDelete(replyBoardPk);
        if(result==0) throw new KdcException("���� �����߽��ϴ�.");
        
        return result;
    }

    /**
     * ��� ���� �ϱ�
     * */
    @Override
    public int replyDelete(int replyBoardReplyPk) {
        int result = replyBoardDAO.replyDelete(replyBoardReplyPk);
        return result;
    }

    /**
     * replyBoard�Խ��ǿ��� ���Ǻ� �˻��ϱ�
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch,String classification) {
        List<ReplyBoardDTO> list = replyBoardDAO.replyBoardListSearch(department, boardSearch,classification);
        return list;
    }

    /**
     * replyBoard ���ƿ�
     * */
    @Override
    public int replyBoardLike(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardLike(replyBoardPk);
        return result;
    }
    
    /**
     * replyBoard �Ⱦ��
     * */
    @Override
    public int replyBoardDisLike(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardDisLike(replyBoardPk);
        return result;
    }
    
    /**
     * replyBoard ���ƿ�,�Ⱦ�� ��� ���
     * */
    @Override
    public int replyBoardLikeCancle(int replyBoardPk) {
        int result = replyBoardDAO.replyBoardLikeCancle(replyBoardPk);
        return result;
    }
    
    /**
     * �ؽ��±� �����ϱ�
     * */
    @Override
    public List<String> hashtagSuggest(String keyword) {
        List<HashTagDTO> resultList = replyBoardDAO.hashtagSuggest(keyword);
        List<String> list = new ArrayList<String>();
        
        for(HashTagDTO dto:resultList) {
            list.add(dto.getHashTagName());
        }
        
        return list;
    }
    
    /**
     * �Ű� insert�ϱ�
     * */
    @Override
    @Transactional
    public int reportPopInsert(String reportContents, int replyBoardPkReport, String otherWords) {
        
        int result=0;
        if(reportContents.length()!=0) {
            result = replyBoardDAO.reportPopInsert(reportContents, replyBoardPkReport);
        }else {
            result = replyBoardDAO.reportPopInsert(otherWords, replyBoardPkReport);
        }
        
        return result;
    }

    /**
     * ����±� �����ϱ�
     * */
    @Override
    public List<String> mentionSuggest(String keyword) {
        
        String revised = keyword.replace("@", "");
        
        List<MemberDTO> resultList = replyBoardDAO.mentionSuggest(revised);
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@"+dto.getMemberNickName());
        }

        return list;
    }

    /**
     * ����±� �����ϱ�(�Ǽ��� �־�� insert�� �ǾߵǼ� ��� nickname������)
     * */
    @Override
    public List<String> allNicknames() {
        
        List<MemberDTO> resultList = replyBoardDAO.allNicknames();
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@"+dto.getMemberNickName());
        }

        return list;
    }

}

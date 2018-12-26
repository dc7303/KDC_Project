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
        
        List<ReplyBoardDTO> list = replyBoardDAO.selectAll(title);
        if(list == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        return list;
    }

    /**
     * �����Ͽ� select
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardSelectAllOrderBy(String classification, String sort) {
        
        List<ReplyBoardDTO> list = replyBoardDAO.replyBoardSelectAllOrderBy(classification, sort);
        if(list == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        return replyBoardDAO.replyBoardSelectAllOrderBy(classification, sort);
    }
    
    /**
     *  ���ڵ� ����
     */
    @Override
    @Transactional
    public int insertReply(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        
        int result = 0;
        //�Խñ� ���
        result = replyBoardDAO.insertReply(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("��� �����Դϴ�.");
        }
        
        //�ؽ��±� ���
        if(hashTagName.length > 0) {
            for(String s: hashTagName) {
                result = replyBoardDAO.insertHashTag(s);
                if(result == 0) {
                    throw new KdcException("�ؽ��±� ��� �����Դϴ�.");
                }
            }
        }
        
        return result;
    }

    /**
     * ���ڵ� ����(���)
     * */
    @Override
    public int replyInsert(ReplyBoardDTO replyBoardDTO) {
        
        //@ �������� ��ȯ
        String revised = replyBoardDTO.getMentionNickName().replaceAll("@", "");
        //replace �� set
        replyBoardDTO.setMentionNickName(revised);
        
        int result = 0;
        
        result = replyBoardDAO.replyInsert(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("��� �Է� �����Դϴ�.");
        }
        
        return result;
    }
    
    /**
     * �Խñ� ���� �ش��ϴ� �󼼺���
     * @param: state true�̸� ��ȸ������, false�̸� ��ȸ��������.
     *                transaction���� �������Ѵ�. 
     * */
    @Override
    @Transactional
    public List<ReplyBoardDTO> selectByReplyBoardPK(ReplyBoardDTO replyBoardDTO, boolean state) {
        
        //�Խñ� ��ȸ
        List<ReplyBoardDTO> list = replyBoardDAO.selectByReplyBoardPK(replyBoardDTO);
        if(list == null) {
            throw new KdcException("�Խñ��� �ҷ����µ� �����߽��ϴ�.");
        }
        
        //��ȸ�� ����
        if(state) {
            int result = replyBoardDAO.readnumUpdate(replyBoardDTO.getReplyBoardPk());
            if(result == 0) {
                throw new KdcException("��ȸ�� ���� ���� �Դϴ�.");
            }
        }
        
        return list;
    }

    /**
     * �Խñ� �����ϱ�
     * */
    @Override
    @Transactional
    public int replyBoardUpdate(ReplyBoardDTO replyBoardDTO, String[] hashTagName) {
        
        int result = 0;
        //�ؽ��±� ������Ʈ�� ���� ����
        result = replyBoardDAO.hashTagUpdateDelete(replyBoardDTO);
        /*if(result == 0) {
            throw new KdcException("�ؽ��±� ���� ���з� �����߻�");
        }*/
        
        //�Խñ� ������Ʈ
        result = replyBoardDAO.replyBoardUpdate(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("�Խñ� ���� �����Դϴ�.");
        }
        
        //�ؽ��±� ���Է� 
        if(hashTagName.length!=0) {
            for(String s: hashTagName) {
                result = replyBoardDAO.hashTagUpdateInsert(replyBoardDTO, s);
                if(result == 0) {
                    throw new KdcException("�ؽ��±� ���Է� ���з� �����߻�");
                }
            }
        }
        
        return result;
    }
    
    /**
     * ��� �����ϱ�
     * */
    @Override
    public int replyUpdate(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyUpdate(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("��� ���� ����");
        }
        
        return replyBoardDAO.replyUpdate(replyBoardDTO);
    }

    /**
     * �Խñ� �����ϱ�
     * */
    @Override
    @Transactional
    public int replyBoardDelete(int replyBoardPk) {
        ReplyBoardDTO replyBoardDTO = new ReplyBoardDTO();
        int result =0;
        
        result=replyBoardDAO.replyBoardDelete(replyBoardPk);
        if(result==0) throw new KdcException("���� �����߽��ϴ�.");

        if(replyBoardDTO.getReplyBoardReplyNo()!=0) {
            result=replyBoardDAO.replyBoardReplyDelete(replyBoardPk);
            if(result==0) throw new KdcException("���� �����߽��ϴ�.");
        }
        
        //���� ó���ϸ� �ؽ��±� ������ ������ �ȵ�(���ľ���)
        result=replyBoardDAO.replyBoardHashTagDelete(replyBoardPk);
        
        //���� ó���ϸ� ���ƿ� ������ ������ �ȵ�(���ľ���)    
        result=replyBoardDAO.replyBoardUpDownDelete(replyBoardPk);
        
        return result;
    }

    /**
     * ��� ���� �ϱ�
     * */
    @Override
    public int replyDelete(int replyBoardReplyPk) {
        
        int result = 0;
        
        result = replyBoardDAO.replyDelete(replyBoardReplyPk);
        if(result == 0) {
            throw new KdcException("��� ���� ����.");
        }
        
        return result;
    }

    /**
     * replyBoard�Խ��ǿ��� ���Ǻ� �˻��ϱ�
     * */
    @Override
    public List<ReplyBoardDTO> replyBoardListSearch(String department, String boardSearch,String classification) {
        
        List<ReplyBoardDTO> list = replyBoardDAO.replyBoardListSearch(department, boardSearch,classification);
        
        if(list == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        return list;
    }

    /**
     * replyBoard ���ƿ�
     * */
    @Override
    public int replyBoardLike(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyBoardLike(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("���ƿ� ��� �����߽��ϴ�.");
        }
        
        return result;
    }
    
    /**
     * replyBoard �Ⱦ��
     * */
    @Override
    public int replyBoardDisLike(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyBoardDisLike(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("�Ⱦ�� ��� �����߽��ϴ�.");
        }
        return result;
    }
    
    /**
     * replyBoard ���ƿ�,�Ⱦ�� ��� ���
     * */
    @Override
    public int replyBoardLikeCancle(ReplyBoardDTO replyBoardDTO) {
        
        int result = 0;
        
        result = replyBoardDAO.replyBoardLikeCancle(replyBoardDTO);
        if(result == 0) {
            throw new KdcException("��� �����Դϴ�.");
        }
        
        return result;
    }
    
    /**
     * �ؽ��±� �����ϱ�
     * */
    @Override
    public List<String> hashtagSuggest(String keyword) {
        
        //�ؽ��±� DTO
        List<HashTagDTO> resultList = replyBoardDAO.hashtagSuggest(keyword);
        if(resultList == null) {
            throw new KdcException("�ؽ��±װ� �������� �ʽ��ϴ�.");
        }
        
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
    public int reportPopInsert(String reportContents, int replyBoardPkReport, String otherWords, String memberId) {
        
        int result = 0;
        
        if(reportContents.length()!=0) {
            result = replyBoardDAO.reportPopInsert(reportContents, replyBoardPkReport,memberId);
            if(result == 0) {
                throw new KdcException("��� �����Դϴ�.");
            }
        }else {
            result = replyBoardDAO.reportPopInsert(otherWords, replyBoardPkReport,memberId);
            if(result == 0) {
                throw new KdcException("��� �����Դϴ�.");
            }
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
        if(resultList == null) {
            throw new KdcException("����Ʈ�� �ҷ����µ� �����߽��ϴ�.");
        }
        
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@" + dto.getMemberNickName());
        }

        return list;
    }

    /**
     * ����±� �����ϱ�(�Ǽ��� �־�� insert�� �ǾߵǼ� ��� nickname������)
     * */
    @Override
    public List<String> allNicknames() {
        
        List<MemberDTO> resultList = replyBoardDAO.allNicknames();
        if(resultList == null) {
            throw new KdcException();
        }
        
        List<String> list = new ArrayList<String>();
        
        for(MemberDTO dto:resultList) {
            list.add("@"+dto.getMemberNickName());
        }

        return list;
    }

    /**
     * ����ȭ�鿡 ��� tech �Խ��� �Խñ� 5�� ��������
     * */
    @Override
    public List<ReplyBoardDTO> selectFiveByTitle(String title) {
        
        List<ReplyBoardDTO> fiveSelectList = replyBoardDAO.selectFiveByTitle(title);
        if(fiveSelectList == null) {
            throw new KdcException("�Խñ��� �������� �ʽ��ϴ�.");
        }
        
        return fiveSelectList;
    }

}

package edu.kosta.kdc.util;

import java.sql.CallableStatement; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.HandlesTypes;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * BooleanŸ�� �ڵ鷯 ������
 * 
 * @author mark
 *
 */
@MappedJdbcTypes(value = JdbcType.VARCHAR)
@MappedTypes(Boolean.class)
public class YesNoBooleanTypeHandler extends BaseTypeHandler<Boolean> {

    /**
     * ps.setString���� booleanŸ���� �Ķ���͸� ������
     * TRUE�̸� "TRUE"�� ����, FALSE�̸� "FALSE"�� ����
     * 
     * ������ ���� ����Ʈ���� �ֱ⶧���� �츮 ȯ�濡�� DTO boolean�� ���� set�ؼ� db�� �߰��Ѵٱ� ���ٴ�
     * ���������� ����ϴ� ���� ��κ��ϰŶ�� ������. (��! �빮�ڷ� ����� ��)
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter ? "TRUE" : "FALSE");
        
    }

    /**
     * �÷��̸����� �����ö� resuletSet ����.
     * �Ʒ� �� �����ڷ� �� �� BooleanŸ���� �����Ѵ�.
     */
    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        
        return rs.getString(columnName) != null && "TRUE".equalsIgnoreCase(rs.getString(columnName));
    }

    /**
     * �÷� �ε����� �����ö� resuletSet ����.
     * �Ʒ� �� �����ڷ� �� �� BooleanŸ���� �����Ѵ�.
     */
    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        
        return rs.getString(columnIndex) != null && "TRUE".equalsIgnoreCase(rs.getString(columnIndex));
    }

    /**
     * Stored Procedure(����� ���ν���)�� ȣ���ϱ� ���� �����ϴ� ��ü 
     * CallableStatement ��ü�� PreparedStatement ��ü�� ��ӹ޾� ����Ѵ�.
     * �Ʒ� �� �����ڷ� �� �� BooleanŸ���� �����Ѵ�.
     */
    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        
        return cs.getString(columnIndex) != null && "TRUE".equalsIgnoreCase(cs.getString(columnIndex));
    }

}
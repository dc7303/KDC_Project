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
 * Boolean타입 핸들러 재정의
 * 
 * @author mark
 *
 */
@MappedJdbcTypes(value = JdbcType.VARCHAR)
@MappedTypes(Boolean.class)
public class YesNoBooleanTypeHandler extends BaseTypeHandler<Boolean> {

    /**
     * ps.setString으로 boolean타입의 파라미터를 가져와
     * TRUE이면 "TRUE"로 저장, FALSE이면 "FALSE"로 저장
     * 
     * 하지만 보통 디폴트값이 있기때문에 우리 환경에서 DTO boolean에 직접 set해서 db에 추가한다기 보다는
     * 쿼리문에서 등록하는 일이 대부분일거라는 생각듬. (꼭! 대문자로 등록할 것)
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter ? "TRUE" : "FALSE");
        
    }

    /**
     * 컬럼이름으로 가져올때 resuletSet 설정.
     * 아래 논리 연사자로 비교 후 Boolean타입을 리턴한다.
     */
    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        
        return rs.getString(columnName) != null && "TRUE".equalsIgnoreCase(rs.getString(columnName));
    }

    /**
     * 컬럼 인덱스로 가져올때 resuletSet 설정.
     * 아래 논리 연사자로 비교 후 Boolean타입을 리턴한다.
     */
    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        
        return rs.getString(columnIndex) != null && "TRUE".equalsIgnoreCase(rs.getString(columnIndex));
    }

    /**
     * Stored Procedure(저장된 프로시저)를 호출하기 위해 존재하는 객체 
     * CallableStatement 객체는 PreparedStatement 객체를 상속받아 사용한다.
     * 아래 논리 연사자로 비교 후 Boolean타입을 리턴한다.
     */
    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        
        return cs.getString(columnIndex) != null && "TRUE".equalsIgnoreCase(cs.getString(columnIndex));
    }

}
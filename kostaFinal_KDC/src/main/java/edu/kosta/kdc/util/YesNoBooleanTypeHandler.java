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

public class YesNoBooleanTypeHandler extends BaseTypeHandler<Boolean> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter ? "TRUE" : "FALSE");
        
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        
        return rs.getString(columnName) != null && "TRUE".equalsIgnoreCase(rs.getString(columnName));
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        
        return rs.getString(columnIndex) != null && "TRUE".equalsIgnoreCase(rs.getString(columnIndex));
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        
        return cs.getString(columnIndex) != null && "TRUE".equalsIgnoreCase(cs.getString(columnIndex));
    }

}
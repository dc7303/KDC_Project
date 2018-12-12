<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
  nav {
    float: left;
    height: 100%;
    postion: fixed;
  }
  
  section {
    height: 100%;
    padding-left: 280px;
  }
  


  
  footer {
    height: 200px;
    width: 100%;
  } 
  
</style>
</head>
<body>
  <nav>
    <tiles:insertAttribute name="nav"/>
  </nav>>
  
  <section>
    <tiles:insertAttribute name="content"/>
  </section>
  
  <footer>
    <tiles:insertAttribute name="footer"/>
  </footer>
</body>
</html>
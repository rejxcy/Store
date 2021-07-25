<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>練習用電腦商城</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
a:link {
	font-size: 18px;
	color: #DB8400;
	text-decoration: none;
	font-weight: bolder;
}
a:visited {
	font-size: 18px;
	color: #DB8400;
	text-decoration: none;
	font-weight: bolder;
}
a:hover {
	font-size: 18px;
	color: #DB8400;
	text-decoration: underline;
	font-weight: bolder;
}
</style>
</head>

<body>
<div class="header">練習用電腦商城</div>
<hr width="100%" />
<div>
  <p class="text1"> <img src="images/4.jpg"   align="absmiddle" /> <a href="controller?action=list">商品列表</a> </p>
  
</div>
<hr width="100%" />
<div>
  <p class="text1"> <img src="images/mycar1.jpg" align="absmiddle"  /> <a href="controller?action=cart">購物車</a></p>
  
</div>
<%@include file="footer.jsp"%>
</body>
</html>

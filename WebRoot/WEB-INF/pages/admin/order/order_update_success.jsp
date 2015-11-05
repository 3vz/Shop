<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Successfully</title>
<style type="text/css">
	div{
		font-size: 12px;
	}
	p{
		font-size: 14px;
		color: red;
	}
</style>
<script type="text/javascript">
function closewindow(){
	if(window.opener){
		window.opener.location.reload(true);
		window.close();
	}
}
function clock(){
	i = i -1;
	if(i > 0){
		setTimeout("clock();",1000);
	}else{
		closewindow();
	}
}
var i = 3;
clock();
</script>
</head>
<body>
	<p>Update Successfully</p>
	<div id="info">Window will cloas after 10 seconds</div>
	<input type="button" value="close window" onclick="closewindow();">
</body>
</html>
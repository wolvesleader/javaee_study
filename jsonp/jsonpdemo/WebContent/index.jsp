<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>

 <script type="text/javascript" src="jquery-1.9.1.js"></script>
  <script type="text/javascript">
  alert(2);
  $.ajax({
	   
	     type:"GET",
	     async:true,
	     url:"http://localhost:8080/jsonp1/JsonpDemoServlet",
	     dataType:"jsonp",
	     jsonp: "callbackparam",
	     jsonpCallback:"successCallback",
	     beforeSend:function(){
	    	 
	     },
	     success:function(data){
	    	 alert(data + "   success");
	     },
	     complete:function(){
	    	 
	     },
	     error:function(){
	    	 
	     },
	    

});
  
    function successCallback(data){
    	alert(data + "  successCallback");
    }
  

  
  </script>
</html>
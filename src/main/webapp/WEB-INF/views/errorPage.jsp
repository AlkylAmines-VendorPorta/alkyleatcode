<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>404 Page Not Fond</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
  .center {text-align: center; margin-left: auto; margin-right: auto; margin-bottom: auto; margin-top: auto;}
.vertical-center {
  min-height: 100%; 
  min-height: 100vh;    
  display: flex;
  align-items: center;
}
</style>
<style>
</style>
</head>
<body>
<div class="vertical-center">
<div class="container">
  <div class="jumbotron">
  <div class="row">
    <div class="span12">
      <div class="hero-unit center">
      <!-- resources/home/img/eauction2.png -->
			<img src="<%=request.getContextPath()%>/resources/home/img/eauction2.png" style="background: #486074; padding: 10px;">
          <h1>Page Not Found <small><font face="Tahoma" color="red">${errorMsg}</font></small></h1>
          <br />
          <p>The page you requested could not be found, either contact your webmaster or try again. Use your browsers <b>Back</b> button to navigate to the page you have previously come from</p>
          <p><b>Or you could just press this little button:</b></p>
          <a href="/eat" class="btn btn-large btn-info"><i class="icon-home icon-white"></i> Go To Home</a>
        </div>
        <br />
        <!-- By ConnerT HTML & CSS Enthusiast -->  
    </div>
  </div>
</div>
</div>
</div>

</body>
</html>
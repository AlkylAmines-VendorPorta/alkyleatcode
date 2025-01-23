<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<style>
.detailsheader{    height: 36px;}.massageview{margin-top:10px;}
</style>
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg"> 
        	 <div class="detailsheader">
        		<div class="col-sm-3 text-center brdrgt"><label>1 Conversation<i class="fa fa-envelope fa-lg"></i></label></div>
       		</div>          
                <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#contains">Code</a></li>
                            <li class="divider"></li>
                            <li><a href="#all">Name</a></li>
                        </ul>
                    </div>
                    <input type="hidden" name="search_param" value="all" id="search_param">
                    <input type="text" class="search-query form-control" name="x" id="searchlitralid" onkeyup="searchuom()" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    </span>
                </div>
                <ul id="example" class="nav nav-tabs tabs-left example">
					<li class="active">
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> E-Auctionapp User </label>
                            	<label class="col-xs-6 ">10.09 AM</label>
                            </div>	
                            <div class="col-md-12 nopadding">
                           		<label class="col-xs-12 nopadding">Wishing You Very Happy Birthday</label>
                            </div>
                            
                        </a>
                    </li>
                </ul>
                
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        		<div class="col-sm-9 text-center">
        			<label>Mail Details</label> 
        			<button type="submit" class="btn btn-info NewMessage pull-right">New Message</button>
        		</div>
        	 </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->

                    <div class="tab-pane active in" id="AddUoM">
                    	<div class="massageview col-sm-12">
                    		<span class="btn-group">
							  	<button class="btn btn-default"><span class="fa fa-star"></span></button>
							  	<button class="btn btn-default"><span class="fa fa-star-o"></span></button>
								<button class="btn btn-default"><span class="fa fa-bookmark-o"></span></button>
							</span>

							<span class="btn-group">
							  	<button class="btn btn-default"><span class="fa fa-mail-reply"></span></button>
							  	<button class="btn btn-default"><span class="fa fa-mail-reply-all"></span></button>
							  	<button class="btn btn-default"><span class="fa fa-mail-forward"></span></button>
							</span>

							<button class="btn btn-default"><span class="fa fa-trash-o"></span></button>

							<span class="btn-group">
								<button class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="fa fa-tags"></span> <span class="caret"></span></button>
								</span>
								<br><br>
								<hr><br>
						   <div class="col-md-12">
                                <label class="col-xs-6 nopadding"> E-Auctionapp User </label>
                            	<label class="col-xs-6 nopadding">10.09 AM</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-12 nopadding">Wishing You Very Happy Birthday</label>
                            </div>
                           <div class="clearfix"></div>
                            <hr>
                            <br>
                            
                            <div class="col-sm-12">
								<p>
									Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
								</p>
								<blockquote>
									Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
								</blockquote>	
							</div>
                    	</div>
                        <div class="panel panel-default message">
						<div class="panel-body">
							
							<p class="text-center">New Message</p>
							
							<form class="form-horizontal" role="form">
								<div class="form-group">
							    	<label for="to" class="col-sm-1 control-label">To:</label>
							    	<div class="col-sm-11">
							      		<input type="text" class="form-control">
							    	</div>
							  	</div>
								<div class="form-group">
							    	<label for="cc" class="col-sm-1 control-label">CC:</label>
							    	<div class="col-sm-11">
							      		<input type="text" class="form-control">
							    	</div>
							  	</div>
								<div class="form-group">
							    	<label for="bcc" class="col-sm-1 control-label">BCC:</label>
							    	<div class="col-sm-11">
							      		<input type="text" class="form-control">
							    	</div>
							  	</div>
							  
							</form>
							
							<div class="col-sm-11 col-sm-offset-1">
								
								<div class="btn-toolbar" role="toolbar">
									
									<div class="btn-group">
									  	<button class="btn btn-default"><span class="fa fa-bold"></span></button>
									  	<button class="btn btn-default"><span class="fa fa-italic"></span></button>
										<button class="btn btn-default"><span class="fa fa-underline"></span></button>
									</div>

									<div class="btn-group">
									  	<button class="btn btn-default"><span class="fa fa-align-left"></span></button>
									  	<button class="btn btn-default"><span class="fa fa-align-right"></span></button>
									  	<button class="btn btn-default"><span class="fa fa-align-center"></span></button>
										<button class="btn btn-default"><span class="fa fa-align-justify"></span></button>
									</div>
									
									<div class="btn-group">
									  	<button class="btn btn-default"><span class="fa fa-indent"></span></button>
									  	<button class="btn btn-default"><span class="fa fa-outdent"></span></button>
									</div>
									
									<div class="btn-group">
									  	<button class="btn btn-default"><span class="fa fa-list-ul"></span></button>
									  	<button class="btn btn-default"><span class="fa fa-list-ol"></span></button>
									</div>

									<button class="btn btn-default"><span class="fa fa-trash-o"></span></button>	
									<button class="btn btn-default"><span class="fa fa-paperclip"></span></button>

									<div class="btn-group">
										<button class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="fa fa-tags"></span> <span class="caret"></span></button>
										<ul class="dropdown-menu">
											<li><a href="page-inbox-compose.html#">add label <span class="label label-danger"> Home</span></a></li>
											<li><a href="page-inbox-compose.html#">add label <span class="label label-info">Job</span></a></li>
											<li><a href="page-inbox-compose.html#">add label <span class="label label-success">Clients</span></a></li>
											<li><a href="page-inbox-compose.html#">add label <span class="label label-warning">News</span></a></li>
										</ul>
									</div>
								
								</div>
								
								<br>	
								
								<div class="form-group">
								
									<textarea class="form-control" id="message" name="body" rows="10" placeholder="Click here to reply"></textarea>
								
								</div>
								
								<div class="form-group">	

									<button type="submit" class="btn btn-success">Send</button>
									<button type="submit" class="btn btn-default">Draft</button>
									<button type="submit" class="btn btn-danger Discard">Discard</button>
								
								</div>
								
							</div>	

						</div>	
						
					</div>
                    </div>

                </div>
                <!-- Master tab end-->

            </div>
            <!-- right-side end-->
        </div>
        <!-- full-container end-->

        <!-- full-container end-->
        <div class="clearfix"></div>
        <script>
        $(document).ready(function(){
        	$('.NewMessage').click(function(){
        		$('.message').show();
        		$('.massageview').hide();
        	});
        	$('.Discard').click(function(){
        		$('.message').hide();
        		$('.massageview').show();
        	});
        });
        </script>
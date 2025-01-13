<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Reference Report (<span class="reportCount">0</span>)</label></div>
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
                <input type="text" class="form-control" name="x" id="searchLiteralId" placeholder="Search term...">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    <!-- <button class="btn btn-default addNewReference addNewReferenceList" type="button"><span class="glyphicon glyphicon-plus"></span></button> -->
                <button class="btn btn-default addnewlist addnewReference" id="addnewReference" type="button">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example second">
      
        
    </ul>
    
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
    <div class="detailsheader">
        	<div class="col-sm-9 text-center"><label>Reference  Detail</label></div>
        </div>
    <div class="clearfix"></div>
    <div class="detailscont">
  							 <div class="col-md-12" id="masterDetails">
                             
                            </div>
                            
    </div>
    <div class="tab-content"> 
      <!-- Master tab start-->
      
      <div class="tab-pane active in" id="AddMaterialGroup">
      <section>
            <div class="board">
                <div class="board-inner">
                    <ul class="nav nav-tabs" id="myTab">
                        <div class="liner"></div>
                        <li class="active" id="referenceTabId">
                            <a href="#home" data-toggle="tab" title="Reference" onclick="renderList()">
                                <span class="round-tabs one">
                                <i class="fa fa-info" aria-hidden="true"></i>
                                </span>
                            </a>
                            <label>Reference</label>
                        </li> 
                         <!-- <li id="referenceListTabId" onclick="return submitWithParam('getReferenceListTab','referenceId','appendLists');"> -->
                          
                             <li id="referenceListTabId">
                              <a href="#home2" data-toggle="tab" title="Reference List" class="ReferenceList">
                                <span class="round-tabs one">
                                <i class="glyphicon glyphicon-list-alt" aria-hidden="true"></i>
                                </span>
                            </a> 
                            <label>Reference List</label>
                        </li>                        
                    </ul>
                </div>
                
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="home">
                    <div class="col-sm-12 text-right">
		                		 <div class="form-group">
		                		<button type="button" class="btn btn-info bluebutton" id="delreference"><span class="glyphicon glyphicon-trash"></span>Delete</button>
		                										  
		                		<button type="button" class="btn btn-info bluebutton" id="editreference"><span class="glyphicon glyphicon-edit"></span>Edit</button>
		                		</div>
		                		</div>
		                		<div class="clearfix"></div>
                    
            <sf:form id="referencemaster"  class="readonly" method="POST" autocomplete="off" modelAttribute="Reference">
               <div class="form-group">  
                <div class="col-sm-4">
                    <div class="styled-input">
                    
                     <input type="hidden" id="referenceId" name="referenceId" class="referenceId" />
                        <input type="text" id="Name" name="name" class="Name" required="required" />
                        <label>Name<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="Code" name="code" class="Code" required="required" />
                        <label>Code<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="Description" name="description" class="Description" required="required" />
                        <label>Description<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
               </div> 
              
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                    <button type="button" class="btn btn-info save" id="saveReference">Save</button>
                   <!--  <button  type="button"  class="btn btn-info save" id="addreference">Save</button>
                      <button  type="button"  class="btn btn-info save" id="editnewreference">Save</button> -->
                      <button  type="button" class="btn btn-info cancel" id="cancelReference">Cancel</button> 
                    </div>
                 </sf:form >
                    </div>
                    <div class="tab-pane fade" id="home2">
                    <div class="col-sm-12 text-right">
		                		 <div class="form-group">
		                		<button type="button" class="btn btn-info bluebutton" id="delReferenceList"><span class="glyphicon glyphicon-trash"></span>Delete</button>
		                										  
		                		<button type="button" class="btn btn-info bluebutton" id="editReferenceList"><span class="glyphicon glyphicon-edit"></span>Edit</button>
		                		</div>
		                		</div>
                    
                    
            <sf:form id="referenceListMaster"  class="readonly" method="POST" autocomplete="off" modelAttribute="ReferenceList">
               <div class="form-group">
               <div class="col-sm-4">
                    <div class="styled-input">
                    
                      <input type="hidden" id="referenceListId" name="referenceListId" />
                      <input type="hidden" id="referenceIdnew" name="reference.referenceId" class="referenceId" />
                        <input type="text" id="referenceCode"  class="referenceCode" name="referenceCode" required="required" />
                        <label>Reference Code<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>  
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="Name"  class="Name" name="name" required="required" />
                        <label>Name<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="Code"  class="Code" name="code" required="required" />
                        <label>Code<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="styled-input">
                        <input type="text" id="Description" class="Description" name="description" required="required" />
                        <label>Description<span class="red">*</span></label>
                        <span></span>
                    </div>
                </div>
               </div> 
               
               
                  <div class="clearfix"></div>
                    <div class="col-md-12 text-center">
                     <button type="button" class="btn btn-info save" id="saveReferenceList">Save</button>
                     <!--  <button  type="button"  class="btn btn-info save" id="addreferencelist">Save</button>
                      <button  type="button"  class="btn btn-info save" id="editnewreferencelist">Save</button> -->
                      
                      <button type="button"  class="btn btn-info cancel" id="cancelreferenceList">Cancel</button> 
                    </div>
                 </sf:form>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
		 </section>        
      </div>
      
    
      </div>
      <!-- Master tab end--> 
      
                      
    </div>
  <!-- right-side end--> 
  </div>
  
  <script src="<%=request.getContextPath()%>/resource/js/reference.js"></script>
  <script src="<%=request.getContextPath()%>/resource/js/referenceList.js"></script>
   <script src="<%=request.getContextPath()%>/resources/commons/js/formFields.js"></script>
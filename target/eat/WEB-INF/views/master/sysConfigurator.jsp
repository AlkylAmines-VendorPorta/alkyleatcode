<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/mobile.css?appVer=${appVer}"/>
    <div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
  <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>System Configurator (<span class="reportCount">0</span>)</label></div>
        </div>
     <div class="input-group">
                    <div class="input-group-btn search-panel">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span id="search_concept">Filter by</span> <span class="caret"></span>
                        </button>
                         <ul class="dropdown-menu" role="menu" id="filterById">
                         <li class="divider"></li>
                      <li><a href="#contains"><input type="radio" name="filterBy" value="name" checked/> Configurator Name</a></li>
                      <li><a href="#all"><input type="radio" name="filterBy" Title" value="configType" /> Configurator Type</a></li>
                    </ul>
                    </div>
                    <input type="text" id="searchLiteralId" class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button" id="searchBtn"><span class="glyphicon glyphicon-search"></span></button>
                   
					<span class="glyphicon glyphicon-plus"></span>
                    </span>
                </div>
    <ul id="leftPaneData" class="nav nav-tabs tabs-left leftPaneData">
      
        
    </ul>
    <p id="pagination-here"></p>
    
    <div class="clearfix"></div>
  </div>
  <!-- left-side end--> 
  
  <!-- right-side start-->
  <div id="mobile_second_container" class="right-side col-md-9 no-marg">
    <div class="detailsheader">
        	<div class="col-sm-9 text-center"><label>Configurator Details</label></div>
        </div>
    <div class="clearfix"></div>
    <div class="detailscont">
  							 
    </div>
    <div class="tab-content"> 
      <!-- Master tab start-->
      
      <div class="tab-pane active in" id="">
      <section>
            <div class="board">
                <div class="board-inner">
                    <ul class="nav nav-tabs" id="myTab">
                        <div class="liner"></div>
                        <li class="active">
                            <a href="#home" data-toggle="tab" title="Information">
                                <span class="round-tabs one">
                                <i class="fa fa-info" aria-hidden="true"></i>
                                </span>
                            </a>
                        </li>                        
                    </ul>
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="home">
                    <div class="col-sm-12 text-right" style="margin-top:7px;" id="manupulationBtnId">
                                    <button type="button" id="reloadAppContextBtnId" onclick="reloadAppContext()" class="btn btn-info bluebutton">ReLoad Application Context</button>
                                    <button type="button" id="reloadAppContextBtnId" onclick="reloadSysConfigurator()" class="btn btn-info bluebutton">ReLoad System Configurator</button>
		                		  <c:if test="${access.modifyAccess== 'Y'}">
			                		<!-- <button type="button" id="addId" onclick="addSysConfigurator()" class="btn btn-info bluebutton"><span class="glyphicon glyphicon-plus-sign"></span>Add</button> -->
			                										  
			                		<button type="button" id="editId" onclick="editSysConfigurator()" class="btn btn-info bluebutton"><span class="glyphicon glyphicon-edit"></span>Edit</button>
		                		  </c:if>
			                	  <c:if test="${access.deleteAccess== 'Y'}">
			                			<button type="button" id="deleteId"  class="btn btn-info bluebutton" onclick="deleteSysConfigurator()"><span class="glyphicon glyphicon-trash"></span>Delete</button>
	           					  </c:if>
		             </div>
			            <sf:form class="readonly" id="sysConfiguratorForm" method="POST" autocomplete="off" modelAttribute="sysConfigurator">
										<div class="clearfix"></div>
							  <input type="text" style="display :none ;" class="sysConfigurator" id="p_systemConfiguratorId"/>
							  <input type="text" style="display :none ;" class="sysConfigurator" id="systemConfiguratorId" name="systemConfiguratorId"/>
			                  <div class="form-group">  
							   <div class="col-sm-4">
						          <div class="styled-input">
										<input type="text" id="value"  class="sysConfiguratorFormEle value requiredField" name="value" />
										<label>Key<span class="red">*</span></label>
										<span></span>
									</div>
						       </div>
								<div class="col-sm-4">
									<div class="styled-input">
										<input type="text" id="code"  class="sysConfiguratorFormEle code requiredField" name="code" />
										<label>Code<span class="red">*</span></label>
										<span></span>
									</div>
								</div>
								<div class="col-sm-4">
									 <div class="styled-input">
						            <input type="text" id="name"  class="sysConfiguratorFormEle name requiredField" name="name"/>
						            <label>Value<span class="red">*</span></label>
									<span></span>
						          </div>
								</div>
								<div class="clearfix"></div>
								<div class="col-sm-4">
									<div class="styled-input">
										<input type="text" id="description"  class="sysConfiguratorFormEle configType requiredField" name="description" />
										<label>Description<span class="red">*</span></label>
										<span></span>
									</div>
								</div>
								
								<div class="col-sm-4">
									<div class="styled-input">
										<input type="text" id="configType"  class="sysConfiguratorFormEle configType requiredField" name="configType" />
										<label>Configurator Type<span class="red">*</span></label>
										<span></span>
									</div>
								</div>
			   				</div>       
			                              
			                  <div class="col-md-6 col-xs-12">
			                    <label class="checkbox-inline">
			                      <input type="checkbox" id="isActive" value="Y" name="isActive" class="isActive" checked="checked"/>
			                       Active </label>
			                  </div>
			                  
			                  <div class="clearfix"></div>
			                    <div class="col-md-12 text-center">
			                       <button  type="button"  class="btn btn-info saveBtn save formBtn" onclick="return submitIt('sysConfiguratorForm','saveSysConfigurator','saveSysConfiguratorResp')">Save</button>
			                      <button  type="button"  id="cancelBtnId" class="btn btn-info CancelBtn cancel formBtn">Cancel</button>
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

<!-- full-container end-->
	<script src="<%=request.getContextPath()%>/resource/js/sysConfigurator.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
    <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/commonValidation.js?appVer=${appVer}"></script>
	<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
      
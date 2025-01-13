<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/tilescommon/css/fioristyle.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/tilescommon/css//borderTab.css"/>
<script src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/js/kendo.all.min.js?appVer=${appVer}"></script>
<style>
.tinput{
width: 100% !important;
}
</style>
            <script>
                $(document).ready(function() {
                    $("#tabstrip").kendoTabStrip();
                    $('#leftPaneData').paginathing({perPage: 6});
                });
            </script>
            
        <div class="full-container">
            <!-- left-side start-->
            <div class="clearfix"></div>
            <div id="mobile_first_container" class="left-side col-md-3 no-marg">
                <div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Invitation Report (0)</label></div>
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
                    <input type="text" class="form-control" name="x" placeholder="Search term...">
                    <span class="input-group-btn">
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-plus"></span></button>
                    </span>
                </div>
                  <ul id="example" class="nav nav-tabs tabs-left example">
                  </ul>
                <div class="clearfix"></div>
            </div>
            <!-- left-side end-->

            <!-- right-side start-->
            <div id="mobile_second_container" class="right-side col-md-9 no-marg">
            <div class="detailsheader toptabbrd">
        	<div class="col-sm-9 text-center"><label>Invitation Details</label></div>
        </div>
                <div class="clearfix"></div>
                <div class="tab-content">
                    <!-- Master tab start-->
                     <!-- <div class="col-md-12" id="masterDetails">
                                
                            </div> -->

                    <div class="tab-pane active in" id="">
                        <div class="card">
                            <div class="posrelative" id="">
                           <div class="demo-section k-content">
                                    <div id="tabstrip" class="Firsttab">
                                        <ul>
                                            <!-- tabs -->
                                            <li class="k-state-active tenderTab"><div class="tabcircle"><i class="fa fa-envelope-o"></i></div>Invitation</li>
                                        </ul>
										<div>
										 <div class="detailscont" id="masterDetails">
  							 	
    									</div>
    									   <div class="form-group">
                                                <div class="col-sm-12">
                                                    <h4 class="col-sm-6"><b>Invitation Details</b></h4>
                                                    <div class="col-sm-6 text-right">
                                                    	<button class="btn btn-default bluebutton" id="sendnewinvitation">Send New Invitation</button>
                                                    </div>
                                                    <hr>
                                                </div>
                                            </div>
    									      <div class="form-group">
    									<sf:form id="userdetailsforinvitation"  method="POST" autocomplete="off" modelAttribute="userDto">
    									<div class="form-group">
    									<input type="hidden" name="userId" id="useridforinvitation"/>
    									
    									   <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="partner.panNumber" id="companypanno" required/>
                                                        <label>Pan Card<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                <button class="btn btn-default bluebutton"  id="getbpartnerforinvitation">Search user</button>
                                                </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="email" id="companyemail" class="emailaddress requiredField"/>
                                                        <label>Email Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="partner.name" id="comapanyname" required/>
                                                        <label>Company Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                             
                                            <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="UserDetails.mobileNo" id="companymob" class="mobile requiredField" maxlength="10"/>
                                                        <label>Mobile NO.<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="UserDetails.firstName" id="userfirtsname"  />
                                                        <label>First Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" name="UserDetails.lastName" id="userlastname" required />
                                                        <label>Last Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 <div class="col-sm-4">
                                                    <div class="styled-input ">
                                                        <input type="text" id="initationstatus" required />
                                                        <label>Status<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                    <div class="col-sm-12 text-center">
                                                    <c:if test="${quickParticipation==false}">
															 <button class="btn btn-default save" id="sendinvitationmail">Send</button>
													</c:if>
													 <c:if test="${quickParticipation==true}">
															<button class="btn btn-default save" id="sendQuickInvitationMail">Send</button>
													</c:if>
                                                </div>                                        
                                            </div>
                                           </sf:form>
										</div>
										</div>
                                        <!--fields of field group 1  -->
                                                                   
                        </div>
                        <!-- right-side end-->
                    </div>
                </div>
            </div>
            </div>
            </div>
            </div>
            </div>
             
<script src="<%=request.getContextPath()%>/resources/partner/js/SendInvitatioToParticipant.js"></script>
 <script src="<%=request.getContextPath()%>/resources/commons/js/commonValidation.js"></script>
 <script src="<%=request.getContextPath()%>/resources/commons/js/utility.js"></script>
 
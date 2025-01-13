<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/tilescommon/css/fioristyle.css"/>
<style>.form-group {
    margin-bottom: 5px;
}</style>
            <script>
                $(document).ready(function() {
                	$(".factoryActionshow").click(function(){
                	    $(".zoom").show();
                	});
                
                    $("#tabstrip").kendoTabStrip();
                    $("#tabstrip2").kendoTabStrip();
                    $("#tabstrip3").kendoTabStrip();
                    $("#tabstrip4").kendoTabStrip();
                    $("#tabstrip5").kendoTabStrip();
                    $("#tabstrip6").kendoTabStrip();
                    
                });

                function valueChanged() {
                	debugger;
                    if ($('.Materialvender').is(":checked"))
                        $(".Subvender").show();
                    else
                        $(".Subvender").hide();
                }
            </script>
    
        <div class="full-container"> 
  <!-- left-side start-->
  <div class="clearfix"></div>
  <div id="mobile_first_container" class="left-side col-md-3 no-marg">
<div class="detailsheader">
        	<div class="col-sm-3 text-center brdrgt"><label>Vendor Report (5)</label></div>
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
                </span>
            </div>
    <ul id="example" class="nav nav-tabs tabs-left example">
    <li class="active">
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> NovelERP Solutions</label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Manufacturer</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
                            
                        </a>
                    </li>
                    <li>
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> E-Auctionapp</label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Material Vendor</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
                            
                        </a>
                    </li>
                    <li>
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> MSETCL</label>
                            	<label class="col-xs-6 ">9484404040</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">User</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
                            
                        </a>
                    </li>
                    <li>
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> Mahavitran </label>
                            	<label class="col-xs-6 ">345263448</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Contractor</label>
                            	<label class="col-xs-6 ">Hsk384940</label>
                            </div>
                            
                        </a>
                    </li>
                    <li>
                        <a href="#Master" data-toggle="tab">
                            <div class="col-md-12">
                                <label class="col-xs-6"> Mahadiscom </label>
                            	<label class="col-xs-6 ">75464920282</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Trador</label>
                            	<label class="col-xs-6 ">HSSLDLDL34</label>
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
        	<div class="col-sm-9 text-center"><label>Vendor Details</label></div>
        </div>
    <div class="clearfix"></div>
    <div class="tab-content"> 
      <!-- Master tab start-->
      
      <div class="tab-pane active in" id="AddUoM"> 
      <div class="card">       
                                          <div id="example">

                                    <div class="demo-section k-content">

                                        <div id="tabstrip" class="Firsttab">
                                            <ul>
                                                
                                                <li class="k-state-active">Company Details</li>
                                                <li>Company Contacts</li>
                                                <li>Digital Signatory</li>
                                                <li>Director Details</li>
                                                <li class="factoryActionshow">Factory Details</li>
                                                <li>Financial Details</li>                                                
                                                <li>Completion Page</li>
                                                <li>Manufacturer</li>
                                            </ul>

                                        <!--fields of field group 2  -->
                                        <div>
                                        <div class="detailscont">
  							 <div class="col-md-12">
                                <label class="col-xs-6"> <h4>NovelERP Solutions</h4></label>
                            	<label class="col-xs-6 ">12765456</label>
                            </div>	
                            <div class="col-md-12">
                           		<label class="col-xs-6">Manufacturer</label>
                            	<label class="col-xs-6 ">EOCPK88Pk</label>
                            </div>
                            
                            
    </div>
				                               <div class="clearfix"></div>
                                        <div class="form-group">
                                     
                                        <div class="col-sm-12"><h4 ><b>Company Details</b></h4><hr></div>
                                        </div>
                                        
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="partner.name" value="${partner.name}" required />
                                                        <label>Company Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="companyType" name="companyType" required>
                                                        <!-- <option>Select Contractor Type</option> -->
                                                        <c:forEach var="companyType" items="${companyTypes}">
                                                        	<option value="${companyType.code}">${companyType.name}</option>
                                                        </c:forEach>
                                                        </select>
                                                        <label>Company Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="partner.crnNumber" value="${partner.crnNumber}" title="Company Registration Number"  required />
                                                        <label>CRN<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                 
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3" style="margin-top: 40px;">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="" onchange="gstnumShow()" id="IsGSTNApplic">Is GSTN Applicable</label>
                                            	</div>
												<div class="col-sm-3 GSTidenNumb">
                                                    <div class="styled-input">
                                                        <input type="text" id="GSTNumber" name="GSTNumber" required />
                                                        <label>GST Identification Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="contractorType" name="contractorType" required >
                                                        	<!-- <option>Select Contractor Type</option> -->
															<c:forEach var="contractorType" items="${contractorTypes}">
	                                                        	<option value="${contractorType.code}" ${contractorType.code==partner.partnerCompany.contractorType ? 'selected' :''}>${contractorType.name}</option>
	                                                        </c:forEach>
                                                        
                                                        </select>
                                                        <label>Contractor Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="contractorType" name="contractorType" required />
                                                        <label>License Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="clearfix"></div>
                                            <div class="form-group" style="margin-top:20px;">
                                        	<div class="col-sm-12">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="">Contractor</label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="">Manufacturer</label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="">Trader</label>
                                            
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="">Customer</label>
                                            </div>
                                        </div>
                                            <div class="form-group">
                                                <label class="col-sm-10">Note : <span class="smalltext">Registration process does not guarantee award of any contract.</span></label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                             <div class="col-md-12 col-xs-12">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" value="" class=" isActive" checked="checked" >I have read the standards terms and conditions as specified by E-Auctionapp and I agree to them</label>
                                            </div>                                               
                                            </div>
                                        </div>
                                        <!--fields of field group 2  -->
                                        <!--fields of field group 2  -->

                                        <!--fields of field group 3  -->
                                        <div>
                                        <div class="detailscont">
								  							 <div class="col-md-12">
								                                <label class="col-xs-6"> <h4>Ankush Agarwal</h4></label>
								                            	<label class="col-xs-6 ">7524536745</label>
								                            </div>	
								                            <div class="col-md-12">
								                           		<label class="col-xs-6">Annkush.a@novelerp.com</label>
								                            	<label class="col-xs-6 ">Andheri  Mumbai</label>
								                            </div>
								                            
								    </div>
                                        <!-- tab inside tab -->
                                            <div id="tabstrip6">
                                                <ul>
                                                    <li  class="k-state-active">Company Contact</li>
                                                    <li>Company Address</li>
                                                </ul>
												 <!--tab inside tab fields 1  -->
                                                <div>
                                                 
                                        <div class="form-group">
                                        <div class="col-sm-12"><h4 ><b>Company Contact Details</b></h4><hr></div></div>
                                        
                                                    <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="title" required />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>First name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Middle name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Last name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>
                                            
                                                    <div class="form-group">

                                                  <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number" required />
                                                        <label>Telephone 1<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Telephone 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Mobile</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax1" name="Fax1" title="Enter 10 digit number" />
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax2" name="Fax2" title="Enter 10 digit number" />
                                                        <label>Fax 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Office Location Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Select Office Location<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>
                                            </div>
                                            <!--tab inside tab fields 1  -->
                                            
                                            <!--tab inside tab fields 2  -->
												<div>
												
												<div class="form-group">
                                        <div class="col-sm-12"><h4 ><b>Company Address Details</b></h4><hr></div></div>
                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address" name="address" required></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>State<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Ship to Address<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Bill to Address<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                        
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 2  -->

                                        </div>
                                        </div>
                                        
                                        <!--fields of field group 3  -->

                                        <!--fields of field group 4  -->
                                        <div>
                                        <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Digital Signatory</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                        
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="title" required />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>First name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Middle name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Last name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="address" name="address" required></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span></div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="City" name="City" required />
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="District" name="District" required />
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="State" name="State" required /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="Country" name="Country" required /></select>
                                                        <label>State<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Pincode" name="Pincode" required />
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number" required />
                                                        <label>Telephone 1<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number"/>
                                                        <label>Telephone 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Telephone1" name="Telephone1" title="Enter 10 digit number" />
                                                        <label>Mobile</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax1" name="Fax1" title="Enter 10 digit number"/>
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="Fax2" name="Fax2" title="Enter 10 digit number" />
                                                        <label>Fax 2<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <label>Gender: <span class="red">*</span>
                                                        <input type="radio" name="gender" value="male" checked> Male
                                                        <input type="radio" name="gender" value="female"> Female
                                                        <input type="radio" name="gender" value="other"> Other
                                                    </label>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Date of Birth" required />
                                                        <label>DOB<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Validity<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                        <!--fields of field group 4  -->

                                        <!--fields of field group 5  -->
                                        <div>
                                        <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Director Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="title" name="title" required />
                                                        <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>First Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Middle Name<span class="red">*</span></label>
                                                        <span></span></div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Last Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="address" name="address" required></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>State<span class="red">*</span></label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Designation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Emter 10 digit number" required />
                                                        <label>Mobile Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" required />
                                                        <label>Telephone 1<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Telephone 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number" />
                                                        <label>Fax 1</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" title="Enter 10 digit number"/>
                                                        <label>Fax 2</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--fields of field group 5  -->

                                       

                                        <!--fields of field group 6  -->
                                        <div>

                                            <!-- tab inside tab -->
                                            <div id="tabstrip3">
                                                <ul>
                                                    <li  class="k-state-active">Factory Details</li>
                                                    <li>License Details</li>
                                                    <li>Contact Person</li>
                                                    <li>Experience Details</li>
                                                    <li>PBG Details</li>                                                    
                                                    <li>ISO Certification</li>
                                                    <li>Items Manufactured</li>
                                                    <!-- <li>MSME Registration</li> -->
                                                    <li>Udyog Aadhar Registration</li>
                                                    <li>Past Performance</li>
                                                    <li>RDAEC</li>
                                                    <li>Other Eligibilty Details</li>
                                                    <li>BIS Details</li>
                                                    <li>Vendor Payment Details</li>
                                                </ul>
												 <!--tab inside tab fields 1  -->
                                                <div>
                                                <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 class="col-xs-6 nopadding"><b>Factory Essential Details</b></h4>
                                        <div class="col-xs-6 text-right nopadding">
                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-plus-sign"></span>Add Factory</button>
                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>Edit Factory</button>
                                                    <button class="btn btn-default"><span class="glyphicon glyphicon-trash"></span>Delete Factory</button></div>
                                        <hr>
                                        </div>
                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Factory Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Established date of Factory<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Manpower<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Factory Inspection Report no.</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address" name="address" required></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>State<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 1  -->		
                                                
                                                <!-- tab inside tab fields 2 -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Factory License Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Factory License Number</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Factory License Validity Date</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Factory License Copy</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Machinery List Copy of Factory<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 2 -->
                                                
                                                <!-- tab inside tab fields 3 -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Factory Contact Person Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="title" name="title" required />
                                                                <label>Title [Mr.,Miss.,Mrs.,Dr.]<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>First Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Middle Name<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Last Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-12">
                                                            <div class="styled-input">
                                                                <textarea type="text" id="address" name="address" required></textarea>
                                                                <label>Registered Office Address<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>City<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>District<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Country<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>State<span class="red">*</span></label>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Pincode<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Email Id<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Designation<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Department</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="clearfix"></div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" required />
                                                                <label>Telephone 1<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" />
                                                                <label>Telephone 2</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" />
                                                                <label>Fax 1</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number"/>
                                                                <label>Fax 2</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Enter 10 digit number" />
                                                                <label>Mobile Number</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 3 -->
                                                
                                                <!-- tab inside tab fields 4 -->
                                                <div>
                                                <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Experience Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                                <div class="col-sm-12">
                                                    <label>Experience in Manufacturing: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Design: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Testing: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <label>Experience in Supplying: </label>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Years</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Months</label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                </div>
                                                <!-- tab inside tab fields 4 -->
												
												 <!-- tab inside tab fields 5  -->
                                                <div>
                                                <div class="form-group">
                                        <div class="col-sm-12">
                                        <h4 ><b>Permanent Bank Guarantee Details</b></h4>
                                        <hr>
                                        </div>
                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Bank Guarantee Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Permanent Bank Guarantee Amount" required />
                                                                <label>PBG Amount<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Bank Guarantee Copy<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 5 -->

                                                <!--tab inside tab fields 6  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Factory ISO Certification Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Name of ISO Standard<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>ISO Certifying Authority<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>ISO Certificate Number<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 6  -->

                                                <!--tab inside tab fields 7  -->
                                                <div>
													<div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Items Manufactured by Firms</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Item Name</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Unit<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Quantity Manufactured Per Month"  required />
                                                                <label>Quantity Manufactured/Month<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Turnover Per Annum For Item(In Lakhs)" required />
                                                                <label>Turnover Per Annum For Item<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Industrial license number</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Industrial License Copy<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <!--tab inside tab fields 7   -->
                                                <!--tab inside tab fields 9   -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Udyog Aadhar Registration Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>NSIC Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>NSIC Registration Number<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of NSIC Registration<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of NSIC Registration<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Date of Commencement of Commercial Production</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Items For Which NSIC Registration is Given<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Attach SSI Registration Copy<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 9  -->

                                                <!--tab inside tab fields 10  -->
                                                <div>
                                                
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Past Performance Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Item Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Name of Firm<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Order Start Date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Order Completion Date<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Quantity Supplied<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" />
                                                                <label>Reference 1</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Reference 2</label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Certificate Awarded (If Any)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 10  -->

                                                <!--tab inside tab fields 11 -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Regional Development Authority Eligibility</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Type of Eligibility<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Developing Region<span class="red">*</span></label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Pioneer Industry</label>
                                                                <span></span></div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of Eligibility<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of Eligibility Certificate<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Valid Eligibility Certificate<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 11  -->

                                                <!-- tab inside tab fields 12  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Other Eligibility Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable for DGS&D Registration Eligibility</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" /></select>
                                                                <label>Select DGS&D Type</label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGS&D Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGS&D Registration Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date Of DGS&D Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <input type="checkbox" id="" name="" />
                                                            <label>Not Applicable for DGTD Registration Eligibility</label>
                                                            <span></span>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGTD Registering Authority<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DGTD Registration Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date Of DGTD Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date Of DGTD Registration<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 12  -->

                                                <!-- tab inside tab fields 13  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Bureau of Indian Standards</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" title="Bureau of Indian Standards License Number" required />
                                                                <label>BIS License number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Issue Date of BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Validity Date of BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach BIS License Certificate<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Items In BIS License<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 13  -->

                                                <!-- tab inside tab fields 14  -->
                                                <div>
                                                
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Vendor Payment</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <select id="" name="" required /></select>
                                                                <label>Type of Payment<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Mode of Payment: <span class="red">*</span>
                                                                <input type="radio" name="DD" value="DD" checked> DD
                                                            </label>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>DD Date<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Demand Draft Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>MICR Code<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Amount in Rs.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>GST Identification Number(GSTIN) <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>GST(@18% on Amount:SAC No.998599) in Rs. <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Total Amount including GST in Rs.<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Bank Name<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Branch Name <span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <!-- tab inside tab fields 14  -->

                                            </div>
                                            <!-- tab inside tab -->

                                        </div>
                                        <!--fields of field group 6  -->
                                        
                                         <!--fields of field group 7  -->
                                        <div>

                                            <!-- tab inside tab -->
                                            <div id="tabstrip2">
                                                <ul>
                                                    <li class="k-state-active">
                                                        P&L Account
                                                    </li>
                                                    <li>
                                                        BS Account
                                                    </li>
                                                    <li>
                                                        Other Documents Details
                                                    </li>
                                                    <li>
                                                        Turnover
                                                    </li>
                                                </ul>

                                                <!--tab inside tab fields 1  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Profit and Loss Account</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
			                                        
                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Profit and loss Account for the year 2016-2017</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Profit and loss Account for the year 2015-2016</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Profit and loss Account for the year 2014-2015</label>
                                                            <input type="file" id="Pincode" name="Pincode" class="form-control" />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 1  -->

                                                <!--tab inside tab fields 2  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Balance Sheet Account</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Balance sheet Account for the year 2016-2017</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Balance sheet Account for the year 2015-2016</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Attach Copy of Balance sheet Account for the year 2014-2015</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 2  -->

                                                <!--tab inside tab fields 3  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Other Document Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>GST Identification Number(GSTIN)<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Copy of GSTIN Registration Certificate<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <div class="styled-input">
                                                                <input type="text" id="" name="" required />
                                                                <label>Pan Number<span class="red">*</span></label>
                                                                <span></span>
                                                            </div>
                                                        </div>

                                                        <div class="col-sm-3">
                                                            <label>Attach Copy of Pan Card<span class="red">*</span></label>
                                                            <input type="file" id="" name="" class="form-control" required />
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--tab inside tab fields 3  -->

                                                <!--tab inside tab fields 4  -->
                                                <div>
                                                <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Turnover Details</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                                    <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Turnover Document(Recent Years)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-3">
                                                        <label>Turnover Amount(Rs)</label>
                                                            <input type="TEXT" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        
                                                    </div>

                                                    <!-- <div class="form-group">
                                                        <div class="col-sm-4">
                                                            <label>Turnover for year 2013-2014(in lakhs)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>

                                                        <div class="col-sm-4">
                                                            <label>Turnover for year 2012-2013(in lakhs)</label>
                                                            <input type="file" id="" name="" class="form-control" />
                                                            <span></span>
                                                        </div>
                                                    </div> -->
                                                </div>
                                                <!--tab inside tab fields 4  -->

                                            </div>
                                            <!-- tab inside tab -->

                                        </div>
                                        <!--fields of field group 7  -->

                                        <!--fields of field group 8  -->
                                        <div>
                                            <div class="form-group">
                                                <label><b>CONGRATULATIONS.....!  

                                          You have successfully filled all the details.</b>
                                                </label>
                                            </div>

                                            <div class="form-group">
                                                <button class="btn-all btn btn-info btnPrevious pull-left">Click Here</button>
                                            </div>

                                            <div class="form-group">
                                                <label>Do you want to apply for the registration now?</label>
                                                <input type="radio" name="" value="" checked> No
                                                <input type="radio" name="" value="" checked> Yes

                                            </div>
                                        </div>
                                        <!--fields of field group 8  -->

                                        <!--fields of field group 9  -->
                                        <div>
                                        
                                        <div class="form-group">
			                                        <div class="col-sm-12">
			                                        <h4 ><b>Details Of Manufacturer</b></h4>
			                                        <hr>
			                                        </div>
			                                        </div>
                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Name of Manufacturer<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Email Id<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <div class="styled-input">
                                                        <textarea type="text" id="address" name="address" required></textarea>
                                                        <label>Registered Office Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>District<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>State<span class="red">*</span></label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <select id="" name="" required /></select>
                                                        <label>Pincode<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" required />
                                                        <label>Telephone 1(Enter 10 digit number)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Telephone 2(Enter 10 digit number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Fax 1(Enter 10 digit Number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="clearfix"></div>

                                            <div class="form-group">

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Fax 2(Enter 10 digit number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>

                                                <div class="col-sm-3">
                                                    <div class="styled-input">
                                                        <input type="text" id="" name="" />
                                                        <label>Mobile Number(Enter 10 digit number)</label>
                                                        <span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-12 text-center positionABS" style="margin-bottom:10px;">
                                        <button class="btn-all btn btn-info k-tabstrip-prev btnPrevious pull-left">Previous</button>
                                        <button class="btn btn-info">Save</button>
                                        <button class="btn-all btn btn-info k-tabstrip-next btnNext pull-right">Next</button>
                                    </div>
                                    <br>
								<div class="clearfix"></div>
                                </div>

            </div>                </div>     
      </div>
      
    
      </div>
      <!-- Master tab end--> 
      
                      
    </div>
  <!-- right-side end--> 
  </div>

        <div class="clearfix"></div>

        <div class="footer">@ All Right Reserved E-Auctionapp</div>

    </body>
<script>
	function gstnumShow() {
	 if ($('#IsGSTNApplic').is(":checked")){
	        $(".GSTidenNumb").show();
	        }
	    else
	        $(".GSTidenNumb").hide();
}
</script>
    </html>
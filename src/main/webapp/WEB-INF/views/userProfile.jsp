<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/css/fioristyle.css?appVer=${appVer}"/>

<style>
.img{
  max-width:180px;
}
.card{padding:15px;}
input[type=file]{
padding:10px;
background:#2d2d2d;}
.btn-bs-file{
    position:relative;
}
.btn-bs-file input[type="file"]{
    position: absolute;
    top: -9999999;
    filter: alpha(opacity=0);
    opacity: 0;
    width:0;
    height:0 ;
    outline: none;
    cursor: inherit;
}
.usecont{margin-top:80px; margin-bottom:40px;}
</style>




	            <div class="container-fluid usecont">
	                <div class="clearfix">
	                <div class="col-sm-4 text-center">
	                <div class="card">
	                <img id="blah" class="img" src="<%=request.getContextPath()%>/resources/${appMode}/tilescommon/images/userProfile.png?appVer=${appVer}" alt="your image" />
	                <br> <br>
	                		<button type="file" class="btn btn-default">Save</button>
    					
    						<label class="btn-bs-file btn  btn-default">
                Change Profile
                <input type="file" onchange="readURL(this);" />
            </label>
			</div>
		    			</div>
		    			
	                    <div class="col-sm-8">
	                        <div class="card">
	                                <h4 class="col-sm-6">Personal Information</h4>
	                            
	                            
	                                <p class="col-sm-6 text-right">	                                    
    									<button type="button" class="btn btn-default">Save</button>
    									<button type="button" class="btn btn-default">Edit</button>
    									<button type="button" class="btn btn-default">Cancel</button>    									
    								</p> 
	                                <div class="clearfix"></div> 
	                                	<div class="form-group">                                      
	                                        <div class="col-sm-6">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Fist Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-6">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Last Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>                                    
	                                        <div class="col-sm-6">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>URL<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-6">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>PAN NO.<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>                                    
	                                        <div class="col-sm-6">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Email<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-6">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Mobile NO.(Without 0 Or +91)<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>
										 </div>
	                            </div>
	                        </div>
	                    </div>
	                    <br>
	                    <div class="clearfix"></div>
						<div class="col-sm-12">
	                        <div class="card">
	                                <h4 class="col-sm-6">Bank Details</h4>
	                            <p class="col-sm-6 text-right">	                                    
    									<button type="button" class="btn btn-default">Save</button>
    									<button type="button" class="btn btn-default">Edit</button>
    									<button type="button" class="btn btn-default">Cancel</button>    									
    								</p>
    								<div class="clearfix"></div>
	                                <div class="form-group">                                      
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Bank Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Account Type<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Account Number<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>                                    
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Branch Name<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Branch Address:<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label> NEFT CODE<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>                                      
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>VAT TIN NUMBER<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>CST REGN. NUMBER<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>SERVICE TAX REGN. NUMBER<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>                                    
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>TYPE OF VENDOR<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Date Of Incorporation<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>	
	                                        </div>
	                                        <div class="col-sm-3">
											   <p>
													<input type="checkbox" >
													<label for="EmailNotificationId">Email Notification </label>
												</p>
											   <p>
													<input type="checkbox">
													<label for="SMSNotificationId">SMS Notification </label>
												</p>
												
	                                        </div>
										 </div>
	                            </div>
	                        </div><br>
	                        <div class="clearfix"></div><br>
	                    <div class="col-sm-12">
	                        <div class="card">
	                                <h4 class="col-sm-6">Others Details</h4>
	                                <p class="col-sm-6 text-right">	                                    
    									<button type="button" class="btn btn-default">Save</button>
    									<button type="button" class="btn btn-default">Edit</button>
    									<button type="button" class="btn btn-default">Cancel</button>    									
    								</p>
    								<div class="clearfix"></div>
	                                <div class="form-group">
	                                <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Industry<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Business Entity<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>No. Of Employees<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Annual Turnover<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Minimum Batch Duration<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                            </div>
	                        </div>
	                    </div>
	                    <br><div class="clearfix"></div><br>
	                    <div class="col-sm-12">
	                        <div class="card">
	                                <h4 class="col-sm-6">Address Details</h4>
	                                <p class="col-sm-6 text-right">	                                    
    									<button type="button" class="btn btn-default">Save</button>
    									<button type="button" class="btn btn-default">Edit</button>
    									<button type="button" class="btn btn-default">Cancel</button>    									
    								</p>
    								<div class="clearfix"></div>
	                                <div class="form-group">
	                                <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Street Address<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Locality<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Select Country<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	                                        </div>
	                                        <div class="form-group">
	                                        <div class="col-sm-3">
	                                                <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Select State<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>													
	                                        </div>
	                                        <div class="col-sm-3">
	                                                <div class="styled-input">
                                                        <select id="" name="" required=""></select>
                                                        <label>Select City<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>													
	                                        </div>
	                                        <div class="col-sm-3">
	                                               <div class="styled-input">
                                                        <input type="text" id="" name="" required=""/>
                                                        <label>Zip Code<span class="red">*</span></label>
                                                        <span></span>
                                                    </div>												
	                                        </div>
	       	                            </div>
	                        </div>
	                    </div>
	                    </div>
	            <script>
	            function readURL(input) {
	                if (input.files && input.files[0]) {
	                    var reader = new FileReader();

	                    reader.onload = function (e) {
	                        $('#blah')
	                            .attr('src', e.target.result);
	                    };

	                    reader.readAsDataURL(input.files[0]);
	                }
	            }
	            </script>
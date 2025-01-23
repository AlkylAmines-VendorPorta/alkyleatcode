<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>E-Auctionapp e-Tendering-Master</title>

    <!-- Bootstrap -->
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/font-awesome.css?appVer=${appVer}">
    <link href="<%=request.getContextPath()%>/resource/css/common.css?appVer=${appVer}" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/mobile.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/styleless.css?appVer=${appVer}" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/animate.min.css?appVer=${appVer}" />
    <!-- HTML5 shim and Respond.js?appVer=${appVer} for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js?appVer=${appVer} doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js?appVer=${appVer}"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js?appVer=${appVer}"></script>
    <![endif]-->
   
</head>

<body class="tiles_background">
      <jsp:include page="dashbordSideBar.jsp" />
    <!-- Header start -->
    <div class="header text-center">
            <div class="col-xs-4"><div class="pull-left">
                <button type="button" title="Menue" class="menu-toggle btn btn-info btnsm">
                    <i class="fa fa-bars"></i>
                </button>
                <a href="dashboard" title="Go Back" class="goback2  btn btn-info btnsm"><i
						class="fa fa-arrow-left" aria-hidden="true"></i></a> <a href="" class="goback3 btn btn-info btnsm pull-left"><i class="fa fa-arrow-left"
						aria-hidden="true"></i></a></div>
	</div>
	<div class="col-xs-4"><img src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.jpg?appVer=${appVer}" class="dash_logo"></div>
	<div class="col-xs-4"><div class="welcome pull-right">
		 <div class="dropdown welcome" style="margin-top: 5px;">
				    Welcome |<a class="dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"><span class="glyphicon glyphicon-user userdet"></span>MSCDCL_User<span class="caret"></span></a> 
				    
				  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				    <li><a href="vendorRegistration">Profile</a></li>
				    <li><a href="logout">Logout</a></li>
				  </ul>
				</div>
	</div>
	</div>
        </div>
    <!-- Header end -->
    <div class="clearfix"></div>

    <!-- full-container start -->
    <div class="full-container animated slideInLeft">
        <!-- MainProductTiles start-->

        <!-- MainProductTiles end-->
        <!-- ProductDetailsTiles start-->
        <div id="ProductDetailsTiles" class="container top20">
         
            <!--MasterDetailsTiles-->
            <div id="MasterDetailsTiles">
           
             <a href="getUserView" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Add User</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                
                <a href="material" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
               
                <a href="DocumentType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Document Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="TenderType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tender Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
               
                <a href="TenderBudgetType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tender Budget Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="BidType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Bid Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="uom" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">UOM</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="PaymentType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Payment Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
               
                <a href="ContractorType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Contractor Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
               
                <a href="materialGroup" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="MaterialSubGroup" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Sub-Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <%-- <c:if test="${roleAccessMaster.dashboard=='Y'}"> --%>
                <!-- <a href="#" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Location</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="#" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Payment Term</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="#" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Vendor Rating Weight-Age</h4>
                        <div class="clearfix"></div>
                    </div>
                </a> -->
               
               
                <a href="MaterialSpecification" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Specification</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="departmentView" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Department</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="MaterialVersion" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Version</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                 
                <a href="GtpParameter" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">GTP Parameters</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
              
                <a href="GtpParameterType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">GTP Parameters Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="PublicNotice" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Public Notices</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="IsStd" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">IS-STD</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                 
                <a href="designation" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Designation</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="CompanyType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Company Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <%-- <c:if test="${roleAccessMaster.dashboard=='Y'}"> --%>
               <!--  <a href="country" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Country</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                <a href="state" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">State</h4>
                        <div class="clearfix"></div>
                    </div>
                </a> -->
           
                <a href="RegistrationType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Registration Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                 
                <a href="tax" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tax</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="TaxCategory" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Tax Category</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
              <!--  <a href="itemGroup" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Item Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a> -->
                
                <a href="HolderType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Holder Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                 
                 <a href="role" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Role</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                 <a href="reference" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Reference</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                 <a href="referenceList" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Reference List</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                 
                <a href="MaterialType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Material Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                <a href="VendorType" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Vendor Type</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                 
                <a href="ExemptedCategories" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i><i class="fa fa-plus pull-left font36"></i><i class="fa fa-pencil pull-left font36"></i>
                        <h4 class="tiles-heading">Exempted Categories</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                 <a href="FinancialDocuments" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Financial Documents</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
                 <a href="BusinessPartnerGroup" id="" class="col-md-2 col-xs-6">
                    <div class="tiles text-center">
                        <i class="fa fa-eye pull-left font36"></i>
                        <h4 class="tiles-heading">Business Partner Group</h4>
                        <div class="clearfix"></div>
                    </div>
                </a>
                
            </div>
            <!--  MasterDetailsTiles	end -->

        </div>
        <!-- ProductDetailsTiles end-->
    </div>
    
    <!-- full-container end-->
    <div class="clearfix"></div>
    <div class="footer">© 2017 E-Auctionapp. All Rights Reserved.</div>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/tiles.js?appVer=${appVer}"></script>
<%-- <script src="<%=request.getContextPath()%>/resource/js/less.min.js?appVer=${appVer}"></script> --%>
<script src="<%=request.getContextPath()%>/resource/js/color.js?appVer=${appVer}"></script>
</html>
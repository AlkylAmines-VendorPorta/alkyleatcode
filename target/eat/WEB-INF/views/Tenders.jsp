<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Auctionapp e-Tendering</title>
    <meta name="description" content="Fullscreen Pageflip Layout with BookBlock" />
    <meta name="keywords" content="fullscreen pageflip, booklet, layout, bookblock, jquery plugin, flipboard layout, sidebar menu" />
    <meta name="author" content="Codrops" />
   <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/bootstrap.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/jquery.dataTables.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/style.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/dataTables.bootstrap.min.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/TableResponsive.css?appVer=${appVer}">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/css/Responsive.css?appVer=${appVer}">
 </head>


<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img alt="" src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.png?appVer=${appVer}"></a>
                <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav  main-nav">
                    <li class="HomeLink"><a href="home">Home</a></li>
                    <li class="active"><a href="tenders">Tenders</a></li>
                    <li><a href="bids">Bids</a></li>
                    <li><a href="profile">Profile</a></li> 
                    <li><a href="help">Help</a></li>
                    <li><a href="contactus">Contact Us</a></li>
                    <li><a href="sitemap">Site Map</a></li>
                </ul>
                <ul id="SmallNav" class="nav navbar-nav navbar-right sidenav">
                    <li><a href="disclaimer"><span class="glyphicon glyphicon-exclamation-sign"></span>Disclaimer</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-th-list"></span>Related Links</a></li>
                    <li><a href="#"><span class=" glyphicon glyphicon-credit-card"></span>Payments</a></li>
                   <li><a href="#"><span class=" glyphicon glyphicon-cog"></span>Utilities</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Calendar</a></li>
                    <li><a href="download"><span class="glyphicon glyphicon-download-alt"></span>Downloads</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Public Notices</a></li>
                    <li><a href="Notification"><span class="glyphicon glyphicon-bell"></span> Notification</a></li>
                    <li><a href="registration"><span class="glyphicon glyphicon-registration-mark"></span> Registration</a></li>
                    <li><a href="javascript:void(0)" class="LoginLink"><span class="glyphicon glyphicon-lock"></span> Login</a></li>
                </ul>
                <div class="welcome"> <span class="glyphicon glyphicon-user" style="color:gray"></span> Welcome TestContract |<a href="">Logout</a></div>
            </div>
        </div>
    </nav>
<div id="content-wrapper">
        <!-- PAGE CONTENT -->
        <div class="container-fluid" style="margin-bottom:40px;">

            <div class="clearfix"></div>

            <div class="col-sm-12 mobileNoPadding">
                <div class="card padding card-min-height positionREL">
                    <div class="col-sm-12 mobileNoPadding AddSlideTab">
                        <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                            <ul id="myTab" class="nav nav-tabs nav-tabs-responsive first-level-tab" role="tablist">
                                <li role="presentation" class="active tenderlist">
                                    <a href="#TenderListing" id="TenderListing-tab" role="tab" data-toggle="tab" aria-controls="TenderListing-tab" aria-expanded="true">
                                        <span class="text">Tender Listing</span>
                                    </a>
                                </li>
                                <li role="presentation" class="next tenderdet">
                                    <a href="#TenderDetails" role="tab" id="TenderDetails-tab" data-toggle="tab" aria-controls="TenderDetails">
                                        <span class="text">Tender Details</span>
                                    </a>
                                </li>
                                <li role="presentation" class="tendercom">
                                    <a href="#TenderCommittee" role="tab" id="TenderCommittee-tab" data-toggle="tab" aria-controls="TenderCommittee">
                                        <span class="text">Tender Committee</span>
                                    </a>
                                </li>

                                <li role="presentation" class="technicalev">
                                    <a href="#TechnicalEvaluation" role="tab" id="TechnicalEvaluation-tab" data-toggle="tab" aria-controls="TechnicalEvaluation">
                                        <span class="text">Technical Evaluation</span>
                                    </a>
                                </li>
                                <li role="presentation" class="tenderex">
                                    <a href="#TenderExemption" role="tab" id="TenderExemption-tab" data-toggle="tab" aria-controls="TenderExemption">
                                        <span class="text">Tender Exemption</span>
                                    </a>
                                </li>
                                <li role="presentation" class="tenderex">
                                    <a href="#TenderOpening" role="tab" id="TenderOpening-tab" data-toggle="tab" aria-controls="TenderOpening">
                                        <span class="text">Tender Opening</span>
                                    </a>
                                </li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="TenderListing" aria-labelledby="TenderListing-tab">
                                    <h3 class="col-sm-12">Public Tenders</h3>
                                    <div class="col-sm-12 mobileNoPadding">
                                        <table class="TenderListingTable table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>
                                                    <th>Download (Published Only)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>20,00,000</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="TenderDetails" aria-labelledby="TenderDetails-tab">
                                    <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs2">
                                        <ul id="myTab" class="nav nav-tabs nav-tabs-responsive second-level-tab" role="tablist">
                                            <li role="presentation" class="active BasicInformation">
                                                <a href="#BasicInformation" id="BasicInformation-tab" role="tab" data-toggle="tab" aria-controls="BasicInformation-tab" aria-expanded="true">
                                                    <span class="text">Basic Information</span>
                                                </a>
                                            </li>
                                            <li role="presentation" class="next">
                                                <a href="#ContactDetailsListing" role="tab" id="ContactDetailsListing-tab" data-toggle="tab" aria-controls="ContactDetailsListing">
                                                    <span class="text">Contact Details</span>
                                                </a>
                                            </li>
                                            <li role="presentation">
                                                <a href="#Location" role="tab" id="Location-tab" data-toggle="tab" aria-controls="Location">
                                                    <span class="text">Location</span>
                                                </a>
                                            </li>

                                            <li role="presentation" class="TenderVersions">
                                                <a href="#TenderVersions" role="tab" id="TenderVersions-tab" data-toggle="tab" aria-controls="TenderVersions">
                                                    <span class="text">Tender Versions</span>
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="myTabContent2" class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade in active" id="BasicInformation" aria-labelledby="BasicInformation-tab">
                                                <div class="form-group">
                                                    <label class="col-sm-2">Tender Number<span class="red">*</span></label>
                                                    <div class="col-sm-4">
                                                        <input type="text" class="form-control">
                                                    </div>
                                                    <label class="col-sm-6 textright">Viewing Ver # 3. (Latest Ver # 3).

                                                        <a class="ViewAll" data-toggle="tab" href="#TenderVersions">Click her to see All</a></label>

                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Tender Name<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control">
                                                    </div>
                                                    <label class="col-sm-2">Tender Type<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option></option>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-2">Tender Budget Type<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option></option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Brief Description of Work<span class="red">*</span></label>
                                                    <div class="col-sm-10">
                                                        <textarea rows="2" cols="50" class="form-control"></textarea>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Qualifying Requirement:</label>
                                                    <div class="col-sm-10">
                                                        <textarea rows="2" cols="50" class="form-control"></textarea>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Office Type<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option></option>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-2">Office Location<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option></option>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-2">Scheme Name<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Scheme Code<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option></option>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-2">Estimated Cost (Rs.)</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option></option>
                                                        </select>
                                                    </div>
                                                    <label class="col-sm-2">Tender Fee (Rs.)<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <input type="text" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Emd (Rs.)</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option></option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Sale Opening Date<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <div class="input-group date datepicker">
                                                            <input class="form-control" type="text" />
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-2">Sale Closing Date<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <div class="input-group date datepicker">
                                                            <input class="form-control" type="text" />
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Pre-bid Date</label>
                                                    <div class="col-sm-2">
                                                        <div class="input-group date datepicker">
                                                            <input class="form-control" type="text" />
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-2">Time</label>
                                                    <div class="col-sm-2">
                                                        <div class="col-sm-5 col-xs-5 nopadding">
                                                            <input class="form-control" type="text" />
                                                        </div>
                                                        <div class="col-sm-2 col-xs-2 text-center nopadding"><b>:</b></div>
                                                        <div class="col-sm-5 col-xs-5 nopadding">
                                                            <input class="form-control" type="text" />
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-4">(24 Hrs Format)</label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Submission Closing Date<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <div class="input-group date datepicker">
                                                            <input class="form-control" type="text" />
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-2">Time</label>
                                                    <div class="col-sm-2">
                                                        <div class="col-sm-5 col-xs-5 nopadding">
                                                            <input class="form-control" type="text" />
                                                        </div>
                                                        <div class="col-sm-2 col-xs-2 text-center nopadding"><b>:</b></div>
                                                        <div class="col-sm-5 col-xs-5 nopadding">
                                                            <input class="form-control" type="text" />
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-4">(24 Hrs Format)</label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Techno-Commercial Opening Date<span class="red">*</span></label>
                                                    <div class="col-sm-2">
                                                        <div class="input-group date datepicker">
                                                            <input class="form-control" type="text" />
                                                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-2">Time</label>
                                                    <div class="col-sm-2">
                                                        <div class="col-sm-5 col-xs-5 nopadding">
                                                            <input class="form-control" type="text" />
                                                        </div>
                                                        <div class="col-sm-2 col-xs-2 text-center nopadding"><b>:</b></div>
                                                        <div class="col-sm-5 col-xs-5 nopadding">
                                                            <input class="form-control" type="text" />
                                                        </div>
                                                    </div>
                                                    <label class="col-sm-4">(24 Hrs Format)</label>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="ContactDetailsListing" aria-labelledby="ContactDetailsListing-tab">
                                                <div class="col-sm-12 mobileNoPadding">
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Name<span class="red">*</span></label>
                                                        <div class="col-sm-2">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <label class="col-sm-2">Email-ID<span class="red">*</span></label>
                                                        <div class="col-sm-2">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <label class="col-sm-2">Phone Number<span class="red">*</span></label>
                                                        <div class="col-sm-2">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Designation<span class="red">*</span></label>
                                                        <div class="col-sm-2">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="Location" aria-labelledby="Location-tab">
                                                <div class="col-sm-12 mobileNoPadding">
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Tender Purchasing Office Address<span class="red">*</span></label>
                                                        <div class="col-sm-10">
                                                            <textarea rows="4" cols="50" class="form-control"></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Tender Opening Address<span class="red">*</span></label>
                                                        <div class="col-sm-10">
                                                            <textarea rows="4" cols="50" class="form-control"></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Pre-Bid Address</label>
                                                        <div class="col-sm-10">
                                                            <textarea rows="4" cols="50" class="form-control"></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>

                                            </div>
                                            <div role="tabpanel" class="tab-pane fade" id="TenderVersions" aria-labelledby="TenderVersions-tab">
                                                <div class="col-sm-12 mobileNoPadding">
                                                    <div class="col-sm-6" style="margin-top:10px;">
                                                        <div class="single category">
                                                            <ul class="list-unstyled">
                                                                <li><a href="" title="">View Registration Process </a></li>
                                                                <li><a href="" title="">e-Tendering Contact Details </a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="TenderCommittee" aria-labelledby="TenderCommittee-tab">
                                    <div class="col-sm-12 mobileNoPadding">
                                        <table class="TenderCommitteeTable table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No.</th>
                                                    <th>Description</th>
                                                    <th>Sale Opening</th>
                                                    <th>Sale Closing</th>
                                                    <th>Submission Due </th>
                                                    <th>Status</th>
                                                    <th>Define/Edit Committee</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>
                                                        <button type="button" class="btn btn-info Edit-Committee-button"><span class="glyphicon glyphicon-edit"></span></button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>
                                                        <button type="button" class="btn btn-info Edit-Committee-button"><span class="glyphicon glyphicon-edit"></span></button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>
                                                        <button type="button" class="btn btn-info Edit-Committee-button"><span class="glyphicon glyphicon-edit"></span></button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>
                                                        <button type="button" class="btn btn-info Edit-Committee-button"><span class="glyphicon glyphicon-edit"></span></button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                    <td>20,00,000</td>
                                                    <td>
                                                        <button type="button" class="btn btn-info Edit-Committee-button"><span class="glyphicon glyphicon-edit"></span></button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div role="tabpanel" class="tab-pane fade" id="TechnicalEvaluation" aria-labelledby="TechnicalEvaluation">
                                    <div class="col-sm-12 mobileNoPadding">
                                        <table class="TechnicalEvaluationTable table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Tender No.</th>
                                                    <th>Tender Version</th>
                                                    <th>Description</th>
                                                    <th>Status</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>Approved</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>Approved</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>Approved</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>Approved</td>
                                                </tr>
                                                <tr>
                                                    <td><a class="tenderdetailsTab" data-toggle="tab" href="#TenderDetails">TestTender1103</a></td>
                                                    <td>Browser Requirements </td>
                                                    <td>21st October 2017</td>
                                                    <td>Approved</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="TenderExemption" aria-labelledby="TenderExemption-tab">
                                    <h4>Tender Purchase Fees</h4>
                                    <div class="form-group">
                                        <label class="col-sm-2">Tender Number</label>
                                        <div class="col-sm-3">
                                            <select class="form-control">
                                                <option></option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                   <div role="tabpanel" class="tab-pane fade" id="TenderOpening" aria-labelledby="TenderOpening-tab">
                                    <h4>Tender Opening</h4>
                                    <div class="form-group">
                                        <label class="col-sm-2">Tender Opening</label>
                                        <div class="col-sm-3">
                                            <select class="form-control">
                                                <option></option>
                                            </select>
                                        </div>
                                    </div>
                                </div>                                
                            </div>
                        </div>

                        <div class="clearfix"></div>

                    </div>
                    <div class="clearfix"></div>
                    <div class="EditPopup">
                        <div class="col-sm-12">
                            <h4 class="col-sm-11 col-xs-11">Tender Committee</h4>
                            <div class="col-sm-1 col-xs-1 text-right" style="padding: 10px; cursor: pointer;">
                                <span class="glyphicon glyphicon-remove closeDiv"></span>
                            </div>
                            <table class="TenderCommiteTable table table-striped table-bordered" width="100%">
                                <thead style="display:none;">
                                    <tr>
                                        <td></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="form-group">
                                                <label class="col-sm-3 col-xs-6">Tender Number.</label>
                                                <div class="col-sm-6 col-xs-6">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-3 col-xs-6">Tender Name</label>
                                                <div class="col-sm-6 col-xs-6">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">

                                                <label class="col-sm-3 col-xs-6">Under Jurisdiction of Office</label>
                                                <div class="col-sm-2 col-xs-6">
                                                    <select class="form-control">
                                                        <option></option>
                                                    </select>
                                                </div>
                                                <label class="col-sm-2 col-xs-6 clearBoth">Location</label>
                                                <div class="col-sm-2 col-xs-6">
                                                    <select class="form-control">
                                                        <option></option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-2 col-xs-12 mobcenter clearBoth">
                                                    <button class="btn btn-info">Search</button>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-12">
                            <h4>Choose Committee Members</h4>
                            <table class="CommitteeMembersTable table table-striped table-bordered" width="100%">
                                <thead style="display:none">
                                    <tr>
                                        <th>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="form-group">
                                                <label class="col-sm-3 col-xs-6">First Name.</label>
                                                <div class="col-sm-6 col-xs-6">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-3 col-xs-6">Last Name</label>
                                                <div class="col-sm-6 col-xs-6">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="form-group">
                                <div class="col-sm-1 col-xs-3">
                                    <button type="button" class="btn btn-info">Add</button>
                                </div>
                                <div class="col-sm-3 col-xs-9">
                                    <select class="form-control">

                                    </select>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="col-sm-12">
                            <h4>Existing Committee Members</h4>
                            <table class="ExistingCommitteeMembersTable table table-striped table-bordered" width="100%">
                                <thead>
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Member Type</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <label class="checkbox-inline">
                                                <input type="radio" value="">Option 1
                                            </label>
                                        </td>
                                        <td>Section </td>
                                        <td>Accounts Person</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="clearfix"></div>
                        <div class="col-sm-12 mobcenter">
                            <div class="form-group">
                                <button type="button" class="btn btn-info">Remove</button>
                                <button type="button" class="btn btn-info closeDiv">Close</button>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <!-- End Container -->
            </div>
        </div>
    </div>
     <div class="height20"></div>
    <div class="footer">
        © 2017 E-Auctionapp. All Rights Reserved.
    </div>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/tender.js?appVer=${appVer}"></script>
<!-- <script> 
$(document).ready(function() {
	$(".nav a").on("click", function(){
		   $(".nav").find(".active").removeClass("active");
		   $(this).parent().addClass("active");
		});
});</script> -->
</html>
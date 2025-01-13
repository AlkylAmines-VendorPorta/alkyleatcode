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
                <a class="navbar-brand" href="#"><img alt="" src="<%=request.getContextPath()%>/resource/images/Mahadiscom_Logo.png"></a>
                <a href="javascript:void(0)" class="LoginLink LoginLinkMobile"><span class="glyphicon glyphicon-lock"></span> Login</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav  main-nav">
                    <li class="HomeLink"><a href="home">Home</a></li>
                    <li><a href="tenders">Tenders</a></li>
                    <li><a href="bids">Bids</a></li>
                    <li class="active"><a href="profile">Profile</a></li>
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
                                    <a href="#ContractorDetails" id="ContractorDetails-tab" role="tab" data-toggle="tab" aria-controls="ContractorDetails-tab" aria-expanded="true">
                                        <span class="text">Contractor Details</span>
                                    </a>
                                </li>
                                <li role="presentation" class="next">
                                    <a href="#ContactDetails" role="tab" id="ContactDetails-tab" data-toggle="tab" aria-controls="ContactDetails">
                                        <span class="text">Contact Details</span>
                                    </a>
                                </li>
                                <li role="presentation">
                                    <a href="#SSICertificate" role="tab" id="SSICertificate-tab" data-toggle="tab" aria-controls="SSICertificate">
                                        <span class="text">Upload SSI Certificate</span>
                                    </a>
                                </li>

                                <li role="presentation">
                                    <a href="#LicensedCA" role="tab" id="LicensedCA-tab" data-toggle="tab" aria-controls="LicensedCA">
                                        <span class="text">Licensed CA</span>
                                    </a>
                                </li>
                                <li role="presentation">
                                    <a href="#DownloadsProfile" role="tab" id="DownloadsProfile-tab" data-toggle="tab" aria-controls="DownloadsProfile">
                                        <span class="text">Downloads</span>
                                    </a>
                                </li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="ContractorDetails" aria-labelledby="ContractorDetails-tab">
                                    <h4 class="col-sm-12">Edit Profile</h4>
                                    <div class="col-sm-12 mobileNoPadding">
                                        <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs2">
                                            <ul id="myTab2" class="nav nav-tabs nav-tabs-responsive second-level-tab" role="tablist">
                                                <li role="presentation" class="active BasicInformation">
                                                    <a href="#Step-1" id="Step-1-tab" role="tab" data-toggle="tab" aria-controls="Step-1-tab" aria-expanded="true">
                                                        <span class="text">Company Essential Details</span>
                                                    </a>
                                                </li>
                                                <li role="presentation" class="next">
                                                    <a href="#Step-2" role="tab" id="Step-2-tab" data-toggle="tab" aria-controls="Step-2">
                                                        <span class="text">Company Login Details</span>
                                                    </a>
                                                </li>
                                                <li role="presentation">
                                                    <a href="#Step-3" role="tab" id="Step-3-tab" data-toggle="tab" aria-controls="Step-3">
                                                        <span class="text">Company Contact Details</span>
                                                    </a>
                                                </li>
                                            </ul>
                                            <div id="myTabContent2" class="tab-content" style="margin-top: 10px;">
                                                <div role="tabpanel" class="tab-pane fade in active" id="Step-1" aria-labelledby="Step-1-tab">
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Company Name<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <label class="col-sm-2">Company Type<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <select class=""></select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Company Registration Number<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <label class="col-sm-2">Sales Tax Number<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>

                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Central Sales Tax Number<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <label class="col-sm-2">GSTIN Applicable<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <select class="form-control">
                                                                <option></option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2">GST Identification Number</label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <label class="col-sm-2">PAN Number<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2">Contractor Type<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                        <label class="col-sm-2">License Number<span class="red">*</span></label>
                                                        <div class="col-sm-4">
                                                            <input type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div role="tabpanel" class="tab-pane fade" id="Step-2" aria-labelledby="Step-2-tab">
                                                    <div class="col-sm-12 mobileNoPadding">
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Username<span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-sm-4">[6-20 characters][Only letters, numbers, underscores are allowed]</label>
                                                            <div class="col-sm-2">
                                                                <button class="btn btn-info ChangePassword">Change Password</button>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="PasswordBox">
                                                            <div class="form-group ">
                                                                <label class="col-sm-2">Old Password<span class="red">*</span></label>
                                                                <div class="col-sm-2">
                                                                    <input type="text" class="form-control">
                                                                </div>
                                                                <label class="col-sm-2">New Password<span class="red">*</span></label>
                                                                <div class="col-sm-2">
                                                                    <input type="text" class="form-control">
                                                                </div>
                                                                <label class="col-sm-2">Confirm new password<span class="red">*</span></label>
                                                                <div class="col-sm-2">
                                                                    <input type="text" class="form-control">
                                                                </div>
                                                            </div>
                                                            <div class="clearfix"></div>
                                                            <div class="form-group">
                                                                <div class="col-sm-12 text-center">
                                                                    <button class="btn btn-info PasswordBoxClose">Save</button>
                                                                    <button class="btn btn-info PasswordBoxClose">Cancel</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div role="tabpanel" class="tab-pane fade" id="Step-3" aria-labelledby="Step-3-tab">
                                                    <div class="col-sm-12 mobileNoPadding">
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Registered Office Address<span class="red">*</span></label>
                                                            <div class="col-sm-10">
                                                                <textarea rows="4" cols="50" class="form-control"></textarea>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">City <span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-sm-2">District<span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">State <span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <select class="form-control">
                                                                </select>
                                                            </div>
                                                            <label class="col-sm-2">Country<span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <select class="form-control">
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Pincode <span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Telephone Number<span class="red">*</span></label>
                                                            <label class="col-sm-2">Country Code<span class="red">*</span>
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-2">Area Code<span class="red">*</span>
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-3">Telephone 1<span class="red">*</span>
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-3">Telephone 2
                                                                <input type="text" class="form-control">
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Fax Number</label>
                                                            <label class="col-sm-2">Country Code
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-2">Area Code
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-3">Fax 1
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-3">Fax 2
                                                                <input type="text" class="form-control">
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Email Address <span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-sm-2">Select Office Location Type<span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <select class="form-control">
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">

                                                            <label class="col-sm-2">Select Office Location<span class="red">*</span></label>
                                                            <div class="col-sm-4">
                                                                <select class="form-control">
                                                                </select>
                                                            </div>
                                                            <label class="col-sm-2">Contractor Name</label>
                                                            <div class="col-sm-4">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Title:</label>
                                                            <div class="col-sm-2">
                                                                <input type="text" class="form-control">
                                                            </div>
                                                            <label class="col-sm-2">[Mr.,Miss.,Mrs.,Dr.]</label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Name<span class="red">*</span></label>
                                                            <label class="col-sm-3">First Name<span class="red">*</span>
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-3">Father/Husband Name:
                                                                <input type="text" class="form-control">
                                                            </label>
                                                            <label class="col-sm-3">Surname<span class="red">*</span>
                                                                <input type="text" class="form-control">
                                                            </label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <label class="col-sm-2">Note :</label>
                                                            <label class="col-sm-8">
                                                                <h4>Registration process does not guarantee award of any contract.</h4></label>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <div class="col-sm-2">
                                                                <input type="checkbox" class="checkbox">
                                                            </div>
                                                            <label class="col-sm-8">I have read the standards terms and conditions as specified by E-Auctionapp and I agree to them</label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="ContactDetails" aria-labelledby="ContactDetails-tab">
                                    <div class="col-sm-12 mobileNoPadding">
                                        <h4>ContactDetails</h4>
                                        <div class="alert alert-success">
                                            <strong>Note :</strong> <a href="#" class="alert-link">To proceed to the next step, please create at least ONE contact that is holder of Tender Signatory.</a>.
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="form-group">
                                            <button class="btn btn-info addDetailsButton"><span class="glyphicon glyphicon-plus"></span>Add Details</button>
                                            <button class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>Edit Details</button>
                                            <button class="btn btn-info"><span class="glyphicon glyphicon-trash"></span>Delete Details</button>
                                        </div>
                                        <div class="clearfix"></div>
                                        <table class="AddContactDetailsTable table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>Select</th>
                                                    <th>Full Name</th>
                                                    <th>Holds</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <input type="radio" class="radio">
                                                    </td>
                                                    <td>Test signatory</td>
                                                    <td>Tender Signatory</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <div class="AddContactDetailsDiv">
                                            <h4>Representative Details</h4>
                                            <div class="form-group">
                                                <label class="col-sm-2">Holder of<span class="red">*</span></label>
                                                <div class="col-sm-4">
                                                    <select class="form-control">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">Full Name<span class="red">*</span></label>
                                                <label class="col-sm-3">First Name<span class="red">*</span>
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-3">Middle Name
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-3">Last Name<span class="red">*</span>
                                                    <input type="text" class="form-control">
                                                </label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">Full Address <span class="red">*</span></label>
                                                <div class="col-sm-10">
                                                    <textarea rows="4" cols="50" class="form-control"></textarea>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">City <span class="red">*</span></label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control">
                                                </div>
                                                <label class="col-sm-2">District<span class="red">*</span></label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">State <span class="red">*</span></label>
                                                <div class="col-sm-4">
                                                    <select class="form-control">
                                                    </select>
                                                </div>
                                                <label class="col-sm-2">Country<span class="red">*</span></label>
                                                <div class="col-sm-4">
                                                    <select class="form-control">
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">Pincode <span class="red">*</span></label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">Telephone Number<span class="red">*</span></label>
                                                <label class="col-sm-2">Country Code<span class="red">*</span>
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-2">Area Code<span class="red">*</span>
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-3">Telephone 1<span class="red">*</span>
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-3">Telephone 2
                                                    <input type="text" class="form-control">
                                                </label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">Fax Number</label>
                                                <label class="col-sm-2">Country Code
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-2">Area Code
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-3">Fax 1
                                                    <input type="text" class="form-control">
                                                </label>
                                                <label class="col-sm-3">Fax 2
                                                    <input type="text" class="form-control">
                                                </label>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="form-group">
                                                <label class="col-sm-2">Email Address<span class="red">*</span></label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="SSICertificate" aria-labelledby="SSICertificate-tab">
                                    <div class="col-sm-12 mobileNoPadding">
                                        <h4>Upload SSI Documents</h4>
                                        <div class="form-group">
                                            <label class="col-sm-12">Attach SSI Documents</label>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3">Name<span class="red">*</span></label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="form-group">
                                            <label class="col-sm-3">Select File<span class="red">*</span></label>
                                            <div class="col-sm-4">
                                                <input type="file" class="form-control">
                                            </div>
                                            <div class="col-sm-2">
                                                <button class="btn btn-info">Attach</button>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div>
                                        <table class="SSIDocumentsTable table table-striped table-bordered" width="100%">
                                            <thead>
                                                <tr>
                                                    <th>List of Files attached for : TestContractor1</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>21st October 2017</td>
                                                    <td>21st October 2017</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div role="tabpanel" class="tab-pane fade" id="LicensedCA" aria-labelledby="LicensedCA">
                                    <div class="col-sm-12 mobileNoPadding">

                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="DownloadsProfile" aria-labelledby="DownloadsProfile-tab">
                                    <div class="col-sm-12 mobileNoPadding">
                                        <h4>Downloads</h4>
                                        <div class="alert alert-success">
                                            <strong>Note :</strong> <a href="#" class="alert-link">All the Downloads are in Zipped Format. Please Unzip them before installations.</a>.
                                        </div>
                                        <div class="single category">
                                            <ul class="list-unstyled">
                                                <li><a href="#" title="">SecureSign</a>
                                                    <label>Must be Downloaded and installed so the Bids can be digitally signed </label>
                                                </li>
                                                <li><a href="#" title="">User Manuals </a>
                                                    <label>Documents containing instructions for using the Works eTender application </label>
                                                </li>
                                                <li><a href="#" title="">CBTs</a>
                                                    <label>Computer Based Training for Contractors to use the Works eTender Application</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix"></div>                                
                            </div>
                        </div>

                    </div>
                    <!-- End Container -->
                   <div class="col-sm-12 text-center positionABS">
                      <button class="btn-all btn btn-info btnPrevious pull-left">Previous</button>
                      <button class="btn btn-info">Save</button>
                      <button class="btn-all btn btn-info btnNext pull-right">Next</button>
                   </div>
                </div>
            </div>
        </div>
        </div>
        <!-- PAGE CONTENT -->
         <div class="height20"></div>
<div class="footer">
        Â© 2017 E-Auctionapp. All Rights Reserved.
    </div>
</body>
<script src="<%=request.getContextPath()%>/resource/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resource/js/bootstrap-datepicker.js?appVer=${appVer}"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/Profile.js?appVer=${appVer}"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/mdb.min.js?appVer=${appVer}"></script>

</html>
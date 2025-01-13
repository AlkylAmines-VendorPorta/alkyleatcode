<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<div class="viewtender" style="padding-top:20px;">
									 <div class="form-group">
                                                    <label class="col-sm-2">Tender Number<span class="red">*</span></label>
                                                    <label class="col-sm-4 detspan">
                                                        00001 Tender e-Auction
                                                    </label>
                                                    <label class="col-sm-6 textright">
                                                        <a class="seeall">Click her to see All</a></label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Tender Name<span class="red">*</span></label>
                                                    <label class="col-sm-2  detspan">
                                                        00001 Tender e-Auction
                                                    </label>
                                                    <label class="col-sm-2">Tender Type<span class="red">*</span></label>
                                                    <label class="col-sm-2  detspan">
                                                        Works Tender
                                                    </label>
                                                    <label class="col-sm-2">Tender Budget Type<span class="red">*</span></label>
                                                    <label class="col-sm-2  detspan">
                                                         00001 Tender e-Auction
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Brief Description of Work<span class="red">*</span></label>
                                                    <label class="col-sm-4 detspan">
                                                       001TestTender
                                                    </label>
                                                    
                                                    <label class="col-sm-2">Qualifying Requirement:</label>
                                                    <label class="col-sm-4 detspan">
                                                        001TestTender
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Office Type<span class="red">*</span></label>
                                                    <Label class="col-sm-2 detspan">
                                                        Ho
                                                    </label>
                                                    <label class="col-sm-2">Office Location<span class="red">*</span></label>
                                                    <label class="col-sm-2 detspan">
                                                        PRAKASHGHAD
                                                    </label>
                                                    <label class="col-sm-2">Scheme Name<span class="red">*</span></label>
                                                    <label class="col-sm-2 detspan">
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Scheme Code<span class="red">*</span></label>
                                                    <label class="col-sm-2 detspan">
                                                       00001 Tender e-Auction
                                                    </label>
                                                    <label class="col-sm-2">Estimated Cost (Rs.)</label>
                                                    <label class="col-sm-2 detspan">
                                                        10,000 Rs
                                                    </label>
                                                    <label class="col-sm-2">Tender Fee (Rs.)<span class="red">*</span></label>
                                                    <label class="col-sm-2 detspan">
                                                         10,000 Rs
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Emd (Rs.)</label>
                                                    <label class="col-sm-2 detspan">
                                                         10,000 Rs
                                                    </label>                                                
                                                    <label class="col-sm-2">Sale Opening Date<span class="red">*</span></label>
                                                    <label class="col-sm-2 detspan">
                                                        11-03-2018
                                                    </label>
                                                    <label class="col-sm-2">Sale Closing Date<span class="red">*</span></label>
                                                    <label class="col-sm-2 detspan">
                                                        11-03-2018
                                                    </label>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="col-sm-2">Pre-bid Date</label>
                                                    <label class="col-sm-2 detspan">
                                                        11-03-2018
                                                    </label>
                                                    <label class="col-sm-2">Time</label>
                                                    <label class="col-sm-2 detspan">
                                                        11:25PM
                                                    </label>                                              
                                                    <label class="col-sm-2">Submission Closing Date<span class="red">*</span></label>
                                                    <label class="col-sm-2 detspan">
                                                        11-03-2018
                                                    </label> <div class="clearfix"></div>
                                                    <label class="col-sm-2">Time</label>
                                                    <label class="col-sm-2 detspan">
                                                        11:25PM
                                                    </label>  
                                                </div>
                                               
                                                </div>  
                                  
<script src="resources/${appMode}/commons/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>                                                
<script>
$(document).ready(function(){
	$('.tenderdetailsTab').click(function(){
		$('.tenderdetailsTablediv').hide();
		$('.viewtender').show();
	});
	$('.seeall').click(function(){
		$('.tenderdetailsTablediv').show();
		$('.viewtender').hide();
	});
});
</script>
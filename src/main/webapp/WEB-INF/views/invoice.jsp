<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40">
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="ProgId" content="Excel.Sheet"/>
  <meta name="Generator" content="WPS Office ET"/>
 <style>
.header{display:none;}
.footer{display:none;}
td {
    white-space: initial !important;
    overflow: hidden;
    text-overflow: ellipsis;
    max-width: 200px;
}
</style>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script>
<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/numberToWords.js?appVer=${appVer}"></script>
<script	src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/paymentReceipt.js?appVer=${appVer}"></script>
 </head>
 <body>
<input type="hidden" id="receiptUrl" value="${receiptUrl}" />

 <div style="color:#000; width:98%; font-weight:bold; margin:10px auto; border:1px solid #000;clear: both;  overflow: hidden;">
   <div style="width:20%;  float:left; font-size:13px;">
   <img alt="" src="resources/${appMode}/commons/images/Mahadiscom_Logo.jpg?appVer=${appVer}">
   </div>
 <div style="width:60%;  float:left; text-align:center;">
 <label style="width:100%; display: inline-block;"><h3 id="supplierName" class=""></h3></label>																			
<label style="width:100%; display: inline-block;">Address:<span id="supplierAddress" class=""></span>				</label>												
<label style="width:100%; display: inline-block;">GSTIN:<span id="supplierGSTIN" class=""></span>		</label>											

 </div>
  <div style="width:20%;  float:left; text-align:right; font-size:13px;">
			
	<label style="width:100%; display: inline-block;">	Original for Recipient</label>		
	<label style="width:100%; display: inline-block;">	Duplicate for Transporter</label><!-- 	
	<label style="width:100%; display: inline-block;">	In case of services, only Duplicate for Supplier to be mentioned</label>	 -->
	<label style="width:100%; display: inline-block;">	Triplicate for Supplier</label>	<br><br>
	
	
											

 </div>
 
 <div style="width:100%; color:#000; padding:7px 0px; overflow: hidden; border-top:1px solid #000; border-bottom:1px solid #000;">
 <label style="width:33%; text-alignt:left; display: inline-block;">Invoice No.:<span id="invoiceNo" class=""></span>	</label>	
 <label style="width:33%; text-align:center; display: inline-block;">TAX INVOICE</label>
	<label style="width:33%; text-align:right; display: inline-block;">Invoice date:<span id="invoiceDate" class=""></span>	</label>
	</div>	
 
<table style="width:100%;">
	<tr>
		<th style="width: 100px;">Vendor ID: &nbsp; &nbsp;</th>
		<td><span id="vendorID" class=""></span></td>
		<th style="width: 150px;">GSTIN/Unique ID:</th>
		<td><span id="vendorGSTIN" class=""></span></td>
		<th style="width: 100px;">State:</th>
		<td><span id="vendorState" class=""></span></td>
	</tr>
	<tr>
		<th>Name:</th>
		<td><span id="vendorName" class=""></span></td>
		<th>PAN:</th>
		<th><span id="vendorPAN" class=""></span></th>
		<th></th>
		<td></td>
	</tr>
	<tr>
		<th>Address:</th>
		<td><span id="vendorAddress" class=""></span></td>
		<th>CIN:</th>
		<td><span id="vendorPAN" class=""></span></td>
		<th>State Code:</th>
		<td><span id="vendorStateCode" class=""></span></td>
	</tr>
</table>
 <!-- <div style="width:40%;  float:left;">	
<label style="width:100%; display: inline-block;">Vendor ID: &nbsp; &nbsp;</label>		
<label style="width:100%; display: inline-block;">Name: &nbsp; &nbsp;<span id="vendorName" class=""></span>	</label>
<label style="width:100%; display: inline-block;">Address: &nbsp; &nbsp;<span id="vendorAddress" class=""></span></label>
</div>	 -->
<!-- <div style="width:30%;  float:left;">
<label style="width:100%; display: inline-block;">GSTIN/Unique ID: &nbsp; &nbsp;<span id="vendorGSTIN" class=""></span></label>
<label style="width:100%; display: inline-block;">PAN: &nbsp; &nbsp;<span id="vendorPAN" class=""></span></label>
<label style="width:100%; display: inline-block;">CIN: &nbsp; &nbsp;<span id="vendorPAN" class=""></span></label>	
</div> -->
<!--  <div style="width:30%;  float:left;">	
 	
<label style="width:100%; display: inline-block;">State: &nbsp; &nbsp;<span id="vendorState" class=""></span></label>	
<label style="width:100%; display: inline-block;">State Code: &nbsp; &nbsp;<span id="vendorState" class=""></span></label>	 -->
 <!-- 
<label style="width:100%; display: inline-block;">Place of Supply (in case of a supply in course of inter state trade)</label>								
								
<label style="width:100%; display: inline-block;">Company Name:<span id="companyName" class=""></span></label>
<label style="width:100%; display: inline-block;">Company Address:<span id="companyAddress" class=""></span></label>	
<label style="width:100%; display: inline-block;">Company State:<span id="companyState" class=""></span></label>	
<label style="width:100%; display: inline-block;">Company GSTIN/Unique ID:<span id="companyGSTIN" class=""></span></label>	
<label style="width:100%; display: inline-block;">Company PAN:<span id="companyPAN" class=""></span></label>	 -->
								
<!-- <label style="width:100%; display: inline-block;">Place of Delivery (if different from place of supply)</label>								
								
<label style="width:100%; display: inline-block;">Name:<span id="delvierName" class=""></span>	</label>
<label style="width:100%; display: inline-block;">Address:<span id="delvierAddress" class=""></span></label>	
<label style="width:100%; display: inline-block;">State:<span id="delvierState" class=""></span></label>	
<label style="width:100%; display: inline-block;">State Code:<span id="delvierStateCode" class=""></span></label>	
<label style="width:100%; display: inline-block;">GSTIN/Unique ID:<span id="delvierGSTIN" class=""></span></label>	
<label style="width:100%; display: inline-block;">PAN:<span id="delvierPAN" class=""></span></label>	
<label style="width:100%; display: inline-block;">CIN:<span id="delvierCIN" class=""></span></label>	 -->							

<!-- </div>	 -->

  <table style="width: 99%;  margin: 0px auto; text-align:center;" border="1" cellpadding="0" cellspacing="0" >
   <tr style='height:15.00pt; text-align:center;'>
    <td width="64" rowspan="2">S.No</td>
    <td width="64" rowspan="2" style='width:48.00pt;'>Description of Goods/ Services</td>
    <td width="64" rowspan="2" style='width:48.00pt;'>HSN code/ SAC code</td>
    <td width="128" rowspan="2" style='width:96.00pt;'>Qty/ Unique qty code (in case of goods)</td>
    <td width="64" rowspan="2" style='width:48.00pt;'>UoM</td>
    <td width="64" rowspan="2" style='width:48.00pt;'><span style='mso-spacerun:yes;'>&nbsp;&nbsp;&nbsp;</span>Rate (per item )</td>
    <td width="64" rowspan="2" style='width:48.00pt;'>Total value</td>
    <td width="64" rowspan="2" style='width:48.00pt;'>Discount</td>
    <td width="128" rowspan="2"  style='width:96.00pt;'>Taxable value (after abatement/ discount, if any)</td>
    <td width="128" colspan="2" style='width:96.00pt;'>CGST/ UTGST</td>
    <td width="128" colspan="2" style='width:96.00pt;'>SGST</td>
    <td width="128" colspan="2" style='width:96.00pt;'>IGST</td>
    <td width="128" colspan="2" style='width:96.00pt;'>Cess</td>
   </tr>
   <tr style='height:15.00pt; text-align:center;'>
    <td>Rate(%)</td>
    <td>Amount</td>
    <td>Rate(%)</td>
    <td>Amount</td>
    <td>Rate(%)</td>
    <td>Amount</td>
    <td>Rate(%)</td>
    <td>Amount</td>
   </tr>
   <tr id="paymentDetail">
    <!-- <td rowspan="5"></td>
    <td rowspan="5"></td>
    <td rowspan="5"></td>
    <td rowspan="5"></td>
    <td rowspan="5"></td>
    <td rowspan="5"></td>
	<td rowspan="5"></td>
    <td rowspan="10"></td>
    <td  rowspan="10"></td>
    <td  rowspan="10"colspan="2" ></td>
    <td rowspan="10"></td>
    <td rowspan="10"></td>
    <td rowspan="10"></td>
    <td rowspan="10"></td>
    <td rowspan="10"></td>
    <td rowspan="10"></td>
    <td rowspan="10"></td> --> 
   </tr>
   <tr>
   </tr>
   <tr>
   </tr>
   <tr>
   </tr>
   <tr>
   </tr>
<!--    <tr height="20" style='height:15.00pt;'>
    <td height="20" colspan="7" style='height:15.00pt;'>Freight</td>
   </tr>
   <tr height="20" style='height:15.00pt;'>
    <td height="20" colspan="7" style='height:15.00pt;'></td>
   </tr>
   <tr height="20" style='height:15.00pt;'>
    <td height="20" colspan="7" style='height:15.00pt;'>Insurance</td>
   </tr>
   <tr height="20" style='height:15.00pt;'>
    <td height="20" colspan="7" style='height:15.00pt;'></td>
   </tr> 
   <tr height="20" style='height:15.00pt;'>
    <td height="20" colspan="7" style='height:15.00pt;'>Packing and Forwarding Charges</td>
   </tr>-->
  
   <tr id="totalOfAllValues">
    <!-- <td height="20" style='height:15.00pt;'></td>
    <td colspan="6" style=''>Total</td>
    <td></td>
    <td></td>
    <td colspan="2" ></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td> -->
   </tr>
   <tr id="totalInvoiceValue">
    <!-- <td height="20" colspan="11" style='height:15.00pt;'>Total Invoice value (in figure)</td>
    <td colspan="8" ></td> -->
   </tr>
   <tr id="totalInvoiceValueInWords">
    <!-- <td height="20" colspan="11" style='height:15.00pt;'>Total Invoice value (in words)</td>
    <td colspan="8" ></td> -->
   </tr>
   <tr id="isRefundable">
    <!-- <td height="20" colspan="11" style='height:15.00pt;'>Whether Reverse charge applicable (Y/N)</td>
	<td colspan="8" >N</td> -->
   </tr>
  
  </table>
  
  <div style="width:50%;  float:left; margin-top:20px;">
 <label style="width:100%; display: inline-block;">Declaration/ Terms & Conditions: &nbsp; &nbsp;</label>																			
<label style="width:100%; display: inline-block;">Electronic Reference Number: &nbsp; &nbsp;</label>												
<label style="width:100%; display: inline-block;">Certified that the particulars given above are true and correct: &nbsp; &nbsp;</label>											

 </div>
  <div style="width:50%;  float:left; margin-top:20px;">
			
	<label style="width:100%; display: inline-block;">Signature: &nbsp; &nbsp;</label>		
	<label style="width:100%; display: inline-block;">Name of Signatory: &nbsp; &nbsp;<span id="signatoryName" class=""></span></label>	
	<label style="width:100%; display: inline-block;">Designation/Status: &nbsp; &nbsp;<span id="signatoryDesignation" class=""></span></label>	
	<label style="width:50%; float:left; display: inline-block;">Date: &nbsp; &nbsp;<span id="date" class=""></span></label>
	<label style="width:50%; float:left; display: inline-block;">Place: &nbsp; &nbsp;<span id="place" class="">Mumbai</span></label>	
	
											

 </div>
 
 
 
 <div style="text-align:center; margin:5px;"><button id="print"  style="background: #000; color: #fff; border: 1px solid; padding: 5px 10px;" onclick="printPage()">Print this page</button></div>
  </div>
 </body>
</html>

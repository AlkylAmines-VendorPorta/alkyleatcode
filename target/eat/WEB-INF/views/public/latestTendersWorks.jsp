<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<style>.form-group {
    margin-top: 5px;
    margin-bottom: 0px;
}
.close {    color: #f50303;}</style>
    <div class="container-fluid slidepadding">
    <c:if test="${tahdrType.equalsIgnoreCase('WT')}">
      		<input type="hidden" id="latestTAHDR" value="WT" >
      </c:if>
      <c:if test="${tahdrType.equalsIgnoreCase('PT')}">
      		<input type="hidden" id="latestTAHDR" value="PT" >
      </c:if>
      <c:if test="${tahdrType.equalsIgnoreCase('FA')}">
      		<input type="hidden" id="latestTAHDR" value="FA" >
      </c:if>
      <c:if test="${tahdrType.equalsIgnoreCase('RA')}">
      		<input type="hidden" id="latestTAHDR" value="RA" >
      </c:if>
        <section class="center-section">
 <h4 id="tahdrHeader"></h4>
        <div class="tenderdetailsTablediv">
           <table class="tableResponsive table table-striped table-bordered" width="100%" id="tahdrTable">
                                            <thead>
                                                <tr>
                                                    <th>Tender No</th>
                                                    <th>Description</th>
                                                    <th>Purchase FromDate</th>
                                                    <th>Purchase ToDate</th>
                                                    <th>TechnicalBid OpenDate</th> 
                                                    <th>Submission Due</th>
                                                    <th>Tender Fee(Rs)</th>
                                                    <th>Pre Bid MOM</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                               
                                            </tbody>
                                        </table>   
                                        </div> 
                                                    <jsp:include page="viewTenderDetails.jsp" />
                                        </section> 
        </div>          
        
        
		<script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/formFields.js?appVer=${appVer}"></script>
        <script src="<%=request.getContextPath()%>/resources/${appMode}/commons/js/utility.js?appVer=${appVer}"></script> 
		<script src="<%=request.getContextPath()%>/resources/${appMode}/transaction/js/headerLatestTahdr.js?appVer=${appVer}"></script>
		<script>
$(document).ready(function(){
	
});
</script>
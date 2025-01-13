<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
<style>.form-group {
    margin-top: 15px;
    margin-bottom: 5px;
}</style>
   <div class="container-fluid slidepadding">
        <section class="center-section">
        <div class="tenderdetailsTablediv">
        <h4>Forward Auction</h4>
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
                                         <jsp:include page="viewTenderDetails.jsp" />                 
                                        </section> 
        </div>     
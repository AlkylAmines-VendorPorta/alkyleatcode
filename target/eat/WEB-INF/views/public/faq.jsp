<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "sf"%>
 <link rel="stylesheet" href="resources/${appMode}/commons/css/faq.css?appVer=${appVer}">
   <div class="container-fluid slidepadding">
        <section class="center-section">
        <h4>Frequently Asked Questions</h4>
              <div class="col-sm-12 nopaddingleft">
  <div class="col-md-4">
    <ul class="list-group help-group">
      <div class="faq-list list-group nav nav-tabs">
        <a href="#tab1" class="list-group-item active" role="tab" data-toggle="tab">Frequently Asked Questions</a>
        <a href="#tab2" class="list-group-item" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> My profile</a>
        <a href="#tab3" class="list-group-item" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-list-alt"></span>My account</a>
        <a href="#tab4" class="list-group-item" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-star"></span> My favorites</a>
        <a href="#tab5" class="list-group-item" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-shopping-cart"></span> Checkout</a>
        <a href="#tab6" class="list-group-item" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-calendar"></span> Lorem ipsum</a>
        <a href="#tab7" class="list-group-item" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-thumbs-up"></span> Dolor sit amet</a>
      </div>
    </ul>
  </div>
  <div class="col-md-8">
    <div class="tab-content panels-faq">
      <div class="tab-pane active" id="tab1">
        <div class="panel-group" id="help-accordion-1">
          <div class="panel panel-default panel-help">
            <a href="#opret-produkt" data-toggle="collapse" data-parent="#help-accordion-1">
              <div class="panel-heading">
                <h2>How to register?</h2>
              </div>
            </a>
            <div id="opret-produkt" class="collapse in">
              <div class="panel-body">
                <p>Visit website,click on “New User” enter your details. Click on “Register” after</p>
              </div>
            </div>
          </div>
          <div class="panel panel-default panel-help">
            <a href="#rediger-produkt" data-toggle="collapse" data-parent="#help-accordion-1">
              <div class="panel-heading">
                <h2>In case if I forget my password?</h2>
              </div>
            </a>
            <div id="rediger-produkt" class="collapse">
              <div class="panel-body">
                <p>Please click on forgot “ password”or else request mail can be sent to Service Help-desk for further assistance</p>
              </div>
            </div>
          </div>
          <div class="panel panel-default panel-help">
            <a href="#ret-pris" data-toggle="collapse" data-parent="#help-accordion-1">
              <div class="panel-heading">
                <h2>In case of portal problem,whom should we contact for further communication?</h2>
              </div>
            </a>
            <div id="ret-pris" class="collapse">
              <div class="panel-body">
                <p>For portal problem,please mention service help-desk number of E-Auctionapp,or Toll Free number if any.</p>
              </div>
            </div>
          </div>
          <div class="panel panel-default panel-help">
            <a href="#slet-produkt" data-toggle="collapse" data-parent="#help-accordion-1">
              <div class="panel-heading">
                <h2>Can I change my bid data after once it is uploaded on E-Auctionapp portal?</h2>
              </div>
            </a>
            <div id="slet-produkt" class="collapse">
              <div class="panel-body">
                <p>Yes,till date of Bid opening you can edit data uploaded & resubmit again</p>
              </div>
            </div>
          </div>
          <div class="panel panel-default panel-help">
            <a href="#opret-kampagne" data-toggle="collapse" data-parent="#help-accordion-1">
              <div class="panel-heading">
                <h2>Is hard copy needed to submitted for Tender Filling after registration?</h2>
              </div>
            </a>
            <div id="opret-kampagne" class="collapse">
              <div class="panel-body">
                <p>This depends on the details mentioned in particular Tender.</p>
              </div>
            </div>
          </div>
          <div class="panel panel-default panel-help">
            <a href="#opret-kampagne2" data-toggle="collapse" data-parent="#help-accordion-1">
              <div class="panel-heading">
                <h2>If I am registered,how long it will be valid?</h2>
              </div>
            </a>
            <div id="opret-kampagne2" class="collapse">
              <div class="panel-body">
                <p>Please confirm it from E-Auctionapp</p>
              </div>
            </div>
          </div>
          <div class="panel panel-default panel-help">
            <a href="#opret-kampagne2" data-toggle="collapse" data-parent="#help-accordion-1">
              <div class="panel-heading">
                <h2>What about the privacy of my information?</h2>
              </div>
            </a>
            <div id="opret-kampagne2" class="collapse">
              <div class="panel-body">
                <p>The information provided by you is secure</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="tab-pane" id="tab2">
        <div class="panel-group" id="help-accordion-2">      
          <div class="panel panel-default panel-help">
            <a href="#help-three" data-toggle="collapse" data-parent="#help-accordion-2">
              <div class="panel-heading">
                <h2>Lorem ipsum?</h2>
              </div>
            </a>
            <div id="help-three" class="collapse in">
              <div class="panel-body">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloribus nesciunt ut officiis accusantium quisquam minima praesentium, beatae fugit illo nobis fugiat adipisci quia distinctio repellat culpa saepe, optio aperiam est!</p>
                <p><strong>Example: </strong>Facere, id excepturi iusto aliquid beatae delectus nemo enim, ad saepe nam et.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="tab-pane" id="tab3">
        <div class="panel-group" id="help-accordion-2">      
          <div class="panel panel-default panel-help">
            <a href="#help-three" data-toggle="collapse" data-parent="#help-accordion-2">
              <div class="panel-heading">
                <h2>Aman</h2>
              </div>
            </a>
            <div id="help-three" class="collapse in">
              <div class="panel-body">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloribus nesciunt ut officiis accusantium quisquam minima praesentium, beatae fugit illo nobis fugiat adipisci quia distinctio repellat culpa saepe, optio aperiam est!</p>
                <p><strong>Example: </strong>Facere, id excepturi iusto aliquid beatae delectus nemo enim, ad saepe nam et.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>    
  </div>
</div>        
        </section> 
        </div>        
<script src="resources/${appMode}/transaction/js/home.js?appVer=${appVer}"></script> 
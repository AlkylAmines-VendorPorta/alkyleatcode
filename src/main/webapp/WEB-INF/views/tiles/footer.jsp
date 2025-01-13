<div class="footer">
            <div class="addressFtr">
               <span>
                  <i class="glyphicon glyphicon-globe"></i> Address : Anant Kanekar Marg, D Block BKC, Naupada, Bandra East, Mumbai, Maharashtra 400051
               </span>
              <!--  |
               <span>
               	Head Office : Prakashgad, Prakash Ganga, Estrella Batt., Dharavi
               	</span>
               	| -->
               <span>
                  <i class="glyphicon glyphicon-earphone"></i> Phone : 022 2647 4753
               </span>
               |
               <span>
                  <i class="glyphicon glyphicon-envelope"></i> Email : support_etender@mahadiscom.in
               </span>
            </div>
            <div class="clearfix"></div>
            <div class="copyrightFtr"> 
               Copyright © e-Auction All rights reserved.
            </div>  
            <div class="clearfix">
         </div>
         </div>

<script src="resources/${appMode}/commons/js/jquery-3.2.1.min.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/bootstrap.min.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/jquery.dataTables.min.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/dataTables.bootstrap.min.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/homeSlider.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/header.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/commons/js/springform.js?appVer=${appVer}"></script>
<script src="resources/${appMode}/transaction/js/datatable.js?appVer=${appVer}"></script> 
<script src="resources/${appMode}/commons/js/sweetalert2.min.js?appVer=${appVer}"></script>
<script>
$(document).ready(function(){
	$("#password").focus();
});
$(function() {
    var pgurl = window.location.href.substr(window.location.href
    .lastIndexOf("/")+1);
     $(".activenav li a").each(function(){
          if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
          /* $(this).parent().addClass("active"); */
          $(this).parent().siblings().removeClass('active').end().addClass('active');          
     })     
});
</script>
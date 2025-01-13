 <div class="clearfix"></div>

    <div class="footer">© 2018 Novelerp. All Rights Reserved.</div>
<script>
$(function() {
    var pgurl = window.location.href.substr(window.location.href
    .lastIndexOf("/")+1);
     $(".menu-content li a").each(function(){
          if($(this).attr("href") == pgurl || $(this).attr("href") == '' )
          /* $(this).parent().addClass("active"); */
          $(this).parent().siblings().removeClass('active').end().addClass('active');          
     })     
});
</script>
<div id="alert-info" class="alert alert-info animated fadeInRight alert-top" role="alert">
    <button type="button" class="close alert-close" aria-label="Close"><span aria-hidden="true">×</span></button>
    <span class="alert-msg"></span>
</div>
<div id="alert-warn" class="alert alert-warning alert-top animated fadeInRight" role="alert">
    <button type="button" class="close alert-close" aria-label="Close"><span aria-hidden="true">×</span></button>
    <span class="alert-msg"></span>
</div>
<!-- Modal  For File Upload Progress Bar -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content mdcontent">
    
      <div class="modal-body">
       <div class="form-group">
              <div class="progress">
          <div class="progress-bar bar">
            
          </div>
        </div>
                </div>
                <div class="clearfix"></div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
 <script type="text/javascript">
$(document).ready(function(){
    function alignModal(){
        var modalDialog = $(this).find(".modal-dialog");
        /* Applying the top margin on modal dialog to align it vertically center */
        modalDialog.css("margin-top", Math.max(0, ($(window).height() - modalDialog.height()) / 2));
    }
    // Align modal when it is displayed
    $(".modal").on("shown.bs.modal", alignModal);
    
    // Align modal when user resize the window
    $(window).on("resize", function(){
        $(".modal:visible").each(alignModal);
    });   
});
</script>
var plugin_index = $('.paginate').length;
var lengthMenu;

if ($(window).width() < 480) {
    $('.mobileNav').show();
    $.fn.DataTable.ext.pager.numbers_length = 4;       
    lengthMenu = [ 1, 5, 7, 10, ],
    [ 1, 5, 7, 10, ]
} else {        
    lengthMenu = [ 7, 10, ],
    [ 7, 10, ]
}
$(document).ready(function() {
	// tiles  
	
	/*Right Key And Inspect Element Block
	$("body").on("contextmenu",function(e){
        return false;
    });
	$(document).keydown(function (event) {
	    if (event.keyCode == 123) { // Prevent F12
	        return false;
	    } else if (event.ctrlKey && event.shiftKey && event.keyCode == 73) { // Prevent Ctrl+Shift+I        
	        return false;
	    }
	});*/
	/*Right Key And Inspect Element Block*/
		if ($(window).width() < 768) {
			$('.dataTables_length').parent().addClass('col-xs-6');
		
		 $('.dataTables_filter').parent().addClass('col-xs-6');
		 /*$('#auctionparticipate').DataTable({ 'oLanguage': {
		       sLengthMenu: "_MENU_",
		    }
			});
			$('#Serchauctionparticipate').DataTable({ 'oLanguage': {
		       sLengthMenu: "_MENU_",
		    }
			});*/
		}
		
	if ($(window).width() < 768) {
		
	$('body').on('click', '.tabs-left li a', function(e) {	
		mobiletable();
	});	
	}
	if ($(window).width() < 768) {
	$('body').on('click', '.searchItemList', function() {	
		mobiletable();
		});
	}
	$('.styled-input select').parent().addClass('slate');
	$('#mobile_first_container').before('.tabs-left').append('<div class="gobtnfixed"><input type="text" id="jumpIndex" class="form-control goinput"/><button class="btn btn-default goinputbtn jumpPagination jumptopage" type="button">Go</button></div>');
	$('#zoomBtn').click(function() {
  	  $('.zoom-btn-sm').toggleClass('scale-out');
  	  if (!$('.zoom-card').hasClass('scale-out')) {
  	    $('.zoom-card').toggleClass('scale-out');
  	  }  	 
  	});
	 $(".tabs-left li a label").text(function(index, currentText) {
		    return currentText.substr(0, 20);
		});
	if ($(window).width() < 768) {
	 $("#dropdownMenu1").text(function(index, currentText) {
         return currentText.substr(0, 10);
     });
	 
	}
	/*$('.datepicker').datepicker({
		autoclose: true,
		Default: true,
		orientation: "auto"
	});
	  $('.timepicker').timepicker();*/
	  
	/*$("#date").datetimepicker({
        format: "dd MM yyyy",
        autoClose: true,
        todayBtn: false,
        pickerPosition: "top-left"
    });*/
	/* $("#datepicker").datetimepicker({
		    format: "L",
		    icons: {
		      next: "fa fa-chevron-right",
		      previous: "fa fa-chevron-left"
		    }
		  });*/
  	$('.zoom-btn-sm').click(function() {
  	  var btn = $(this);
  	  var card = $('.zoom-card');

  	  if ($('.zoom-card').hasClass('scale-out')) {
  	    $('.zoom-card').toggleClass('scale-out');
  	  }
  	  if (btn.hasClass('zoom-btn-person')) {
  	    card.css('background-color', '#d32f2f');
  	  } else if (btn.hasClass('zoom-btn-doc')) {
  	    card.css('background-color', '#fbc02d');
  	  } else if (btn.hasClass('zoom-btn-tangram')) {
  	    card.css('background-color', '#388e3c');
  	  } else if (btn.hasClass('zoom-btn-report')) {
  	    card.css('background-color', '#1976d2');
  	  } else {
  	    card.css('background-color', '#7b1fa2');
  	  }
  	});
	$('#Auction').click(function() {
		$('#MainProductTiles').hide();
		$('#AuctionDetailsTiles').show();
		$('.goback').show();
	});
	$('#Master').click(function() {
		$('#MainProductTiles').hide();
		$('#MasterDetailsTiles').show();
		$('.goback').show();
	});
	$('#Vendor').click(function() {
		$('#MainProductTiles').hide();
		$('#VendorDetailsTiles').show();
		$('.goback').show();
	});
	$('#User').click(function() {
		$('#MainProductTiles').hide();
		$('#UserDetailsTiles').show();
		$('.goback').show();
	});
	// home 
	$('body').on('click', '.tabs-left li a', function(e) {		
		
		if ($(window).width() < 768) {
			$('#mobile_first_container').hide();
			$('#mobile_second_container').show();
			$('.mobile_Batch_container').hide();
			$('.mobile_Auction_container').show();
			$('.backTolist').show();
			$('.backTotils').hide();
			$("#tabstrip").kendoTabStrip();
			$("#tabstrip1").kendoTabStrip();
            $("#tabstrip2").kendoTabStrip();
            $("#tabstrip3").kendoTabStrip();
            $("#tabstrip4").kendoTabStrip();
            $("#tabstrip5").kendoTabStrip();
           
            
		}
	});
	$('.addnewlist').click(function(){
		if ($(window).width() < 768) {
			$('#mobile_first_container').hide();
			$('#mobile_second_container').show();
			$('.backTolist').show();
			$('.backTotils').hide();
			$("#tabstrip").kendoTabStrip();
			$("#tabstrip1").kendoTabStrip();
            $("#tabstrip2").kendoTabStrip();
            $("#tabstrip3").kendoTabStrip();
            $("#tabstrip4").kendoTabStrip();
            $("#tabstrip5").kendoTabStrip();
		}
	});
	/*$('.homeSection').on('click', function(){
	$('#sidebar-wrapper').removeClass('active');
	$('.dashcontent').css("width","100% !important");
	});*/
	$('.backTolist').click(function() {
		if ($(window).width() < 768) {
			$('#mobile_first_container').show();
			$('#mobile_second_container').hide();
			$('.backTolist').hide();
			$('.backTotils').show();
		}
	});
	/*$('.btnNext').click(function() {
		$('.nav-tabs > .active').next('li').find('a').trigger('click');
	});

	$('.btnPrevious').click(function() {
		$('.nav-tabs > .active').prev('li').find('a').trigger('click');
	});
	$('.tab_bar_twoNext').click(function() {
		$('.tab_bar_two > .active').next('li').find('a').trigger('click');
	});

	$('.tab_bar_twoPrevious').click(function() {
		$('.tab_bar_two > .active').prev('li').find('a').trigger('click');
	});*/
	
	$(".menu-toggle").click(function(e) {
		e.preventDefault();
		$("#sidebar-wrapper").toggleClass("active");
	});
	$(function() {
		$(".feedback-tab").click(function() {
			$(".feedback-form").toggle("slide");
		});
		

		$(".feedback-form form").on('submit', function(event) {
			var $form = $(this);
			$.ajax({
				type: $form.attr('method'),
				url: $form.attr('action'),
				data: $form.serialize(),
				success: function() {
					$(".feedback-form").toggle("slide").find("textarea").val('');
				}
			});
			event.preventDefault();
		});
	});
});

$(window).on('load', function(){
	$('#loading-wrapper').fadeOut("slow");
});
function mobiletable(){
	
	if ($(window).width() < 768) {
		$('.dataTables_length').parent().addClass('col-xs-6');
	
	 $('.dataTables_filter').parent().addClass('col-xs-6');
	}
	$('.tableResponsive').each(function(){		
		var text = []
		$(this).find('thead tr th').each(function(){
			text.push($(this).text())

			for (var i = 0; i < text.length; i++) {
				$(this).parents('.tableResponsive').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
			}	
		});		
	}); 
		$('.pagination li.paginate_button a').click(function(){
		$('.tableResponsive').each(function(){		
			var text = []
			$(this).find('thead tr th').each(function(){
				text.push($(this).text())

				for (var i = 0; i < text.length; i++) {
					$(this).parents('.tableResponsive').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
				}	
			});		
		});
		});
		$('.dataTable').on('draw.dt', function() {
			$('.tableResponsive').each(function(){		
				var text = []
				$(this).find('thead tr th').each(function(){
					text.push($(this).text())

					for (var i = 0; i < text.length; i++) {
						$(this).parents('.tableResponsive').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
					}	
				});		
			});
		});
	$( ".dataTables_length select").change(function() {
	$('.tableResponsive').each(function(){		
		var text = []
		$(this).find('thead tr th').each(function(){
			text.push($(this).text())

			for (var i = 0; i < text.length; i++) {
				$(this).parents('.tableResponsive').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
			}	
		});		
	});
	});
}
$(function() {
	  Alert = {
	    show: function($div, msg) {
	      $div.find('.alert-msg').html(msg);
	      if ($div.css('display') === 'none') {
	    	  if($div.attr('id') ===('alert-warn')){
	    		  $div.fadeIn(100);
	    		  $('.alert-backdrop').show();
	    	  }else{
	    		 
	    		  $div.fadeIn(100).delay(1000).fadeOut(2000);
	    	  }
	        // fadein, fadeout.
	       
	      }
	    },
	    info: function(msg) {
	      this.show($('#alert-info'), msg);
	    },
	    warn: function(msg) {
	      this.show($('#alert-warn'), msg);
	    }
	  }
	  $('body').on('click', '.alert-close', function() {
	  	$(this).parents('.alert').hide();
	  	$('.alert-backdrop').hide();
	  });
	  $('#info').click(function() {
	    Alert.info('This is infomation alert This is infomation alert This is infomation alert.')
	  });
	  $('#warn').click(function() {
	    Alert.warn('This is warning alert This is infomation alert This is infomation alert This is infomation alert.')
	  });
	});

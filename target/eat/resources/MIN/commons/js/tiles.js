
$(document).ready(function() {
	// tiles  
	$('#zoomBtn').click(function() {
  	  $('.zoom-btn-sm').toggleClass('scale-out');
  	  if (!$('.zoom-card').hasClass('scale-out')) {
  	    $('.zoom-card').toggleClass('scale-out');
  	  }
  	});

	/**/
	
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

		if ($(window).width() < 480) {
			$('#mobile_first_container').hide();
			$('#mobile_second_container').show();
			$('.mobile_Batch_container').hide();
			$('.mobile_Auction_container').show();
			$('.backTolist').show();
			$('.backTotils').hide();
			$("#tabstrip").kendoTabStrip();
            $("#tabstrip2").kendoTabStrip();
            $("#tabstrip3").kendoTabStrip();
            $("#tabstrip4").kendoTabStrip();
            $("#tabstrip5").kendoTabStrip();
            
		}
	});

	$('.backTolist').click(function() {
		if ($(window).width() < 480) {
			$('#mobile_first_container').show();
			$('#mobile_second_container').hide();
			$('.backTolist').hide();
			$('.backTotils').show();
		}
	});
	$('.btnNext').click(function() {
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
	});
	
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
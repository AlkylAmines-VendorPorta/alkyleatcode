    $(document).ready(function() {

        // Math.floor(Math.random() * 256) [0-255]
        // rgb(255,255,255)
        //call paginate
        $('.example').paginate();
        $(".form_datetime").datetimepicker({
            format: "dd MM yyyy - hh:ii",
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        });
        $('.AuctionShow').click(function() {
            $('.Batch_container').hide();
            $('.Auction_container').show();
            if ($(window).width() < 480) {
                $('#mobile_Batch_container').show();
                $('#mobile_Auction_container ').hide();
                $('#mobile_second_container').hide();
                $('#mobile_first_container').hide();
                $('.actionbutton').hide();
            }
        });
        $('.Backtobatch').click(function() {
            $('.Batch_container').show();
            $('.Auction_container').hide();
            if ($(window).width() < 480) {
                $('#mobile_second_container').show();
                $('.actionbutton').show();
            }
        });

        $('body').on('click', '.auctionnav li a', function(e) {

            if ($(window).width() < 480) {
                $('.Backtobatch').hide();
                $('.auctinlist').show();
                $('.actionbutton').show();
                $('#mobile_Batch_container').hide();
    			$('#mobile_Auction_container').show();

            }
        });
        $('body').on('click', '.batchnav li a', function(e) {

            if ($(window).width() < 480) {
                $('.BAlist').show();
                $('.BAtilse').hide();
                $('#mobile_first_container').hide();
    			$('#mobile_second_container').show();
    			$('.mobile_Batch_container').hide();
    			$('.mobile_Auction_container').show();
    			$('.actionbutton').show();
    			$('.actionarrowbutton').hide();
    			$('.goback3').show();
            }
        });
        $('.BAlist').click(function() {
            if ($(window).width() < 480) {
                $('.BAlist').hide();
                $('.BAtilse').show();
                $('#mobile_second_container').hide();
                $('#mobile_first_container').show();
                $('.actionbutton').hide();
            }
        });

        $('.auctinlist').click(function() {
            if ($(window).width() < 480) {
                $('.auctinlist').hide();
                $('.actionbutton').hide();
                $('.Backtobatch').show();
                $('#mobile_Batch_container').show();
                $('#mobile_Auction_container ').hide();
                $('.Auction_container').show();
            }
        });
        $('#zoomBtn').click(function() {
        	  $('.zoom-btn-sm').toggleClass('scale-out');
        	  if (!$('.zoom-card').hasClass('scale-out')) {
        	    $('.zoom-card').toggleClass('scale-out');
        	  }
        	});

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
    });
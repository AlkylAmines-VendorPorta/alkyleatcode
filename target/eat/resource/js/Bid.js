 $(document).ready(function() {
	 $('.btnNext').click(function () {
		      $('#myTab > .active').next('li').find('a').trigger('click');
		    });

		    $('.btnPrevious').click(function () {
		      $('#myTab > .active').prev('li').find('a').trigger('click');
		    });
    	var lengthMenu;

        if ($(window).width() < 480) {
            $('.mobileNav').show();
            $('.margintop').css('margin-top', '10px');
            $.fn.DataTable.ext.pager.numbers_length = 4;       
            lengthMenu = [ 1, 5, 7, 10, ],
            [ 1, 5, 7, 10, ]
        } else {        
            lengthMenu = [ 7, 10, ],
            [ 7, 10, ]
        }
        
        $('table').each(function(){		
    		var text = []
    		$(this).find('thead tr th').each(function(){
    			text.push($(this).text())

    			for (var i = 0; i < text.length; i++) {
    				$(this).parents('table').find('tbody tr td:nth-of-type(' + (i + 1) +')').attr('data-th', text[i])
    			}	
    		});		
    	});	
       $('.BidsListingTable').DataTable({
           /* "scrollY": scrY,
            "scrollX": scrX,
             "pageLength": 4, */
    	   "lengthMenu":lengthMenu
        });
       
        
    });

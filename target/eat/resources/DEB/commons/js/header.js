    $(document).ready(function() { 
        $('ul#SmallNav li a').click(function() {
            var $this = $(this);
            $this.parent().siblings().removeClass('active').end().addClass('active');
        });

        $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
            e.preventDefault();
            $(this).siblings('a.active').removeClass("active");
            $(this).addClass("active");
            var index = $(this).index();
            $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
            $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");           
        });
        $(".RemoveTab").click(function(){
            $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
            $(".list-group-item").removeClass("active");
        });
        if ($(window).width() > 768) { 
        $(".dropdown").hover(            
                function() {
                    $('.dropdown-menu', this).stop( true, true ).fadeIn("fast");
                    $(this).toggleClass('open');
                    $('b', this).toggleClass("caret caret-up");                
                },
                function() {
                    $('.dropdown-menu', this).stop( true, true ).fadeOut("fast");
                    $(this).toggleClass('open');
                    $('b', this).toggleClass("caret caret-up");                
                });
        }

    });
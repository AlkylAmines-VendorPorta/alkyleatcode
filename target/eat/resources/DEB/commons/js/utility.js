var selectedPage = 1;
var selectedEle = 0;
var totalPages= 0;

function setCurrentDate(longDate){
	var dt;
	if(null!=longDate && undefined!=longDate && ""!=longDate){
		dt=new Date(longDate);
	}
	else{
		dt=new Date();
	}
			var dd=dt.getDate()+1;
			var MM=dt.getMonth()+1;
			var yyyy=dt.getFullYear();
			var HH= dt.getHours();
			var mm= (dt.getMinutes()<10?'0':'') + dt.getMinutes();   /*var mm= dt.getMinutes();*/
			var ss= dt.getSeconds();
		
			return dd+'-'+MM+'-'+yyyy+' '+HH+':'+mm;
}

function indianRupeesFormat(amount){
	var x=amount;
	x=x.toString();
	var lastThree = x.substring(x.length-3);
	var otherNumbers = x.substring(0,x.length-3);
	if(otherNumbers != '')
	    lastThree = ',' + lastThree;
	var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
	return res;
}

function indianRupeesFormatWithDecimal(amount){
	var x=amount;
	x=x.toString();
	var afterPoint = '';
	if(x.indexOf('.') > 0)
	   afterPoint = x.substring(x.indexOf('.'),x.length);
	x = Math.floor(x);
	x=x.toString();
	var lastThree = x.substring(x.length-3);
	var otherNumbers = x.substring(0,x.length-3);
	if(otherNumbers != '')
	    lastThree = ',' + lastThree;
	var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
	return res;
}

function getNewDate(date){
	if(typeof(date)=='string'){
		return new Date(date+'GMT+0530');
	}else if(date=='' || date==null || typeof(date)==undefined){
		var tempDt=new Date();
		return new Date(tempDt+'GMT+0530');
	}else{
		var tempDt=new Date(date);
		return new Date(tempDt+'GMT+0530');
	}
}

function formatDate(longDate){
			    	var dt;
			    	if(null!=longDate && undefined!=longDate && ""!=longDate){
			    		dt=new Date(longDate);
			    	}else{
			    		dt=new Date();
			    	}
            	   var dd=dt.getDate();
            	   var MM=dt.getMonth()+1;
            	   var yyyy=dt.getFullYear();
            	   var HH= dt.getHours();
            	   var mm= dt.getMinutes();
            	   var ss= dt.getSeconds();
            	   
            	   return getDoubleDigit(dd)+'-'+getDoubleDigit(MM)+'-'+getDoubleDigit(yyyy)
    	
}

function formatDateTime(longDate){
	    	var dt;
	    	if(null!=longDate && undefined!=longDate && ""!=longDate){
	    		dt=new Date(longDate);
	    	}else{
	    		dt=new Date();
	    	}
	 	   var dd=dt.getDate();
	 	   var MM=dt.getMonth()+1;
	 	   var yyyy=dt.getFullYear();
	 	   var HH= dt.getHours();
	 	   var mm= dt.getMinutes();
	 	   var ss= dt.getSeconds();
	 	   
	 	   return getDoubleDigit(dd)+'-'+getDoubleDigit(MM)+'-'+getDoubleDigit(yyyy)+' '+getDoubleDigit(HH)+':'+getDoubleDigit(mm);
}

function formateCurrentDate(longDate) {
	 	if(null!=longDate && undefined!=longDate && ""!=longDate){
	 		var dt1=new Date().getTime();
	 		var diff=longDate-dt1;
	 		if(diff>0){
	 			var dt=new Date(longDate);
	 			 var dd = Math.floor(diff / (1000 * 60 * 60 * 24));
	 		     var hh = Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	 		     var mm = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
	 		     var ss = Math.floor((diff % (1000 * 60)) / 1000);
	 		
	 			return dd+'d'+hh+'h'+mm+'m'+ss+'s';
	 		}else{
	 			return 0+'s';
	 		}
	 		
	 	}else{
	 		return 3+'s';
	 	}
 }

function formateServerDate(auctionEndTime,serverTime) {
	 	if(null!=auctionEndTime && undefined!=auctionEndTime && ""!=auctionEndTime){
	 		var diff=auctionEndTime-serverTime;
	 		if(diff>0){
	 			 var dd = Math.floor(diff / (1000 * 60 * 60 * 24));
	 		     var hh = Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	 		     var mm = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
	 		     var ss = Math.floor((diff % (1000 * 60)) / 1000);
	 		
	 			return dd+'d'+hh+'h'+mm+'m'+ss+'s';
	 		}else{
	 			return 0+'s';
	 		}
	 		
	 	}else{
	 		return 3+'s';
	 	}
	 }
	
   /* function formatDateTime(longDate){
			    	var dt;
			    	if(null!=longDate && undefined!=longDate && ""!=longDate){
			    		dt=new Date(longDate);
			    	}else{
			    		dt=new Date();
			    	}
			 	   var dd=dt.getDate();
			 	   var MM=dt.getMonth()+1;
			 	   var yyyy=dt.getFullYear();
			 	   var HH= dt.getHours();
			 	   var mm= dt.getMinutes();
			 	   var ss= dt.getSeconds();
			 	   
			 	   return yyyy+'-'+MM+'-'+dd+' '+HH+':'+mm+':'+ss;
    }*/
    
    function setHeaderValues(leftTop,rightTop,leftBottom,rightBottom){
    	$(".detailscont").empty();
    	$(".detailscont").append('<div class="col-md-12">'+
                '<label id="leftTopId" class="col-md-6">'+leftTop+'</label>'+
                '<label id="rightTopId" class="col-md-6 rightTopClass">'+rightTop+'</label>'+
            '</div>'+
            '<div class="col-md-12" style="margin-top:10px;">'+
                '<label id="leftBottomId" class="col-md-6">'+leftBottom+'</label>'+
                '<label id="rightBottomId" class="col-md-6">'+rightBottom+'</label>'+
            '</div>');
    }
    function setHeaderValuesWithMoreParam(topLeft,topRight,middleLeft,middleRight,bottomLeft,bottomRight){
    	$(".detailscont").empty();
    	$(".detailscont").append('<div class="col-md-12">'+
                '<label id="topLeftId" class="col-md-6">'+topLeft+'</label>'+
                '<label id="topRightId" class="col-md-6 rightTopClass">'+topRight+'</label>'+
            '</div>'+
            '<div class="col-md-12" style="margin-top:10px;">'+
                '<label id="middleLeftId" class="col-md-6">'+middleLeft+'</label>'+
                '<label id="middleRightId" class="col-md-6">'+middleRight+'</label>'+
            '</div>'+
            '<div class="col-md-12" style="margin-top:10px;">'+
            '<label id="bottomLeftId" class="col-md-6">'+bottomLeft+'</label>'+
            '<label id="bottomRightId" class="col-md-6">'+bottomRight+'</label>'+
            '</div>');
    }

    function setLeftPaneHeader(header,count){
    	$(".leftPaneHeader").empty();
    	if(count!=null){
    		$(".leftPaneHeader").append('<div class="col-sm-3 text-center brdrgt"><label>'+header+' ('+count+')</label></div>');
    	}else{
    		$(".leftPaneHeader").append('<div class="col-sm-3 text-center brdrgt"><label>'+header+'</label></div>');
    	}
    	
    }
    
    function getValue(val){
    	return val==null?'':val;
    }

    function getCheckBoxVal(check){
    	return check=="Y"?true:false;
    }
    
    function appendLiData(leftTop,rightTop,leftBottom,rightBottom,liId,active,eventClass){
    	var li=	'<li id="'+liId+'" class="'+eventClass+' '+active+'" >'+
    			   	'<a data-toggle="tab">'+
    			   		'<div class="col-md-12">'+
    				   		'<label class="col-md-6">'+leftTop+'</label>'+
    			            '<label class="col-md-6">'+rightTop+'</label>'+
    			        '</div>'+
    			        '<div class="col-md-12 leftPaneLowerDiv">'+
    			            '<label class="col-md-6">'+leftBottom+'</label>'+
    			            '<label class="col-md-6">'+rightBottom+'</label>'+
    			        '</div>'+
    			    '</a>'+
    		    '</li>';
    	return li;
    }
    
    function appendArrayToLiData(arr,liId,active,eventClass){
    	var li=	'<li id="'+liId+'" class="'+eventClass+' '+active+'" ><a data-toggle="tab"><div class="col-md-12">';
    	$.each(arr,function(idx,obj){
    		li+='<label class="col-xs-6">'+obj+'</label>';
    	});
    	li+='</div></a></li>';	    
    	return li;
    }
    
    function showLoader(){
    	$('#loading-wrapper').show();
    }
    
    function hideLoader(){
    	$('#loading-wrapper').hide();
    }
    
    function getDoubleDigit(value){
    	var nmr=Number(value);
    	var intPart=Math.floor(nmr/10);
    	if(intPart==0){
    		return value="0"+value;
    	}else{
    		return value;
    	}
    }
    
    function isEmpty(value){
    	if(typeof(value)=='object' || typeof(value)=='string'){
    		return $.isEmptyObject(value);
    	}else{
    		if(value=='' || value==null || typeof(value)==undefined){
    			return true;
    		}else{
    			return false;
    		}
    	}
    }
    
    function getErrorMsgFromList(errors){
    	var msg='';
    	$.each(errors, function(key, value) {
		     msg=msg+'\n'+value.errorMessage +','+ '<br/>';
		});
    	return msg;
    }

    function setPagination(totalPage, selectedPage, maxVisible, pId){
    	
    	var id='';
    	if(pId != '' && pId !=undefined){
    	id=pId;
    	}
    	else{
    		id='pagination-here';
    	}
    	$('.pagination-container').remove();
    	$('#'+id).empty();
    	$('#'+id).bootpag({
    	    total: totalPage,          
    	    page: selectedPage,            
    	    maxVisible: maxVisible,     
    	    leaps: true,
    	    href: "#result-page-{{number}}",
    	    pageNumbers: true
    	});
    }
    
    (function ( $ ) {
    	 
        $.fn.paginate = function( options ) {
        	var settings = $.extend({
                pageSize:7,
                maxVisiblePageNumbers:3,
                loadOnStartup : true,
                searchBtnId: 'searchBtn',
                defaultNextPage: true,
                defaultSearch:true,
                defaultManualSearch:true,
                getAllData : false,
                pId: 'pagination-here'
            }, options );
        	
        	/*    On page load  Start*/
        	if(settings.loadOnStartup){
        	var dataSorce = window[settings.dataSource](1, settings.pageSize, 'none', 'none');
			window[settings.responseTo](dataSorce.data);
			window['setPagination'](dataSorce.objectMap.LastPage, 1 , settings.maxVisiblePageNumbers);
			totalPages=dataSorce.objectMap.LastPage;
        	}
			/*    On page load  End*/
			
        	/*    on page number click Start */
        	if(settings.defaultNextPage){
        		this.on("page", function(event, num){
            		
            		selectedPage = num;
            		var searchby = $('#searchLiteralId').val();
            		var searchMode = $('input[name=filterBy]:checked').val();
            		if(searchby != '' && searchby!=undefined){
            			var dataSorce = window[settings.dataSource](num, settings.pageSize, searchMode, searchby);
            			if(settings.getAllData){
            				window[settings.responseTo](dataSorce);
            			}else{
            				window[settings.responseTo](dataSorce.data);
            			}
            		}else{
            			var dataSorce = window[settings.dataSource](num, settings.pageSize, 'none', 'none');
            			if(settings.getAllData){
            				window[settings.responseTo](dataSorce);
            			}else{
            				window[settings.responseTo](dataSorce.data);
            			}
            		}
            	});
        	}
        	/*    on page number click END */
        	
        	/*    on search box   Start*/
        	$('#'+settings.searchBoxID).keyup(function( e ){
        		var key = e.which;
        		
        		if (key == 13 || key == 8) {
					isServerSidePaginationEmpty = $('#'+settings.pId).is(':empty');
	        		if(!isServerSidePaginationEmpty){
	        			
	        			var searchby = $('#searchLiteralId').val();
	        			var searchMode = $('input[name=filterBy]:checked').val();
	        			if(searchMode != undefined){
	        				var dataSorce = window[settings.dataSource](1, settings.pageSize, searchMode, searchby);
	        				if(settings.getAllData){
	            				window[settings.responseTo](dataSorce);
	            			}else{
	            				window[settings.responseTo](dataSorce.data);
	            			}
	                		window['setPagination'](dataSorce.objectMap.LastPage, 1 , settings.maxVisiblePageNumbers);
	                		totalPages=dataSorce.objectMap.LastPage;
	        			}else{
	        				Alert.warn('Select Filter Mode');
	        			}
	        		}
				}
        	});
        	
        	$('#'+settings.searchBtnId).click(function( e ){
					isServerSidePaginationEmpty = $('#'+settings.pId).is(':empty');
	        		if(!isServerSidePaginationEmpty){
	        			var searchby = $('#searchLiteralId').val();
	        			var searchMode = $('input[name=filterBy]:checked').val();
	        			if(searchMode != undefined){
	        				var dataSorce = window[settings.dataSource](1, settings.pageSize, searchMode, searchby);
	        				if(settings.getAllData){
	            				window[settings.responseTo](dataSorce);
	            			}else{
	            				window[settings.responseTo](dataSorce.data);
	            			}
	                		window['setPagination'](dataSorce.objectMap.LastPage, 1 , settings.maxVisiblePageNumbers);
	                		totalPages=dataSorce.objectMap.LastPage;
	        			}else{
	        				Alert.warn('Select Filter Mode');
	        			}
	        		}
        	});
        	/*    on search box End     */
        	
        	/*    jump on page number  Start*/
        	$(".jumptopage").click(function(){
    			
    			var jumpIndex = $("#jumpIndex").val();
    				if(jumpIndex != ''){
    					isServerSidePaginationEmpty = $('#'+settings.pId).is(':empty');
    					if(!isServerSidePaginationEmpty){	
    						var searchby = $('#'+settings.searchBoxID).val();
    						var searchMode = $('input[name=filterBy]:checked').val();
    						if(searchby != ''){
    							
    							var dataSorce = window[settings.dataSource](jumpIndex, settings.pageSize, searchMode, searchby);
    							if(settings.getAllData){
    	            				window[settings.responseTo](dataSorce);
    	            			}else{
    	            				window[settings.responseTo](dataSorce.data);
    	            			}
                    			window['setPagination'](dataSorce.objectMap.LastPage, jumpIndex , settings.maxVisiblePageNumbers);
                    			totalPages=dataSorce.objectMap.LastPage;
    						}else{
    							
    							var dataSorce = window[settings.dataSource](jumpIndex, settings.pageSize, 'none', 'none');
    							if(settings.getAllData){
    	            				window[settings.responseTo](dataSorce);
    	            			}else{
    	            				window[settings.responseTo](dataSorce.data);
    	            			}
                    			window['setPagination'](dataSorce.objectMap.LastPage, jumpIndex , settings.maxVisiblePageNumbers);
                    			totalPages=dataSorce.objectMap.LastPage;
    						}
    				}	
    			}else{
    				Alert.warn('Enter Page number');
    			}
    		});
        	/*    jump on page number  Start*/

        };
     
    }( jQuery ));
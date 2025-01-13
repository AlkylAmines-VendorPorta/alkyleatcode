function contructCumulativeTable(materialList,bidders){
	var html='<thead><tr style="background: #92b6da;"><th></th>';
   $.each(bidders, function(key, value) {
	   html=html+'<th>'+value.partner.name+'</th>';
	});
   html=html+'</tr></thead><tbody>';
	$.each(materialList, function(key, value) {
		 html=html+'<tr><td style="font-weight: bold;">'+value.material.name+'</td>';
		 $.each(bidders, function(key, value1) {
			   html=html+'<td style="text-align:right; padding-right: 15px;" id="'+value.tahdrMaterialId+'-'+value1.bidderId+'">0</td>';
			});
		 html=html+'</tr>'; 
	});
	html=html+'<tr style="background: #eae654; font-weight: bold;"><td>Total</td>';
	$.each(bidders, function(key, value1) {
			   html=html+'<td  style="text-align:right; padding-right: 15px;" id="total-'+value1.bidderId+'">0</td>';
			});
	html=html+'</tr>';
	html=html+'<tr style="background: #ce9c51; font-weight: bold;"><td>Rating</td>';
	$.each(bidders, function(key, value1) {
		   html=html+'<td style="text-align:right; padding-right: 15px;" id="star-'+value1.bidderId+'">0</td>';
		});
	/*html=html+'</tr><tr><td>Over All Rank</td>';
	$.each(bidders, function(key, value1) {
		   html=html+'<td style="text-align:right; padding-right: 15px;" id="rank-'+value1.bidderId+'">-</td>';
		});*/
	html=html+'</tr></tbody>'; 
	return html;
}

function setMinumumValue(){
	$.each(materialList, function(key, value) {
		var preVal=0;
		var selectedMatId='';
		var selectedBidderId='';
		var matId=value.tahdrMaterialId;
		 $.each(bidders, function(key, value1) {
			 var bidderId=value1.bidderId;
			 var val= Number($('#'+matId+'-'+bidderId+'').html().replace(/\,/g,""));
			 if(val!=0){
				 if(preVal!=0){
					 if(preVal>val){
						 selectedMatId=matId;
						 selectedBidderId=bidderId;
					 }else{
						 preVal=val;
					 }
				 }else{
					 selectedMatId=matId;
					 selectedBidderId=bidderId;
					 preVal=val; 
				 } 
			 }
		});
		 $('#'+selectedMatId+'-'+selectedBidderId+'').addClass('approvedStatus') ;
	});
}
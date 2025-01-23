var tahdrMaterialArray= new Array();

$(document).ready(function() {
			
			$(".winnerItems").on('click',function(event) {
						$('.pagination').children().remove();
						event.preventDefault();
						loadAwardTahdrMaterialToLeftPane(fetchList("getTAHDRMaterialList", $("#tahdrForm #tahdrDetail").val()));
						$('#leftPane').paginathing({perPage: 6});
					});

		});

function loadAwardTahdrMaterialToLeftPane(tahdrMaterialList){
	$("#leftPane").html("");
	var leftPanelHtml='';
	var i=0;
	var active="";
	var firstRow=null;
	tahdrMaterialArray=[];
	$.each(Object.values(tahdrMaterialList),function(key,tahdrMaterial){
		
		tahdrMaterialArray["tahdrMaterial"+tahdrMaterial.material.materialId]=tahdrMaterial;
		if(i==0){
			firstRow = tahdrMaterial;
			active="active";
		}
		leftPanelHtml= leftPanelHtml +appendTahdrMaterialLi(tahdrMaterial,active);
		active="";
		i++;
	});
	$("#leftPane").append(leftPanelHtml);
	loadAwardTahdrMaterialToRightPane(firstRow);
	setLeftPaneHeader("Item List", Object.values(tahdrMaterialArray).length);
	$("#leftPane").on('click','.tahdrMaterial',function(){
		var id=$(this).attr('id');
		loadAwardTahdrMaterialToRightPane(tahdrMaterialArray["tahdrMaterial"+id]);	
	});
	setLeftPaneHeader("Material List", $("#leftPane li").length);
	$('#leftPane').paginathing({perPage: 6});
}

function loadAwardTahdrMaterialToRightPane(tahdrMaterial){
	if(!$.isEmptyObject(tahdrMaterial)){
		$(".tahdrMaterial").val(getValue(tahdrMaterial.tahdrMaterialId));
		$("#itemForm #quantity").val(getValue(tahdrMaterial.quantity));
		$("#itemForm #matType").val(getValue(tahdrMaterial.materialTypeCode));
		if(tahdrMaterial.material!=null){
			$("#itemForm #materialName").val(getValue(tahdrMaterial.material.name));
			if(tahdrMaterial.material.uom!=null){
				$("#itemForm #uom").val(getValue(tahdrMaterial.material.uom.name));	
			}
			$("#itemForm #matDescription").val(getValue(tahdrMaterial.material.description));
		}
		if(tahdrMaterial.materialVersion){
			$("#itemForm #spec").val(getValue(tahdrMaterial.materialVersion.name));
		}
	}
	/*setHeaderValues("Tender No.: ", "Tender Title : ", "Department : ", "Status : ");*/
	
}

function appendTahdrMaterialLi(tahdrMaterial,active){
	return appendLiData(tahdrMaterial.material.code, tahdrMaterial.materialDescription, tahdrMaterial.material.uom.code, tahdrMaterial.quantity, tahdrMaterial.material.materialId, active, 'tahdrMaterial');
}


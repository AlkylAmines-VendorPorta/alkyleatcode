package com.novelerp.alkyl.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.novelerp.alkyl.dao.AdvanceShipmentNoticeLineDao;
import com.novelerp.alkyl.dto.ASNReadDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeDto;
import com.novelerp.alkyl.dto.AdvanceShipmentNoticeLineDto;
import com.novelerp.alkyl.dto.PurchaseOrderDto;
import com.novelerp.alkyl.dto.PurchaseOrderLineDto;
import com.novelerp.alkyl.dto.SSNReadDto;
import com.novelerp.alkyl.entity.AdvanceShipmentNoticeLine;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.core.dao.impl.AbstractJpaDAO;

@Repository
public class AdvanceShipmentNoticeLineDaoImpl extends AbstractJpaDAO<AdvanceShipmentNoticeLine, AdvanceShipmentNoticeLineDto> implements AdvanceShipmentNoticeLineDao{

	
	@PostConstruct
	public void postconstruct(){
		
		setClazz(AdvanceShipmentNoticeLine.class, AdvanceShipmentNoticeLineDto.class);
	}
	
	public String getASNLinesByASNId(){
		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvanceShipmentNoticeLine A ")
				.append(" INNER JOIN FETCH A.advanceshipmentnotice B ")
				.append(" LEFT JOIN FETCH B.po po  ")
				.append(" LEFT JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
				.append(" LEFT JOIN FETCH A.parentASNLine E ")
				.append(" LEFT JOIN FETCH A.asnLineCostCenter F ")
				.append(" INNER JOIN FETCH B.createdBy G ")
				.append(" INNER JOIN FETCH G.userDetails H ")				
				.append(" WHERE  A.isActive = 'Y' and B.advanceShipmentNoticeId =:asnId and E.advanceShipmentNoticeLineId is null and A.deliveryQuantity>0  ")
				.append(" Order By C.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getASNLineByASNLineId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNoticeLine A ")
				.append(" INNER JOIN FETCH A.advanceshipmentnotice B ")
				.append(" LEFT JOIN FETCH A.parentASNLine E ")
				.append(" INNER JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
				.append(" WHERE A.isActive = 'Y' and A.advanceShipmentNoticeLineId =:asnLineId and E.advanceShipmentNoticeLineId is null ")
				.append(" Order By C.lineItemNumber ");;
		return jpql.toString();
	}
	
	public String getServiceLineByPOLineId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNoticeLine A ")
				.append(" INNER JOIN FETCH A.advanceshipmentnotice B ")
				.append(" INNER JOIN FETCH A.parentASNLine E ")
				.append(" INNER JOIN FETCH E.poLine F ")
				.append(" INNER JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
				.append(" WHERE A.isActive = 'Y' and F.purchaseOrderLineId =:poLineId ")
				.append(" Order By C.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getServiceLineByASNLineId(){
		StringBuilder jpql = new StringBuilder(" SELECT A FROM AdvanceShipmentNoticeLine A ")
				.append(" INNER JOIN FETCH A.advanceshipmentnotice B ")
				.append(" INNER JOIN FETCH A.parentASNLine E ")
				.append(" INNER JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
				.append(" WHERE A.isActive = 'Y' and E.advanceShipmentNoticeLineId =:asnLineId ")
				.append(" Order By C.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getServiceLineByASNId(){
		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvanceShipmentNoticeLine A ")
				.append(" INNER JOIN FETCH A.advanceshipmentnotice B ")
				.append(" INNER JOIN FETCH A.parentASNLine E ")
				.append(" INNER JOIN FETCH E.poLine G ")
				.append(" INNER JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
				.append(" INNER JOIN FETCH B.po F ")
				.append(" LEFT JOIN FETCH A.asnLineCostCenter G ")
				.append(" WHERE A.isActive = 'Y' and B.advanceShipmentNoticeId =:asnId and A.deliveryQuantity > 0 ")
				
				.append(" Order By C.lineItemNumber ");
		return jpql.toString();
	}
	
	public String getASNLinesByASNIdMain(){
		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvanceShipmentNoticeLine A ")
				.append(" INNER JOIN FETCH A.advanceshipmentnotice B ")
				.append(" LEFT JOIN FETCH B.po po  ")
				.append(" LEFT JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
//				.append(" LEFT JOIN FETCH A.parentASNLine E ")
//				.append(" LEFT JOIN FETCH A.asnLineCostCenter F ")
				.append(" INNER JOIN FETCH B.createdBy G ")
				.append(" INNER JOIN FETCH G.userDetails H ")				
				.append(" WHERE  A.isActive = 'Y' and B.advanceShipmentNoticeId =:asnId")
//				.append(" WHERE  A.isActive = 'Y' and B.asnHeaderId =:asnHeaderId and A.deliveryQuantity>0  ")
				.append(" Order By C.lineItemNumber ");
		return jpql.toString();
	}
	
//	public String printASNByASNNOforSecurity() {
//		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvanceShipmentNoticeLine A ")
//				
//				.append(" INNER JOIN FETCH A.advanceshipmentnotice B ")
//				.append(" INNER JOIN FETCH B.po po  ")
//				.append(" INNER JOIN FETCH A.poLine C ")
//				.append(" LEFT JOIN FETCH A.asnLineCostCenter F ")
//			//	.append(" INNER JOIN FETCH A.asnLineList C ")
//			//	.append("INNER JOIN FETCH B.PurchaseOrderLine C")
////				.append(" INNER JOIN FETCH A.partner C ")
////				.append(" INNER JOIN FETCH A.createdBy E")
//				.append(" WHERE A.isActive = 'Y' and B.advanceShipmentNoticeId = :asnId")
//		    .append(" Order By C.lineItemNumber ");
//		return jpql.toString();
//	}
	

//	 public List<AdvanceShipmentNoticeLineDto> printASNByASNNOforSecurityNew(Long asnId){
//				StringBuilder query = new StringBuilder()
//						
//						.append(" select b.advance_shipment_notice_no as asnNo,b.gatein_date as gateinDate from t_advance_shipment_notice_line as a left join t_advance_shipment_notice as b on b.t_advance_shipment_notice_id = a.t_advance_shipment_notice_id")
//				        .append(" WHERE (a.isactive = 'Y') and (b.t_advance_shipment_notice_id='"+asnId+"')");
//				         Query query1 = getEntityManager().createNativeQuery(query.toString());
//				         List<AdvanceShipmentNoticeLineDto> resultSet = query1.getResultList();
//	                    return resultSet;
//				
//				}
	
	@Override
	public List<AdvanceShipmentNoticeLineDto> printASNByASNNOforSecurityNew(Long asnId){
			StringBuilder jpql = new StringBuilder( "SELECT b.advance_shipment_notice_no,b.gatein_date,b.invoice_no,b.invoice_date,b.vehical_no,b.lr_number,b.net_weight,b.transporter_no,a.delivery_quanity,p.purchase_odrer_number,pline.plant,pline.uom,pline.name,pline.value,pline.line_item_number from t_advance_shipment_notice_line a")
					.append(" inner join t_advance_shipment_notice b on (a.t_advance_shipment_notice_id=b.t_advance_shipment_notice_id) ")
					.append(" left join t_purchase_order p on (b.t_purchase_order_id=p.t_purchase_order_id)")
					.append(" left join t_purchase_order_line pline on (a.t_purchase_order_line_id=pline.t_purchase_order_line_id)")
//					.append(" inner join t_advance_shipment_notice b on (a.t_advance_shipment_notice_id=b.t_advance_shipment_notice_id) ")
//					.append(" inner join t_purchase_order p on (b.t_purchase_order_id=p.t_purchase_order_id)")
//					.append(" inner join t_purchase_order_line pline on (a.t_purchase_order_line_id=pline.t_purchase_order_line_id)")
                    .append(" WHERE a.isactive= 'Y' and a.delivery_quanity>0 and b.t_advance_shipment_notice_id="+asnId);

			        Query query1 = getEntityManager().createNativeQuery(jpql.toString());
					@SuppressWarnings("unchecked")
					List<Object[]> data= query1.getResultList();
					
					List<AdvanceShipmentNoticeDto> asndto=new ArrayList<AdvanceShipmentNoticeDto>();
					List<AdvanceShipmentNoticeLineDto> asnline=new ArrayList<AdvanceShipmentNoticeLineDto>();
				    for(Object[] array:data){
				    	AdvanceShipmentNoticeLineDto line= new AdvanceShipmentNoticeLineDto();
				    	AdvanceShipmentNoticeDto asn=new AdvanceShipmentNoticeDto();
				    	PurchaseOrderDto po=new PurchaseOrderDto();
				    	PurchaseOrderLineDto poline=new PurchaseOrderLineDto();
				    	
				    	
				    	
				    	asn.setAdvanceShipmentNoticeNo((Integer) array[0]); 
				    	asn.setGateInDate((Date) array[1]);
				    	asn.setInvoiceNo((String) array[2]);
				    	asn.setInvoiceDate((Date) array[3]);
				    	asn.setVehicalNo((String) array[4]);
				    	asn.setLrNumber((String) array[5]);
				    	asn.setNetWeight((String) array[6]);
				    	asn.setTransporterNo((String) array[7]);
				    	line.setAdvanceshipmentnotice(asn);
				    	BigDecimal bd1 = BigDecimal.valueOf(((BigDecimal) array[8]).doubleValue());
				    	Double d2 = bd1.doubleValue();
				    	line.setDeliveryQuantity(d2);
//				    	List<AdvanceShipmentNoticeLineDto> list = new ArrayList<>();
//				    	list.add(line);
//				    	asn.setAsnLineList(list);
                        po.setPurchaseOrderNumber((String) array[9]);
                        asn.setPo(po);
                        poline.setPlant((String) array[10]);
                        poline.setUom((String) array[11]);
                        poline.setName((String) array[12]);
                        poline.setCode((String) array[13]);
                        poline.setLineItemNumber((String) array[14]);
                        line.setPoLine(poline);
				    	
				    //	asndto.add(asn); // you need to set the value of each pojo field here.
				    	asnline.add(line);
				
				    }
				 //   return asndto;
				//	return data;
				    return asnline;
			
			}
	
	
	@Override
	public List<AdvanceShipmentNoticeLineDto> printASNByASNNOforSecurityNewWithoutPO(Long asnId){
			StringBuilder jpql = new StringBuilder( "SELECT b.advance_shipment_notice_no,b.gatein_date,b.invoice_no,b.invoice_date,b.vehical_no,a.delivery_quanity,b.plant,a.uom,a.name,a.value,a.line_item_no from t_advance_shipment_notice_line a")
					.append(" inner join t_advance_shipment_notice b on (a.t_advance_shipment_notice_id=b.t_advance_shipment_notice_id) ")
//					.append(" inner join t_purchase_order p on (b.t_purchase_order_id=p.t_purchase_order_id)")
//					.append(" inner join t_purchase_order_line pline on (a.t_purchase_order_line_id=pline.t_purchase_order_line_id)")
                    .append(" WHERE a.isactive= 'Y' and a.delivery_quanity>0 and b.t_advance_shipment_notice_id="+asnId);

			        Query query1 = getEntityManager().createNativeQuery(jpql.toString());
					@SuppressWarnings("unchecked")
					List<Object[]> data= query1.getResultList();
					
					List<AdvanceShipmentNoticeDto> asndto=new ArrayList<AdvanceShipmentNoticeDto>();
					List<AdvanceShipmentNoticeLineDto> asnline=new ArrayList<AdvanceShipmentNoticeLineDto>();
				    for(Object[] array:data){
				    	AdvanceShipmentNoticeLineDto line= new AdvanceShipmentNoticeLineDto();
				    	AdvanceShipmentNoticeDto asn=new AdvanceShipmentNoticeDto();
//				    	PurchaseOrderDto po=new PurchaseOrderDto();
//				    	PurchaseOrderLineDto poline=new PurchaseOrderLineDto();
				    	
				    	
				    	
				    	asn.setAdvanceShipmentNoticeNo((Integer) array[0]); 
				    	asn.setGateInDate((Date) array[1]);
				    	asn.setInvoiceNo((String) array[2]);
				    	asn.setInvoiceDate((Date) array[3]);
				    	asn.setVehicalNo((String) array[4]);
				    	line.setAdvanceshipmentnotice(asn);
				    	BigDecimal bd1 = BigDecimal.valueOf(((BigDecimal) array[5]).doubleValue());
				    	Double d2 = bd1.doubleValue();
				    	line.setDeliveryQuantity(d2);
				    	line.getAdvanceshipmentnotice().setPlant((String) array[6]);
				    	line.setUom((String) array[7]);
				    	line.setName((String) array[8]);
				    	line.setLineItemNo((String) array[10]);
//				    	List<AdvanceShipmentNoticeLineDto> list = new ArrayList<>();
//				    	list.add(line);
//				    	asn.setAsnLineList(list);
//                        po.setPurchaseOrderNumber((String) array[6]);
//                        asn.setPo(po);
//                        poline.setPlant((String) array[7]);
//                        poline.setUom((String) array[8]);
//                        poline.setName((String) array[9]);
//                        poline.setCode((String) array[10]);
//                        poline.setLineItemNumber((String) array[11]);
//                        line.setPoLine(poline);
				    	
				    //	asndto.add(asn); // you need to set the value of each pojo field here.
				    	asnline.add(line);
				
				    }
				 //   return asndto;
				//	return data;
				    return asnline;
			
			}
	

	public String getASNReportLinelist(ASNReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvanceShipmentNoticeLine A ")
				.append(" LEFT JOIN FETCH A.advanceshipmentnotice B ")
				.append(" INNER JOIN FETCH B.po po  ")
				.append(" INNER JOIN FETCH A.poLine C ")
				.append(" INNER JOIN FETCH A.partner D ")
//				.append(" LEFT JOIN FETCH A.parentASNLine E ")
			.append(" LEFT JOIN FETCH A.asnLineCostCenter F ")
		.append(" LEFT JOIN FETCH B.createdBy G ")
		.append(" LEFT JOIN FETCH G.userDetails H ")
         .append(" LEFT JOIN FETCH B.partner p")
		.append(" LEFT JOIN FETCH B.grnPostedby gp")
        .append(" LEFT JOIN FETCH gp.userDetails I ")
//        .append(" INNER JOIN FETCH A.gateinBy gb")
//        .append(" INNER JOIN FETCH D.userDetails E ")
        .append(" LEFT JOIN FETCH B.gateinPostedby gpb")
        .append(" LEFT JOIN FETCH gpb.userDetails J ");

				

		String where =  getWhereClause(dto);
		jpql.append(where);
	//	System.out.println(jpql.toString());
	
		return jpql.toString();
	}
	private String getWhereClause(ASNReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y'  and A.deliveryQuantity > 0 and B.advanceShipmentNoticeNo IS NOT NULL");
   		
   		
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
   			where.append(" AND po.purchaseOrderNumber BETWEEN :fromNo AND :toNo ");
   		}
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			where.append(" AND po.purchaseOrderNumber =:fromNo ");
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			where.append(" AND po.purchaseOrderNumber =:toNo ");
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()!=null){
   			where.append(" AND B.advanceShipmentNoticeNo BETWEEN :asnNoFrom AND :asnNoTo ");
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()==null){
   			where.append(" AND B.advanceShipmentNoticeNo =:asnNoFrom ");
   		}
   		if(dto.getAsnNoFrom()==null && dto.getAsnNoTo()!=null){
   			where.append(" AND B.advanceShipmentNoticeNo =:asnNoTo ");
   			
   		}
   		
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()!=null){
   			where.append(" AND B.created BETWEEN :fromDate AND :toDate ");
   		}
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()==null){
   			where.append(" AND B.created =:fromDate ");
   		}
   		if(dto.getAsnDateFrom()==null && dto.getAsnDateTo()!=null){
   			where.append(" AND B.created =:toDate ");
   		}
   		
		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()==null){
   			where.append(" AND p.vendorSapCode =:vendorCode ");
   		}
		
		if(dto.getVendorCodeTo()!=null && dto.getVendorCode()==null){
   			where.append(" AND p.vendorSapCode =:vendorCodeTo ");
   		}
		
		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()!=null){
   			where.append(" AND p.vendorSapCode BETWEEN :vendorCode AND :vendorCodeTo ");
   		}
		
		
		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()==null){
   			where.append(" AND C.code =:itemCodeFrom ");
   		}
		
		if(dto.getItemCodeTo()!=null && dto.getItemCodeFrom()==null){
   			where.append(" AND C.code =:itemCodeTo ");
   		}
		
		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()!=null){
   			where.append(" AND C.code BETWEEN :itemCodeFrom AND :itemCodeTo ");
   		}
		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			
   			where.append(" AND B.status = (:status) ");  			
   		}
   		
//   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
//   			where.append(" AND B.reqby.userName =:requestedBy");
//   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			where.append(" AND C.plant =:plant");
   		}
   		
   		
   		if(dto.getGateInDateFrom()!=null && dto.getGateInDateTo()!=null){
   			where.append(" AND B.gateInDate BETWEEN :gateInDateFrom AND :gateInDateTo ");
   		}
   		if(dto.getGateInDateFrom()!=null && dto.getGateInDateTo()==null){
   			where.append(" AND B.gateInDate =:gateInDateFrom ");
   		}
   		if(dto.getGateInDateFrom()==null && dto.getGateInDateTo()!=null){
   			where.append(" AND B.gateInDate =:gateInDateTo ");
   		}
   		
   		
   		return where.toString();
	}
	
	
	public String getASNReportLinelistWithoutPO(ASNReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvanceShipmentNoticeLine A ")
		.append(" LEFT JOIN FETCH A.advanceshipmentnotice B ")
		.append(" INNER JOIN FETCH A.partner D ")
	    .append(" LEFT JOIN FETCH A.asnLineCostCenter F ")
		.append(" LEFT JOIN FETCH B.createdBy G ")
		.append(" LEFT JOIN FETCH G.userDetails H ")
        .append(" LEFT JOIN FETCH B.partner p");
		String where =  getWhereClauseWithoutPO(dto);
		jpql.append(where);
	//	System.out.println(jpql.toString());
	
		return jpql.toString();
	}
	private String getWhereClauseWithoutPO(ASNReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y'  and A.deliveryQuantity > 0 and B.advanceShipmentNoticeNo IS NOT NULL and B.po.purchaseOrderId is null");
   		
   		
//   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
//   			where.append(" AND po.purchaseOrderNumber BETWEEN :fromNo AND :toNo ");
//   		}
//   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
//   			where.append(" AND po.purchaseOrderNumber =:fromNo ");
//   		}
//   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
//   			where.append(" AND po.purchaseOrderNumber =:toNo ");
//   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()!=null){
   			where.append(" AND B.advanceShipmentNoticeNo BETWEEN :asnNoFrom AND :asnNoTo ");
   		}
   		if(dto.getAsnNoFrom()!=null && dto.getAsnNoTo()==null){
   			where.append(" AND B.advanceShipmentNoticeNo =:asnNoFrom ");
   		}
   		if(dto.getAsnNoFrom()==null && dto.getAsnNoTo()!=null){
   			where.append(" AND B.advanceShipmentNoticeNo =:asnNoTo ");
   			
   		}
   		
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()!=null){
   			where.append(" AND B.created BETWEEN :fromDate AND :toDate ");
   		}
   		if(dto.getAsnDateFrom()!=null && dto.getAsnDateTo()==null){
   			where.append(" AND B.created =:fromDate ");
   		}
   		if(dto.getAsnDateFrom()==null && dto.getAsnDateTo()!=null){
   			where.append(" AND B.created =:toDate ");
   		}
   		
//		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()==null){
//   			where.append(" AND p.vendorSapCode =:vendorCode ");
//   		}
//		
//		if(dto.getVendorCodeTo()!=null && dto.getVendorCode()==null){
//   			where.append(" AND p.vendorSapCode =:vendorCodeTo ");
//   		}
//		
//		if(dto.getVendorCode()!=null && dto.getVendorCodeTo()!=null){
//   			where.append(" AND p.vendorSapCode BETWEEN :vendorCode AND :vendorCodeTo ");
//   		}
		
		
//		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()==null){
//   			where.append(" AND C.code =:itemCodeFrom ");
//   		}
//		
//		if(dto.getItemCodeTo()!=null && dto.getItemCodeFrom()==null){
//   			where.append(" AND C.code =:itemCodeTo ");
//   		}
//		
//		if(dto.getItemCodeFrom()!=null && dto.getItemCodeTo()!=null){
//   			where.append(" AND C.code BETWEEN :itemCodeFrom AND :itemCodeTo ");
//   		}
		
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			
   			where.append(" AND B.status = (:status) ");  			
   		}
   		
//   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
//   			where.append(" AND B.reqby.userName =:requestedBy");
//   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			where.append(" AND B.plant =:plant");
   		}
   		
   		
   		return where.toString();
	}
	
	public String getSSNReportLinelist(SSNReadDto dto) {
		StringBuilder jpql = new StringBuilder(" SELECT distinct(A) FROM AdvanceShipmentNoticeLine A ")
				.append(" LEFT JOIN FETCH A.advanceshipmentnotice B ")
			//	.append(" INNER JOIN FETCH A.parentASNLine E ")
				.append(" INNER JOIN FETCH B.po po  ")
				.append(" INNER JOIN FETCH A.poLine C ")
				
				.append(" INNER JOIN FETCH C.parentPOLine I")
				.append(" INNER JOIN FETCH A.partner D ")				
			    .append(" LEFT JOIN FETCH A.asnLineCostCenter F ")
		        .append(" LEFT JOIN FETCH B.createdBy G ")
		        .append(" LEFT JOIN FETCH G.userDetails H ")
                .append(" LEFT JOIN FETCH B.partner p")
		        .append(" LEFT JOIN FETCH B.ssnApprovedBy J ")
		         .append(" LEFT JOIN FETCH J.userDetails K ");


				

		String where =  getssnWhereClause(dto);
		jpql.append(where);
	//	System.out.println(jpql.toString());
	
		return jpql.toString();
	}
private String getssnWhereClause(SSNReadDto dto){
		
		
   		StringBuilder where = new StringBuilder();
   		where.append(" WHERE A.isActive = 'Y'  and A.deliveryQuantity > 0 AND B.serviceSheetNo IS NOT NULL");
   		
   		
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()!=null){
   			where.append(" AND po.purchaseOrderNumber BETWEEN :fromNo AND :toNo");
   		}
   		
   		if(dto.getPoNoFrom()!=null && dto.getPoNoTo()==null){
   			where.append(" AND po.purchaseOrderNumber =:fromNo ");
   		}
   		if(dto.getPoNoFrom()==null && dto.getPoNoTo()!=null){
   			where.append(" AND po.purchaseOrderNumber =:toNo");
   		}
   		
   		if(dto.getSsnNoFrom()!=null && dto.getSsnNoTo()!=null){
   			where.append(" AND B.serviceSheetNo BETWEEN :ssnNoFrom AND :ssnNoTo ");
   		}
   		if(dto.getSsnNoFrom()!=null && dto.getSsnNoTo()==null){
   			where.append(" AND B.serviceSheetNo =:ssnNoFrom ");
   		}
   		if(dto.getSsnNoFrom()==null && dto.getSsnNoTo()!=null){
   			where.append(" AND B.serviceSheetNo =:ssnNoTo ");
   		}
   		if(dto.getSsnDateFrom()!=null && dto.getSsnDateTo()!=null){
   			where.append(" AND B.created BETWEEN :fromDate AND :toDate");
   		}
   		if(dto.getSsnDateFrom()!=null && dto.getSsnDateTo()==null){
   			where.append(" AND B.created =:fromDate");
   		}
   		if(dto.getSsnDateFrom()==null && dto.getSsnDateTo()!=null){
   			where.append(" AND B.created =:toDate");
   		}
   		
   		if(dto.getVendorCode()!=null && !dto.getVendorCode().equals("")){
   			where.append(" AND p.vendorSapCode =:vendorCode");
   		}
   		if(dto.getStatus()!=null && !dto.getStatus().equals("")){
   			//UPPER(A.status) like CONCAT('%',UPPER(:status),'%')
   			where.append(" AND B.status = (:status)");
   		}
   		
   		if(dto.getRequestedBy()!=null && !dto.getRequestedBy().equals("")){
   			where.append(" AND po.reqby.userName =:requestedBy");
   		}
   		
   		if(dto.getPlant()!=null && !dto.getPlant().equals("")){
   			where.append(" AND I.plant =:plant");
   		}
   		
   		return where.toString();

}
	
}

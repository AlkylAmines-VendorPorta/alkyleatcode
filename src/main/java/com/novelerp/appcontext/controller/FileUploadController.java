package com.novelerp.appcontext.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.novelerp.alkyl.dto.QuotationDto;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.dto.CustomResponseDto;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.core.dto.Errors;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.util.ValidationUtil;
import com.novelerp.eat.dto.BidderDto;
import com.novelerp.eat.dto.ItemBidDto;
import com.novelerp.eat.dto.PriceBidDto;
import com.novelerp.eat.service.ItemBidService;
import com.novelerp.eat.service.PriceBidService;


@Controller
@RequestMapping("/rest")
public class FileUploadController {


	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private PriceBidService priceBidService;
	
	@Autowired
	private ItemBidService itemBidService;
	
	@Autowired
	private ValidationUtil validationUtil;
	
	/*@RequestMapping(value="/addAttachment", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AttachmentDto uploadProtected (@RequestParam("file") MultipartFile multipartFile){
			
		return attachmentService.addAttachment(multipartFile);
	}*/
	
	
	@RequestMapping(value="/addAttachment", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AttachmentDto upload(@RequestParam("file") MultipartFile multipartFile){
		ResponseDto response=attachmentService.isValid(multipartFile);
		AttachmentDto attachment = new AttachmentDto();	
		if(response.isHasError()){
			attachment.setResponse(response);
			return attachment;
		}else{
			
			/*if("Y".equals(flag)){
				attachment=attachmentService.addEncryptedAttachment(multipartFile, flag);
				if(!attachment.getResponse().isHasError()){
					return attachmentService.save(attachment);
				}
				return attachment;
			}else{*/
				attachment=attachmentService.addAttachment(multipartFile);
				if(!attachment.getResponse().isHasError()){
					return attachmentService.save(attachment);
				}
				return attachment;
			/*}*/
		}
	}
	

	
	@PostMapping(value = "/uploadExcel/{bidderId}")
	public @ResponseBody CustomResponseDto uploadExcel(@RequestParam("file") MultipartFile file,
			@PathVariable("bidderId") Long bidderId) {
		
		 Map<String, Object> params= new HashMap<>();
		 params.put("bidderId",bidderId);
		 
		 
		 List<ItemBidDto> itemBiddto = itemBidService.findDtos("getItemBidByeqnId", params);
		 
		 Long EnquiryNo=null;
		 
		 EnquiryNo=itemBiddto.get(0).getBidder().getEnquiry().getEnquiryId();
		 CustomResponseDto add=new CustomResponseDto();

		try {
			//String home = System.getProperty("user.home");
		//	String filename = "howtodoinjava_demo.xlsx";
		//	FileInputStream uploadfile = new FileInputStream(new File(home + "/Downloads/" + file.getOriginalFilename()));
			
			   
		//  File path = new File("C:\\Users\\Administrator\\Desktop\\TestingUpload\\" + file.getOriginalFilename());
			File path = new File("D:\\VendorQuotationItemLevelFileUpload\\" + file.getOriginalFilename());
		    	if (path.exists() && path.isFile()) {
		    		path.delete();
		    	  }
		    	
	            path.createNewFile();
	            FileOutputStream output = new FileOutputStream(path);
	            output.write(file.getBytes());
	            output.close();
			// Create Workbook instance holding reference to .xlsx file
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(path);
			
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			
		

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			QuotationDto quotationDto = new QuotationDto();
			List<PriceBidDto> newQuotationList = new ArrayList<>();
			
			Double TotalOtherCharges=0.0;
			Double TotalTaxAmt=0.0;
			Double TotalGrossAmt=0.0;
			Double TotalBasicAmt=0.0;
			
			 ArrayList<Date> myList = new ArrayList<Date>();
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				
				int rowNum = row.getRowNum();
				// For each row, iterate through all the columns
			
				if (rowNum != 0) {
					
					if (row.getCell(1).getNumericCellValue() !=EnquiryNo ){
						return new CustomResponseDto(false, "Please Upload proper enquiry File");
					}
					
					if (row.getCell(7) == null || row.getCell(7).getCellType() == Cell.CELL_TYPE_BLANK ||  row.getCell(9) == null || row.getCell(9).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(11) == null || row.getCell(11).getCellType() == Cell.CELL_TYPE_BLANK ||  row.getCell(13) == null || row.getCell(13).getCellType() == Cell.CELL_TYPE_BLANK ||row.getCell(15) == null || row.getCell(15).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(16) == null || row.getCell(16).getCellType() == Cell.CELL_TYPE_BLANK) {
						return new CustomResponseDto(false, "Please Enter All Values");
					}
					
					if (row.getCell(7).getCellType() != Cell.CELL_TYPE_NUMERIC ||  row.getCell(9).getCellType() != Cell.CELL_TYPE_NUMERIC || row.getCell(11).getCellType() != Cell.CELL_TYPE_NUMERIC || row.getCell(13).getCellType() != Cell.CELL_TYPE_NUMERIC || row.getCell(15).getCellType() != Cell.CELL_TYPE_NUMERIC ||  row.getCell(16).getCellType() != Cell.CELL_TYPE_NUMERIC) {
					return new CustomResponseDto(false, "Please Enter All Values in Numbers");
				}
					
					Cell DateCell1 = row.getCell((short)17);
//					short y = workbook.createDataFormat().getFormat("dd/mm/yyyy;@");
//					CellStyle dateCellFormat1 = workbook.createCellStyle();
//					dateCellFormat1.setDataFormat(y);
//
//					DateCell1.setCellStyle(dateCellFormat1);
					
					 DateCell1.setCellType(CellType.NUMERIC);
					 
						if (DateCell1.getCellType() != Cell.CELL_TYPE_NUMERIC || row.getCell(17) == null || row.getCell(17).getCellType() == Cell.CELL_TYPE_BLANK){
							return new CustomResponseDto(false, "Please Enter Delivery Date in Proper Date format");
						}
						
					 
//					if (!DateUtil.isCellDateFormatted(DateCell1)){
//						return new CustomResponseDto(false, "Please Enter Delivery Date in Proper Date format");
//					}
//					
//					if (row.getCell(7) == null || row.getCell(7).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(8) == null || row.getCell(8).getCellType() == Cell.CELL_TYPE_BLANK ||  row.getCell(9) == null || row.getCell(9).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(10) == null || row.getCell(10).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(11) == null || row.getCell(11).getCellType() == Cell.CELL_TYPE_BLANK || row.getCell(12) == null || row.getCell(12).getCellType() == Cell.CELL_TYPE_BLANK ) {
//						return new CustomResponseDto(false, "Please Enter All Values");
//					}
					
//					if (row.getCell(7).getCellType() != Cell.CELL_TYPE_NUMERIC ||  row.getCell(8).getCellType() != Cell.CELL_TYPE_NUMERIC || row.getCell(9).getCellType() != Cell.CELL_TYPE_NUMERIC || row.getCell(10).getCellType() != Cell.CELL_TYPE_NUMERIC || row.getCell(11).getCellType() != Cell.CELL_TYPE_NUMERIC ||  row.getCell(12).getCellType() != Cell.CELL_TYPE_NUMERIC) {
//						return new CustomResponseDto(false, "Please Enter All Values in Numbers");
//					}
					
					
					
				Iterator<Cell> cellIterator = row.cellIterator();


					Cell cell = cellIterator.next();
					
			
				
					
					PriceBidDto pb = new PriceBidDto();
					ItemBidDto itembidder = new ItemBidDto();
					BidderDto bidder = new BidderDto();
					
					String basicAmount="";
					Double taxAmount=0.0;
					Double GrossAmount=0.0;
				//	Double OtherCharges=0.0;
					// PriceBidDto newQuotationList = new PriceBidDto();
					// Check the cell type and format accordingly
		

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue() + "t");
						String quantity=(NumberToTextConverter.toText(row.getCell(4).getNumericCellValue()));
						String Rate = (NumberToTextConverter.toText(row.getCell(7).getNumericCellValue()));
						String DiscountType=(row.getCell(8).getStringCellValue());
						String Discount = NumberToTextConverter.toText(row.getCell(9).getNumericCellValue());
		//				double Discount = row.getCell(8).getNumericCellValue();
						double NetValue=0.0;
						Double Discount2=Double.parseDouble(Discount);
						Double Rate2=Double.parseDouble(Rate);
						Double quantity2=Double.parseDouble(quantity);
						if(DiscountType.equals("%")) {
							Discount2=(Discount2/100);
							NetValue=(Rate2)-(Rate2*Discount2);
						}else {
							NetValue=(Rate2-Discount2);
						}
						
						
						double Basic=NetValue*quantity2;
					
						
						double OtherChrg=0.0;
						double Totalfreightcharge=0.0;
						String freightType=(row.getCell(10).getStringCellValue());
						double frightCharge=(row.getCell(11).getNumericCellValue());
						if(freightType.equals("%")) {
							Totalfreightcharge=Totalfreightcharge + Basic*(frightCharge/100);
						}else {
							Totalfreightcharge=Totalfreightcharge + (frightCharge*quantity2);
						}
						
						double Totalpackingcharge=0.0;
						String packingFwdType=(row.getCell(12).getStringCellValue());
						double packingFwdCharge=(row.getCell(13).getNumericCellValue());
						if(packingFwdType.equals("%")) {
							Totalpackingcharge=Totalpackingcharge + Basic*(packingFwdCharge/100);
						}else {
							Totalpackingcharge=Totalpackingcharge + (packingFwdCharge*quantity2);
						}
						
						double Totalothercharge=0.0;
						String otherType=(row.getCell(14).getStringCellValue());
						double otherCharge=(row.getCell(15).getNumericCellValue());
						if(otherType.equals("%")) {
							Totalothercharge=Totalothercharge + Basic*(otherCharge/100);
						}else {
							Totalothercharge=Totalothercharge + (otherCharge*quantity2);
						}
						
						OtherChrg=Totalfreightcharge+Totalpackingcharge+Totalothercharge;
						
	                     
						double taxes=0.0;
						String taxRate = NumberToTextConverter.toText(row.getCell(16).getNumericCellValue());
						Double taxRate2=Double.parseDouble(taxRate);
						
						taxes=taxes+(Basic+OtherChrg)*(taxRate2/100);
						
						double grossAmtLine=0.0;
						grossAmtLine=grossAmtLine+Basic+OtherChrg+taxes;
						
						
						
						//String taxRate = NumberToTextConverter.toText(row.getCell(12).getNumericCellValue());
						Long itembidId = (long) row.getCell(2).getNumericCellValue();
						itembidder.setItemBidId(itembidId);
						
						
						
						
						Cell DateCell = row.getCell((short)17);
						short x = workbook.createDataFormat().getFormat("dd/mm/yyyy;@");
						CellStyle dateCellFormat = workbook.createCellStyle();
						dateCellFormat.setDataFormat(x);

						DateCell.setCellStyle(dateCellFormat);
						DataFormatter df = new DataFormatter();
						String stringCellValue = df.formatCellValue(DateCell).toString();
						
						Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(stringCellValue);
						pb.setDeliveryDate(date1);
						pb.setExGroupPriceRate(Rate);
						pb.setDiscountCharge(Discount);
//						pb.setDiscountType(DiscountType);
						if(DiscountType.equals("%")) {
							pb.setDiscountType("percent");
							}else if (DiscountType.equals("Per Unit")) {
								pb.setDiscountType("perUnit");
							}else {
								pb.setDiscountType("");
							}
//						pb.setDiscountType("lumpsum");
						
						pb.setFreightChargeRate(frightCharge);
						if(freightType.equals("%")) {
						pb.setFreightChargesType("percent");
						}else if(freightType.equals("Per Unit")){
							pb.setFreightChargesType("perUnit");
						}else {
							pb.setFreightChargesType("");
						}
						//pb.setFreightChargeRate(row.getCell(9).getNumericCellValue());
//						pb.setFreightChargesType("lumpsum");
						pb.setTaxRate(taxRate);
						pb.setPackingFwdCharge(packingFwdCharge);
						if(packingFwdType.equals("%")) {
							pb.setPackingFwdChargeType("percent");
							}else if(packingFwdType.equals("Per Unit")) {
								pb.setPackingFwdChargeType("perUnit");
							}else {
								pb.setPackingFwdChargeType("");
							}
//						pb.setPackingFwdChargeType(packingFwdType);
						pb.setOtherChargesRate(otherCharge);
						if(otherType.equals("%")) {
							pb.setOtherChargesType("percent");
							}else if(otherType.equals("Per Unit")) {
								pb.setOtherChargesType("perUnit");
							}else {
								pb.setOtherChargesType("");
							}
//						pb.setOtherChargesType(otherType);
						pb.setOtherChargesAmt(otherCharge);
						pb.setPerQtyFreightAmt(frightCharge);
						pb.setPerQtyOtherAmt(otherCharge);
						pb.setPerQtyPKAmt(packingFwdCharge);
						pb.setTotalPackingFwdChargeAmt(packingFwdCharge);
						pb.setCompleteOther(OtherChrg);
//						pb.setPackingFwdCharge(row.getCell(10).getNumericCellValue());
//						pb.setPackingFwdChargeType("lumpsum");
//						pb.setOtherChargesRate(row.getCell(11).getNumericCellValue());
//						pb.setOtherChargesType("lumpsum");
//						pb.setOtherChargesAmt(row.getCell(11).getNumericCellValue());
//						pb.setPerQtyFreightAmt(row.getCell(9).getNumericCellValue());
//						pb.setPerQtyOtherAmt(row.getCell(11).getNumericCellValue());
//						pb.setPerQtyPKAmt(row.getCell(10).getNumericCellValue());
//						pb.setTotalPackingFwdChargeAmt(row.getCell(10).getNumericCellValue());

//						Double completeOther = row.getCell(9).getNumericCellValue()
//								+ row.getCell(10).getNumericCellValue() + row.getCell(11).getNumericCellValue();
//						pb.setCompleteOther(completeOther);
						// pb.getItemBid().setItemBidId(itembidId);
						pb.setItemBid(itembidder);
						
//						Double Rate1=Double.parseDouble(Rate);
//						Double quantity1=Double.parseDouble(quantity);
//						Double basicRate=Rate1 * quantity1;
//						Double discount1=row.getCell(8).getNumericCellValue();
//						Double NetRate=basicRate-discount1;
//						Double OtherCharges=row.getCell(9).getNumericCellValue()+row.getCell(10).getNumericCellValue()+row.getCell(11).getNumericCellValue();
//						TotalOtherCharges=TotalOtherCharges+OtherCharges;
//						Double Taxes=row.getCell(12).getNumericCellValue()/100;
//						Double perQtyDiscount=discount1/basicRate;
//						pb.setNetRate(NetRate);
						
						pb.setNetRate(NetValue);
						//bidder
						
						TotalOtherCharges=TotalOtherCharges+OtherChrg;
						
					//	String setBasicAmt=NumberToTextConverter.toText(Basic);;
						TotalBasicAmt=(TotalBasicAmt)+(Basic);
						 //basicAmount=basicRate.toString();
//					     taxAmount=(basicRate-discount1+OtherCharges)*(Taxes);
						 
						 //TotalTaxAmt=TotalTaxAmt+ taxAmount;
					     TotalTaxAmt=TotalTaxAmt+ taxes;
					     
//						 GrossAmount=basicRate-discount1+OtherCharges+taxAmount;
					     TotalGrossAmt=TotalGrossAmt+grossAmtLine;
						
					     String Settax=NumberToTextConverter.toText(taxes);
					     pb.setTaxAmount(Settax);
//						 pb.setTaxAmount(taxAmount.toString());
					     
					     Double perQtyDiscount=Discount2/Basic;
						 pb.setPerQtyDiscount(perQtyDiscount);
					//	 String discountAmount=basicAmount-Discount;


						break;
					   case Cell.CELL_TYPE_STRING:

						System.out.print(cell.getStringCellValue() + "t");
						break;


					}
					
					
					newQuotationList.add(pb);
					
										
					bidder.setBidderId(bidderId);
//					bidder.setBasicAmt(basicAmount);
					bidder.setBasicAmt(TotalBasicAmt.toString());
					bidder.setTaxAmt(TotalTaxAmt.toString());
					bidder.setGrossAmt(TotalGrossAmt.toString());
//					bidder.setGrossAmt(GrossAmount.toString());
//					bidder.setOtherCharge(TotalOtherCharges.toString());
					bidder.setOtherCharge(TotalOtherCharges.toString());
					bidder.setOtherChargeType("item_level");
					bidder.setUploadExcelFile("Y");
					bidder.setStatus("SBMT");
					


					quotationDto.setBidder(bidder);
					
				}
				
				}
	
				quotationDto.setQuotations(newQuotationList);
				
				
				Errors errors = new Errors();
				for(PriceBidDto newpricebid : quotationDto.getQuotations()){
					
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("itemBidId", newpricebid.getItemBid().getItemBidId());
					 
					 List<ItemBidDto> itemBid = itemBidService.findDtos("getprLineByitemId", param);
					
					if(newpricebid.getDeliveryDate().before(new Date())) {
						
						validationUtil.reject(errors, "Empty Delivery Date ", "Delivery Date Must be today's or later for Material:"+itemBid.get(0).getPrLine().getMaterialCode());
					}
					
					if(newpricebid.getDiscountType().equals("") && !"0".equals(newpricebid.getDiscountCharge())) {
						validationUtil.reject(errors, "Empty discountType ", "Please Select discountType for Material:"+itemBid.get(0).getPrLine().getMaterialCode());

					}
					
					if(newpricebid.getFreightChargesType().equals("") && newpricebid.getFreightChargeRate()!=0.0) {
						validationUtil.reject(errors, "Empty freightChargesType ", "Please Select freightChargesType for Material:"+itemBid.get(0).getPrLine().getMaterialCode());
				
					

					}
					
					if(newpricebid.getPackingFwdChargeType().equals("") && newpricebid.getPackingFwdCharge()!=0.0) {
						validationUtil.reject(errors, "Empty PackingFwdChargesType ", "Please Select PackingFwdChargesType for Material:"+itemBid.get(0).getPrLine().getMaterialCode());
						
						
					}
					
					if(newpricebid.getOtherChargesType().equals("") && newpricebid.getOtherChargesRate()!=0.0) {
						validationUtil.reject(errors, "Empty OtherChargesType ", "Please Select OtherChargesType for Material:"+itemBid.get(0).getPrLine().getMaterialCode());
						
					
					}
				}
				
				
				if(errors.getErrorCount()>0){
					return new CustomResponseDto(false, errors.getErrorString());
				}else {
		    	quotationDto = priceBidService.saveQuaotation(quotationDto);
		   // 	workbook.close();
		    	
			    return new CustomResponseDto(true, "File Uploaded SuccesFully");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// CustomResponseDto message = new CustomResponseDto(true,"uploaded
		// Successfully");
		return add;
	}	
	
	

	
	
	@RequestMapping(value="/addImageAttachment", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AttachmentDto uploadImage(@RequestParam("file") MultipartFile multipartFile,@RequestParam("flag") String flag){
		/*ResponseDto response=attachmentService.isImageValid(multipartFile);*/
		AttachmentDto attachment = new AttachmentDto();	
		/*if(response.isHasError()){
			attachment.setResponse(response);
			return attachment;
		}else{*/
				attachment=attachmentService.addAttachment(multipartFile);
				if(!attachment.getResponse().isHasError()){
					return attachmentService.save(attachment);
				}
				return attachment;
		/*}*/
	}
	
	//for signed file upload ruchi
	@RequestMapping(value="/addSignedAttachment", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AttachmentDto uploadSign(@RequestParam("file") MultipartFile multipartFile,@RequestParam("flag") String flag){
		ResponseDto response=attachmentService.isSignFile(multipartFile);
		AttachmentDto attachment = new AttachmentDto();	
		if(response.isHasError()){
			attachment.setResponse(response);
			return attachment;
		}else{
			
			if("Y".equals(flag)){
				attachment=attachmentService.addEncryptedAttachment(multipartFile, flag);
				if(!attachment.getResponse().isHasError()){
					return attachmentService.save(attachment);
				}
				return attachment;
			}else{
				attachment=attachmentService.addAttachment(multipartFile);
				if(!attachment.getResponse().isHasError()){
					return attachmentService.save(attachment);
				}
				return attachment;
			}
		}
	}
	
	@RequestMapping(value="/deleteAttachment/{attachmentId}",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto deleteAttachment(@PathVariable("attachmentId") Long attachmentId){
		ResponseDto resp=null;
		try{
			boolean deleted = attachmentService.deleteById(attachmentId);
			if(deleted){
				resp=new ResponseDto(false, "Attachment Deleted");
			}else{
				resp= new ResponseDto(true,"Problem in deleting attachment");
			}
		}catch (Exception e) {
			resp= new ResponseDto(true,"Problem in deleting attachment"+e.getMessage());
		}
		return resp;
	}
	
	@RequestMapping(value="/deleteAttachmentById/{name}/{attachmentFieldName}/{attachmentId}",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseDto delete(@PathVariable("name") String name,@PathVariable("attachmentFieldName") String attachmentFieldName,@PathVariable("attachmentId") Long attachmentId)
	{
		return attachmentService.deleteAttachment(name,attachmentFieldName, attachmentId);
		
	}
}

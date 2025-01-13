package com.novelerp.eat.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.core.util.AppPropertyUtil;
import com.novelerp.core.util.FileUtil;
import com.novelerp.eat.dao.MISDao;
import com.novelerp.eat.dto.MISDto;
import com.novelerp.eat.entity.MIS;
import com.novelerp.eat.service.MISService;

@Service
public class MISServiceImpl extends AbstractContextServiceImpl<MIS, MISDto> implements MISService {

	@Autowired
	private MISDao misDao;

	@Autowired
	private AppPropertyUtil appProperties;
	@Autowired
	private SystemConfiguratorService sysConfiguratorService;

	@Autowired
	private FileUtil fileUtil;
	
	private Map<String, Integer> columnIndex =null;
	
	@PostConstruct
	private void init() {
		super.init(MISServiceImpl.class, misDao, MIS.class, MISDto.class);
		setByPassProxy(true);
	}

	private static final Logger log = LoggerFactory.getLogger(MISServiceImpl.class);
	
	@Override
	public List<MISDto> readMisExcel() {
		
		List<MISDto> list = new ArrayList<>();
		/*String FILE_NAME = "C:\\Users\\HP\\Desktop\\PAN verification\\updated.xlsx";*/
		/*String FILE_NAME =sysConfiguratorService.getAppDocDir()+"updated.xlsx";*/
		String FILE_NAME =sysConfiguratorService.getPropertyConfigurator(AppBaseConstant.EAT_MIS_FILE_PATH)+sysConfiguratorService.getPropertyConfigurator(AppBaseConstant.EAT_MIS_FILE_NAME); 
		FileInputStream fileStream=null;
		File file=null;
		try {
			file=new File(FILE_NAME);
			fileStream = new FileInputStream(file);
			String fileExtn = GetFileExtension(FILE_NAME);
			Workbook workbook = null;
			if (fileExtn.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(fileStream);
				readExcelSheet(list,workbook);
				workbook.close();
			} else if(fileExtn.equalsIgnoreCase("xls")){
				workbook = new HSSFWorkbook(fileStream);
				readExcelSheet(list,workbook);
				workbook.close();
			} else {
				FileReader fr=new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				readCSV(list,br);
				br.close();
			}
			
			backupFile(file);
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			fileUtil.closeInputStream(fileStream);
		} 
		return list;
	}
	
	private List<MISDto> readExcelSheet(List<MISDto> list, Workbook workbook) throws IOException{
		Sheet datatypeSheet = workbook.getSheetAt(0);
		if(datatypeSheet.getPhysicalNumberOfRows()>1){
			for (int i = 1; i < datatypeSheet.getPhysicalNumberOfRows() ; i++) {
				MISDto misDto = new MISDto();
				misDto.setDocNo(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_ONLINE_PAY_ID, i));
				misDto.setCharges(Double.parseDouble(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_CHARGES, i)));
				misDto.setBillerId(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_BILLER_ID, i));
				misDto.setBankId(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_BANK_ID, i));
				misDto.setBankRefNo(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_BANK_REF_NO, i));				
				misDto.setpGIRefNo(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_PGI_REF_NO, i));
				misDto.setFeeType(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_FEE_TYPE, i));
				misDto.setCompanyName(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_COMPANNY_NAME, i));
				misDto.setTenderNo(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_TENDER_NO, i));
				misDto.setRef5(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_PAN_NO, i));
				misDto.setRef6(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_GSTIN, i));
				misDto.setRef7(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_VENDOR_CODE, i));
				misDto.setRef8(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_REF_NO_8, i));
				misDto.setFiller(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_FILLER, i));
				misDto.setDateofTxn(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_DATE_OF_TXN, i));
				misDto.setSettlementdate(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_SETTLEMENT_DATE, i));
				misDto.setGrossAmount(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_GROSS_AMOUNT, i));
				misDto.setGst(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_GST_AMOUNT, i));
				misDto.setNetAmount(Double.parseDouble(getCellData(datatypeSheet, AppBaseConstant.MIS_COLUMN_NET_AMOUNT, i)));
				list.add(misDto);
			}
		}
		return list;
	}

	private List<MISDto> readCSV(List<MISDto> list, BufferedReader br) throws IOException{
		String line="";
		String csvSplitBy=",";
		int count=0;
		columnIndex=new HashMap<>();
		while ((line = br.readLine()) != null) {
			String[] headers = null;
            String[] column = null;
            if(count==0){
            	headers = line.split(csvSplitBy);
            	setAllColumnIndex(headers);
            	count++;
            }else{
            	column = line.split(csvSplitBy);
            	MISDto misDto = new MISDto();
    			misDto.setDocNo(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_ONLINE_PAY_ID));
    			misDto.setCharges(Double.parseDouble(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_CHARGES)));
    			misDto.setBillerId(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_BILLER_ID));
    			misDto.setBankId(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_BANK_ID));
    			misDto.setBankRefNo(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_BANK_REF_NO));				
    			misDto.setpGIRefNo(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_PGI_REF_NO));
    			misDto.setFeeType(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_FEE_TYPE));
    			misDto.setCompanyName(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_COMPANNY_NAME));
    			misDto.setTenderNo(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_TENDER_NO));
    			misDto.setRef5(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_PAN_NO));
    			misDto.setRef6(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_GSTIN));
    			misDto.setRef7(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_VENDOR_CODE));
    			misDto.setRef8(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_REF_NO_8));
    			misDto.setFiller(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_FILLER));
    			misDto.setDateofTxn(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_DATE_OF_TXN));
    			misDto.setSettlementdate(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_SETTLEMENT_DATE));
    			misDto.setGrossAmount(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_GROSS_AMOUNT));
    			misDto.setGst(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_GST_AMOUNT));
    			misDto.setNetAmount(Double.parseDouble(getCellValueForCSVCell(column, AppBaseConstant.MIS_COLUMN_NET_AMOUNT)));
    			list.add(misDto);
            }
        }
		return list;
	}
	
	private String getCellValueForCSVCell(String[] column,String key){
		return column[columnIndex.get(key)];
	}
	
	private void setAllColumnIndex(String[] headers){
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_ONLINE_PAY_ID);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_CHARGES);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_BILLER_ID);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_BANK_ID);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_BANK_REF_NO);				
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_PGI_REF_NO);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_FEE_TYPE);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_COMPANNY_NAME);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_TENDER_NO);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_PAN_NO);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_GSTIN);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_VENDOR_CODE);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_REF_NO_8);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_FILLER);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_DATE_OF_TXN);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_SETTLEMENT_DATE);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_GROSS_AMOUNT);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_GST_AMOUNT);
		getColumnIndex(headers, AppBaseConstant.MIS_COLUMN_NET_AMOUNT);
	}
	
	private void getColumnIndex(String[] headers,String coulumnName){
		int index = -1;
		for (int i=0;i<headers.length;i++) {
		    if (headers[i].trim().equalsIgnoreCase(coulumnName.trim())) {
		        index = i;
		        break;
		    }
		}
		columnIndex.put(coulumnName, index);
	}
	
	private String getCellData(Sheet sheet, String columnName, int rowNum){
		try{
			int col_Num = -1;
			Row row = sheet.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++){
                if(row.getCell(i).getStringCellValue().trim().toLowerCase().equals(columnName.trim().toLowerCase()))
                    col_Num = i;
            }
            row = sheet.getRow(rowNum);
            Cell cell = row.getCell(col_Num);
            
            if(cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
            {
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if(HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }else if(cell.getCellTypeEnum() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
		}catch(Exception e){
			log.error(e.getMessage());
			return "row "+rowNum+" or column does not exist  in Excel";
		}
	}

	private static String GetFileExtension(String fname2) {
		String fileName = fname2;
		String ext = "";
		int mid = fileName.lastIndexOf(".");
		ext = fileName.substring(mid + 1, fileName.length());
		return ext;
	}

	public static void main(String s[]) {
		MISServiceImpl impl = new MISServiceImpl();
		impl.readMisExcel();
	}
	
	private void backupFile(File file){
		if(file!=null ){
			if(file.exists()){
				String bkpFilePath=sysConfiguratorService.getPropertyConfigurator(AppBaseConstant.EAT_MIS_FILE_BKP_PATH);
				Date dt=new Date();
				String bkpFileName=dt.getTime()+"_"+file.getName();
				fileUtil.moveFile(file,bkpFilePath,bkpFileName);
				file.delete();
			}else{
				log.info("File does not exists"+file.getPath()+file.getName());
			}
		}else{
			log.info("File Object is Null");
		}
	}

	
	
}

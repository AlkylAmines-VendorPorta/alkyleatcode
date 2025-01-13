package com.novelerp.appbase.master.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.novelerp.appbase.master.dao.AsnReminderDao;
import com.novelerp.appbase.master.dao.AttachmentDao;
import com.novelerp.appbase.master.dto.AsnReminderDto;
import com.novelerp.appbase.master.dto.AttachmentDto;
import com.novelerp.appbase.master.entity.AsnReminder;
import com.novelerp.appbase.master.entity.Attachment;
import com.novelerp.appbase.master.service.AsnReminderService;
import com.novelerp.appbase.master.service.AttachmentService;
import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.dto.BPartnerDto;
import com.novelerp.appcontext.service.ContextService;
import com.novelerp.appcontext.service.MediaService;
import com.novelerp.appcontext.service.SystemConfiguratorService;
import com.novelerp.appcontext.service.impl.AbstractContextServiceImpl;
import com.novelerp.appcontext.util.ContextConstant;
import com.novelerp.commons.util.CommonUtil;
import com.novelerp.core.dto.ResponseDto;
import com.novelerp.core.service.impl.AbstractServiceImpl;
import com.novelerp.core.service.impl.MD5Security;
import com.novelerp.core.util.FileUtil;

@Service
public class AsnReminderServiceImpl extends AbstractContextServiceImpl<AsnReminder, AsnReminderDto>
		implements AsnReminderService {

	private Logger log = LoggerFactory.getLogger(AsnReminderServiceImpl.class);

	@Autowired
	private AsnReminderDao asnReminderDao;

	@Autowired
	/* @Qualifier(AppBaseConstant.SFTP_MEDIA_SERVICE_IMPL) */
//    @Qualifier(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)
	@Qualifier(AppBaseConstant.FTP_MEDIA_SERVICE_IMPL)
	private MediaService mediaService;

	@Autowired
	private FileUtil fileUtil;

	@PostConstruct
	private void init() {
		super.init(AsnReminderServiceImpl.class, asnReminderDao, AsnReminder.class, AsnReminderDto.class);
		setByPassProxy(true);
	}

	@Override
	/* @Transactional(propagation = Propagation.REQUIRED) */
	public List<AsnReminderDto> saveExcelData(MultipartFile multipartFile) throws IOException {
		System.out.println("servImpl");
		AsnReminderDto attachment = new AsnReminderDto();
		List<AsnReminderDto> reminderList = new ArrayList<AsnReminderDto>();
		String fileName = multipartFile.getOriginalFilename();
		// Vector dataHolder=read(fileName);
		// saveToDatabase(dataHolder);
		System.out.println("servImpl2");
		attachment.setFileExtension(getFileExtension(fileName));
		attachment.setFileName(fileName);
		long time = System.currentTimeMillis();
		String name = time + fileName;
		System.out.println("servImpl3");
		byte[] media = getMedia(multipartFile);
		// String savedFilePath = saveMedia(media, name);
		/* String savedFilePath=mediaService.save(media,name); */
		String savedFilePath = "C:\\Users\\developer\\Downloads\\test.xls";

		if (savedFilePath == null) {
			System.out.println("servImpl4");
			attachment.setResponse(new ResponseDto(true, "Error in destination path creation"));
			reminderList.add(attachment);
			return reminderList;
		} else {
			reminderList = read(savedFilePath);
			System.out.println("servImpl5");
			attachment.setResponse(new ResponseDto(false, "Path created successfully"));
		}
		attachment.setPath(savedFilePath);
		return reminderList;
	}

	public List<AsnReminderDto> read(String fileName) throws FileNotFoundException, IOException {

		List<AsnReminderDto> reminderList = new ArrayList<AsnReminderDto>();
		// obtaining input bytes from a file
		System.out.println("read 1");
		FileInputStream fis = new FileInputStream(fileName);
		// creating workbook instance that refers to .xls file
		System.out.println("read 2");
		// XSSFWorkbook workbook = new XSSFWorkbook(fis);
		HSSFWorkbook wb = new HSSFWorkbook(fis);

		// creating a Sheet object to retrieve the object
		System.out.println("read 3");
		// XSSFSheet sheet = workbook.getSheetAt(0);
		HSSFSheet sheet = wb.getSheetAt(0);
		// Workbook wb = WorkbookFactory.create(fis);

		// get the total row count in the excel sheet
		Iterator<Row> iterator = sheet.rowIterator();
		int cellIndex = 0;
		String description = null;
		while (iterator.hasNext()) {
			AsnReminderDto dto = new AsnReminderDto();
			Row row = iterator.next();
			Cell asnCell = row.getCell(0);
			dto.setPoNo(asnCell.getStringCellValue());
			System.out.println("asnCell.getStringCellValue() = " + asnCell.getStringCellValue());
			Cell vendorEmailcell = row.getCell(1);
			dto.setVendorEmail(vendorEmailcell.getStringCellValue());
			System.out.println("vendorEmailcell.getStringCellValue() = " + vendorEmailcell.getStringCellValue());
			reminderList.add(dto);
			/*
			 * for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) { Cell cell =
			 * row.getCell(i); // print the cell value System.out.println(i + " " + cell);
			 * 
			 * }
			 */
		}
		return reminderList;

	}

	private String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		if (index < 0) {
			return "";
		}
		return fileName.substring(index + 1);
	}

	private byte[] getMedia(MultipartFile multipartFile) {
		byte[] media = null;
		try {
			media = multipartFile.getBytes();
		} catch (Exception e) {
			log.error("ERROR", e);
		}
		return media;
	}

	public String saveMedia(byte[] media, String fileName) {
		return mediaService.save(media, fileName);
	}
	
	@Override
	@Transactional
	public boolean deleteAsnReminder(Long id) {
		return deleteById(id);
	}

}


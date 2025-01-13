package com.novelerp.appcontext.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.MediaService;

@Service(AppBaseConstant.LOCAL_MEDIA_SERVICE_IMPL)
public class LocalMediaServiceImpl extends AbstractMediaServiceImpl
			implements MediaService{

	private Logger log = LoggerFactory.getLogger(LocalMediaServiceImpl.class);
	
	@Override
	public String save(byte[] media, String fileName) {
		log.info("Saving File..");
		File file =writeByteArrayTo(media, fileName);
		if(file!=null && file.exists()){			
			return file.getParent()+File.separator;
		}
		return null;
	}
	
	

}

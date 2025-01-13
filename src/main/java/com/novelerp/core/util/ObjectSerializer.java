package com.novelerp.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ObjectSerializer {

	private static final Logger log= LoggerFactory.getLogger(ObjectSerializer.class);
	
	 public String toString( Serializable o ) throws IOException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream( baos );
	        oos.writeObject( o );
	        oos.close();
	        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
	 }
	 
	 public Object fromString( String s ) throws IOException ,ClassNotFoundException {
         byte [] data = Base64.getDecoder().decode( s );
         ObjectInputStream ois = new ObjectInputStream( new ByteArrayInputStream(  data ) );
         Object o  = ois.readObject();
         ois.close();
         return o;
    }
	
}

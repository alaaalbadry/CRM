package com.techvalueinsight.jsf.primefaces.view.helpers;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



@ManagedBean
@ViewScoped
public class FileHelper {

	public static void saveFileInPath(InputStream input, String fileFullPath) {

		try {
			java.io.File folder = new java.io.File(fileFullPath);
			folder.getParentFile().mkdirs();
			FileUtils.copyInputStreamToFile(input, folder);
			System.out.println("Uploaded file successfully saved in " + fileFullPath);

		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static StreamedContent getFileFromPath(String fileFullPath) throws Exception {

		java.io.File folder = new java.io.File(fileFullPath);
		folder.getParentFile().mkdirs();
		String fileName = folder.getName();
		InputStream stream = new FileInputStream(folder);
		String ext = fileName.split(Pattern.quote("."))[1].toString();
		StreamedContent contentFile = new DefaultStreamedContent(stream, "application/" + ext, fileName);
		
		
		return contentFile;
	}

}
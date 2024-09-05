package com.atcollabo.file;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;

public class UploadResultDTO {

	private final String fileName;
	private final String uuid;
	private final String folderPath;
	private final String uploadPath;

//	@Value("${com.atcollabo.domain}")
	private static final String DOMAINS = "";
	
	public UploadResultDTO(String fileName, String uuid, String folderPath, String uploadPath) {
		this.fileName = fileName;
		this.uuid = uuid;
		this.folderPath = folderPath; // have to end with /
		this.uploadPath = uploadPath;
	}

	public String getFileName() {
		return fileName;
	}

	public String getUuid() {
		return uuid;
	}

	public String getFolderPath() {
		return folderPath;
	}

	/**
	 * plain text
	 * 
	 * @return file path
	 */
	public String getFilePath() {
		try {
			return URLEncoder.encode(uploadPath+folderPath+"/"+uuid+"-"+fileName, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "undefined";
	}
	
	/**
	 * 
	 * @see file.FileController
	 * @return URL to get file
	 */
	public String getFileURL() {
		return "/api/get?fileName="+folderPath+uuid+"-"+fileName;
	}
	
	/**
	 * 이미지 경로
	 * 
	 * @return path of image file
	 */
	public String getImagePath() {
		try {
			return URLEncoder.encode(uploadPath+folderPath+uuid+"-"+fileName, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "undefined";
	}
	/**
	 * 이미지 다운로드
	 * @see file.FileController
	 * @return URL to get bytes of image
	 */
	public String getImageURL() {
		return DOMAINS+"/api/image?fileName="+folderPath+uuid+"-"+fileName;
	}
	
	/**
	 * 
	 * @return image path of thumnail
	 */
	public String getThumbnailPath() {
		try {
			return URLEncoder.encode(uploadPath+folderPath+"s_"+uuid+"-"+fileName, "UTF-8");
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "undefined";
	}
	/**
	 * 썸네일 이미지 다운로드
	 * 
	 * @see this.getImageURL()
	 * @see file.FileController
	 * @return URL to get bytes of thumbnail image
	 */
	public String getThumbnailURL() {
		return DOMAINS+"/api/image?fileName="+folderPath+"s_"+uuid+"-"+fileName;
	}

}

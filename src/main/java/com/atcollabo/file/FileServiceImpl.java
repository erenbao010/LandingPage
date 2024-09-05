package com.atcollabo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

	@Value("${com.atcollabo.uploadpath}") // in application-properties
	private String uploadPath;
	
	private void makeDirectory(String folderPath) {
		// TODO: 전역 uploadPath 하위 생성
		File uploadDirectory = new File(uploadPath, folderPath);
		if (uploadDirectory.exists() == false) {
			uploadDirectory.mkdirs();
		}
	}

	private boolean allowedFormat(MultipartFile uploadFile) {
		// TODO: 허용된 형식의 file 인 경우 true
		// [Common MIME
		// types](https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types)
		return false || (uploadFile.getContentType().startsWith("image"))
//				|| (uploadFile.getContentType().startsWith("audio"))
//				|| (uploadFile.getContentType().startsWith("video"))
//				|| (uploadFile.getContentType().endsWith("octet-stream"))
//				|| (uploadFile.getContentType().endsWith("pdf"))
//				|| (uploadFile.getContentType().endsWith("powerpoint"))
//				|| (uploadFile.getContentType().endsWith("msword"))
//				|| (uploadFile.getContentType().contains("office"))
		;
	}

	@Override
	public String saveFile(MultipartFile uploadFile, Consumer<String[]> callback) {
		String result = "";
		// uploaded file name
		String originName = uploadFile.getOriginalFilename();
		// ./ 상위 경로 접근 방지
		String fileName = originName.substring(originName.lastIndexOf("\\") + 1);
		// 확장자 분리
		String ext = "."+fileName.substring(fileName.lastIndexOf(".")+1);
	
		// 허용된 file 만 저장(image만 설정된 상태)
		if (allowedFormat(uploadFile) == true) {
			// 저장 위치를 구분하는 경우
			String folderPath = "";
			makeDirectory(folderPath);
			// file명 변경 - 중복제거를 위한 식별명 처리 UUID
			String uuid = UUID.randomUUID().toString();
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + ext;
			// 실제 저장 처리
			Path savePath = Paths.get(saveName);
			try {
				// 원본 파일
				uploadFile.transferTo(savePath);
	
				// image 인 경우 thumbnail 생성, 원본과 동일 경로에 s_ 만 붙임
				String thumbnailName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + ext;
				File thumbnailFile = new File(thumbnailName);
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 120, 120);
	
				// 응답을 위한 처리 0:saved file name, 1:thumbnail name, 2: directory name
				String[] args = { uuid + ext, "s_" + uuid + ext, folderPath };
				callback.accept(args);
				
				result = folderPath + ((folderPath.isEmpty())? "" : "/") + uuid + ext;
	
			} catch (IOException e) {
				result = e.getMessage();
			}
		} else {
			callback.accept(new String[]{"can't not upload : limited format", "", ""});
		}
		return result;
	}

	@Override
	public byte[] loadFile(String fileName) {
		// TODO: path 처리 필요		
		InputStream in = getClass().getResourceAsStream(fileName);
		try {
			return IOUtils.toByteArray(in);
		} catch (IOException e) {
			log.error("{}", e.getMessage());
		}
		return null;
	}
	
	@Override
	public InputStream getFileInputStream(String fileName) {
		try {
			String filePath = uploadPath + File.separator +fileName;
			InputStream in = new FileInputStream(new File(filePath));
			return in; 
		} catch (IOException e) {
			log.error("{}", e.getMessage());
		}
		return null;
	}

}

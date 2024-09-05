package com.atcollabo.file;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileRestController {
	
	@Value("${com.atcollabo.uploadpath}") // in application-properties
	private String uploadPath;
	
	@GetMapping("/api/image")
	public ResponseEntity<byte[]> image2display(@RequestParam String fileName) {
		ResponseEntity<byte[]> bytes = null;
		try {
			String imageFileName = URLDecoder.decode(fileName, "UTF-8");
			log.debug("image2display: request file decoded name : {}", imageFileName);

			File file = new File(uploadPath + File.separator + imageFileName);
			if( !file.exists() ) {
				log.warn("image2display: not found {} ", imageFileName);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			bytes = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (Exception e) {
			log.error("{}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return bytes;
	}

	/**
	 * File upload
	 * 
	 * @param uploadFiles
	 * @return
	 */
	@PostMapping("/api/uploads")
	public ResponseEntity<List<UploadResultDTO>> uploadRequest(MultipartFile[] uploadFiles) {
		List<UploadResultDTO> dtoList = new ArrayList<>();
		String message = uploadFile(uploadFiles, (ar) -> {
			dtoList.add(new UploadResultDTO(ar[0], ar[1], ar[2], ar[3]));
		});
		if (message.isEmpty()) {
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
		} else {
			log.error("uploadRequest: error {}", message);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * Image upload for Summernote editor
	 * - to insert image
	 * 
	 * @param multipartFile
	 * @return
	 */
    @PostMapping(value="/api/uploadSummernoteImage", produces = "application/json")
    public ResponseEntity<List<UploadResultDTO>> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
    	log.debug("uploadSummernoteImageFile: {}", multipartFile);
    	MultipartFile[] uploadFiles = new MultipartFile[] {multipartFile};
    	return uploadRequest(uploadFiles);
    }

	public String uploadFile(MultipartFile[] uploadFiles, Consumer<String[]> callback) {
		String result = "";
		for (MultipartFile uploadFile : uploadFiles) {
			String originName = uploadFile.getOriginalFilename();
			String fileName = originName.substring(originName.lastIndexOf("\\") + 1);
			log.debug("{},{}", originName, fileName);

			// 허용된 file 만 저장(image만 설정된 상태)
			if (allowedFormat(uploadFile) == true) {
				// 저장 위치를 구분하는 경우
				String folderPath = "";
				makeDirectory(folderPath);
				// 중복제거를 위한 식별명 처리 UUID
				String uuid = UUID.randomUUID().toString();
				String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "-" + fileName;
				// 실제 저장 처리
				Path savePath = Paths.get(saveName);
				try {
					// 원본 파일
					uploadFile.transferTo(savePath);

					// image 인 경우 thumbnail 생성, 원본과 동일 경로에 s_ 만 붙임
					String thumbnailName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "-"
							+ fileName;
					File thumbnailFile = new File(thumbnailName);
					Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 120, 120);

					// 응답을 위한 처리
					String[] args = { fileName, uuid, folderPath, uploadPath  };
					callback.accept(args);

				} catch (IOException e) {
					result = e.getMessage();
				}
			} else {
				result = ("can't not upload : limited format" + fileName);
			}
		}
		return result;
	}

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

}

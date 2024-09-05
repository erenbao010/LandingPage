package com.atcollabo.file;

import java.io.InputStream;
import java.util.function.Consumer;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	String saveFile(MultipartFile uploadFile, Consumer<String[]> callback);

	byte[] loadFile(String fileName);

	InputStream getFileInputStream(String fileName);

}

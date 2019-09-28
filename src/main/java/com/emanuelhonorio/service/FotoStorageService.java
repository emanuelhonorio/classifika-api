package com.emanuelhonorio.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.emanuelhonorio.error.exceptions.FotoStorageException;

@Deprecated
@Service
public class FotoStorageService {
	
	private Path baseLocalStorage = Paths.get("./uploads").normalize().toAbsolutePath();
	
	public FotoStorageService() {
		if(!Files.exists(this.baseLocalStorage)) {
			try {
				Files.createDirectory(this.baseLocalStorage);
			} catch (IOException e) {
				throw new FotoStorageException("falha ao criar path base para upload de arquivos");
			}
		}
	}
	
	public void save(MultipartFile file, Path relativeFolder, String nome) {
		if(file == null || file.isEmpty()) {
			throw new FotoStorageException("arquivo inexistente", HttpStatus.BAD_REQUEST);
		}
		
		if(StringUtils.isEmpty(nome)) {
			throw new FotoStorageException("não foi possivel ler o nome do arquivo");
		}
		
		if(relativeFolder == null || relativeFolder.isAbsolute() || relativeFolder.toString().contains("..")) {
			throw new FotoStorageException("invalid relativeFolder");
		}
		
		Path absoluteFolder = this.baseLocalStorage.resolve(relativeFolder).normalize();
		try {
			if(!Files.exists(absoluteFolder))
				Files.createDirectory(absoluteFolder);
		} catch (IOException e) {
			throw new FotoStorageException("Falha ao criar path relativo para upload de arquivos");
		}
		
		try {
			Path pathComNomeArquivo = absoluteFolder.resolve(nome).normalize();
			Files.copy(file.getInputStream(), pathComNomeArquivo, StandardCopyOption.REPLACE_EXISTING);
		} catch (IllegalStateException | IOException e) {
			throw new FotoStorageException("não foi possível transferir o arquivo");
		}
	}
	
	
	public Resource loadAsResource(Path relativeFolder, String nome) {
		if(StringUtils.isEmpty(nome)) {
			throw new FotoStorageException("não foi possivel ler o nome do arquivo");
		}
		
		if(relativeFolder == null || relativeFolder.isAbsolute() || relativeFolder.toString().contains("..")) {
			throw new FotoStorageException("invalid relativeFolder");
		}
		
		Path absoluteFolder = this.baseLocalStorage.resolve(relativeFolder).normalize();
		Path pathComNomeArquivo = absoluteFolder.resolve(nome).normalize();
		
		if(!Files.exists(pathComNomeArquivo)) {
			throw new FotoStorageException("arquivo não existe", HttpStatus.BAD_REQUEST);
		}
		
		Resource resource;
		try {
			resource = new UrlResource(pathComNomeArquivo.toUri());
		} catch (MalformedURLException e) {
			throw new FotoStorageException("url mal formada", HttpStatus.BAD_REQUEST);
		}
		
		return resource;
	}
	
	public void delete(Path relativeFolder, String nome) {
		if(StringUtils.isEmpty(nome)) {
			throw new FotoStorageException("não foi possivel ler o nome do arquivo");
		}
		
		if(relativeFolder == null || relativeFolder.isAbsolute() || relativeFolder.toString().contains("..")) {
			throw new FotoStorageException("invalid relativeFolder");
		}
		
		Path absoluteFolder = this.baseLocalStorage.resolve(relativeFolder).normalize();
		Path pathComNomeArquivo = absoluteFolder.resolve(nome).normalize();
		
		if(!Files.exists(pathComNomeArquivo)) {
			throw new FotoStorageException("arquivo não existe", HttpStatus.BAD_REQUEST);
		}
		
		try {
			Files.deleteIfExists(pathComNomeArquivo);
		} catch (IOException e) {
			throw new FotoStorageException("falha ao excluir foto");
		}
	}

}

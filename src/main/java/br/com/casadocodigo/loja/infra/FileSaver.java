package br.com.casadocodigo.loja.infra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(final String baseFolder, final MultipartFile file) {
		try {
			final String realPath = request.getServletContext().getRealPath("/" + baseFolder);
			Files.createDirectories(Paths.get(realPath));
			final Path path = Paths.get(realPath).resolve(file.getOriginalFilename());
			file.transferTo(path);

			return Paths.get(baseFolder).resolve(file.getOriginalFilename()).toString();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}

	}

}

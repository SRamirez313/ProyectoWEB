package com.tierrafertil.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    // Carpeta fisica donde se guardan los archivos subidos
    @Value("${app.uploads.dir:src/main/resources/static/uploads/propiedades}")
    private String uploadsDir;

    // Ruta web con la que Thymeleaf/el navegador acceden a la imagen (ver StaticConfig si aplica)
    private static final String RUTA_WEB_BASE = "/uploads/propiedades/";

    public String guardarImagen(MultipartFile archivo) {
        try {
            Path carpeta = Path.of(uploadsDir);
            Files.createDirectories(carpeta);

            String extension = "";
            String nombreOriginal = StringUtils.cleanPath(archivo.getOriginalFilename());
            int puntoIdx = nombreOriginal.lastIndexOf('.');
            if (puntoIdx >= 0) {
                extension = nombreOriginal.substring(puntoIdx);
            }

            String nombreArchivo = UUID.randomUUID() + extension;
            Path destino = carpeta.resolve(nombreArchivo);
            Files.copy(archivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            return RUTA_WEB_BASE + nombreArchivo;
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo guardar la imagen: " + ex.getMessage(), ex);
        }
    }
}

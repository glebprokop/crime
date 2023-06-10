package org.sfec.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Util class for work with files in application
 */
@Component
public class UtilFileService {

    /**
     * Generate file path based on (see params):
     *
     * @param folder    folder name
     * @param fileName  unique name of file
     * @param extension file extension
     * @return generated path of file
     */
    public String generatePath(String folder, String fileName, String extension) {
        StringBuilder path = new StringBuilder();
        path.append(folder)
                .append("/")
                .append(fileName)
                .append(extension);

        return path.toString();
    }

    public File createNewFile(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();

        return file;
    }

    public File createNewFile(String path, MultipartFile requestFile) throws IOException {
        File file = createNewFile(path);
        requestFile.transferTo(file);

        return file;
    }

    public Boolean deleteFileByPath(String path) {
        File file = new File(path);

        return file.delete();
    }

    public String getExtension(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }
}

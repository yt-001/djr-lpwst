package com.xitian.djrlpwst.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 文件上传工具类
 */
public class FileUploadUtil {

    /**
     * 保存上传的文件到指定目录
     *
     * @param file 上传的文件
     * @param uploadDir 保存目录
     * @return 保存后的文件名
     * @throws IOException 文件保存异常
     */
    public static String saveFile(MultipartFile file, String uploadDir) throws IOException {
        // 创建目录如果不存在
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

        // 保存文件
        Path filePath = uploadPath.resolve(uniqueFilename);
        Files.write(filePath, file.getBytes());

        return uniqueFilename;
    }

    /**
     * 删除指定文件
     *
     * @param filename 文件名
     * @param uploadDir 文件所在目录
     * @return 是否删除成功
     */
    public static boolean deleteFile(String filename, String uploadDir) {
        try {
            Path filePath = Paths.get(uploadDir, filename);
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            return false;
        }
    }
}
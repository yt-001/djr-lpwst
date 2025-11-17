package com.xitian.djrlpwst.controller;

import com.xitian.djrlpwst.bean.ResultBean;
import com.xitian.djrlpwst.config.FileUploadConfig;
import com.xitian.djrlpwst.util.FileUploadUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
@Tag(name = "文件上传", description = "公共文件上传接口")
public class FileUploadController {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @PostMapping("/upload-images")
    @Operation(
        summary = "上传图片文件",
        description = "支持上传一个或多个图片文件，文件将被保存到服务器并返回文件名列表",
        responses = {
            @ApiResponse(responseCode = "200", description = "上传成功", 
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResultBean.class))),
            @ApiResponse(responseCode = "505", description = "文件上传失败")
        }
    )
    public ResultBean<List<String>> uploadImages(
            @Parameter(description = "要上传的图片文件，支持多文件上传", required = true) 
            @RequestParam("images") MultipartFile[] images) {
        try {
            List<String> filenames = new ArrayList<>();
            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    // 保存文件并获取文件名
                    String filename = FileUploadUtil.saveFile(image, fileUploadConfig.getUploadDir());
                    // 只返回文件名，不包含路径前缀
                    filenames.add(filename);
                }
            }
            return ResultBean.success(filenames);
        } catch (IOException e) {
            return ResultBean.fail(com.xitian.djrlpwst.bean.StatusCode.INTERNAL_ERROR, "文件上传失败：" + e.getMessage());
        }
    }
}
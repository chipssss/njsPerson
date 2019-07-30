package com.njs.agriculture.service.impl;

import com.njs.agriculture.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/29
 * @Description:
 */
@Service("iFileService")
@Slf4j
public class FileServiceImpl implements IFileService {
    @Override
    public String upload(MultipartFile file, String path) {
        //拿原始名
        String fileName = file.getOriginalFilename();

        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        log.info("开始上传文件，上传文件的文件名:{},上传的路径：{},新文件名:{}", fileName, path, uploadFileName);

        File fildDir = new File(path);
        if(!fildDir.exists()){
            fildDir.setWritable(true);
            fildDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try{
            file.transferTo(targetFile);
        }catch (IOException e){
            log.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

}

package com.njs.agriculture.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: SaikeiLEe
 * @Date: 2019/7/29
 * @Description:
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}

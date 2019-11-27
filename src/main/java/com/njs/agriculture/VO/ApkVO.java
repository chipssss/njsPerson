package com.njs.agriculture.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data

public class ApkVO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer appId;

    private Integer versionCode;

    private String delcare;

    private MultipartFile file;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date createTime;
}

package com.kasey.ssm.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Image {
    // 上传文件集合，如果不写List那么一次只能上传一个文件,如下，只能上传一个
    // private MultipartFile image;
    private List<MultipartFile> images;

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}

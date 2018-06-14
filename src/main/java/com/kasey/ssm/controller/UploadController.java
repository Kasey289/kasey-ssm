package com.kasey.ssm.controller;

import com.kasey.ssm.model.Image;
import com.kasey.ssm.util.FileNameGenerationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class UploadController {

    /**
     * 跳转到上传页面
     * @return
     */
    @RequestMapping("/view")
    public String viewForm(){
        return "upload";
    }


    /**
     * 文件上传（单个/多个）
     * @param images
     * @param model
     * @return
     */
    @RequestMapping("/upload")
    public String upload(Image images, Model model){
        // 1) 获取文件信息
        List<MultipartFile> files =  images.getImages();
        // 2) 判断是否有文件上传
        if(null != files && files.size() >0){
            // 3) 扩展使用，用于多个文件上传
//            List<String> fileNames  = Lists.newArrayList();
            List<String> fileNames  = new ArrayList<>();
            // 4) 遍历需上传的文件个数
            for (MultipartFile multipartFile: files){
                // 5) 获取文件名
                String fileName = multipartFile.getOriginalFilename();
                // 5.1) 验证是否上传的是图片，验证其他文件一样方式，如果去掉便通用
                // if(fileName.endsWith("jpg") || fileName.endsWith("gif") || fileName.endsWith("png"))

                // 6) 将文件名添加至filNames
                fileNames.add(fileName);
                // 7) 构建文件存储位置和文件
                File imageFile = new File("d://imgs",fileName);
                // 8) 判断构建的路径是否存在，如果不存在就创建一个
                if (!imageFile.getParentFile().exists()) {
                    imageFile.getParentFile().mkdirs();
                }
                try {
                    // 9) 将文件上传到目标目录下
                    multipartFile.transferTo(imageFile);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
        // 10) 可向页面返回对应的数据信息
        model.addAttribute("images",images);
        return "upload";
    }


    /**
     * 单个文件上传
     *
     */
    @RequestMapping("/oneUpload")
    public String oneUpload(@PathVariable MultipartFile mfile, HttpServletRequest request, Model model){
        String imgPath ="";
        if(!mfile.isEmpty()){
            String fileName  = mfile.getOriginalFilename();
            // 判断格式
            if(fileName.endsWith("jpg") || fileName.endsWith("gif") || fileName.endsWith("png")){
                // 构建服务器上传路径
                String url = "/upload/img/";
                String basePath = request.getSession().getServletContext().getRealPath(url);
                // 对文件重新命名
                String newFileName = FileNameGenerationUtil.getNewfileName(fileName);
                // 构建一个文件
                File file = new File(basePath, newFileName);
                // 判断构建的路径是否存在，如果不存在就创建一个
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                try {
                    // 上传文件，注意需要处理一异常
                    mfile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 返回路径到页面显示
                imgPath = url + newFileName;
            }else{
                model.addAttribute("error","请上传图片！");
                return "upload";
            }
        }
        model.addAttribute("url",imgPath);
        model.addAttribute("error","上传成功！");
        return "upload";
    }



}



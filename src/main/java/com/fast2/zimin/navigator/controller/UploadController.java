package com.fast2.zimin.navigator.controller;

import com.fast2.zimin.navigator.bean.UploadItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ez2sarang on 15. 5. 27..
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
//    @Inject
//    private FileSystemResource fsResource;

    @RequestMapping(method = RequestMethod.GET)
    public String getUploadForm(Model model)
    {
        model.addAttribute(new UploadItem());
        return "upload/uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(UploadItem uploadItem, BindingResult result)
    {
        if (result.hasErrors())
        {
            for(ObjectError error : result.getAllErrors())
            {
                System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
            }
            return "upload/uploadForm";
        }
        if(null != uploadItem.getFileData()) {
            for(CommonsMultipartFile item: uploadItem.getFileData()) {
                if(!item.isEmpty()){
                    String filename = item.getOriginalFilename();
                    String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());

                    //upload 가능한 파일 타입 지정
                    if(imgExt.equalsIgnoreCase("JPG") || imgExt.equalsIgnoreCase("JPEG") || imgExt.equalsIgnoreCase("GIF")){
                        byte[] bytes = item.getBytes();
                        try{
                            File lOutFile = new File("/Users/ez2sarang/upload"+"_"+filename);
                            FileOutputStream lFileOutputStream = new FileOutputStream(lOutFile);
                            lFileOutputStream.write(bytes);
                            lFileOutputStream.close();
                        }catch(IOException ie){
                            //Exception 처리
                            System.err.println("File writing error! ");
                        }
                        System.err.println("File upload success! ");
                    }else{
                        System.err.println("File type error! ");
                    }
                }
                // Some type of file processing...
                System.err.println("-------------------------------------------");
                System.err.println("Test upload: " + uploadItem.getName());
                System.err.println("Test upload: " + item.getOriginalFilename());
                System.err.println("-------------------------------------------");
            }
            /*if(!uploadItem.getFileData().isEmpty()){
                String filename = uploadItem.getFileData().getOriginalFilename();
                String imgExt = filename.substring(filename.lastIndexOf(".")+1, filename.length());

                //upload 가능한 파일 타입 지정
                if(imgExt.equalsIgnoreCase("JPG") || imgExt.equalsIgnoreCase("JPEG") || imgExt.equalsIgnoreCase("GIF")){
                    byte[] bytes = uploadItem.getFileData().getBytes();
                    try{
                        File lOutFile = new File("/Users/ez2sarang/upload"+"_"+filename);
                        FileOutputStream lFileOutputStream = new FileOutputStream(lOutFile);
                        lFileOutputStream.write(bytes);
                        lFileOutputStream.close();
                    }catch(IOException ie){
                        //Exception 처리
                        System.err.println("File writing error! ");
                    }
                    System.err.println("File upload success! ");
                }else{
                    System.err.println("File type error! ");
                }
            }

            // Some type of file processing...
            System.err.println("-------------------------------------------");
            System.err.println("Test upload: " + uploadItem.getName());
            System.err.println("Test upload: " + uploadItem.getFileData().getOriginalFilename());
            System.err.println("-------------------------------------------");*/

        }
        return "redirect:/index.jsp";
    }
}

package com.volunteer.volunteer.util;


import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Here is the File upload process util.
 * It needs to be called in the controller layer.
 * @Param file It is the file upload from front page.
 */
public class FileUtil {


    public boolean upload(MultipartFile file){
        try{
            if(file.isEmpty()){
                return false;
            }
            //Please replace the file path in you particular project.
            String path = "E:/temptest/";
            //Picture temp = new Picture();
            //temp.setPictureUrl(path);
            // TODO: 2019/3/14 here may need to construct a Picture object to help insert into database
            java.io.File ttep = new java.io.File(path);
            if(!ttep.getParentFile().exists()){
                ttep.getParentFile().mkdir();
            }
            try {
                byte[] bytes = file.getBytes();
                Path paths = Paths.get(path + file.getOriginalFilename());
                Files.write(paths, bytes);
                //Call the service to insert file url into database
             //   this.pictureService.insertFilePath(temp);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}

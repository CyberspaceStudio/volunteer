package com.volunteer.volunteer.util;


import com.volunteer.volunteer.model.Picture;
import com.volunteer.volunteer.service.PictureService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Here is the File upload process util.
 * It needs to be called in the controller layer.
 * file It is the file upload from front page.
 */
public class FileUtil {
    @Resource
    private PictureService pictureService;
    /**
     * 使用uploadHelper来将多文件拆解，在调用单个文件上传函数，加入事务管理
     * @param temp 从前端传来的多文件List
     * @return 返回是否全部上传成功
     */
    @Transactional
    public boolean uploadHelper(List<MultipartFile> temp){

        for(int incr = 0;incr < temp.size();incr++){
            temp.get(incr);
        }
        return false;
    }

    public boolean rebuildFileName(String name){
        return false;
    }
    public boolean upload(MultipartFile file){
        try{
            if(file.isEmpty()){
                return false;
            }
            //Please replace the file path in you particular project.
            String path = "E:/temptest/";
            Picture temp = new Picture();
            temp.setPictureUrl(path);
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
                this.pictureService.insertPicture(temp);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}

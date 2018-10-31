package com.zgy.web.controller;

import com.zgy.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final String folder = "D:\\myWork\\SpringSecurity\\zgy-security\\zgy-security-demo\\files";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println("fileName："+file.getOriginalFilename());
        System.out.println("contentType："+file.getContentType());
        System.out.println("size："+file.getSize());

//        String folder = "D:\\myWork\\SpringSecurity\\zgy-security\\zgy-security-demo\\files";

        File localFile = new File(folder, new Date().getTime()+".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable long id, HttpServletResponse response){
        try(
                InputStream inputStream = new FileInputStream(new File(folder, id+".txt"));
                OutputStream outputStream = response.getOutputStream();
                ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();

        }catch (Exception e){

        }

    }
}

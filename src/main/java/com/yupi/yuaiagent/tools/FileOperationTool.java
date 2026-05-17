package com.yupi.yuaiagent.tools;

import cn.hutool.core.io.FileUtil;
import com.yupi.yuaiagent.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * 文件操作工具类（提供文件读写功能）
 */
public class FileOperationTool {

    //目录的路径
    private final String FILE_PATH= FileConstant.FILE_SAVE_PATH+"/file";

    @Tool(description = "Read content from a file")
    public String readFile(@ToolParam(description = "Name of the file to read") String fileName) {
        //定义文件路径
       String filePath = FILE_PATH + "/" + fileName;
       try {
           //读取内容
           return FileUtil.readUtf8String(filePath);
       }catch (Exception e)
       {
            return "Error reading file"+e.getMessage();
       }
    }

    @Tool(description = "Write content to a file")
    public String writeFile(
            @ToolParam(description = "Name of the file to write") String fileName,
            @ToolParam(description = "Content to write to the file") String content) {
        String filePath = FILE_PATH + "/" + fileName;
        try{
            //创建目录
            FileUtil.mkdir(FILE_PATH);
            //写入内容
            FileUtil.writeUtf8String(content, filePath);
            return "File written successfully"+filePath;
        }
        catch (Exception e)
        {
            return "Error writing file"+e.getMessage();
        }
    }

}

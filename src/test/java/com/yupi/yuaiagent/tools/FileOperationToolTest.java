package com.yupi.yuaiagent.tools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileOperationToolTest {

    @Test
    void readFile() {
        FileOperationTool fileOperationTool = new FileOperationTool();
        String fileName="test.txt";
        String readFile = fileOperationTool.readFile(fileName);
        assertNotNull(readFile);
        System.out.println(readFile);
    }
    @Test
    void writeFile() {
        FileOperationTool fileOperationTool = new FileOperationTool();
        String fileName="test.txt";
        String content="hello AI";
        String writeFile = fileOperationTool.writeFile(fileName, content);
        assertNotNull(writeFile);
    }
}
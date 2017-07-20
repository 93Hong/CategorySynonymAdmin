package com.tmoncorp.admin.file;

import java.io.*;

/**
 * Created by sk2rldnr on 2017-07-13.
 */
public class WriteCSV {
    public void writeToCSV(String csvFormatString, String fileName) throws IOException {

        // todo : refactoring
        String csvPrefix = "카테고리 srl, 1depth 명, 2depth 명, 3depth 명, 4depth 명, 동의어, 유의어, 가중치, 사용 여부, 작업자, 작업 일시\n";
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "euc-kr"));
            writer.write(csvPrefix);
            writer.write(csvFormatString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }
}

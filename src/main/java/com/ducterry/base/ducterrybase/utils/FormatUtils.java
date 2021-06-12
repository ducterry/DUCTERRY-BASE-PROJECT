package com.ducterry.base.ducterrybase.utils;


import com.ducterry.base.ducterrybase.commons.constant.FieldConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletContext;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatUtils {
    public static ResponseEntity<FileSystemResource> returnFile(String path) {
        if (path != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            File file = FileUtils.getFile(path);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            headers.set("File", file.getName());
            headers.set("Content-Disposition", "attachment; filename=" + file.getName());
            headers.set("Access-Control-Expose-Headers", "File");
            return new ResponseEntity<>(fileSystemResource, headers, HttpStatus.OK);
        }
        return null;
    }

    public static CellStyle textCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        borderStyle(cellStyle);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setFont(fontStyle(workbook));
        DataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        return cellStyle;

    }

    public static CellStyle dateCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        borderStyle(cellStyle);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setFont(fontStyle(workbook));
        DataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        return cellStyle;
    }

    public static CellStyle numberCellStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        borderStyle(cellStyle);
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setFont(fontStyle(workbook));
        DataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        return cellStyle;
    }

    public static void borderStyle(CellStyle cellStyle) {
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
    }

    public static Font fontStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Times New Roman");
        return font;
    }

    public static void createCell(Row row, Integer i, String value, CellStyle cellStyle) {
        Cell cell = row.createCell(i);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    public static String getStrDate(Long time, String format) {
        return new SimpleDateFormat(format).format(new Date(time));
    }

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            return MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static String readFileResource(String path) {
        String content = null;
        try {
            InputStream getFile = FileUtils.class.getResourceAsStream(path);
            if (getFile == null)
                throw new FileNotFoundException("File not found:" + path);

            StringWriter writer = new StringWriter();

            IOUtils.copy(getFile, writer, FieldConstants.UTF_8);
            content = writer.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return content;
    }
}

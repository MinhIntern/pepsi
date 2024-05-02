package com.poly.demo.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {

    /**
     * Đọc chuỗi giá trị của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @param request      đối tượng HttpServletRequest
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public String getString(String name, String defaultValue, HttpServletRequest request) {
        return request.getParameter(name) != null ? request.getParameter(name) : defaultValue;
    }

    /**
     * Đọc số nguyên giá trị của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @param request      đối tượng HttpServletRequest
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public int getInt(String name, int defaultValue, HttpServletRequest request) {
        return request.getParameter(name) != null ? Integer.valueOf(request.getParameter(name)) : defaultValue;
    }

    /**
     * Đọc số thực giá trị của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @param request      đối tượng HttpServletRequest
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public double getDouble(String name, double defaultValue, HttpServletRequest request) {
        return request.getParameter(name) != null ? Double.valueOf(request.getParameter(name)) : defaultValue;

    }

    /**
     * Đọc giá trị boolean của tham số
     *
     * @param name         tên tham số
     * @param defaultValue giá trị mặc định
     * @param request      đối tượng HttpServletRequest
     * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
     */
    public boolean getBoolean(String name, boolean defaultValue, HttpServletRequest request) {
        return request.getParameter(name) != null ? Boolean.parseBoolean(request.getParameter(name)) : defaultValue;
    }

    /**
     * Đọc giá trị thời gian của tham số
     *
     * @param name    tên tham số
     * @param pattern là định dạng thời gian
     * @param request đối tượng HttpServletRequest
     * @return giá trị tham số hoặc null nếu không tồn tại
     * @throws RuntimeException lỗi sai định dạng
     */
    public Date getDate(String name, String pattern, HttpServletRequest request) {
        String value = request.getParameter(name);

        if (value != null) {
            try {
                SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
                return dateFormatter.parse(value);
            } catch (ParseException e) {
                throw new RuntimeException("Lỗi sai định dạng thời gian", e);
            }
        }

        return null;
    }

    /**
     * Lưu file upload vào thư mục
     *
     * @param file chứa file upload từ client
     * @param path đường dẫn tính từ webroot
     * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
     * @throws IOException
     * @throws IllegalStateException
     * @throws RuntimeException lỗi lưu file
     */
    public File save(MultipartFile file, String path) throws IllegalStateException, IOException {
        if (!file.isEmpty()) {
            file.transferTo(new File(path));
            return new File(path);
        } else {
            return null;
        }
    }

}

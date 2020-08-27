package com.heyrace.course_system.config;

import com.heyrace.beans.RespBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class SQLExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = SQLException.class)
    public RespBean defaultExceptionHandler(SQLException e) {
        return RespBean.error("数据库操作异常");
    }
}

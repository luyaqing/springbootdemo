package com.example.springbootdemo.aop;

import com.example.springbootdemo.model.JsonResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BG362793
 * Description: 用于处理Controller中的异常处理
 */
@RestControllerAdvice
public class ControllerExceptionHandlerApplication {

    private final static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandlerApplication.class);

    @ExceptionHandler
    public JsonResults handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        logger.info("Restful Http请求发生异常...");
        Map<String, Object> msgMap = new HashMap<String, Object>();

        if (res.getStatus() == HttpStatus.OK.value()) {
            logger.info("修改返回状态值为200");
            res.setStatus(HttpStatus.OK.value());
        }

        if (e instanceof NullPointerException) {

            logger.error("代码00：" + e.getMessage(), e);
            msgMap.put("errMsg", "空指针异常!");
            return JsonResults.fail(msgMap);
        } else if (e instanceof IllegalArgumentException) {

            logger.error("代码01：" + e.getMessage(), e);
            msgMap.put("msgError", "请求参数不匹配！");
            return JsonResults.fail(msgMap);
        } else if (e instanceof SQLException) {

            logger.error("代码02：" + e.getMessage(), e);
            msgMap.put("msgError", "数据库异常！");
            return JsonResults.fail(msgMap);
        } else {
            logger.error("代码99：" + e.getMessage(), e);
            msgMap.put("msgError", "服务器代码发生异常，联系管理员");
            return JsonResults.fail(msgMap);
        }
    }
}

package com.hdm.springbootdemo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述：统一业务异常处理类
 * @author Ay
 * @date   2017/12/3
 */
@ControllerAdvice(basePackages={"com.hdm.springbootdemo",})
public class GlobalBusinessExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    //如果返回的为json数据或其它对象，添加该注解
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(req.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);
        return errorInfo;
    }

}

package com.zchu.friendbook;

import com.zchu.friendbook.domain.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());
        return modelAndView;
    }

    @ExceptionHandler(value = HTTPException.class)
    @ResponseBody
    public ErrorInfo<String> httpErrorHandler(HttpServletRequest request, HTTPException exception) {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(exception.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(request.getRequestURL().toString());
        return r;
    }
}

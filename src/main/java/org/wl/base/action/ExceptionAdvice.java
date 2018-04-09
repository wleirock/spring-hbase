package org.wl.base.action;

import java.net.BindException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.wl.base.entity.MessageText;
import org.wl.base.entity.ResponseEntity;


/**
 * @className: ExceptionAdvice.java
 * @author: wanglei
 * @version: v1.0
 * @date: 2018年3月20日 下午1:26:12
 * @description: 异常处理
 */
@RestControllerAdvice(basePackages = "org.wl")
public class ExceptionAdvice {

    /**
     * 400-BindException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity handlerBindException(Exception e) {
        System.out.println("BindException");
        return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), MessageText.BAD_REQUEST.getText());
    }

    /**
     * 400-HttpMessageNotReadableException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleMsgNotReadException(HttpMessageNotReadableException e) {
        System.out.println("HttpMessageNotReadableException");
        return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), MessageText.BAD_REQUEST.getText());
    }

    /**
     * 400-MissingServletRequestParameterException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMisParamException(MissingServletRequestParameterException e) {
        System.out.println("MissingServletRequestParameterException");
        return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), MessageText.BAD_REQUEST.getText());
    }

    /**
     * 400-TypeMismatchException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity handleTypeMismatch(TypeMismatchException e) {
        System.out.println("TypeMismatchException");
        return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), MessageText.BAD_REQUEST.getText());
    }

    /**
     * 400-MethodArgumentNotValidException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleArgNotValid(MethodArgumentNotValidException e) {
        System.out.println("TypeMismatchException");
        return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), MessageText.BAD_REQUEST.getText());
    }

    /**
     * 400-MissingServletRequestPartException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity handleMisRequestPart(MissingServletRequestPartException e) {
        System.out.println("TypeMismatchException");
        return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), MessageText.BAD_REQUEST.getText());
    }

    /**
     * 415-HttpMediaTypeNotAcceptableException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity handleMediaNotAcceptException(HttpMediaTypeNotAcceptableException e) {
        System.out.println("HttpMediaTypeNotAcceptableException");
        return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(),
                MessageText.UNSUPPORTED_MEDIA_TYPE.getText());
    }

    /**
     * 415-HttpMediaTypeNotSupportedException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        System.out.println("HttpMediaTypeNotSupportedException");
        return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(),
                MessageText.UNSUPPORTED_MEDIA_TYPE.getText());
    }

    /**
     * 405-HttpRequestMethodNotSupportedException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        System.out.println("HttpRequestMethodNotSupportedException");
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED.toString(), MessageText.METHOD_NOT_ALLOWED.getText());
    }

    /**
     * 404-NoHandlerFoundException
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity handleMethodNotSupportedException(NoHandlerFoundException e) {
        System.out.println("NoHandlerFoundException");
        return new ResponseEntity(HttpStatus.NOT_FOUND.toString(), MessageText.NOT_FOUND.getText());
    }

    /**
     * 其它运行异常
     *
     * @param e
     * @return
     */
    /*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity internalException(Exception e) {
        System.out.println("Exception");
        System.out.println(e);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.toString(), MessageText.INTERNAL_SERVER_ERROR.getText());
    }*/
}

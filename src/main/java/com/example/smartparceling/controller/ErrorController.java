/*   Created by IntelliJ IDEA.
 *   Author: Devvrat Sharma (devrats)
 *   Date: 31-Jul-21
 *   Time: 11:43 AM
 *   File: ErrorController.java
 */

package com.example.smartparceling.controller;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({SizeLimitExceededException.class,MaxUploadSizeExceededException.class})
    public String sizeLimitExceedException(){
        return "SizeError";
    }
}
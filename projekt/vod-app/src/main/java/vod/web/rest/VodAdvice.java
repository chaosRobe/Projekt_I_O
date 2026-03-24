package vod.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;


@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class VodAdvice {

    private final BakeryValidator bakeryValidator;
    private final ProductValidator productValidator;

    @InitBinder("bakery")
    void initBinderForBakery(WebDataBinder binder) {binder.setValidator(bakeryValidator);}

    @InitBinder("productDTO")
    void initBinderForProduct(WebDataBinder binder) {binder.setValidator(productValidator);}

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException", e);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleException(Exception e) {
        log.error("Exception", e);
        return ResponseEntity.status(HttpStatus.LOOP_DETECTED).body(e.getMessage());
    }
}

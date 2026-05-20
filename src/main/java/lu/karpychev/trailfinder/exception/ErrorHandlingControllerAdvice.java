package lu.karpychev.trailfinder.exception;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lu.karpychev.trailfinder.dataio.CustomDateTimeSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
@Component
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<Object> handleNotFound(ObjectNotFoundException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                "The required object was not found.",
                ex.getMessage(),
                LocalDateTime.now(),
                new ArrayList<>()
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Data
    private static class ApiError {
        private HttpStatus status;
        private String reason;
        private String message;
        @JsonSerialize(using = CustomDateTimeSerializer.class)
        private LocalDateTime timestamp;
        private List<String> errors;

        private ApiError(HttpStatus status, String reason, String message, LocalDateTime timestamp, List<String> errors) {
            super();
            this.status = status;
            this.reason = reason;
            this.message = message;
            this.timestamp = timestamp;
            this.errors = errors;
        }
    }
}

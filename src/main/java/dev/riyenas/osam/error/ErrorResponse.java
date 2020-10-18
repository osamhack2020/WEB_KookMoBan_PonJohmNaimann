package dev.riyenas.osam.error;

import dev.riyenas.osam.error.exception.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
        this.code = code.getCode();
    }

    private ErrorResponse(final ErrorCode code, final String message) {
        this.message = message;
        this.status = code.getStatus();
        this.errors = new ArrayList<>();
        this.code = code.getCode();
    }

    public static ErrorResponse of(ErrorCode code, String message){
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse of(ErrorCode code, BindingResult bindingResult){
        return new ErrorResponse(code, FieldError.of(bindingResult));
    }

    public static ErrorResponse of(ErrorCode code) {
        return new ErrorResponse(code);
    }

    @Getter
    @NoArgsConstructor
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
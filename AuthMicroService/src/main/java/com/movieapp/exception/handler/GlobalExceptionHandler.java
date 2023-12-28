
    package com.movieapp.exception.handler;

    import com.movieapp.dto.response.FriendlyMessage;
    import com.movieapp.dto.response.InternalApiResponse;
    import com.movieapp.exception.enums.FriendlyMessageCodes;
    import com.movieapp.exception.exceptions.*;
    import com.movieapp.exception.utils.FriendlyMessageUtils;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseStatus;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.util.Collections;

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(AuthNotCreatedException.class)
        public InternalApiResponse<String> handleAuthNotCreatedException(AuthNotCreatedException exception){
            return InternalApiResponse.<String>builder()
                    .friendlyMessage(FriendlyMessage.builder()
                            .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                            .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                            .build())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .hasError(true)
                    .errorMessages(Collections.singletonList(exception.getMessage()))
                    .build();
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(AuthAlreadyDeletedException.class)
       public InternalApiResponse<String> handleAuthAlreadyDeletedException(AuthAlreadyDeletedException exception){
            return InternalApiResponse.<String>builder()
                    .friendlyMessage(FriendlyMessage.builder()
                            .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                            .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                            .build())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .hasError(true)
                    .errorMessages(Collections.singletonList(exception.getMessage()))
                    .build();

       }
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(AuthDeleteFailed.class)
        public InternalApiResponse<String> handleAuthDeleteFailed(AuthDeleteFailed exception){
            return InternalApiResponse.<String>builder()
                    .friendlyMessage(FriendlyMessage.builder()
                            .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                            .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                            .build())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .hasError(true)
                    .errorMessages(Collections.singletonList(exception.getMessage()))
                    .build();

        }
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(AuthNotFoundException.class)
        public InternalApiResponse<String> handleAuthNotFoundException(AuthNotFoundException exception){
            return InternalApiResponse.<String>builder()
                    .friendlyMessage(FriendlyMessage.builder()
                            .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                            .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                            .build())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .hasError(true)
                    .errorMessages(Collections.singletonList(exception.getMessage()))
                    .build();

        }
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(AuthUpdateFailed.class)
        public InternalApiResponse<String> handleAuthUpdateFailed(AuthUpdateFailed exception){
            return InternalApiResponse.<String>builder()
                    .friendlyMessage(FriendlyMessage.builder()
                            .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                            .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                            .build())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .hasError(true)
                    .errorMessages(Collections.singletonList(exception.getMessage()))
                    .build();

        }
    }


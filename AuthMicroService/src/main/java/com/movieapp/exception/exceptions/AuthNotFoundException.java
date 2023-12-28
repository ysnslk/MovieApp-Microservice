package com.movieapp.exception.exceptions;

import com.movieapp.exception.enums.IFriendlyMessageCode;
import com.movieapp.exception.utils.FriendlyMessageUtils;
import com.movieapp.repository.enums.ELanguage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class AuthNotFoundException extends RuntimeException{
    private final ELanguage language;
    private final IFriendlyMessageCode friendlyMessageCode;

    public AuthNotFoundException(ELanguage language, IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[AuthFoundException] -> message:{} developer message: {}",
                FriendlyMessageUtils.getFriendlyMessage(language, friendlyMessageCode), message);
    }
}
package com.movieapp.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode{

    OK(1000),
    ERROR(1001),
    SUCCESS(1002),

    AUTH_NOT_CREATED_EXCEPTION(1200),
    AUTH_SUCCESSFULLY_CREATED(1201),
    AUTH_NOT_FOUND_EXCEPTION(1202),
    AUTH_SUCCESSFULLY_UPDATED(1203),
    AUTH_UPDATE_FAILED(1204),
    AUTH_ALREADY_DELETED(1205),
    AUTH_SUCCESSFULLY_DELETED(1206),
    AUTH_DELETE_FAILED(1207),
    AUTHS_NOT_FOUND_EXCEPTION(1208);


    private final int value;

    FriendlyMessageCodes(int value){
        this.value=value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}

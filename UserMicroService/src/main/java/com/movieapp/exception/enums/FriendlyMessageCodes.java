package com.movieapp.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode{

    OK(1000),
    ERROR(1001),
    SUCCESS(1002),

    USER_NOT_CREATED_EXCEPTION(1200),
    USER_SUCCESSFULLY_CREATED(1201),
    USER_NOT_FOUND_EXCEPTION(1202),
    USER_SUCCESSFULLY_UPDATED(1203),
    USER_UPDATE_FAILED(1204),
    USER_ALREADY_DELETED(1205),
    USER_SUCCESSFULLY_DELETED(1206),
    USER_DELETE_FAILED(1207);


    private final int value;

    FriendlyMessageCodes(int value){
        this.value=value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}

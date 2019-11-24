package com.newshandler.view;

public interface AddingMainView {
    void isNameValid(boolean status);
    void isLinkValid(boolean status);
    void isAddingCorrect (boolean name , boolean link);
    void isLinkExist (boolean link);
}
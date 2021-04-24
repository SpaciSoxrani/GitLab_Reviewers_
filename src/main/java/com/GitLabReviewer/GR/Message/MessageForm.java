package com.GitLabReviewer.GR.Message;

public class MessageForm {
    private String message;

    public MessageForm(){

    }
    public MessageForm(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String returnMassage(){
//
//        if(message == null) {
//            return "This name doesn't exist";
//        }
         return message;
    }

}


package com.example.projetimmo.Models;

public class Chat {
    String sender;
    String receiver;
    String senderStringName;
    String receiverName;
    String message;

    public Chat() {
    }

    public String getSenderStringName() {
        return senderStringName;
    }

    public void setSenderStringName(String senderStringName) {
        this.senderStringName = senderStringName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Chat(String sender, String receiver, String senderStringName, String receiverName, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.senderStringName = senderStringName;
        this.receiverName = receiverName;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

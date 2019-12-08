package com.example.projetimmo.Models;

import com.google.gson.annotations.SerializedName;

public class Discussion {
    @SerializedName("id_sender")
    int id_sender;
    @SerializedName("id_receiver")
    int id_receiver;
    @SerializedName("message")
    String message;
    @SerializedName("timestamp")
    Long timestamp;

    public Discussion() {
    }

    public Discussion(int id_sender, int id_receiver, String message, Long timestamp) {
        this.id_sender = id_sender;
        this.id_receiver = id_receiver;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId_sender() {
        return id_sender;
    }

    public void setId_sender(int id_sender) {
        this.id_sender = id_sender;
    }

    public int getId_receiver() {
        return id_receiver;
    }

    public void setId_receiver(int id_receiver) {
        this.id_receiver = id_receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "id_sender=" + id_sender +
                ", id_receiver=" + id_receiver +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

package com.trans.todos.api.v1.services;

public interface EmailService {

    void sendEmail(String toAddress, String title, String content );
}

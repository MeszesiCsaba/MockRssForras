/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mockrssforras;

import java.util.List;
/**
 *
 * @author mcsab
 */
public final class FailingContentSource implements ContentSource {
    private final String message;

    public FailingContentSource(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("A hibaüzenet kötelező.");
        }
        this.message = message;
    }

    @Override
    public String name() {
        return "Failing source";
    }

    @Override
    public List<News> load() throws SourceException {
        throw new SourceException(message);
    }
}

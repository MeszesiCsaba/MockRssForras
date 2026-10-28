/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mockrssforras;

/**
 *
 * @author mcsab
 */
public record News(String source, String url, String title, String description) {
    public News {
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("A forrás neve kötelező.");
        }
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("Az URL kötelező.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("A cím kötelező.");
        }
        description = description == null ? "" : description;
    }
}

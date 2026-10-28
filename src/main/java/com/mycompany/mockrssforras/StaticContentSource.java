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
public final class StaticContentSource implements ContentSource {
    private final String name;
    private final List<News> news;

    public StaticContentSource(String name, List<News> news) {
        if (name == null || name.isBlank() || news == null) {
            throw new IllegalArgumentException("Név és hírlista kötelező.");
        }
        this.name = name;
        this.news = List.copyOf(news);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<News> load() {
        return List.copyOf(news);
    }
}

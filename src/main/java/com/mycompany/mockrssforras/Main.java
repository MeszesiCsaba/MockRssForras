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
public final class Main {
    private Main() {}

    public static void main(String[] args) {
        boolean offline = List.of(args).contains("--offline");

        ContentSource source = offline
                ? new MockContentSource(List.of(
                        new News("Demo", "https://example.test/1",
                                "Offline hír", "Minta hír internet nélkül.")))
                : new RssContentSource(
                        "Demo RSS",
                        "https://example.invalid/feed.xml",
                        new UrlHttpGateway());

        try {
            System.out.println(source.name());
            source.load().forEach(System.out::println);
        } catch (SourceException e) {
            System.err.println("Forráshiba: " + e.getMessage());
        }
    }
}

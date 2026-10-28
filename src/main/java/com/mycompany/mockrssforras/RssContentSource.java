/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mockrssforras;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author mcsab
 */
public final class RssContentSource implements ContentSource {
    private final String sourceName;
    private final String url;
    private final HttpGateway http;

    public RssContentSource(
            String sourceName, String url, HttpGateway http) {
        if (sourceName == null || sourceName.isBlank()
                || url == null || url.isBlank() || http == null) {
            throw new IllegalArgumentException(
                    "Forrásnév, URL és HTTP-kapcsolat kötelező.");
        }
        this.sourceName = sourceName;
        this.url = url;
        this.http = http;
    }

    @Override
    public String name() {
        return sourceName;
    }

    @Override
    public List<News> load() throws SourceException {
        HttpResult response = http.get(url);
        if (response.status() != 200) {
            throw new SourceException(
                    "RSS HTTP-hiba, státuszkód: " + response.status());
        }

        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            factory.setFeature(
                    "http://apache.org/xml/features/disallow-doctype-decl",
                    true);
            factory.setFeature(
                    "http://xml.org/sax/features/external-general-entities",
                    false);
            factory.setFeature(
                    "http://xml.org/sax/features/external-parameter-entities",
                    false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

            var builder = factory.newDocumentBuilder();
            var document = builder.parse(new ByteArrayInputStream(
                    response.body().getBytes(StandardCharsets.UTF_8)));

            NodeList items = document.getElementsByTagName("item");
            List<News> result = new ArrayList<>();

            for (int i = 0; i < items.getLength(); i++) {
                Element item = (Element) items.item(i);
                String title = text(item, "title");
                String link = text(item, "link");

                if (!title.isBlank() && !link.isBlank()) {
                    result.add(new News(
                            sourceName, link, title, text(item, "description")));
                }
            }
            return List.copyOf(result);
        } catch (Exception e) {
            throw new SourceException("Az RSS XML feldolgozása nem sikerült.", e);
        }
    }

    private static String text(Element item, String tag) {
        NodeList nodes = item.getElementsByTagName(tag);
        return nodes.getLength() == 0
                ? ""
                : nodes.item(0).getTextContent().trim();
    }
}

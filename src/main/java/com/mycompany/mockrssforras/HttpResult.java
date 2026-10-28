/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mockrssforras;

/**
 *
 * @author mcsab
 */
public record HttpResult(int status, String body) {
    public HttpResult {
        body = body == null ? "" : body;
    }
}

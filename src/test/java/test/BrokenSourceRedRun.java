/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.List;
import com.mycompany.mockrssforras.*;
/**
 *
 * @author mcsab
 */
class BrokenSourceRedRun extends ContentSourceContractTest {
    @Override
    protected ContentSource createSource() {
        return new ContentSource() {
            @Override
            public String name() {
                return " ";
            }

            @Override
            public List<News> load() {
                return null;
            }
        };
    }
}

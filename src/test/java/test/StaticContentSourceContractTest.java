/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import com.mycompany.mockrssforras.*;
import java.util.List;
/**
 *
 * @author mcsab
 */
class StaticContentSourceContractTest extends ContentSourceContractTest {
    @Override
    protected ContentSource createSource() {
        return new StaticContentSource("Fixture", List.of(
                new News("Fixture", "https://example.test/2", "Statikus hír", "")));
    }
}

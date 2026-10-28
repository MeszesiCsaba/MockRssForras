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

class MockContentSourceContractTest extends ContentSourceContractTest {
    @Override
    protected ContentSource createSource() {
        return new MockContentSource(List.of(
                new News("Mock", "https://example.test/1", "Minta", "Szöveg")));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import com.mycompany.mockrssforras.*;
/**
 *
 * @author mcsab
 */
class RssContentSourceContractTest extends ContentSourceContractTest {
    private static final String XML = """
            <rss><channel><item>
              <title>Próbahír</title>
              <link>https://example.test/rss/1</link>
              <description>Rövid leírás</description>
            </item></channel></rss>
            """;

    @Override
    protected ContentSource createSource() {
        HttpGateway fakeHttp =
                address -> new HttpResult(200, XML);

        return new RssContentSource(
                "Teszt RSS", "https://example.test/feed.xml", fakeHttp);
    }
}

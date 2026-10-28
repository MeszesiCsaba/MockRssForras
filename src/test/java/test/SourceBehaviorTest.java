/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import org.junit.jupiter.api.Test;
import com.mycompany.mockrssforras.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author mcsab
 */
class SourceBehaviorTest {
    @Test
    void failingSourceAForrasKiveteltDob() {
        SourceException error = assertThrows(
                SourceException.class,
                () -> new FailingContentSource("teszthiba").load());

        assertEquals("teszthiba", error.getMessage());
    }

    @Test
    void rssKezeliAzOtszazasHibat() {
        RssContentSource source = new RssContentSource(
                "RSS", "https://example.test/feed",
                address -> new HttpResult(500, ""));

        SourceException error =
                assertThrows(SourceException.class, source::load);

        assertTrue(error.getMessage().contains("500"));
    }

    @Test
    void rssKezeliATimeoutKodot() {
        RssContentSource source = new RssContentSource(
                "RSS", "https://example.test/feed",
                address -> new HttpResult(-2, ""));

        SourceException error =
                assertThrows(SourceException.class, source::load);

        assertTrue(error.getMessage().contains("-2"));
    }

    @Test
    void rssXmlTesztelhetoHalozatNelkul() throws Exception {
        String xml = """
                <rss><channel><item>
                  <title>Első</title>
                  <link>https://example.test/1</link>
                </item></channel></rss>
                """;
        RssContentSource source = new RssContentSource(
                "Teszt", "https://example.test/feed",
                address -> new HttpResult(200, xml));

        assertEquals("Első", source.load().get(0).title());
    }
}

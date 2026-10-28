# Mock RSS-forrás és szerződéstesztek

Java 17-es Maven-projekt három ContentSource-megvalósítással:
MockContentSource, RssContentSource és StaticContentSource.
A FailingContentSource a hibás forrás viselkedését demonstrálja.

Az RSS-forrás a HttpGateway interfészen keresztül kapja a HTTP-választ.
A tesztek előre megadott HttpResult értékeket használnak, így nem
igényelnek hálózatot. A Main --offline kapcsolóval Mock forrást választ.

## Futtatás

    mvn test
    mvn package
    java -cp target/classes com.mycompany.mockrss.Main --offline

## Szerződéstesztek

A ContentSourceContractTest ellenőrzi a nem null listát, a nem üres nevet,
a hírek source és URL mezőit, az érvényes betöltés kivételmentességét,
a korábbi lista változatlanságát és a lista módosíthatatlanságát.
A Mock, RSS és Static forrás mind örökli ezt a tesztet.

A BrokenSourceRedRun szándékosan hibás implementáció. Külön futtatása
piros teszteredményt ad; a normál tesztfuttatás kihagyja.

## AI-használat

AI-t használtam a kód és a tesztek előkészítéséhez.

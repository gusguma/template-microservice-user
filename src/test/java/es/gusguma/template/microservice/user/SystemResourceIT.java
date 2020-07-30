package es.gusguma.template.microservice.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
class SystemResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadBadge() {
        byte[] badge =
                this.webTestClient
                        .get().uri(SystemResource.SYSTEM + SystemResource.VERSION_BADGE)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(byte[].class)
                        .returnResult().getResponseBody();
        assertNotNull(badge);
        assertEquals("<svg", new String(badge).substring(0, 4));
    }

    @Test
    void testReadInfo() {
        String body = this.webTestClient
                .get().uri(SystemResource.SYSTEM + SystemResource.VERSION)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();
        assertEquals(3, body.split("::").length);
    }
}

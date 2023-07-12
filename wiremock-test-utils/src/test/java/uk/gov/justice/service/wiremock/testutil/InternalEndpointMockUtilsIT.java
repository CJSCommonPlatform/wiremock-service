package uk.gov.justice.service.wiremock.testutil;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static org.apache.cxf.jaxrs.client.WebClient.create;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.gov.justice.service.wiremock.testutil.InternalEndpointMockUtils.stubPingFor;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link InternalEndpointMockUtils} class.
 */

@WireMockTest(httpPort = 23263)
public class InternalEndpointMockUtilsIT {

    private static final String SERVICE_NAME = "test-command-api";

    @BeforeEach
    public void setUp() {
        reset();
        stubPingFor(SERVICE_NAME);
    }

    @AfterEach
    public void tearDown() {
        reset();
    }

    private static final String PONG = "pong";
    private static final String BASE_URL = "http://localhost:23263";

    @Test
    public void shouldStubPingForGetRequest() {
        Response response = buildWebClient(SERVICE_NAME).get();
        verifyStatusEquals(response, OK);
        assertThat(response.readEntity(String.class), equalTo(PONG));
    }

    @Test
    public void shouldStubPingForHeadRequest() {
        Response response = buildWebClient(SERVICE_NAME).head();
        verifyStatusEquals(response, OK);
        assertThat(response.readEntity(String.class), equalTo(""));
    }


    @Test
    public void shouldOnlyStubForGivingService() {
        WebClient client = buildWebClient("aTestService");

        verifyStatusEquals(client.get(), NOT_FOUND);
        verifyStatusEquals(client.head(), NOT_FOUND);
    }

    @Test
    public void shouldResetAllRequests() {

        reset();

        WebClient client = buildWebClient(SERVICE_NAME);

        verifyStatusEquals(client.get(), NOT_FOUND);
        verifyStatusEquals(client.head(), NOT_FOUND);
    }

    private WebClient buildWebClient(final String serviceName) {
        return create(BASE_URL).path("/" + serviceName + "/internal/metrics/ping");
    }

    private void verifyStatusEquals(final Response response, final Status status) {
        assertThat(response.getStatus(), is(status.getStatusCode()));
    }
}

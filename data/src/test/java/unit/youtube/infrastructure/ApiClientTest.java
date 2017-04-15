/*
 *   Copyright (C) 2015 Karumi.
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package unit.youtube.infrastructure;

import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;

public class ApiClientTest {

    private static final int OK_CODE = 200;

    private MockWebServer server;

    @Before public void setUp() throws Exception {
        this.server = new MockWebServer();
        this.server.start();
    }

    @After public void tearDown() throws Exception {
        server.shutdown();
    }

    protected void enqueueMockResponse() throws IOException {
        enqueueMockResponse(OK_CODE);
    }

    protected void enqueueMockResponse(int code) throws IOException {
        enqueueMockResponse(code, "{}");
    }

    protected void enqueueMockResponse(int code, String response) throws IOException {
        final MockResponse mockResponse = new MockResponse();
        mockResponse.setResponseCode(code);
        mockResponse.setBody(response);
        server.enqueue(mockResponse);
    }

    protected void enqueueMockResponse(String fileName) throws IOException {
        String body = FileExtensions.getStringFromFile(getClass(), fileName);
        MockResponse response = new MockResponse();
        response.setResponseCode(OK_CODE);
        response.setBody(body);
        server.enqueue(response);
    }

    protected void assertRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = server.takeRequest();
        assertEquals(url, request.getPath());
    }

    protected void assertRequestSentToContains(String... paths) throws InterruptedException {
        RecordedRequest request = server.takeRequest();

        for (String path : paths) {
            Assert.assertThat(request.getPath(), StringContains.containsString(path));
        }
    }

    public String getBaseEndpoint() {
        return server.url("/").toString();
    }
}
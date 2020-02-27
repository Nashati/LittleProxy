package org.littleshoot.proxy.utils;

import io.netty.handler.codec.http.HttpMessage;
import org.littleshoot.proxy.impl.ProxyUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HttpUtils {

    private HttpUtils() {
    }

    public static Optional<String> getHeaderValue(HttpMessage msg, String header) {
        return Optional.ofNullable(msg.headers().get(header));
    }

    public static List<String> getHeaderValues(HttpMessage msg, String header) {
        return getHeaderValue(msg, header).map(v -> v.contains(",") ? ProxyUtils.splitCommaSeparatedHeaderValues(v) : Collections.singletonList(v)).orElse(Collections.emptyList());

    }

}

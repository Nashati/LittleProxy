package org.littleshoot.proxy.utils;

import io.netty.handler.codec.http.*;

public class WebSocketUtils {

    private static final String WEBSOCKET_UPGRADE_VALUE = "websocket";

    private WebSocketUtils() {
    }

    public static boolean isWebSocketRequest(HttpRequest request) {
        return isUpgradeConnection(request) && isWebSocketUpgrade(request);
    }

    public static boolean isWebSocketResponse(HttpResponse response) {
        return isUpgradeConnection(response) && isWebSocketUpgrade(response) && isSwitchingProtocolResponse(response);
    }

    private static boolean isSwitchingProtocolResponse(HttpResponse response) {
        return response.status().equals(HttpResponseStatus.SWITCHING_PROTOCOLS);
    }

    private static boolean isWebSocketUpgrade(HttpMessage msg) {
        return HttpUtils.getHeaderValue(msg, HttpHeaderNames.UPGRADE.toString()).map(v -> v.equalsIgnoreCase(WEBSOCKET_UPGRADE_VALUE)).orElse(false);
    }

    private static boolean isUpgradeConnection(HttpMessage msg) {
        return HttpUtils.getHeaderValues(msg, HttpHeaderNames.CONNECTION.toString()).stream().anyMatch(s -> HttpHeaderNames.UPGRADE.toString().equalsIgnoreCase(s));
    }


}

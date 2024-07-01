package com.project.snackcode.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //  HttpServlet class wrapping
        ContentCachingRequestWrapper cachedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachedResponse = new ContentCachingResponseWrapper(response);

        String queryStr     = cachedRequest.getQueryString();
        String reqUri       = queryStr == null ? cachedRequest.getRequestURI() : cachedRequest.getRequestURI() + "?" + queryStr;
        String reqAddr      = cachedRequest.getRemoteAddr();
        String httpMtd      = cachedRequest.getMethod();
        String contentType  = cachedRequest.getContentType();
        String params       = getReqPrmStr(cachedRequest);
        String reqDataStr   = "";
        String resDataStr   = "";

        log.debug("[{}]      method : [{}],   URI : [{}],   contentType : [{}],   params : [{}]",
                    reqAddr,  httpMtd,         reqUri,       contentType,          params );

        // do Filter
        filterChain.doFilter(cachedRequest, cachedResponse);

        reqDataStr = new String(cachedRequest.getContentAsByteArray(), StandardCharsets.UTF_8);

        resDataStr = new String(cachedResponse.getContentAsByteArray(), StandardCharsets.UTF_8);

        // request body data
        log.debug("REQUEST DATA: " + reqDataStr);

        cachedResponse.copyBodyToResponse();
    }

    protected String getReqPrmStr(HttpServletRequest request){

        String result = "";

        for(Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()){
            String  entryStr = entry.getKey();
            entryStr += "=";
            entryStr += Arrays.toString(entry.getValue());
            entryStr += ", ";

            result += entryStr;
        }

        return result;
    }
}

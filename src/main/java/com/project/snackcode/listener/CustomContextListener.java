package com.project.snackcode.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class CustomContextListener implements ServletContextListener {

    // 서버 생성 시
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("-----------    context Initialized    -----------");
        ServletContextListener.super.contextInitialized(sce);
    }

    // 서버 종료 시
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("-----------    context Destroyed    -----------");
        ServletContextListener.super.contextDestroyed(sce);
    }
}

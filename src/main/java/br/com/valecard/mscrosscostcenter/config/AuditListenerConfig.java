package br.com.valecard.mscrosscostcenter.config;

import com.valecard.listeners.AuditListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AuditListener.class)
public class AuditListenerConfig {

}
package br.com.valecard.mscostcenter.config;


import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@Configuration
public class AuditConfig {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostConstruct
    public void registerListeners() {
        var sessionFactory = emf.unwrap( SessionFactoryImplementor.class );
        var registry = sessionFactory.getServiceRegistry().getService( EventListenerRegistry.class );
        //registry.appendListeners(EventType.POST_INSERT, new MeuListener());
    }
}

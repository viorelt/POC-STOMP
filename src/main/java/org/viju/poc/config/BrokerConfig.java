package org.viju.poc.config;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.hooks.SpringContextHook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class BrokerConfig {
    @Bean( initMethod = "start", destroyMethod = "stop" )
    public BrokerService broker() throws Exception {
        final BrokerService broker = new BrokerService();
        broker.addConnector( "ws://localhost:61614" );
        broker.setPersistent( false );
        broker.setShutdownHooks( Collections.< Runnable >singletonList( new SpringContextHook() ) );

        final ActiveMQTopic topic = new ActiveMQTopic( "jms.topic.test" );
        broker.setDestinations( new ActiveMQDestination[] { topic }  );

        final ManagementContext managementContext = new ManagementContext();
        managementContext.setCreateConnector( true );
        broker.setManagementContext( managementContext );

        return broker;
    }

}

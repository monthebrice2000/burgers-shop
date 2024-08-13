package com.ecommerce.burgers_shop.integration_flow;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

@Configuration
public class FileWriterIntegrationConfig {

    // @Bean
    // public MessageChannel textInChannel() {
    //     return new DirectChannel();
    // }

    // @Bean
    // public MessageChannel fileWriterChannel() {
    //     return new DirectChannel();
    // }

    @Bean
    @Transformer(inputChannel = "textInChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> upperCaseTransformer() {
        return text -> text.toUpperCase();
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("/tmp/sia6/files"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }

    // @Bean
    // public IntegrationFlow fileWriterFlow() {
    //     return IntegrationFlow
    //             .from(MessageChannels.direct("textInChannel"))
    //             .<String, String>transform(t -> t.toUpperCase())
    //             .handle(Files
    //                     .outboundAdapter(new File("/tmp/sia6/files"))
    //                     .fileExistsMode(FileExistsMode.APPEND)
    //                     .appendNewLine(true))
    //             .get();
    // }

    // @Bean
    // public IntegrationFlow fileWriterFlow() {
    //     return IntegrationFlow
    //             .from(MessageChannels.direct("textInChannel"))
    //             .<String, String>transform(t -> t.toUpperCase())
    //             .channel(MessageChannels.direct("FileWriterChannel"))
    //             .handle(Files
    //                     .outboundAdapter(new File("/tmp/sia6/files"))
    //                     .fileExistsMode(FileExistsMode.APPEND)
    //                     .appendNewLine(true))
    //             .get();
    // }

}

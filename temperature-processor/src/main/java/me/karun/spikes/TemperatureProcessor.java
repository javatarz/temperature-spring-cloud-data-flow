package me.karun.spikes;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Processor.class)
public class TemperatureProcessor {

  @Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
  public int convertToCelsius(String payload) {
    return (Integer.parseInt(payload) - 30) / 2;
  }
}

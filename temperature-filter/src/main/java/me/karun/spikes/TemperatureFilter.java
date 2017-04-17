package me.karun.spikes;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Filter;

@EnableBinding(Processor.class)
public class TemperatureFilter {

  @Filter(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
  public boolean convertToCelsius(int payload) {
    return payload > 35;
  }
}

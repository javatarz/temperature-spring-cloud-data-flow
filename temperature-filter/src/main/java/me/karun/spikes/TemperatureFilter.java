package me.karun.spikes;

import me.karun.spikes.model.FilterConfig;
import me.karun.spikes.model.TemperaturePayload;
import me.karun.spikes.model.Unit;
import me.karun.spikes.repository.FilterConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.Filter;

import javax.annotation.PostConstruct;

@EnableBinding(Processor.class)
public class TemperatureFilter {

  @Autowired
  private FilterConfigRepository repository;

  @PostConstruct
  public void init() {
    repository.save(celsius());
    repository.save(fahrenheit());
  }

  private FilterConfig celsius() {
    final FilterConfig c = new FilterConfig();
    c.setUnit(Unit.CELSIUS);
    c.setFilterValue(35);
    return c;
  }

  private FilterConfig fahrenheit() {
    final FilterConfig c = new FilterConfig();
    c.setUnit(Unit.FAHRENHEIT);
    c.setFilterValue(30);
    return c;
  }

  @Filter(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
  public boolean filterValues(final TemperaturePayload payload) {
    System.out.println("payload = " + payload);

    final FilterConfig filterConfig = repository.findByUnit(payload.getUnit().name());
    System.out.println("filterConfig = " + filterConfig);

    return payload.getValue() > filterConfig.getFilterValue();
  }
}

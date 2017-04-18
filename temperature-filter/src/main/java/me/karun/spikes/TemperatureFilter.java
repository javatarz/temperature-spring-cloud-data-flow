package me.karun.spikes;

import me.karun.spikes.model.FilterConfig;
import me.karun.spikes.model.TemperaturePayload;
import me.karun.spikes.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.integration.annotation.Filter;

import javax.annotation.PostConstruct;

@EnableBinding(Processor.class)
public class TemperatureFilter {

  @Autowired
  private MongoOperations mongoOperation;

  @PostConstruct
  public void init() {
    mongoOperation.save(celsius());
    mongoOperation.save(fahrenheit());
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
  public boolean filterValues(String payloadString) {
    System.out.println("payloadString = " + payloadString);

    final TemperaturePayload payload = TemperaturePayload.parse(payloadString);

    final FilterConfig filterConfig = fetchById(payload.getUnit());
    System.out.println("filterConfig = " + filterConfig);

    return payload.getValue() > filterConfig.getFilterValue();
  }

  private FilterConfig fetchById(final Unit unit) {
    return mongoOperation.findById(unit, FilterConfig.class);
  }
}

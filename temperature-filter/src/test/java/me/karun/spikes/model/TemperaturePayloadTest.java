package me.karun.spikes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TemperaturePayloadTest {
  @Test
  void shouldParseInput() {
    final TemperaturePayload parse = TemperaturePayload.parse("2017-01-15|12|CELSIUS");

    assertThat(parse.getDate())
      .isEqualTo(LocalDate.of(2017, 1, 15));
  }

}
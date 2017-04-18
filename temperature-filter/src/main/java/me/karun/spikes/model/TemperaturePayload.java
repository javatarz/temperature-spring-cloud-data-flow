package me.karun.spikes.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class TemperaturePayload {
  private final LocalDate date;
  private final int value;
  private final Unit unit;

  public static TemperaturePayload parse(final String line) {
    final String[] split = line.split("\\|");

    final LocalDate date = LocalDate.parse(split[0]);
    final Integer value = Integer.valueOf(split[1]);
    final Unit unit = Unit.valueOf(split[2].toUpperCase());

    return new TemperaturePayload(date, value, unit);
  }
}

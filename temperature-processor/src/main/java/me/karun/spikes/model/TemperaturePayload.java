package me.karun.spikes.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class TemperaturePayload {
  private final String date;
  private final int value;
  private final String unit;
}

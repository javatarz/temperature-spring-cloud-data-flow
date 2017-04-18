package me.karun.spikes.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "filters")
@Data
public class FilterConfig {

  @Id
  private Unit unit;
  @Field
  private int filterValue;
}
package me.karun.spikes.repository;

import me.karun.spikes.model.FilterConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FilterConfigRepository extends MongoRepository<FilterConfig, String> {
  FilterConfig findByUnit(final String unit);
}

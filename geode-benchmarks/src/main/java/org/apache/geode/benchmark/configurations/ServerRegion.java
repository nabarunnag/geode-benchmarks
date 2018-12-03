package org.apache.geode.benchmark.configurations;

import java.util.HashMap;
import java.util.Map;

import org.apache.geode.benchmark.tasks.CreatePartitionedRegion;
import org.apache.geode.cache.RegionShortcut;
import org.apache.geode.perftest.Task;

public class ServerRegion {
  Task regionCreationTask;
  Task indexCreationTask;

  public ServerRegion() {
  }

  public ServerRegion setRegionCreationTask(Task regionCreationTask){
    this.regionCreationTask = regionCreationTask;
    return this;
  }

  public Task getRegionCreationTask() {
    return regionCreationTask;
  }

  public Task getIndexCreationTask() {
    return indexCreationTask;
  }

  public void setIndexCreationTask(Task indexCreationTask){
    this.indexCreationTask = indexCreationTask;
  }
}

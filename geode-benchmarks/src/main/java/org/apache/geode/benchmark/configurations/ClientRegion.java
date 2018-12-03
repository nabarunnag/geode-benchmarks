package org.apache.geode.benchmark.configurations;

import org.apache.geode.perftest.Task;

public class ClientRegion {
  Task clientRegionCreationTask;

  public Task getClientRegionCreationTask() {
    return clientRegionCreationTask;
  }

  public ClientRegion setClientRegionCreationTask(Task clientRegionCreationTask){
    this.clientRegionCreationTask = clientRegionCreationTask;
    return this;
  }
}

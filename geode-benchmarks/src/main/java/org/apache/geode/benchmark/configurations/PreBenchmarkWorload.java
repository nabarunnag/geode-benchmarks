package org.apache.geode.benchmark.configurations;

import org.apache.geode.perftest.Task;

public class PreBenchmarkWorload {
  Task preBenchmarkTask;

  public PreBenchmarkWorload setWorkLoad(Task preBenchmarkTask){
    this.preBenchmarkTask = preBenchmarkTask;
    return this;
  }

  public Task getPreBenchmarkTask() {
    return preBenchmarkTask;
  }
}

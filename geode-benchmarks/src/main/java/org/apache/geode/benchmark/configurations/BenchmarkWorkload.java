package org.apache.geode.benchmark.configurations;

import static org.apache.geode.benchmark.parameters.BenchmarkParameters.BENCHMARK_DURATION;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.WARM_UP_TIME;

import org.yardstickframework.BenchmarkDriver;

import org.apache.geode.perftest.Task;

public class BenchmarkWorkload {
  BenchmarkDriver benchmarkTask;
  int warmupDuaration = WARM_UP_TIME;
  int benchmarkDuration = BENCHMARK_DURATION;

  public BenchmarkWorkload setBenchmarkTask(BenchmarkDriver benchmarkTask){
    this.benchmarkTask = benchmarkTask;
    return this;
  }

  public BenchmarkDriver getBenchmarkTask() {
    return benchmarkTask;
  }

  public int getWarmupDuaration() {
    return warmupDuaration;
  }

  public int getBenchmarkDuration() {
    return benchmarkDuration;
  }

  public BenchmarkWorkload setWarmupDuration(int warmupDuration){
    this.warmupDuaration = warmupDuration;
    return this;
  }

  public BenchmarkWorkload setBenchmarkDuration(int benchmarkDuration){
    this.benchmarkDuration = benchmarkDuration;
    return this;
  }


}


package org.apache.geode.benchmark.tests;

import org.junit.Test;

import org.apache.geode.benchmark.configurations.BenchmarkWorkload;
import org.apache.geode.benchmark.configurations.ClientRegion;
import org.apache.geode.benchmark.configurations.ClientServerTopology;
import org.apache.geode.benchmark.configurations.ConfigurationFactory;
import org.apache.geode.benchmark.configurations.PreBenchmarkWorload;
import org.apache.geode.benchmark.configurations.ServerRegion;
import org.apache.geode.perftest.PerformanceTest;
import org.apache.geode.perftest.TestConfig;
import org.apache.geode.perftest.TestRunners;

public class PartitionedGetBenchmark implements PerformanceTest {

  @Test
  public void run() throws Exception {
    TestRunners.defaultRunner().runTest(this);
  }


  @Override
  public TestConfig configure() {
    ClientServerTopology clientServerTopology = new ClientServerTopology();
    ServerRegion serverRegion = new ServerRegion();
    ClientRegion clientRegion = new ClientRegion();
    PreBenchmarkWorload preBenchmarkWorload = new PreBenchmarkWorload();
    BenchmarkWorkload benchmarkWorkload = new BenchmarkWorkload();
    return new ConfigurationFactory()
        .setClientRegion(clientRegion)
        .setServerRegion(serverRegion)
        .setBenchmarkWorkload(benchmarkWorkload)
        .setPreBenchmarkWorload(preBenchmarkWorload)
        .create();
  }
}

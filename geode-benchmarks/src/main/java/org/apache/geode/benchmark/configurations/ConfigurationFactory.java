package org.apache.geode.benchmark.configurations;

import static org.apache.geode.benchmark.parameters.BenchmarkParameters.Roles.CLIENT;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.Roles.LOCATOR;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.Roles.SERVER;

import org.apache.geode.benchmark.tasks.StartClient;
import org.apache.geode.benchmark.tasks.StartLocator;
import org.apache.geode.benchmark.tasks.StartServer;
import org.apache.geode.perftest.TestConfig;

public class ConfigurationFactory {

  ClientServerTopology clientServerTopology;
  ClientRegion clientRegion;
  ServerRegion serverRegion;
  PreBenchmarkWorload preBenchmarkWorload;
  BenchmarkWorkload benchmarkWorkload;

  public ConfigurationFactory() {
  }

  public ConfigurationFactory setClientServerTopology(
      ClientServerTopology clientServerTopology) {
    this.clientServerTopology = clientServerTopology;
    return this;
  }

  public ConfigurationFactory setClientRegion(ClientRegion clientRegion) {
    this.clientRegion = clientRegion;
    return this;
  }

  public ConfigurationFactory setServerRegion(ServerRegion serverRegion) {
    this.serverRegion = serverRegion;
    return this;
  }

  public ConfigurationFactory setPreBenchmarkWorload(
      PreBenchmarkWorload preBenchmarkWorload) {
    this.preBenchmarkWorload = preBenchmarkWorload;
    return this;
  }

  public ConfigurationFactory setBenchmarkWorkload(
      BenchmarkWorkload benchmarkWorkload) {
    this.benchmarkWorkload = benchmarkWorkload;
    return this;
  }

  public TestConfig create(){
    int locatorPort = clientServerTopology.getLocatorPort();
    checkForMissingConfiguration();
    TestConfig testConfig = new TestConfig();
    testConfig.name(this.getClass().getCanonicalName());
    testConfig.warmupSeconds(benchmarkWorkload.getWarmupDuaration());
    testConfig.durationSeconds(benchmarkWorkload.getBenchmarkDuration());
    testConfig.role(CLIENT,clientServerTopology.getNumberOfClient());
    testConfig.role(SERVER,clientServerTopology.getNumberOfServers());
    testConfig.role(LOCATOR,clientServerTopology.getNumberOfLocators());
    testConfig.jvmArgs(CLIENT,clientServerTopology.getClientJVMArgs());
    testConfig.jvmArgs(SERVER,clientServerTopology.getServerJVMArgs());
    testConfig.before(new StartLocator(locatorPort), LOCATOR);
    testConfig.before(new StartServer(locatorPort), SERVER);
    testConfig.before(serverRegion.getRegionCreationTask(),SERVER);
    if(serverRegion.getIndexCreationTask() != null) {
      testConfig.before(serverRegion.getIndexCreationTask(), SERVER);
    }
    testConfig.before(new StartClient(locatorPort), CLIENT);
    testConfig.before(clientRegion.getClientRegionCreationTask(), CLIENT);
    if(preBenchmarkWorload != null){
      testConfig.before(preBenchmarkWorload.getPreBenchmarkTask());
    }
    testConfig.workload(benchmarkWorkload.getBenchmarkTask(), CLIENT);
    return testConfig;
  }

  private void checkForMissingConfiguration() {
     if(clientServerTopology == null){
       throw new IllegalStateException("No client-server topology selected for the benchmark");
     }

    if(clientRegion == null){
      throw new IllegalStateException("No client region configuration selected for the benchmark");
    }

    if(serverRegion == null){
      throw new IllegalStateException("No server region topology selected for the benchmark");
    }

    if(benchmarkWorkload == null){
      throw new IllegalStateException("No workload selected for the benchmark");
    }
  }
}

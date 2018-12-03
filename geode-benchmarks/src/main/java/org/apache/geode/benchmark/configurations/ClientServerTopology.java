package org.apache.geode.benchmark.configurations;

import static org.apache.geode.benchmark.parameters.BenchmarkParameters.KEY_RANGE;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.LOCATOR_PORT;
import static org.apache.geode.benchmark.parameters.JVMParameters.JVM_ARGS;

public class ClientServerTopology {
  long keyRange = KEY_RANGE;
  private int numberOfServers = 1;
  private int numberOfClient = 1;
  private int numberOfLocators = 1;
  private int locatorPort = LOCATOR_PORT;
  private String[] locatorJVMArgs = JVM_ARGS;
  private String[] serverJVMArgs = JVM_ARGS;
  private String[] clientJVMArgs = JVM_ARGS;

  public ClientServerTopology() {

  }

  public ClientServerTopology setLocatorPort(int locatorPort) {
    this.locatorPort = locatorPort;
    return this;
  }

  public int getLocatorPort() {
    return locatorPort;
  }

  public long getKeyRange() {
    return keyRange;
  }

  public ClientServerTopology setKeyRange(long keyRange) {
    this.keyRange = keyRange;
    return this;
  }

  public int getNumberOfServers() {
    return numberOfServers;
  }

  public ClientServerTopology setNumberOfServers(int numberOfServers) {
    this.numberOfServers = numberOfServers;
    return this;
  }

  public int getNumberOfClient() {
    return numberOfClient;
  }

  public ClientServerTopology setNumberOfClient(int numberOfClient) {
    this.numberOfClient = numberOfClient;
    return this;
  }

  public int getNumberOfLocators() {
    return numberOfLocators;
  }

  public ClientServerTopology setNumberOfLocators(int numberOfLocators) {
    this.numberOfLocators = numberOfLocators;
    return this;
  }

  public String[] getLocatorJVMArgs() {
    return locatorJVMArgs;
  }

  public ClientServerTopology setLocatorJVMArgs(String[] locatorJVMArgs) {
    this.locatorJVMArgs = locatorJVMArgs;
    return this;
  }

  public String[] getServerJVMArgs() {
    return serverJVMArgs;
  }

  public ClientServerTopology setServerJVMArgs(String[] serverJVMArgs) {
    this.serverJVMArgs = serverJVMArgs;
    return this;
  }

  public String[] getClientJVMArgs() {
    return clientJVMArgs;
  }

  public ClientServerTopology setClientJVMArgs(String[] clientJVMArgs) {
    this.clientJVMArgs = clientJVMArgs;
    return this;
  }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.geode.benchmark.tests;

import static org.apache.geode.benchmark.parameters.BenchmarkParameters.BENCHMARK_DURATION;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.KEY_RANGE;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.LOCATOR_PORT;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.Roles.CLIENT;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.Roles.LOCATOR;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.Roles.SERVER;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.WARM_UP_TIME;
import static org.apache.geode.benchmark.parameters.JVMParameters.JVM_ARGS;

import org.junit.Test;

import org.apache.geode.benchmark.tasks.CreateClientProxyRegion;
import org.apache.geode.benchmark.tasks.CreatePartitionedRegion;
import org.apache.geode.benchmark.tasks.GetTask;
import org.apache.geode.benchmark.tasks.PrePopulateRegion;
import org.apache.geode.benchmark.tasks.StartClient;
import org.apache.geode.benchmark.tasks.StartLocator;
import org.apache.geode.benchmark.tasks.StartServer;
import org.apache.geode.perftest.PerformanceTest;
import org.apache.geode.perftest.TestConfig;
import org.apache.geode.perftest.TestRunners;

public class PartitionedGetBenchmark implements PerformanceTest {

  long keyRange = KEY_RANGE;

  public PartitionedGetBenchmark() {}

  PartitionedGetBenchmark(long keyRange) {
    this.keyRange = keyRange;
  }

  @Test
  public void run() throws Exception {
    TestRunners.defaultRunner().runTest(this::configure);
  }

  @Override
  public TestConfig configure() {

    int locatorPort = LOCATOR_PORT;
    TestConfig config = new TestConfig();

    // Configure the test settings.
    config.name(this.getClass().getCanonicalName());
    config.warmupSeconds(WARM_UP_TIME);
    config.durationSeconds(BENCHMARK_DURATION);

    // Setup roles and their total number that will be involved in the benchmark.
    config.role(LOCATOR, 1);
    config.role(SERVER, 2);
    config.role(CLIENT, 1);

    // Set up the JVM ags for the VMs
    config.jvmArgs(SERVER, JVM_ARGS);
    config.jvmArgs(CLIENT, JVM_ARGS);
    config.jvmArgs(LOCATOR, JVM_ARGS);

    // Pre benchmark configurations
    config.before(new StartLocator(locatorPort), LOCATOR);
    config.before(new StartServer(locatorPort), SERVER);
    config.before(new CreatePartitionedRegion(), SERVER);
    config.before(new StartClient(locatorPort), CLIENT);
    config.before(new CreateClientProxyRegion(), CLIENT);
    config.before(new PrePopulateRegion(keyRange), SERVER);

    // Set up the workload to be benchmarked.
    config.workload(new GetTask(keyRange), CLIENT);
    return config;
  }
}

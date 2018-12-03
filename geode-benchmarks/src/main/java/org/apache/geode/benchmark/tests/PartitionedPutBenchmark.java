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
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.Roles.SERVER;
import static org.apache.geode.benchmark.parameters.BenchmarkParameters.WARM_UP_TIME;
import static org.apache.geode.benchmark.parameters.JVMParameters.JVM_ARGS;

import org.junit.Test;

import org.apache.geode.benchmark.configurations.BenchmarkWorkload;
import org.apache.geode.benchmark.configurations.ClientRegion;
import org.apache.geode.benchmark.configurations.ClientServerTopology;
import org.apache.geode.benchmark.configurations.ConfigurationFactory;
import org.apache.geode.benchmark.configurations.PreBenchmarkWorload;
import org.apache.geode.benchmark.configurations.ServerRegion;
import org.apache.geode.benchmark.tasks.CreateClientProxyRegion;
import org.apache.geode.benchmark.tasks.CreatePartitionedRegion;
import org.apache.geode.benchmark.tasks.PrePopulateRegion;
import org.apache.geode.perftest.PerformanceTest;
import org.apache.geode.perftest.TestConfig;
import org.apache.geode.perftest.TestRunners;

public class PartitionedPutBenchmark implements PerformanceTest {

  @Test
  public void run() throws Exception {
    TestRunners.defaultRunner().runTest(this);
  }

  @Override
  public TestConfig configure() {
    ClientServerTopology clientServerTopology = new ClientServerTopology()
        .setNumberOfClient(1)
        .setNumberOfServers(2)
        .setNumberOfClient(1)
        .setKeyRange(KEY_RANGE)
        .setClientJVMArgs(JVM_ARGS)
        .setServerJVMArgs(JVM_ARGS)
        .setLocatorJVMArgs(JVM_ARGS)
        .setLocatorPort(LOCATOR_PORT);

    ServerRegion serverRegion = new ServerRegion()
        .setRegionCreationTask(new CreatePartitionedRegion("region"));

    ClientRegion clientRegion = new ClientRegion()
        .setClientRegionCreationTask(new CreateClientProxyRegion());

    PreBenchmarkWorload preBenchmarkWorload = new PreBenchmarkWorload()
        .setWorkLoad(new PrePopulateRegion(KEY_RANGE));

    BenchmarkWorkload benchmarkWorkload = new BenchmarkWorkload()
        .setBenchmarkDuration(BENCHMARK_DURATION)
        .setWarmupDuration(WARM_UP_TIME);

    return new ConfigurationFactory()
        .setClientServerTopology(clientServerTopology)
        .setServerRegion(serverRegion)
        .setClientRegion(clientRegion)
        .setPreBenchmarkWorload(preBenchmarkWorload)
        .setBenchmarkWorkload(benchmarkWorkload)
        .create();



  }
}

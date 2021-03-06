/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.geode.perftest.jvms;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.apache.geode.perftest.infrastructure.local.LocalInfrastructure;
import org.apache.geode.perftest.infrastructure.local.LocalInfrastructureFactory;

public class RemoteJVMFactoryIntegrationTest {
  @Rule
  public final TemporaryFolder temporaryFolder = new TemporaryFolder();

  @Test
  public void canExecuteCodeOnWorker() throws Exception {
    RemoteJVMFactory remoteJvmFactory = new RemoteJVMFactory(new LocalInfrastructureFactory());
    Map<String, Integer> roles = Collections.singletonMap("worker", 1);
    try (RemoteJVMs jvms = remoteJvmFactory.launch(roles)) {
      File tempFile = new File(temporaryFolder.newFolder(), "tmpfile").getAbsoluteFile();
      jvms.execute(context -> {
        tempFile.createNewFile();
      }, "worker");

      assertTrue(tempFile.exists());
    }
  }

}
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

package org.apache.geode.perftest.jvms.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.apache.commons.io.IOUtils;

/**
 * Utility for manipulating jar files
 */
public class JarUtil {

  /**
   * Jar up the contents of a directory.
   */
  static void jar(File file, File outputFile) throws IOException {
    Manifest manifest = new Manifest();
    try (JarOutputStream outputStream = new JarOutputStream(new FileOutputStream(outputFile), manifest)) {
      Path start = file.toPath();

      Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
          if(file.toFile().isDirectory()) {
            return FileVisitResult.CONTINUE;
          }

          JarEntry entry = new JarEntry(start.relativize(file).toString());
          outputStream.putNextEntry(entry);
          try (FileInputStream input = new FileInputStream(file.toFile())) {
            IOUtils.copy(input, outputStream);
          }
          return FileVisitResult.CONTINUE;
        }
      });
    }
  }

}

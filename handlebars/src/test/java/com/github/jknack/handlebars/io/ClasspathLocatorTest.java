/**
 * Copyright (c) 2012 Edgar Espina
 *
 * This file is part of Handlebars.java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.handlebars.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.junit.Test;



/**
 * Unit test for {@link ClassPathTemplateLoader}.
 *
 * @author edgar.espina
 * @since 0.1.0
 */
public class ClasspathLocatorTest {

  @Test
  public void source() throws IOException {
    TemplateLoader loader = new ClassPathTemplateLoader();
    TemplateSource source = loader.sourceAt(URI.create("template"));
    assertNotNull(source);
  }

  @Test
  public void subFolder() throws IOException {
    URLTemplateLoader loader = new ClassPathTemplateLoader();
    loader.setSuffix(".yml");
    TemplateSource source = loader.sourceAt(URI.create("mustache/specs/comments"));
    assertNotNull(source);
  }

  @Test
  public void subFolderwithDashAtBeginning() throws IOException {
    URLTemplateLoader loader = new ClassPathTemplateLoader();
    loader.setSuffix(".yml");
    TemplateSource source = loader.sourceAt(URI.create("/mustache/specs/comments"));
    assertNotNull(source);
  }

  @Test(expected = FileNotFoundException.class)
  public void failLocate() throws IOException {
    TemplateLoader loader = new ClassPathTemplateLoader();
    loader.sourceAt(URI.create("notExist"));
  }

  @Test
  public void setBasePath() throws IOException {
    TemplateLoader loader = new ClassPathTemplateLoader("/mustache/specs", ".yml");
    TemplateSource source = loader.sourceAt(URI.create("comments"));
    assertNotNull(source);
  }

  @Test
  public void setBasePathWithDashDash() throws IOException {
    TemplateLoader loader = new ClassPathTemplateLoader("/mustache/specs/", ".yml");
    TemplateSource source = loader.sourceAt(URI.create("comments"));
    assertNotNull(source);
  }

  @Test
  public void nullSuffix() throws IOException {
    assertEquals("suffix should be optional",
        new ClassPathTemplateLoader("/", null).sourceAt(URI.create("noextension")).content());
  }

  @Test
  public void emptySuffix() throws IOException {
    assertEquals("suffix should be optional",
        new ClassPathTemplateLoader("/", "").sourceAt(URI.create("noextension")).content());
  }
}

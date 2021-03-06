package com.github.jknack.handlebars;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class TagTypeTest extends AbstractTest {

  Hash helpers = $("tag", new Helper<Object>() {
    @Override
    public CharSequence apply(final Object context, final Options options) throws IOException {
      return options.tagType.name();
    }
  });

  @Test
  public void varTag() throws IOException {
    shouldCompileTo("{{tag}}", $, helpers, "VAR");
  }

  @Test
  public void unescapeVarTag() throws IOException {
    shouldCompileTo("{{&tag}}", $, helpers, "AMP_VAR");
  }

  @Test
  public void tripleVarTag() throws IOException {
    shouldCompileTo("{{{tag}}}", $, helpers, "TRIPLE_VAR");
  }

  @Test
  public void sectionTag() throws IOException {
    shouldCompileTo("{{#tag}}{{/tag}}", $, helpers, "SECTION");
  }

  @Test
  public void inline() {
    assertTrue(TagType.VAR.inline());

    assertTrue(TagType.AMP_VAR.inline());

    assertTrue(TagType.TRIPLE_VAR.inline());
  }

  @Test
  public void block() {
    assertTrue(!TagType.SECTION.inline());
  }
}

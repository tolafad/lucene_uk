/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lucene.analysis.uk;


import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.junit.Test;

/**
 * Test case for UkrainianAnalyzer.
 */

public class TestUkrainianAnalyzer extends BaseTokenStreamTestCase {

	/** Check that UkrainianAnalyzer doesnt discard any numbers */
	@Test
	public void testDigitsInUkrainianCharset() throws IOException {
		UkrainianMorfologikAnalyzer ra = new UkrainianMorfologikAnalyzer();
		assertAnalyzesTo(ra, "text 1000", new String[] { "text", "1000" });
		ra.close();
	}

	public void testReusableTokenStream() throws Exception {
		Analyzer a = new UkrainianMorfologikAnalyzer();
		assertAnalyzesTo(a, "Ця крапка у свою чергу рухається по колу.",
				new String[] { "цей", "крапка", "свій", "черга", "рухатися", "кола", "коло", "коло", "кіл", "кіл" });
		a.close();
	}

	/** blast some random strings through the analyzer */
	public void testRandomStrings() throws Exception {
		Analyzer analyzer = new UkrainianMorfologikAnalyzer();
		checkRandomData(random(), analyzer, 1000*RANDOM_MULTIPLIER);
		analyzer.close();
	}
}
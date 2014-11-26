/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.api.java.aggregation;

import static org.apache.flink.util.TestHelper.uniqueInt;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MaxAggregationFunctionTest {

	private MaxAggregationFunction<Integer> function = new MaxAggregationFunction<Integer>(-1);

	@Test
	public void shouldReturnMaximum() {
		// given
		int value1 = uniqueInt();
		int value2 = uniqueInt();
		int expected = Math.max(value1, value2);

		// when
		Integer actual = function.reduce(value1, value2); 
		
		// then
		assertThat(actual, is(expected));
	}

}

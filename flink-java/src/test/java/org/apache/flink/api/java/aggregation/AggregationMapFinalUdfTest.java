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

import static org.apache.flink.api.java.aggregation.AggregationMapIntermediateUdfTest.setupDummyFunctions;
import static org.apache.flink.api.java.aggregation.AggregationMapIntermediateUdfTest.setupOutputArity;
import static org.apache.flink.api.java.aggregation.AggregationMapIntermediateUdfTest.setupOutputPosition;
import static org.apache.flink.util.TestHelper.uniqueInt;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.apache.flink.api.java.tuple.Tuple;
import org.junit.Test;

public class AggregationMapFinalUdfTest {

	private AggregationMapFinalUdf<Tuple, Tuple> udf;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void shouldCopyValuesForSimpleAggregationFunctions() throws Exception {
		// given
		// setup an intermediate tuple with a fixed value at a random field position
		Object intermediateValue = mock(Object.class);
		Tuple intermediateTuple = mock(Tuple.class);
		int intermediatePos = uniqueInt(0, Tuple.MAX_ARITY - 1);
		given(intermediateTuple.getField(intermediatePos)).willReturn(intermediateValue);

		
		// setup an non-composite aggregation function with an random output field position
		int arity = setupOutputArity();
		int outputPos = setupOutputPosition(arity, intermediatePos);
		AggregationFunction function = mock(AggregationFunction.class);
		given(function.getIntermediatePosition()).willReturn(intermediatePos);
		given(function.getOutputPosition()).willReturn(outputPos);
		AggregationFunction[] functions = setupDummyFunctions(arity);
		functions[outputPos] = function;
		
		// setup creation of output tuple
		udf = new AggregationMapFinalUdf(functions);

		// when
		Tuple actual = udf.map(intermediateTuple);

		// then
		assertThat(actual.getField(outputPos), is(intermediateValue));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void shouldComputeCompositeValuesForCompositeAggregationFunctions() throws Exception {
		// given
		// setup an intermediate tuple
		Tuple intermediateTuple = mock(Tuple.class);
		
		// setup an composite aggregation function with an random output field position
		int arity = setupOutputArity();
		int outputPos = setupOutputPosition(arity);
		Object outputValue = mock(Object.class);
		CompositeAggregationFunction function = mock(CompositeAggregationFunction.class);
		given(function.getOutputPosition()).willReturn(outputPos);
		given(function.computeComposite(intermediateTuple)).willReturn(outputValue);
		AggregationFunction[] functions = setupDummyFunctions(arity);
		functions[outputPos] = function;
		
		// setup creation of output tuple
		udf = new AggregationMapFinalUdf(functions);

		// when
		Tuple actual = udf.map(intermediateTuple);

		// then
		assertThat(actual.getField(outputPos), is(outputValue));
	}

}
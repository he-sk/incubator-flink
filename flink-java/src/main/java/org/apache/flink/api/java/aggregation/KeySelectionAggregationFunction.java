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

/**
 * Pseudo-aggregation function that includes a field on which the input
 * tuples are grouped in the aggregation result.
 * 
 * TODO <p>Note: This function should only be called once for the aggregation
 * (this is currently not implemented). 
 *
 * @author Viktor Rosenfeld <viktor.rosenfeld@tu-berlin.de>
 */
public class KeySelectionAggregationFunction<T> extends InputTypeAggregationFunction<T> {
	private static final long serialVersionUID = -8410506438464358497L;
	
	T key;
	
	public KeySelectionAggregationFunction(int field) {
		super("key", field);
	}

	@Override
	public void initialize() {
		key = null;
	}

	@Override
	public void aggregate(T value) {
		if (key == null) {
			key = value;
		}
	}

	@Override
	public T getAggregate() {
		return key;
	}

}
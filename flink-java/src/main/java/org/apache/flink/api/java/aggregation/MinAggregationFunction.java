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
 * Min aggregation function.
 * 
 * @param <T> The input and output type. Must extend {@link Number}.
 */
public class MinAggregationFunction<T extends Comparable<T>> extends InputTypeAggregationFunction<T> {
	private static final long serialVersionUID = -1587835317837938137L;

	private T min;
	
	public MinAggregationFunction(int field) {
		super("min", field);
	}
	
	@Override
	public void initialize() {
		min = null;
	}

	@Override
	public void aggregate(T value) {
		if (min == null || min.compareTo(value) > 0) {
			min = value;
		}
	}

	@Override
	public T getAggregate() {
		return min;
	}

}

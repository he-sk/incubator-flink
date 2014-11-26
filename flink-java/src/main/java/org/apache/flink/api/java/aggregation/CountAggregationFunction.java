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

import org.apache.flink.api.common.typeinfo.BasicTypeInfo;

/**
 * Count aggregation function.
 * 
 * <p>Note: Count operates on the entire tuple. 
 * The aggregated field position is always 0. 
 */
public class CountAggregationFunction extends AggregationFunction<Object, Long> {
	private static final long serialVersionUID = 514373288927267811L;

	long count;
	
	public CountAggregationFunction() {
		super("count");
	}

	@Override
	public ResultTypeBehavior getResultTypeBehavior() {
		return ResultTypeBehavior.FIXED;
	}

	@Override
	public BasicTypeInfo<Long> getResultType() {
		return BasicTypeInfo.LONG_TYPE_INFO;
	}

	@Override
	public void setInputType(BasicTypeInfo<Object> inputType) { }

	@Override
	public int getInputPosition() { return 0; }

	@Override
	public Long initialize(Object value) {
		return 1L;
	}

	@Override
	public Long reduce(Long value1, Long value2) {
		return value1 + value2;
	}

}

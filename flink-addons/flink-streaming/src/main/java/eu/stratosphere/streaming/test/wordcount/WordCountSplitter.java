/***********************************************************************************************************************
 *
 * Copyright (C) 2010-2014 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/

package eu.stratosphere.streaming.test.wordcount;

import eu.stratosphere.streaming.api.StreamRecord;
import eu.stratosphere.streaming.api.invokable.UserTaskInvokable;
import eu.stratosphere.types.StringValue;

public class WordCountSplitter extends UserTaskInvokable {

	private StringValue sentence = new StringValue();
	private String[] words = new String[] {};
	private StringValue wordValue = new StringValue("");

 private StreamRecord outputRecord = new StreamRecord(wordValue);

	@Override
	public void invoke(StreamRecord record) throws Exception {
		sentence = (StringValue) record.getRecord(0)[0];
		System.out.println("to split: " + sentence.getValue());
		words = sentence.getValue().split(" ");
		for (CharSequence word : words) {
			wordValue.setValue(word);
			outputRecord.setRecord(wordValue);
			emit(outputRecord);
			//emit(new StreamRecord(wordValue));
		}
	}
}
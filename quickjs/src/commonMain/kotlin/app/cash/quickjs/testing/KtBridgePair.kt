/*
 * Copyright (C) 2021 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.cash.quickjs.testing

import app.cash.quickjs.InternalBridge
import app.cash.quickjs.KtBridge

/** A pair of bridges connected to each other for testing. */
class KtBridgePair {
  val a: KtBridge = KtBridge(object : InternalBridge {
    override fun invoke(
      instanceName: String,
      funName: String,
      encodedArguments: ByteArray
    ): ByteArray {
      return b.inboundBridge.invoke(instanceName, funName, encodedArguments)
    }

    override fun invokeSuspending(
      instanceName: String,
      funName: String,
      encodedArguments: ByteArray,
      callbackName: String
    ) {
      return b.inboundBridge.invokeSuspending(instanceName, funName, encodedArguments, callbackName)
    }
  })

  val b: KtBridge = KtBridge(a.inboundBridge)
}
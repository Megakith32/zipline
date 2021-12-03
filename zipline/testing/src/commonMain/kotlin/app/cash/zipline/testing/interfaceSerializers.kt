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
package app.cash.zipline.testing

import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule

interface RequestInterfaceService {
  fun echo(request: MessageInterface): String
}

interface ResponseInterfaceService {
  fun echo(request: String): MessageInterface
}

interface MessageInterface {
  val message: String
}

@Serializable
data class RealMessageInterface(
  override val message: String
): MessageInterface

val MessageInterfaceSerializersModule: SerializersModule = SerializersModule {
  polymorphic(
    MessageInterface::class,
    RealMessageInterface::class,
    RealMessageInterface.serializer()
  )
}
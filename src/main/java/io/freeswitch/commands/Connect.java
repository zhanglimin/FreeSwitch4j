/*
 * Copyright 2015.
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
package io.freeswitch.commands;

/**
 * ConnectCommand. 
 * This command is used to connect to FreeSwitch to retrieve the
 * details of an inbound call. This function will be used in an application
 * server to which FreeSwitch will connect to via its mode outbound event
 * socket.
 * 
 * @author Arsene Tochemey GANDOTE
 *
 */
public class Connect extends BaseCommand {

	@Override
	public String argument() {
		return "";
	}

	@Override
	public String command() {
		return "connect";
	}

}
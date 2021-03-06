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
package io.freeswitch.command;

/**
 * EventsCommand. Enable or disable event by class or all (plain or xml or json
 * output format)
 *
 * @author Arsene Tochemey GANDOTE
 */
public class EventsCommand extends BaseCommand {

    public EventsCommand(String eventlist) {
        this._command = eventlist;
    }

    @Override
    public String argument() {
        return this._command;
    }

    @Override
    public String command() {
        return "event";
    }

}

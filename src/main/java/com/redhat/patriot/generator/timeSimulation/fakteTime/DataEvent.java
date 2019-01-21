/*
 * Copyright 2018 Patriot project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.redhat.patriot.generator.timeSimulation.fakteTime;

import com.redhat.patriot.generator.device.AbstractSensor;
import umontreal.ssj.randvar.BinomialConvolutionGen;
import umontreal.ssj.randvar.ErlangConvolutionGen;
import umontreal.ssj.randvar.RandomVariateGen;
import umontreal.ssj.rng.MRG32k3a;
import umontreal.ssj.simevents.Event;

/**
 * Created by jsmolar on 7/5/18.
 */
public class DataEvent extends Event {

    private AbstractSensor device;

    private Boolean repeat = false;


    private RandomVariateGen timeGeneration = new ErlangConvolutionGen(new MRG32k3a(), 2);

    public DataEvent(AbstractSensor device, Boolean repeat) {
        this.device = device;
        this.repeat = repeat;
    }

    public void actions() {
        if(repeat) {
            this.schedule(timeGeneration.nextDouble());
        }

//        simulatedData.add(sample.getSingleRandomValue());
    }

    public void binomialConvolution() {
        timeGeneration = new BinomialConvolutionGen(new MRG32k3a(), 2, 60.0);
    }

}

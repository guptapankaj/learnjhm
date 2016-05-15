/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * MyBenchmark.testMethod  thrpt   20  10565734.727 ± 281054.846  ops/s - obfuscateTicket1
 * MyBenchmark.testMethod  thrpt   20   2162855.703 ±  35550.870  ops/s - obfuscateTicket2
 * MyBenchmark.testMethod  thrpt   20  18105341.613 ± 273598.727  ops/s - obfuscateTicket3
 */

public class MyBenchmark {

    private static final String OBSCURE_TKT = "******";
    private static final String ticket = "AB-999-QWiiiilt87lz76pou7tqqp";

    @Benchmark
    public void testMethod() {
        MyBenchmark.obfuscateTicket1(ticket);
//        MyBenchmark.obfuscateTicket2(ticket);
//        MyBenchmark.obfuscateTicket3(ticket);
    }

    public static String obfuscateTicket1(String ticket) {
        if (null == ticket) {
            return ticket;
        }

        if (ticket.length() > 20) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(ticket.substring(0, 10)).append(OBSCURE_TKT).append(ticket.substring(16));
            return buffer.toString();
        }
        return ticket;
    }


    public static String obfuscateTicket2(String ticket) {
        if (null == ticket) {
            return ticket;
        }
        if (ticket.length() > 20) {
            return ticket.replace(ticket.substring(10, 16), OBSCURE_TKT);
        }
        return ticket;
    }

    public static String obfuscateTicket3(String ticket) {
        if (null == ticket) {
            return ticket;
        }

        if (ticket.length() > 20) {
            StringBuffer buffer = new StringBuffer(ticket);
            return buffer.replace(10, 16, OBSCURE_TKT).toString();
        }
        return ticket;
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}

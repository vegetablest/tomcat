/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* Generated By:JJTree: Do not edit this line. AstPlus.java */
package org.apache.el.parser;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.el.ELException;

import org.apache.el.lang.ELArithmetic;
import org.apache.el.lang.EvaluationContext;


/**
 * @author Jacob Hookom [jacob@hookom.net]
 */
public final class AstPlus extends ArithmeticNode {

    public AstPlus(int id) {
        super(id);
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object getValue(EvaluationContext ctx) throws ELException {
        Object obj0 = this.children[0].getValue(ctx);
        Object obj1 = this.children[1].getValue(ctx);

        if (obj0 != null) {
            if (obj0 instanceof Map && obj1 instanceof Map) {
                // Maps are merged
                ((Map) obj0).putAll((Map) obj1);
                return obj0;
            } else if (obj0 instanceof Set && obj1 instanceof Collection) {
                // If obj0 is a Set then merge
                ((Set<?>) obj0).addAll((Collection) obj1);
                return obj0;
            } else if (obj0 instanceof List && obj1 instanceof Collection) {
                // If obj0 is a List then concatenate
                ((List) obj0).addAll((Collection) obj1);
                return obj0;
            }
        }

        return ELArithmetic.add(obj0, obj1);
    }
}
